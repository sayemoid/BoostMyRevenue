//package xyz.rimon.smr.service;
//
//import android.content.Context;
//
//import org.greenrobot.eventbus.EventBus;
//
//import java.io.IOException;
//
//import okhttp3.Response;
//import xyz.rimon.smr.commons.Parser;
//import xyz.rimon.smr.commons.Pref;
//import xyz.rimon.smr.exceptions.InvalidException;
//import xyz.rimon.smr.model.User;
//import xyz.rimon.smr.utils.Validator;
//
///**
// * Created by SAyEM on 4/12/17.
// */
//
//public class UserService {
//
//    public UserService(){
//        EventBus.getDefault().register(this);
//    }
//
//    public User registerUser(Context context,String clientId, String clientSecret, User user) throws InvalidException, IOException {
//        if (clientId == null || clientSecret == null)
//            throw new InvalidException("Client Id or secret can not be null");
//        Validator.validateUser(user);
//
//        Response response = ResourceProvider.fetchPostResponse(buildRegistrationUrl(clientId,clientSecret,user));
//        if (response.code()!=200)
//            Pref.savePreference(context,Pref.KEY_INITIALIZED,false);
//        user = Parser.getGson().fromJson(response.body().string(),User.class);
//
//    }
//
//    private String buildRegistrationUrl(String clientId, String clientSecret, User user) {
//        return null;
//    }
//
//}
