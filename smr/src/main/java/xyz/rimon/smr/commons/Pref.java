package xyz.rimon.smr.commons;

import android.content.Context;
import android.content.SharedPreferences;

import xyz.rimon.smr.model.User;

/**
 * Created by SAyEM on 4/12/17.
 */

public class Pref {
    public static final String PREF_NAME = "smr_api_pref";
    public static final String PREF_SIZE = "prefSize";
    public static final String KEY_INITIALIZED = "initialized";
    public static final String KEY_LOGGED_IN = "loggedIn";
    public static final String KEY_USER_REV_JSON = "userRevJson";
    public static final String KEY_CLIENT_ID = "nkjajsydc785345nr";
    public static final String KEY_CLIENT_SECRET = "jcfn4758rnjhdncrweiu";
    public static final String KEY_ACCESS_TOKEN = "jd8ehrukd746e84rn";
    public static final String KEY_REFRESH_TOKEN = "bfic8r7wcyrheudr874";
    public static final String KEY_NAME = "jfiuekwhld8487h";
    public static final String KEY_PASSWORD = "fjkhui3y4dqjk32io";
    public static final String KEY_USERNAME = "jr6843ge38fwyhjwu";
    public static final String KEY_EMAIL = "kfjwduyri2uew897435";
//    public static final String PREF_ACCOUNT_ID = "fb_account_kit_id";

    public static void savePreference(Context context, String key, boolean value) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPref.edit();

        prefEditor.putBoolean(key, value);
        prefEditor.apply();
    }

    public static boolean getPreference(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key, false);
    }

    public static void savePreference(Context context, String key, int value) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPref.edit();

        prefEditor.putInt(key, value);
        prefEditor.apply();

    }

    public static int getPreferenceInt(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPref.getInt(key, 0);
    }

    public static void savePreference(Context context, String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPref.edit();

        prefEditor.putString(key, value);
        prefEditor.apply();
    }

    public static String getPreferenceString(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPref.getString(key, "");
    }


    public static boolean isNull(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return !sharedPref.contains(key);
    }

    public static void saveCredentials(Context context, String clientId, String clientSecret, User user) {
        Pref.savePreference(context, Pref.KEY_CLIENT_ID, clientId);
        Pref.savePreference(context, Pref.KEY_CLIENT_SECRET, clientSecret);
        Pref.savePreference(context, Pref.KEY_NAME, user.getName());
        Pref.savePreference(context, Pref.KEY_PASSWORD, user.getPassword());
        Pref.savePreference(context, Pref.KEY_USERNAME, user.getUsername());
        Pref.savePreference(context, Pref.KEY_EMAIL, user.getEmail());

    }
}