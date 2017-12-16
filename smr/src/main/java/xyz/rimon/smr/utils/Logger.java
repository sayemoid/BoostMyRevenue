package xyz.rimon.smr.utils;

import android.util.Log;

import xyz.rimon.smr.config.ApiConfig;

/**
 * Created by SAyEM on 16/12/17.
 */

public class Logger {
    private Logger() {
    }

    private static String TAG = "SMR: ";

    public static void d(String message) {
        if (ApiConfig.DEBUG)
            Log.d(TAG, "d: " + message);
    }

    public static void e(String error) {
        if (ApiConfig.DEBUG)
            Log.d(TAG, "e: " + error);
    }

    public static void i(String info) {
        if (ApiConfig.DEBUG)
            Log.d(TAG, "i: " + info);
    }
}
