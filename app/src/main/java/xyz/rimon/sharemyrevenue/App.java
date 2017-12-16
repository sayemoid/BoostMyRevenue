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

        User user = new User("Rimon Ryan","rimon.ryan1@gmail.com");

        SMR.initialize(getApplicationContext(), "ujdbjv0rpesugjscmotq6umnqs", "46q40f814msb2r6d6c0o12rb1i", user);

    }
}
