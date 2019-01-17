package xyz.rimon.smr.views;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import xyz.rimon.ael.commons.Commons;
import xyz.rimon.smr.R;
import xyz.rimon.smr.commons.Pref;
import xyz.rimon.smr.events.ConfirmationEvent;
import xyz.rimon.smr.events.LoginEvent;
import xyz.rimon.smr.events.OptInOutEvent;
import xyz.rimon.smr.events.PaymentRequestEvent;
import xyz.rimon.smr.events.PostEventsEvent;
import xyz.rimon.smr.events.RevenueLoadEvent;
import xyz.rimon.smr.model.UserRev;
import xyz.rimon.smr.service.ApiClient;
import xyz.rimon.smr.service.ApiEndpoints;
import xyz.rimon.smr.utils.UIUtils;
import xyz.rimon.smr.utils.Validator;

/**
 * TODO: document your custom view class.
 */
public class MyRevenueView extends LinearLayout implements View.OnClickListener {

    private TextView cmInterationPoints;
    private TextView cmIncome;
    private TextView lastPaymentAmount;
    private TextView pmIncome;

    private View paymentView;
    private View actualNumbersLayout;
    private TextView tvRequestPaymentToggle;
    private EditText etPaymentMethod;
    private EditText etAccountNumber;
    private EditText etAmount;
    private EditText etNote;
    private Button btnSendRequest;
    private TextView btnOptInOut;

    private ImageView smrLogo;
    private TextView smrTitle;

    private CardView cardContainer;
    private TextView txtCmPoints;
    private TextView txtCurrentBalance;
    private TextView txtLastPayment;
    private TextView txtLmBalance;

    private int backgroundColor;
    private int boxBackgroundColor;
    private String title;
    private int textColor;

    public MyRevenueView(Context context) {
        super(context);
        this.initView();
    }

