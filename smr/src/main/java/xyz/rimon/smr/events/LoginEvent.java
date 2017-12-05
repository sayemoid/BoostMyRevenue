package xyz.rimon.smr.events;


import xyz.rimon.smr.model.UserAuth;

/**
 * Created by SAyEM on 5/12/17.
 */

public class LoginEvent {
    private UserAuth auth;

    public LoginEvent(UserAuth auth) {
        this.auth = auth;
    }

    public boolean isSuccess() {
        return this.auth != null;
    }

    public UserAuth getAuth() {
        return auth;
    }

    public void setAuth(UserAuth auth) {
        this.auth = auth;
    }
}
