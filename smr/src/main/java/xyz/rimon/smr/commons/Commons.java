package xyz.rimon.smr.commons;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.provider.Settings;
import android.util.Patterns;

import java.util.regex.Pattern;

import xyz.rimon.ael.commons.utils.PermissionUtil;
import xyz.rimon.smr.utils.Validator;

/**
 * Created by SAyEM on 16/12/17.
 */

public class Commons {
    private Commons() {
    }

    public static String getClientIdSnapshot(String clientId) {
        if (clientId != null && clientId.length() >= 6)
            return clientId.substring(0, 5) + "__";
        return "";
    }

    public static String sliceUsername(String email) {
        if (!Validator.isEmailValid(email)) throw new IllegalArgumentException("Email invalid!");
        return email.split("@")[0];
    }

    public static String getPrimaryEmailAddress(Activity context) {
        if (!PermissionUtil.hasPermission(context, Manifest.permission.GET_ACCOUNTS))
            PermissionUtil.askForPermission(context, Manifest.permission.GET_ACCOUNTS);

        String email = null;
        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(context).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                email = account.name;
            }
        }

        // if permission is granted but still couldn't find email then get device id and build email address
        if (PermissionUtil.hasPermission(context, Manifest.permission.GET_ACCOUNTS)) {
            if (email == null || email.isEmpty()) {
                String androidId = Settings.Secure.getString(context.getContentResolver(),
                        Settings.Secure.ANDROID_ID);
                email = androidId + "@users.sharemyrevenue.net";
            }
        }
        return email;
    }

    public static String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }

    public static void openUrl(Context context, String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        context.startActivity(i);
    }
}
