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
            SMR.initialize(getApplicationContext(), "op11ved6k5h3j4rof6qh04pnfg", "6og8ct2r7okfl0ocgs9sui5jgv", user);
        } catch (InvalidException e) {
            Log.e("Error", e.toString());
        }

    }
}
