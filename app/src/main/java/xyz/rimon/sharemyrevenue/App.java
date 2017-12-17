package xyz.rimon.sharemyrevenue;

import android.app.Application;

import xyz.rimon.smr.SMR;
import xyz.rimon.smr.model.User;

/**
 * Created by SAyEM on 5/12/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        User user = new User("Rimon Ryan","sayemkcn2@gmail.com");

        SMR.initialize(getApplicationContext(), "dbjt2sne4qrjpps0bsc25slpto", "v1e2u3fmb94duc2t75llguhvek", user);

    }
}
