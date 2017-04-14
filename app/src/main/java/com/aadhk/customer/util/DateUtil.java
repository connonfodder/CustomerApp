package com.aadhk.customer.util;

import org.acra.ACRA;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jack on 05/12/2016.
 */

public class DateUtil {

    public static String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.PREF_DEF_DATE, Locale.US);
        return dateFormat.format(new Date());
    }

    public static boolean isToday(String sorucedate) {
        boolean result = false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(Constant.PREF_DEF_DATE, Locale.US);
            Date today = new Date();
            Date date = sdf.parse(sorucedate);
            result = today.compareTo(date) == 0;
        } catch (Exception e) {
            e.printStackTrace();
			ACRA.getErrorReporter().handleException(e);
        }
        return result;
    }

    public static String displayDate(String dateTime) {
        String result = dateTime;
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.PREF_DEF_DATE_TIME, Locale.US);
        try {
            Date date = sdf.parse(dateTime);
            sdf = new SimpleDateFormat(Constant.PREF_DEF_DATE_TIME, Locale.US);
            result = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
			ACRA.getErrorReporter().handleException(e);
        }
        return result;
    }


    public static String displayTime(String dateTime) {
        String result = dateTime;
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.PREF_DEF_DATE_TIME, Locale.US);
        try {
            Date date = sdf.parse(dateTime);
            sdf = new SimpleDateFormat(Constant.TIME_FORMAT, Locale.US);
            result = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
			ACRA.getErrorReporter().handleException(e);
        }
        return result;
    }
}
