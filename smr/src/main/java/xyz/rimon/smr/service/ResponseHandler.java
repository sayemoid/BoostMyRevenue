package xyz.rimon.smr.service;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.error.ANError;

import okhttp3.Response;
import xyz.rimon.ael.commons.utils.StorageUtil;
import xyz.rimon.smr.commons.Auth;
import xyz.rimon.smr.commons.Pref;
import xyz.rimon.smr.model.User;
import xyz.rimon.smr.model.UserAuth;

/**
 * Created by SAyEM on 4/12/17.
 */

public class ResponseHandler {

    public static void onUserRegistration(Context context, User user, Response response) {
        if (response.code() == 200 || response.code() == 226) { // if user created of already exists
            Pref.savePreference(context, Pref.KEY_INITIALIZED, true);
            ApiClient.login(context);
        } else {
            Pref.savePreference(context, Pref.KEY_INITIALIZED, false);
            onError(response);
        }
    }

    public static void onUserLogin(Context context, Response response, UserAuth auth) {
//        UserAuth auth = Parser.parseUserAuth(response);
        if (auth != null)
            Auth.setLoggedIn(context, auth);
        else {
            // check if initialised, if not then initialized
//            if (!Pref.getPreference(context,Pref.KEY_INITIALIZED))
//                Auth.sse
            Pref.savePreference(context, Pref.KEY_LOGGED_IN, false);
            ApiClient.refreshToken(context);
        }
        Log.i("AUTH", auth.toString());
    }

    private static void onError(Response response) {
        Log.e("ERROR_MSG", "RESPONSE_CODE:" + response.code() + "\nMESSAGE:" + response.message());
    }

    public static void onError(ANError anError) {
        Log.e("LOGIN_ERROR", anError.getErrorCode() + ":" + anError.getMessage());
    }

    public static void onPostEvent(Context context, Response response) {
        if (response.code() == 200) {
            Log.i("POST_EVENT_CODE", String.valueOf(response.code()));
            StorageUtil.clearObjects(context, StorageUtil.TEMP_FILE_NAME);
        } else if (response.code() == 401) {
            ApiClient.refreshToken(context);
            Log.e("POST_EVENT_ERROR", response.code() + ": Access_token:" + Pref.getPreferenceString(context, Pref.KEY_ACCESS_TOKEN));
        }
    }


}
