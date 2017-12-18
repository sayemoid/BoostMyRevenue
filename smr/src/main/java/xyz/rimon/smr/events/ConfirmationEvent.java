package xyz.rimon.smr.events;

import android.content.Context;

/**
 * Created by SAyEM on 17/12/17.
 */

public class ConfirmationEvent {
    private boolean status;
    private Context context;

    public ConfirmationEvent(boolean status) {
        this.status = status;
    }

    public ConfirmationEvent(Context context, boolean status) {
        this.status = status;
        this.context = context;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
