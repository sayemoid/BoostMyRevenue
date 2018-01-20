package xyz.rimon.smr.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import xyz.rimon.smr.service.ApiClient;

/**
 * Created by SAyEM on 20/1/18.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ApiClient.getPromoInformations(context);
    }
}
