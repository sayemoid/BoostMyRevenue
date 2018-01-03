package xyz.rimon.smr.commons;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
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
        return email;
    }
}
