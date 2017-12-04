package xyz.rimon.smr.service;

import android.content.Context;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseListener;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;
import xyz.rimon.ael.domains.Event;
import xyz.rimon.smr.commons.Parser;
import xyz.rimon.smr.commons.Pref;
import xyz.rimon.smr.exceptions.InvalidException;
import xyz.rimon.smr.model.User;
import xyz.rimon.smr.utils.Validator;

/**
 * Created by SAyEM on 4/12/17.
 */

public class ApiClient {
    private ApiClient() {
    }

    public static void registerUser(final Context context, final User user) throws InvalidException {
        Validator.validateUser(user);

        AndroidNetworking.post(ApiEndpoints.REGISTER_URL)
                .addBodyParameter(ApiEndpoints.KEY_CLIENT_ID_CAMELCASE, Pref.getPreferenceString(context, Pref.KEY_CLIENT_ID))
                .addBodyParameter(ApiEndpoints.KEY_CLIENT_SECRET_CAMELCASE, Pref.getPreferenceString(context, Pref.KEY_CLIENT_SECRET))
                .addBodyParameter(ApiEndpoints.KEY_NAME, user.getName())
                .addBodyParameter(ApiEndpoints.KEY_EMAIL, user.getEmail())
                .addBodyParameter(ApiEndpoints.KEY_USERNAME, user.getUsername())
                .addBodyParameter(ApiEndpoints.KEY_PASSOWRD, user.getPassword())
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsOkHttpResponse(new OkHttpResponseListener() {
                    @Override
                    public void onResponse(Response response) {
                        ResponseHandler.onUserRegistration(context, user, response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        ResponseHandler.onError(anError);
                    }
                });

    }

    public static void login(final Context context, String username, String password) {
        AndroidNetworking.get(ApiEndpoints.OAUTH_TOKEN_URL)
                .addQueryParameter(ApiEndpoints.KEY_GRANT_TYPE, ApiEndpoints.VAL_PASSWORD)
                .addQueryParameter(ApiEndpoints.KEY_CLIENT_ID, Pref.getPreferenceString(context, Pref.KEY_CLIENT_ID))
                .addQueryParameter(ApiEndpoints.KEY_CLIENT_SECRET, Pref.getPreferenceString(context, Pref.KEY_CLIENT_SECRET))
                .addQueryParameter(ApiEndpoints.KEY_USERNAME, username)
                .addQueryParameter(ApiEndpoints.KEY_PASSOWRD, password)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        ResponseHandler.onUserLogin(context, response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        ResponseHandler.onError(anError);
                    }
                });
    }

    public static void postEvent(final Context context, List<Event> eventList) {
        if (eventList == null) eventList = new ArrayList<>();
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(Parser.getGson().toJson(eventList));
        } catch (JSONException e) {
            jsonArray = new JSONArray();
        }

        AndroidNetworking.post(ApiEndpoints.POST_EVENT_URL)
                .addHeaders("Content-Type", "application/json")
                .addQueryParameter(ApiEndpoints.KEY_ACCESS_TOKEN, Pref.getPreferenceString(context, Pref.KEY_ACCESS_TOKEN))
                .addJSONArrayBody(jsonArray)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsOkHttpResponse(new OkHttpResponseListener() {
                    @Override
                    public void onResponse(Response response) {
                        ResponseHandler.onPostEvent(context, response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        ResponseHandler.onError(anError);
                    }
                });
    }

}
