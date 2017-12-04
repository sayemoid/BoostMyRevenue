package xyz.rimon.smr.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;

/**
 * Created by SAyEM on 4/12/17.
 */

public class NetworkUtil {
    public static int TYPE_WIFI = ConnectivityManager.TYPE_WIFI;
    public static int TYPE_MOBILE = ConnectivityManager.TYPE_MOBILE;
    public static int TYPE_OTHERS = 100;
    public static int TYPE_NOT_CONNECTED = -1;

    public static int getNetworkConnection(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = connMgr.getActiveNetworkInfo();

        if (nInfo != null && nInfo.isConnected()) {
            if (nInfo.getType() == ConnectivityManager.TYPE_WIFI) return TYPE_WIFI;
            else if (nInfo.getType() == ConnectivityManager.TYPE_MOBILE) return TYPE_MOBILE;

            // at least its connected
            return TYPE_OTHERS;
        }

        return TYPE_NOT_CONNECTED;
    }

    public static boolean isConnected(Context context) {
        return getNetworkConnection(context) != TYPE_NOT_CONNECTED;
    }

    public static void loadImage(final Activity context, final ImageView imageView, final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL u = new URL(url);
                    final Bitmap bmp = BitmapFactory.decodeStream(u.openConnection().getInputStream());
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bmp);
                        }
                    });
                } catch (IOException e) {
                    Log.e("LOAD_IMAGE", e.toString());
                }
            }
        }).start();
    }

}
