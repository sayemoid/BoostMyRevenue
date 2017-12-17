package xyz.rimon.smr.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import xyz.rimon.smr.events.ConfirmationEvent;

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

    public static void showConfimationDialog(final Context context, String title, String message) {
        final TextView txtMessgae = new TextView(context);
        txtMessgae.setPadding(45,45,45,45);
        // i.e.: R.string.dialog_message =>
        // "Test this dialog following the link to dtmilano.blogspot.com"
        final SpannableString s =
                new SpannableString(message);
        Linkify.addLinks(s, Linkify.WEB_URLS);
        txtMessgae.setText(s);
        txtMessgae.setMovementMethod(LinkMovementMethod.getInstance());
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setView(txtMessgae)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        EventBus.getDefault().post(new ConfirmationEvent(context, true));
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EventBus.getDefault().post(new ConfirmationEvent(context, false));
                    }
                }).show();
    }
}
