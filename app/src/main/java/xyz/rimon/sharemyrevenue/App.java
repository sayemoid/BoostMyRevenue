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

        User user = new User("Rimon Ryan", "rimonryan", "email@rimon.xyz", "qwerty");
        try {
            SMR.initialize(getApplicationContext(), "giqpjglhuk77j6j4q7rkeplces", "3fl6cf4e7d1hug06856ei6v1o6", user);
        } catch (InvalidException e) {
            Log.e("Error", e.toString());
        }

    }
}
