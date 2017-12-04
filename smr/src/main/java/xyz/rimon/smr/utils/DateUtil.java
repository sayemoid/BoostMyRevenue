package xyz.rimon.smr.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by SAyEM on 4/12/17.
 */

public class DateUtil {
    private DateUtil() {
    }

    public static String SERVER_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static String DATE_PATTERN_BACKWARDS = "yyyy-MM-dd";
    public static String DATE_TIME_PATTERN_BACKWARDS = "yyyy-MM-dd HH:mm:ss";

    public static String DATE_PATTERN_READABLE = "MMM dd, YYYY";
    public static String DATE_TIME_PATTERN_READABLE = "MMM dd, YYYY hh:mm a";


    public static String getReadableDate(Date date) {
        return getReadableDateFormat().format(date);
    }

    public static String getReadableDateTime(Date date) {
        return getReadableDateTimeFormat().format(date);
    }


    public static Date parseServerDateTime(String date) throws ParseException {
        DateFormat sdf = getServerDateTimeFormat();
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.parse(date);
    }

    public static DateFormat getReadableDateFormat() {
        return new SimpleDateFormat(DATE_PATTERN_READABLE);
    }

    public static DateFormat getReadableDateTimeFormat() {
        return new SimpleDateFormat(DATE_TIME_PATTERN_READABLE);
    }

    public static DateFormat getServerDateTimeFormat() {
        return new SimpleDateFormat(SERVER_DATE_TIME_PATTERN);
    }


}