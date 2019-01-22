package xyz.rimon.sharemyrevenue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import xyz.rimon.ael.domains.Event;
import xyz.rimon.ael.factory.EventFactory;
import xyz.rimon.smr.SMR;
import xyz.rimon.smr.utils.DateUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SMR.setUser(this, "Test Name","01710226163");

        Button initButton = this.findViewById(R.id.btnInitialize);
        initButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event initEvent = EventFactory.getInstance().createEvent(Event.Type.APP_EVENT, "203", "smr_init", (byte) 5);
                Map<String,String> data = new HashMap<>();
                data.put("package",MainActivity.this.getPackageName());
                data.put("name","Sayem");
                data.put("time", DateUtil.getServerDateTimeFormat().format(new Date()));
                initEvent.setEventData(data);
                SMR.logSecured(MainActivity.this, initEvent);
            }
        });
    }
}
