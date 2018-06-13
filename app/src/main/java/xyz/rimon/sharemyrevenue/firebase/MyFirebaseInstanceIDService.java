package xyz.rimon.sharemyrevenue.firebase;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import xyz.rimon.smr.SMR;
import xyz.rimon.smr.utils.Logger;

/**
 * Created by SAyEM on 11/6/18.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Logger.i("RefreshedToken: " + refreshedToken);

        SMR.dispatchFirebaseUserToken(this, refreshedToken);

    }
}
