package xyz.rimon.smr.events;

import xyz.rimon.smr.model.UserRev;

/**
 * Created by SAyEM on 5/12/17.
 */

public class RevenueLoadEvent {
    private UserRev userRev;

    public RevenueLoadEvent(UserRev userRev) {
        this.userRev = userRev;
    }

    public boolean isSuccess() {
        return this.userRev != null;
    }

    public UserRev getUserRev() {
        return userRev;
    }

    public void setUserRev(UserRev userRev) {
        this.userRev = userRev;
    }
}
