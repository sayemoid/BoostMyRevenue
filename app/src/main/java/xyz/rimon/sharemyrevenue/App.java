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

        User user = new User("Rimon Ryan","sayemkcn3@gmail.com");

        SMR.initialize(getApplicationContext(), "q8qggggoe0ers995n3u885ukre", "gtto5v82rcunnmv4s5neik7ul3", user);

    }
}
