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

        SMR.initialize(getApplicationContext(), "snbob56dlcvmf21ci197stfr1u", "d4r3m9tfv4gqe7u6d8sn2ncb5d");

    }
}
