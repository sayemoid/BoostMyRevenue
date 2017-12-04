package xyz.rimon.sharemyrevenue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import xyz.rimon.ael.domains.Event;
import xyz.rimon.ael.factory.EventFactory;
import xyz.rimon.smr.SMR;
import xyz.rimon.smr.exceptions.InvalidException;
import xyz.rimon.smr.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button initButton = this.findViewById(R.id.btnInitialize);
        initButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User("Rimon Ryan", "rimonryan8", "email8@rimon.xyz", "qwerty");
                try {
                    SMR.initialize(getApplicationContext(), "ir8tm91qmvd9pbg7dat7e32p22", "48fn7kn3irmndhus48q7l6agto", user);
                } catch (InvalidException e) {
                    Log.e("Error", e.toString());
                }


                Event initEvent = EventFactory.getInstance().createEvent(Event.Type.APP_EVENT, "203", "smr_init", (byte) 5);
                SMR.logOnline(MainActivity.this, initEvent);
            }
        });
    }
}
