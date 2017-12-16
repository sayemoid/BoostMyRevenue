package xyz.rimon.smr.commons;

import xyz.rimon.smr.utils.Validator;

/**
 * Created by SAyEM on 16/12/17.
 */

public class Commons {
    private Commons() {
    }

    public static String getClientIdSnapshot(String clientId) {
        if (clientId != null && clientId.length() >= 6)
            return clientId.substring(0, 5)+"__";
        return "";
    }

    public static String sliceUsername(String email) {
        if (!Validator.isEmailValid(email)) throw new IllegalArgumentException("Email invalid!");
        return email.split("@")[0];
    }
}
