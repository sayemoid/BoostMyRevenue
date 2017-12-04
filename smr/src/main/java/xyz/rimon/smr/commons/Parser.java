package xyz.rimon.smr.commons;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

import xyz.rimon.smr.model.User;
import xyz.rimon.smr.model.UserAuth;
import xyz.rimon.smr.utils.DateUtil;

/**
 * Created by SAyEM on 4/12/17.
 */

public class Parser {
    public static Gson getGson() {
        return new GsonBuilder().setDateFormat(DateUtil.SERVER_DATE_TIME_PATTERN).create();
    }

    public static User parseUser(String response) {
        JsonReader reader = new JsonReader(new StringReader(response));
        reader.setLenient(true);
        return getGson().fromJson(reader, User.class);
    }

    public static UserAuth parseUserAuth(String response) {
        return getGson().fromJson(response, UserAuth.class);
    }
}
