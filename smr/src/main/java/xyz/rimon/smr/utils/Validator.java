package xyz.rimon.smr.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import xyz.rimon.smr.exceptions.InvalidException;
import xyz.rimon.smr.model.User;

/**
 * Created by SAyEM on 4/12/17.
 */

public class Validator {
    private Validator(){}

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public static void validateUser(User user) throws InvalidException {
        if (user==null) throw new IllegalArgumentException("User can not be null!");
        if (user.getEmail()==null || !Validator.isEmailValid(user.getEmail()))
            throw new InvalidException("Email invalid!");
        if (user.getUsername()==null|| user.getUsername().length()<6)
            throw new InvalidException("Username length must be at least 6 characters. (Tips) Use a static namespace for your your users like: \'yourAppName-\'");
        if (user.getPassword()==null || user.getPassword().length()<6)
            throw new InvalidException("Password length must be at least 6 characters");
    }

    public static boolean isEmailValid(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
}
