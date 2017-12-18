package xyz.rimon.smr;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.androidnetworking.AndroidNetworking;

import java.util.List;

import xyz.rimon.ael.commons.utils.StorageUtil;
import xyz.rimon.ael.domains.Event;
import xyz.rimon.ael.logger.Ael;
import xyz.rimon.smr.commons.Pref;
import xyz.rimon.smr.model.User;
import xyz.rimon.smr.service.ApiClient;

/**
 * Created by SAyEM on 4/12/17.
 */

public class SMR {
    private SMR() {
    }

    private static boolean LOCKED = false;

    public static void initialize(Context context, String clientId, String clientSecret) {
        if (clientId == null || clientSecret == null)
            throw new IllegalArgumentException("Client id or secret can not be null");
        Pref.saveCredentials(context, clientId, clientSecret, null);
        AndroidNetworking.initialize(context);
    }

    public static void setUser(Context context, String name, String email) {
        if (Pref.isNull(context, Pref.KEY_CLIENT_ID) || Pref.isNull(context, Pref.KEY_CLIENT_ID))
            throw new RuntimeException("Have you initialized by calling SMR.initialize(Context context, String clientId, String clientSecret) method?");
        User user = new User(name, email);
        Pref.saveUser(context, user);
        ApiClient.registerUser(context, user);
    }

    public static void logOffline(Activity context, Event event) {
        Ael.logEvent(context, event);
    }

    public static void logOnline(final Activity context, Event event) {
        if (Pref.isNull(context, Pref.KEY_NAME) || Pref.isNull(context, Pref.KEY_EMAIL))
            throw new RuntimeException("Have you set user by calling SMR.setUser(Context context, String name, String email) method?");

        if (LOCKED || isOptedOut(context)) return;
        LOCKED = true;
        StorageUtil.writeObject(context, StorageUtil.TEMP_FILE_NAME, event);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Event> eventList = StorageUtil.readObjects(context, StorageUtil.TEMP_FILE_NAME);
                ApiClient.postEvent(context, eventList);
                LOCKED = false;
            }
        }, 10000);
    }

    private static boolean isOptedOut(Context context) {
        return !Pref.getPreference(context, Pref.USER_OPT_IN);

    }

}
