package xyz.rimon.smr.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import xyz.rimon.smr.R;
import xyz.rimon.smr.commons.Commons;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by SAyEM on 20/1/18.
 */

public class NotificationService {

    public static void showNotification(Context context) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) return;

        NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notification Title")
                .setContentText("Description of the Noti!")
                .setAutoCancel(true);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notiBuilder.setSound(alarmSound);
//        Intent resultIntent = new Intent(context, ForegroundActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
//        stackBuilder.addParentStack(MainActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(Commons.getOpenPlayStoreIntent(context, "com.masudbappy.caloriesinfood"));
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        notiBuilder.setContentIntent(resultPendingIntent);


        int mNotificationId = 001;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notiBuilder.getNotification().flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(mNotificationId, notiBuilder.build());
    }


}
