package xyz.rimon.smr;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.androidnetworking.AndroidNetworking;

import java.util.List;

import xyz.rimon.ael.commons.utils.PermissionUtil;
import xyz.rimon.ael.domains.Event;
import xyz.rimon.ael.logger.Ael;
import xyz.rimon.smr.commons.Commons;
import xyz.rimon.smr.commons.Pref;
import xyz.rimon.smr.commons.SystemAlarm;
import xyz.rimon.smr.model.User;
import xyz.rimon.smr.service.ApiClient;

/**
 * Created by SAyEM on 4/12/17.
 */

public class SMR {
    private static String CLIENT_ID;
    private static String CLIENT_SECRET;

    private SMR() {
    }

    private static boolean LOCKED = false;

    public static void initialize(Context context, String clientId, String clientSecret) {
        if (clientId == null || clientSecret == null)
            throw new IllegalArgumentException("Client id or secret can not be null");
        Pref.saveCredentials(context, clientId, clientSecret, null);
        CLIENT_ID = clientId;
        CLIENT_SECRET = clientSecret;
        AndroidNetworking.initialize(context);

        SystemAlarm.scheduleAlarm(context);
    }

    public static void setUser(Activity context, String name) {
        Pref.savePreference(context, Pref.KEY_NAME, name);
        String email = Commons.getPrimaryEmailAddress(context);
        if (email == null) return;
        String username = Pref.getPreferenceString(context,Pref.KEY_USERNAME);
        setUser(context, name, username,email);
    }


    public static void setUser(Activity context, String name, String username) {
        Pref.savePreference(context, Pref.KEY_NAME, name);
        String email = Commons.getPrimaryEmailAddress(context);
        if (email == null) return;
        setUser(context, name, username, email);
    }

    public static void setUser(Activity context, String name, String username, String email) {
        if (Pref.isNull(context, Pref.KEY_CLIENT_ID) || Pref.isNull(context, Pref.KEY_CLIENT_ID)) {
            if (CLIENT_ID == null || CLIENT_SECRET == null)
                throw new RuntimeException("Have you initialized by calling SMR.initialize(Context context, String clientId, String clientSecret) method?");
            else {
                SMR.initialize(context, CLIENT_ID, CLIENT_SECRET);
                return;
            }
        }
        User user = new User(name, username, email);
        Pref.saveUser(context, user);
        ApiClient.registerUser(context, user);
    }

    public static void logOffline(Activity context, Event event) {
        Ael.logEvent(context, event);
    }

    public static void logOnline(final Activity context, final Event event) {
        if (isUserSetButNotEmail(context)) {
            setUser(context, Pref.getPreferenceString(context, Pref.KEY_NAME));
            return;
        }

        if (Pref.isNull(context, Pref.KEY_NAME) || Pref.isNull(context, Pref.KEY_EMAIL)) {
            setUser(context, Commons.getApplicationName(context) + " User");
            return;
            //throw new RuntimeException("Have you set user by calling SMR.setUser(Context context, String name, String email) method?");
        }

        if (LOCKED || isOptedOut(context)) return;
        LOCKED = true;
        Ael.logEvent(context,event);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Event> eventList=Ael.getEvents(context);
                ApiClient.postEvent(context, eventList);
                LOCKED = false;
            }
        }, 5000);
    }

    private static boolean isOptedOut(Context context) {
        return !Pref.isNull(context, Pref.USER_OPT_IN) && !Pref.getPreference(context, Pref.USER_OPT_IN);
    }

    private static boolean isUserSetButNotEmail(Context context) {
        return Pref.isNull(context, Pref.KEY_EMAIL) && !Pref.isNull(context, Pref.KEY_NAME);
    }

    private static boolean hasStoragePermission(Context context) {
        return PermissionUtil.hasPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    public static void dispatchFirebaseUserToken(Context context, String token) {
        Pref.savePreference(context, Pref.KEY_FIREBASE_TOKEN, token);
    }
}
