package xyz.rimon.smr.events;

/**
 * Created by SAyEM on 11/12/17.
 */

public class PaymentRequestEvent {
    private boolean success;
    private String errorMessage;

    public PaymentRequestEvent(boolean success) {
        this.success = success;
    }

    public PaymentRequestEvent(boolean success, String errorMessage) {
        this.success = false;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
