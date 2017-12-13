package xyz.rimon.smr.commons;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by SAyEM on 11/12/17.
 */

public class UIUtils {
    public static void showDialog(Context context, String title, String message) {
        if (title == null) title = "";
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .show();
    }
}
