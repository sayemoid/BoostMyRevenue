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

        SMR.initialize(getApplicationContext(), "c1pdmokem15sdncvu1m0ovivsv", "6baoeb9cqgrv8eug5353njrtc2");

    }
}