    public MyRevenueView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyRevenueView, 0, 0);
        try {
            backgroundColor = ta.getColor(R.styleable.MyRevenueView_backgroundColor, getResources().getColor(R.color.background_color));
            boxBackgroundColor = ta.getColor(R.styleable.MyRevenueView_boxBackgroundColor, getResources().getColor(R.color.box_background_color));
            title = ta.getString(R.styleable.MyRevenueView_title);
            textColor = ta.getColor(R.styleable.MyRevenueView_textColor, getResources().getColor(android.R.color.white));
        } finally {
            ta.recycle();
        }


        this.initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.my_revenue_view, this);
        EventBus.getDefault().register(this);

        this.cmInterationPoints = this.findViewById(R.id.cmInteractionPoints);
        this.cmIncome = this.findViewById(R.id.cmIncome);
        this.lastPaymentAmount = this.findViewById(R.id.lastPaymentAmount);
        this.pmIncome = this.findViewById(R.id.pmIncome);

        this.paymentView = this.findViewById(R.id.paymentView);
        this.actualNumbersLayout = this.findViewById(R.id.actualNumbersLayout);
        this.tvRequestPaymentToggle = this.findViewById(R.id.tvRequestPaymentToggle);
        this.tvRequestPaymentToggle.setOnClickListener(this);
        this.etPaymentMethod = this.findViewById(R.id.etPaymentMethod);
        this.etAccountNumber = this.findViewById(R.id.etAccountNumber);
        this.etNote = this.findViewById(R.id.etNote);
        this.etAmount = this.findViewById(R.id.etAmount);
        this.btnSendRequest = this.findViewById(R.id.btnSendRequest);
        this.btnOptInOut = this.findViewById(R.id.btnOptInOut);
        this.smrLogo = this.findViewById(R.id.smrLogo);
        this.smrTitle = this.findViewById(R.id.smrTitle);

        this.cardContainer = this.findViewById(R.id.cardContainer);
        this.txtCmPoints = this.findViewById(R.id.txtCmPoints);
        this.txtCurrentBalance = this.findViewById(R.id.txtCurrBalance);
        this.txtLastPayment = this.findViewById(R.id.txtLastPayment);
        this.txtLmBalance = this.findViewById(R.id.txtLmBalance);
        // set custom attr values
        this.cardContainer.setCardBackgroundColor(this.backgroundColor);
        this.cmInterationPoints.setBackgroundColor(this.boxBackgroundColor);
        this.cmIncome.setBackgroundColor(this.boxBackgroundColor);
        this.lastPaymentAmount.setBackgroundColor(this.boxBackgroundColor);
        this.pmIncome.setBackgroundColor(this.boxBackgroundColor);

        if (this.title == null) this.title = getResources().getString(R.string.viewTitle);
        this.smrTitle.setText(this.title);

        this.smrTitle.setTextColor(this.textColor);
        this.txtCmPoints.setTextColor(this.textColor);
        this.txtCurrentBalance.setTextColor(this.textColor);
        this.txtLastPayment.setTextColor(this.textColor);
        this.txtLmBalance.setTextColor(this.textColor);
        this.cmInterationPoints.setTextColor(this.textColor);
        this.cmIncome.setTextColor(this.textColor);
        this.lastPaymentAmount.setTextColor(this.textColor);
        this.pmIncome.setTextColor(this.textColor);


        this.btnSendRequest.setOnClickListener(this);
        this.btnOptInOut.setOnClickListener(this);
        this.smrLogo.setOnClickListener(this);
        this.smrTitle.setOnClickListener(this);

        this.resolveOptInOutButton(isOptedIn());
        this.loadUserRevenue();

    }

    @Subscribe
    public void onUserRevLoaded(RevenueLoadEvent event) {
        if (!event.isSuccess()) return;
        UserRev userRev = event.getUserRev();
        Pref.savePreference(getContext(), Pref.KEY_USER_REV_JSON, Commons.buildGson().toJson(userRev));
        this.updateViews(userRev);
    }

    private void updateViews(UserRev userRev) {
        if (userRev.isCanRequestForPayment())
            this.paymentView.setVisibility(VISIBLE);
        this.lastPaymentAmount.setText(String.valueOf(userRev.getLastPaymentAmount()));
        this.pmIncome.setText(String.valueOf(userRev.getPreviousMonthIncome()));
        this.cmIncome.setText(String.valueOf(userRev.getCurrentBalance()));
        this.cmInterationPoints.setText(String.valueOf(userRev.getCurrentMonthInteractionPoints()));

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

    @Subscribe
    public void onPaymentRequestSendEvent(PaymentRequestEvent event) {
        if (event.isSuccess()) {
            UIUtils.showDialog(getContext(), "Success!", "Successfully sent a payment request!");
            this.paymentView.setVisibility(GONE);
        } else
            UIUtils.showDialog(getContext(), "Error!!", event.getErrorMessage());
    }

    @Subscribe
    public void onDialogConfirmationEvent(ConfirmationEvent e) {
        this.setOptedIn(e.isStatus());
        this.resolveOptInOutButton(e.isStatus());
    }

    @Subscribe
    public void onOptInOutEvent(OptInOutEvent e) {
        resolveOptInOutButton(e.isStatus());
    }

    private void loadUserRevenue() {
        // update user rev by default
        if (!Pref.isNull(getContext(), Pref.KEY_USER_REV_JSON)) {
            UserRev userRev = Commons.buildGson().fromJson(Pref.getPreferenceString(getContext(), Pref.KEY_USER_REV_JSON), UserRev.class);
            this.updateViews(userRev);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String month = new SimpleDateFormat("MMMM", Locale.US).format(calendar.getTime());
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        ApiClient.loadUserRevenue((Activity) getContext(), month.trim().toLowerCase(), year);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnSendRequest) {
            if (!Validator.isValidRequestInformations(getContext(), this.etPaymentMethod, this.etAccountNumber, this.etAmount, this.etNote))
                return;
            ApiClient.sendPaymentRequest(getContext(),
                    this.etPaymentMethod.getText().toString(),
                    this.etAccountNumber.getText().toString(),
                    this.etAmount.getText().toString(),
                    this.etNote.getText().toString());
        } else if (id == R.id.tvRequestPaymentToggle) {
            this.toggleRequestFormVisibility();
        } else if (id == R.id.btnOptInOut) {
            UIUtils.optInOutConfimationDialog(getContext(), isOptedIn());
        } else if (id == R.id.smrLogo || id == R.id.smrTitle) {
            xyz.rimon.smr.commons.Commons.openUrl(getContext(), ApiEndpoints.HOME_URL);
        }
    }

    private void resolveOptInOutButton(boolean optedIn) {
        if (!isConfirmationDialogShown()) {
            UIUtils.showConfimationDialog(
                    getContext(),
                    "Sign up for BoostMyRevenue programme",
                    "BoostMyRevenue is a revenue sharing programme for the developers/companies to share a percentage of their revenue among their users. That means you'll get paid by using this app according to your interactions.\n\n" +
                            "What you\'ll be able to do by signing up?\n\n" +
                            "-> Earn interaction points when you use the app.\n" +
                            "-> Those points will be converted to money every months.\n" +
                            "-> You'll be able to send payment requests when your earning meets the threshold.\n" +
                            "-> You'll NOT get paid by clicking any kind of ad.\n\n" +
                            "By clicking \"Ok\", you agree to our terms of service and privacy policy\n( https://app.boostmyrevenue.net/privacy-policy )."
            );
            return;
        }

        if (optedIn) {
            this.setOptedIn(true);
            this.btnOptInOut.setText("Opt Out");
            this.btnOptInOut.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            this.actualNumbersLayout.setVisibility(VISIBLE);
        } else {
            this.setOptedIn(false);
            this.btnOptInOut.setText("Opt in again");
            this.btnOptInOut.setTextColor(getResources().getColor(android.R.color.holo_green_light));
            this.actualNumbersLayout.setVisibility(GONE);
        }
    }


    private void toggleRequestFormVisibility() {
        if (this.etPaymentMethod.getVisibility() == View.GONE)
            this.etPaymentMethod.setVisibility(View.VISIBLE);
        else this.etPaymentMethod.setVisibility(View.GONE);
        if (this.etAmount.getVisibility() == View.GONE)
            this.etAmount.setVisibility(View.VISIBLE);
        else this.etAmount.setVisibility(View.GONE);
        if (this.etAccountNumber.getVisibility() == View.GONE)
            this.etAccountNumber.setVisibility(View.VISIBLE);
        else this.etAccountNumber.setVisibility(View.GONE);
        if (this.etNote.getVisibility() == View.GONE)
            this.etNote.setVisibility(View.VISIBLE);
        else this.etNote.setVisibility(View.GONE);
        if (this.btnSendRequest.getVisibility() == View.GONE)
            this.btnSendRequest.setVisibility(View.VISIBLE);
        else this.btnSendRequest.setVisibility(View.GONE);
    }

    public boolean isConfirmationDialogShown() {
        return !Pref.isNull(getContext(), Pref.USER_OPT_IN);
    }

    public boolean isOptedIn() {
        return Pref.getPreference(getContext(), Pref.USER_OPT_IN);
    }

    private void setOptedIn(boolean optedIn) {
        Pref.savePreference(getContext(), Pref.USER_OPT_IN, optedIn);
    }
}
