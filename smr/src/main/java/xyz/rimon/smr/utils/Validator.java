package xyz.rimon.smr.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import xyz.rimon.ael.commons.Commons;
import xyz.rimon.smr.commons.Pref;
import xyz.rimon.smr.model.User;
import xyz.rimon.smr.model.UserRev;

/**
 * Created by SAyEM on 4/12/17.
 */

public class Validator {
    private Validator() {
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public static void validateUser(User user) throws IllegalArgumentException {
        if (user == null) throw new IllegalArgumentException("User can not be null!");
        if (user.getEmail() == null || !Validator.isEmailValid(user.getEmail()))
            throw new IllegalArgumentException("Email invalid!");
        if (user.getUsername() == null)
            throw new IllegalArgumentException("Username can not be null!");
//        if (user.getPassword() == null || user.getPassword().length() < 6)
//            throw new IllegalArgumentException("Password length must be at least 6 characters");
    }

    public static boolean isEmailValid(String emailStr) {
        if (emailStr == null) return false;
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean isValidRequestInformations(Context context, TextView etPaymentMethod, EditText etAccountNumber, EditText etAmount, EditText etNote) {
        if (etPaymentMethod == null || etAccountNumber == null || etAmount == null || etNote == null)
            return false;

        if (TextUtils.isEmpty(etNote.getText())) {
            etNote.setError("Please write some note to the payers so that they can use that information when transferring your earnings!");
            return false;
        }
        if (TextUtils.isEmpty(etPaymentMethod.getText()) || etPaymentMethod.getText().toString().length() < 3) {
            etPaymentMethod.setError("Length must be greater than 3");
            return false;
        }
        if (etAccountNumber.getText().toString().isEmpty() || etAccountNumber.getText().toString().length() < 11) {
            etAccountNumber.setError("Invalid account number!");
            return false;
        }
        UserRev userRev = Commons.buildGson().fromJson(Pref.getPreferenceString(context, Pref.KEY_USER_REV_JSON), UserRev.class);
        if (etAmount.getText().toString().isEmpty() || Integer.parseInt(etAmount.getText().toString()) < userRev.getThresholdLimit()) {
            etAmount.setError("Request amount should be greater than " + userRev.getThresholdLimit());
            return false;
        }
        return true;
    }
}
