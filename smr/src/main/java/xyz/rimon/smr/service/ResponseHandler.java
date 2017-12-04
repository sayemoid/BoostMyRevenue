package xyz.rimon.smr.service;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.error.ANError;

import okhttp3.Response;
import xyz.rimon.smr.commons.Parser;
import xyz.rimon.smr.commons.Pref;
import xyz.rimon.smr.model.User;
import xyz.rimon.smr.model.UserAuth;

/**
 * Created by SAyEM on 4/12/17.
 */

public class ResponseHandler {

    public static void onUserRegistration(Context context, User user, Response response) {
        if (response.code() == 200 || response.code() == 226)
            ApiClient.login(context, user.getUsername(), user.getPassword());
        else {
            Pref.savePreference(context, Pref.KEY_INITIALIZED, false);
            onError(response);
        }
    }

    public static void onUserLogin(Context context, String response) {
        UserAuth auth = Parser.parseUserAuth(response);
        if (auth != null) {
            Pref.savePreference(context, Pref.KEY_INITIALIZED, true);
            Pref.savePreference(context, Pref.KEY_ACCESS_TOKEN, auth.getAccessToken());
            Pref.savePreference(context, Pref.KEY_REFRESH_TOKEN, auth.getRefreshToken());
        }
        Log.i("AUTH", auth.toString());
    }

    private static void onError(Response response) {
        Log.e("ERROR_MSG", "RESPONSE_CODE:" + response.code() + "\nMESSAGE:" + response.message());
    }

    public static void onError(ANError anError) {
        Log.e("LOGIN_ERROR", anError.getErrorCode() + ":" + anError.getMessage());
    }

}
