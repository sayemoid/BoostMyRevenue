package xyz.rimon.sharemyrevenue;

import android.app.Application;

import xyz.rimon.smr.SMR;

/**
 * Created by SAyEM on 5/12/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SMR.initialize(getApplicationContext(), "dbjt2sne4qrjpps0bsc25slpto", "v1e2u3fmb94duc2t75llguhvek");

    }
}
