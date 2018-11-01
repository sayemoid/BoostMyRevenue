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

        SMR.initialize(getApplicationContext(), "hkac9f2sbg2o9l92611a4m3mvj", "8sqifn36v5jb9vq8pt79nbpulu");

    }
}
