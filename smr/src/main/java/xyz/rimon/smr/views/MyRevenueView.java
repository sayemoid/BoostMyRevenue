package xyz.rimon.smr.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import xyz.rimon.smr.R;
import xyz.rimon.smr.events.LoginEvent;
import xyz.rimon.smr.events.PostEventsEvent;
import xyz.rimon.smr.events.RevenueLoadEvent;
import xyz.rimon.smr.model.UserRev;
import xyz.rimon.smr.service.ApiClient;

/**
 * TODO: document your custom view class.
 */
public class MyRevenueView extends LinearLayout {

    private TextView cmInterationPoints;
    private TextView cmIncome;
    private TextView pmInteractionPoints;
    private TextView pmIncome;

    public MyRevenueView(Context context) {
        super(context);
        this.initView();
    }

    public MyRevenueView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.my_revenue_view, this);
        EventBus.getDefault().register(this);

        this.cmInterationPoints = this.findViewById(R.id.cmInteractionPoints);
        this.cmIncome = this.findViewById(R.id.cmIncome);
        this.pmInteractionPoints = this.findViewById(R.id.pmInteractionPoints);
        this.pmIncome = this.findViewById(R.id.pmIncome);

        this.loadUserRevenue();
    }

    @Subscribe
    public void onUserRevLoaded(RevenueLoadEvent event) {
        if (!event.isSuccess()) return;
        UserRev userRev = event.getUserRev();
        this.pmInteractionPoints.setText(String.valueOf(userRev.getPreviousMonthInteractionPoints()));
        this.pmIncome.setText(String.valueOf(userRev.getPreviousMonthIncome()));
        this.cmIncome.setText(String.valueOf(userRev.getCurrentBalance()));
        this.cmInterationPoints.setText(String.valueOf(userRev.getCurrentMonthInteractionPoints()));
        Toast.makeText(getContext(), "Earnings updated!", Toast.LENGTH_SHORT);
    }

    @Subscribe
    public void onUserLogin(LoginEvent event) {
        if (event.isSuccess())
            this.loadUserRevenue();
    }

    @Subscribe
    public void onEventListPostEvent(PostEventsEvent event) {
        if (event.isSuccess())
            this.loadUserRevenue();
    }

    private void loadUserRevenue() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String month = new SimpleDateFormat("MMMM", Locale.US).format(calendar.getTime());
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        ApiClient.loadUserRevenue(getContext(), month.trim().toLowerCase(), year);
    }
}
