package xyz.rimon.smr.commons;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SAyEM on 4/12/17.
 */

public class Pref {
    public static final String PREF_NAME = "smr_api_pref";
    public static final String PREF_SIZE = "prefSize";
    public static final String KEY_INITIALIZED = "initialized";
    public static final String KEY_CLIENT_ID = "nkjajsydc785345nr";
    public static final String KEY_CLIENT_SECRET = "jcfn4758rnjhdncrweiu";
    public static final String KEY_ACCESS_TOKEN = "jd8ehrukd746e84rn";
    public static final String KEY_REFRESH_TOKEN = "bfic8r7wcyrheudr874";
//    public static final String PREF_ACCOUNT_ID = "fb_account_kit_id";

    public static void savePreference(Context context, String key, boolean value) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPref.edit();

        prefEditor.putBoolean(key, value);
        prefEditor.apply();
    }

    public static boolean getPreference(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key, true);
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
        if (sharedPref.contains(key))
            return false;
        return true;
    }
}