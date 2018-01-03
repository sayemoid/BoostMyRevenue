# [ShareMyRevenue](http://www.sharemyrevenue.net) 

###[Javadoc](https://www.sharemyrevenue.net/docs/javadoc/index.html)
*Share a percentage of your revenue among your users, grow your company!* 
*By simply logging their interactions.*

*If you want to distribute your revenues/earnings among your users according to their interactions and usage of your app, then this api does just that for you.*

<b> Add ShareMyRevenue to your project</b>

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
	
dependencies {
	compile 'com.github.sayemkcn:ShareMyRevenue:v1.0.0-beta'
}

```

<b>Register</b>


*You have to register our website to get client_id and client_secret for your app*

[Register Here](http://www.sharemyrevenue.net)

Click 'Show' button to copy your client id and secret.

![Alt text](https://i.imgur.com/apsUw6h.jpg "ShareMyRevenue")

<b>Initialize ShareMyRevenue</b>

```
    // On your application class
    SMR.initialize(getApplicationContext(), "client_id", "client_secret");
```
<b>Get User info and set user</b>
```
    SMR.setUser(this, "John Doe"); // Will take the device primary email address to create account

    // Or, if users email address is verified by you, you can set it yourself
    SMR.setUser(this, "John Doe", "example@example.com");

```

###Now you just have to log user interactions

<b>Create Events</b>

```
    EventFactory.getInstance().createEvent(Event.Type.USER_EVENT, "event_code", "event_tag", Event.Weight.NORMAL):
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

<img src="https://i.imgur.com/QaoSe3z.png" width="500" /> <img src="https://i.imgur.com/mwRCOnN.png" width="500" />
<img src="https://i.imgur.com/ITN8z5a.png" width="500" />

Just add this view anywhere you want. That's it.

```
     <xyz.rimon.smr.views.MyRevenueView
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
```



Whoolala you're a sperstar now!!!
But it's not all over yet, We've a dashboard for you on our [Website](http://www.sharemyrevenue.net) to manage your users, Share your revenues, resolving payment requests and so on!
If you find any trouble integrating our api, Please contact me by [email](email@rimon.xyz) at any time. I'm looking forward to help you with my best efforts.

God Bless you.

