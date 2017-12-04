package xyz.rimon.smr.commons;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

import xyz.rimon.ael.commons.Commons;
import xyz.rimon.smr.model.User;
import xyz.rimon.smr.model.UserAuth;

/**
 * Created by SAyEM on 4/12/17.
 */

public class Parser {
    public static Gson getGson() {
        return Commons.buildGson();
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
