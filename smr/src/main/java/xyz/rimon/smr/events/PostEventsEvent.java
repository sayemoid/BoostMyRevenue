package xyz.rimon.smr.events;

/**
 * Created by SAyEM on 5/12/17.
 */

public class PostEventsEvent {
    private boolean success;

    public PostEventsEvent() {
        this.success = true;
    }

    public PostEventsEvent(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
