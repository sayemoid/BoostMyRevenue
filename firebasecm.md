# How to use BoostMyRevenue as a push server

If you need to implement a custom push server ( sending push notifications programmatically from your application ) you can use BoostMyRevenue to take care of for you. you just need to follow few steps.

### 1. Implement firebase cloud messaging on your application according to firebase documentation

At first you need to implement firebase cloud messaging on your application. Follow firebase [documentation](https://firebase.google.com/docs/cloud-messaging/android/client) to implement it successfully. If you already implemented two service `FirebaseMessagingService` and `FirebaseInstanceIdService` and can receive a push sent from firebase console, you are ready to go.

### 2. Implement BoostMyRevenue sdk

if you haven't implemented BoostMyRevenue sdk on your application, you can follow this [documentation](https://firebase.google.com/docs/cloud-messaging/android/client) to implement it on your application. You can skip implementing event logging if you want. But in this case you need to at least initialize firebase sdk.

### 3. Register your users firebase refresh token

In your implementation of `FirebaseInstanceIdService` service, override `onTokenRefresh()` method and add this code on that method

```
String refreshedToken = FirebaseInstanceId.getInstance().getToken();
 SMR.dispatchFirebaseUserToken(this, refreshedToken);
```

### 4. Add your server key

<b>You will have a server key on your firebase console</b>

<img src="https://i.imgur.com/uPbagh3.png" style="width:100%; height:auto;"/>

<b>copy this key and add it on BoostMyRevenue console</b>

<img src="https://i.imgur.com/dstMmdj.png" style="width:100%; height:auto;"/>

<img src="https://i.imgur.com/KxYta9F.png" style="width:100%; height:auto;"/>


<b>Now you can send a push notification from your admin panel:</b>

<img src="https://i.imgur.com/ULylq93.png" style="width:100%; height:auto;"/>

<b>Send notification to a single user.</b>

<img src="https://i.imgur.com/IlsXyZu.png" style="width:100%; height:auto;"/>

### On on specific topic:
In this case you'll have to subscribe your user to a specific topic on your app by using a firebase method.
```
FirebaseMessaging.getInstance().subscribeToTopic("all")
        .addOnCompleteListener(new OnCompleteListener<Void>() {
    @Override
    public void onComplete(@NonNull Task<Void> task) {
        String msg = getString(R.string.msg_subscribed);
        if (!task.isSuccessful()) {
            msg = getString(R.string.msg_subscribe_failed);
        }
        Log.d(TAG, msg);
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
});
```

<img src="https://i.imgur.com/gC6lDiT.png" style="width:100%; height:auto;"/>

We'e subscribed a topic 'all' on the above example. So in this case we've to provide topic name on the topic name field to send notifications to all users who subscribe to that topic. You can surely subscribe to multiple topics if you want.

<img src="https://i.imgur.com/x8LvEmP.png" style="width:100%; height:auto;"/>

## To send a push programatically

If you want to send a push programatically from your own server or trigger it from any of your applications you can definitely do that by using our web api.

To send a push you must get an access token. See [this documentation](https://docs.boostmyrevenue.net) for the access token api.

Endpoint: api.boostmyrevenue.net/api/v1/firebase/notify/{userId}
Params:
Path Param:
```
userId: Long (Get user id from [users api](https://docs.boostmyrevenue.net))
```
Request Param:
```
access_token:string ->required,
title:string ->required,
message:string ->required,
type:string (general, alert, promotion) ->required
```

