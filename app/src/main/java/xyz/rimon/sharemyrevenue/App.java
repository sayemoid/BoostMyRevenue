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
            SMR.initialize(getApplicationContext(), "5aquturfa7ivulehbo5k6rbc1b", "mfbt0ttghhjerkpbi13k24jh99", user);
        } catch (InvalidException e) {
            Log.e("Error", e.toString());
        }

    }
}
