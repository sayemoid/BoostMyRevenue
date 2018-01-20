package xyz.rimon.smr.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import xyz.rimon.smr.service.NotificationService;
import xyz.rimon.smr.utils.Logger;
import xyz.rimon.smr.utils.UIUtils;

/**
 * Created by SAyEM on 20/1/18.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        UIUtils.showToast(context,"Alarm Received: "+intent.getAction());
        Logger.d("Alarm Received: "+intent.getAction());
        NotificationService.showNotification(context);
    }
}
