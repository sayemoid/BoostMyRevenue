package xyz.rimon.sharemyrevenue;

import android.app.Application;
import android.util.Log;

import xyz.rimon.smr.SMR;
import xyz.rimon.smr.exceptions.InvalidException;
import xyz.rimon.smr.model.User;

/**
 * Created by SAyEM on 5/12/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        User user = new User("Rimon Ryan", "rimonryan10", "email10@rimon.xyz", "qwerty");
        try {
            SMR.initialize(getApplicationContext(), "ir8tm91qmvd9pbg7dat7e32p22", "48fn7kn3irmndhus48q7l6agto", user);
        } catch (InvalidException e) {
            Log.e("Error", e.toString());
        }

    }
}
