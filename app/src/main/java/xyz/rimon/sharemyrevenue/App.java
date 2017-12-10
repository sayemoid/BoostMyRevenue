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

        User user = new User("Rimon Ryan", "rimonranbir", "rimonranbir@gmail.com", "qwerty");
        try {
            SMR.initialize(getApplicationContext(), "8bheq0so8mo3rdced0tmh15to1", "bl482aps529a8o817vs4uppepa", user);
        } catch (InvalidException e) {
            Log.e("Error", e.toString());
        }

    }
}
