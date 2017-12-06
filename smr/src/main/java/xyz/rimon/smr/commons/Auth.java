package xyz.rimon.smr.commons;

import android.content.Context;

import xyz.rimon.smr.model.UserAuth;

/**
 * Created by SAyEM on 5/12/17.
 */

public class Auth {

    public static void setLoggedIn(Context context, UserAuth auth) {
        Pref.savePreference(context, Pref.KEY_LOGGED_IN, true);
        Pref.savePreference(context, Pref.KEY_ACCESS_TOKEN, auth.getAccessToken());
        Pref.savePreference(context, Pref.KEY_REFRESH_TOKEN, auth.getRefreshToken());
    }

    public static boolean isLoggedIn(Context context){
        return Pref.getPreference(context,Pref.KEY_LOGGED_IN);
    }
}
