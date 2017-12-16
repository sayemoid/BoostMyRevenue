package xyz.rimon.smr.commons;

/**
 * Created by SAyEM on 16/12/17.
 */

public class Commons {
    private Commons() {
    }

    public static String getClientIdSnapshot(String clientId) {
        if (clientId != null && clientId.length() >= 6)
            return clientId.substring(0, 5);
        return "";
    }
}
