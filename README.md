# [BoostMyRevenue](http://www.boostmyrevenue.net)
*Share a percentage of your revenue among your users, grow your company!* 
*By simply logging their interactions.*

*If you want to distribute your revenues/earnings among your users according to their interactions and usage of your app, then this api does just that for you.*


**You can find a test implementation project [here](https://github.com/sayemkcn/bmr-test)***

<b> Add BoostMyRevenue to your project</b>

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
	
dependencies {
    compile 'com.github.sayemkcn:BoostMyRevenue:v1.3.0-beta'
}

```

<b>Register</b>


*You have to register our website to get client_id and client_secret for your app*

[Register Here](http://www.boostmyrevenue.net)

Click 'Show' button to copy your client id and secret.

![Alt text](https://i.imgur.com/apsUw6h.jpg "BoostMyRevenue")

<b>Initialize BoostMyRevenue</b>

```
    // On your MainActivity
    SMR.initialize(getApplicationContext(), "client_id", "client_secret");
```
<b>Get User info and set user</b>
```
    SMR.setUser(this, "John Doe"); // Will take the device primary email address to create account

    // Or, if users email address is verified by you, you can set it yourself
    // !IMPORTANT! Please remember to verify email address if you use this method. Otherwise your user can register with any email address. It's what you want to forbid,right?
    SMR.setUser(this, "John Doe", "example@example.com");

```

## Now you just have to log user interactions
<b> Before logging user interactions, be sure to take storage permission from user. We use storage to provide offline logging capabilities. </b>
```
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```
You may need [runtime permissions](https://developer.android.com/training/permissions/requesting.html#java) on android version higher than marshmallow.

<b>Create Events</b>

```
    EventFactory.getInstance().createEvent(Event.Type.USER_EVENT, "event_code", "event_tag", Event.Weight.NORMAL); // returns an event instance too.
```

You can create as much events as you want for future use. All of the events will be registered to <b>EventRegistry</b>.

You can find any specific event by tag or event code. (You should always get events from the registry instead of creating one every time)

```
    Event e = EventRegistry.getInstance().getEventByTag("event_tag");
```

<b>Log User Interactions</b>

```
    SMR.logOnline(MainActivity.this, e);
```


##<b>View for users to see earnings, send payment requests</b>

<img src="https://i.imgur.com/bDkdKp3.png" width="500" /> <img src="https://i.imgur.com/mwRCOnN.png" width="500" />
<img src="https://i.imgur.com/ITN8z5a.png" width="500" />

Just add this view anywhere you want. That's it.

```
       <xyz.rimon.smr.views.MyRevenueView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"

        app:backgroundColor="#797979"
        app:boxBackgroundColor="#676767"
        app:textColor="@android:color/white"
        app:title="Your App Name" />
```



Whoolala you're a sperstar now!!!
But it's not all over yet, We've a dashboard for you on our [Website](http://www.boostmyrevenue.net) to manage your users, Share your revenues, resolving payment requests and so on!

# Access your data anytime
Base URL: 
```
https://api.boostmyrevenue.net
```
## Get access token
Endpoint: ```/oauth/token```
<br/>Params: 
``` 
grant_type:string (`password`,'refresh_token') ->required,
client_id:string ->required,
client_secret:string ->required,
username:string ->required,
password:string ->required
```
You'll get an access token and a refresh token here. Use this access_token to access your resources.
## Events Api
Endpoint: ```/api/v1/events```
<br/>Method: GET,
<br/>Params: 
``` 
page:integer,
access_token:string ->required
```
## Payment Requests Api
Endpoint: ```/api/v1/payments/requests/{client_id}```
<br/>Method: GET,
<br/>Params: 
``` 
page:integer,
paid:boolean->required
access_token:string ->required
```
## Users Api
Endpoint: ```/api/v1/users``
<br/>Method: GET,`
<br/>Params: 
``` 
page:integer,
access_token:string ->required
```
## Earnings Api (For single user)
Endpoint: ```/api/v1/users/{userid}/rev```
<br/>Method: GET,
<br/>Params: 
``` 
month:string->required (january,february...),
year:string->required,
access_token:string ->required
```
## Transactions Api (For single user)
Endpoint: ```/api/v1/transactions/{userid}```
<br/>Method: GET,
<br/>Params: 
``` 
page:int
access_token:string ->required
```

If you find any trouble integrating our api, Please contact me by [email](email@rimon.xyz) at any time. I'm looking forward to help you with my best efforts.

God Bless you.

