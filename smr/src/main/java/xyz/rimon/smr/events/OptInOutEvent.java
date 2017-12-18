package xyz.rimon.smr.events;

/**
 * Created by SAyEM on 17/12/17.
 */

public class OptInOutEvent {
    private boolean status;

    public OptInOutEvent(boolean status) {
        this.status = status;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
