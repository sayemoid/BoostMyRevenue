package xyz.rimon.sharemyrevenue.firebase;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import xyz.rimon.smr.utils.Logger;

/**
 * Created by SAyEM on 11/6/18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> data = remoteMessage.getData();
        String type = data.get("type"); // general, alert, promotion
        String title = data.get("title");
        String message = data.get("message");

        Logger.i("title: " + title + "\nmessage: " + message + "\ntype: " + type);
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }
}
