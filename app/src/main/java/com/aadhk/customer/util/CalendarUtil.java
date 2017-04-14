package com.aadhk.customer.util;

import org.acra.ACRA;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.aadhk.customer.util.Constant.PREF_DEF_DATE_TIME;
import static com.aadhk.customer.util.Constant.TIME_FORMAT_TAKE_OUT;

public class CalendarUtil {

    private static final String TAG = "CalendarUtil";

    public static String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.PREF_DEF_DATE, Locale.US);
        return dateFormat.format(new Date());
    }

    public static String getTime24() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.TIME_FORMAT_24, Locale.US);
        return dateFormat.format(new Date());
    }

    public static String getTime12() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.TIME_FORMAT_12, Locale.US);
        return dateFormat.format(new Date());
    }

    public static String displayDate(String dateValue, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.PREF_DEF_DATE, Locale.US);
        String result = dateValue;
        try {
            Date date = sdf.parse(dateValue);
            sdf = new SimpleDateFormat(dateFormat);
            result = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String displayTime(String time, String timeFormat) {
        String result = time;
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.TIME_FORMAT_24, Locale.US);
        try {
            Date date = sdf.parse(time);
            sdf = new SimpleDateFormat(timeFormat, Locale.US);
            result = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
			ACRA.getErrorReporter().handleException(e);
        }
        return result;
    }

    public static String displayNoYearTime(String value){
        String result = value;
        SimpleDateFormat sdf = new SimpleDateFormat(PREF_DEF_DATE_TIME, Locale.US);
        try {
            Date date = sdf.parse(value);
            sdf = new SimpleDateFormat(TIME_FORMAT_TAKE_OUT, Locale.US);
            result = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getCurrentDateTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.PREF_DEF_DATE_TIME, Locale.US);
        return dateFormat.format(new Date());
    }

    public static Calendar getCalendarByDateAndTime(String value) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(PREF_DEF_DATE_TIME, Locale.US);
        Calendar cal = Calendar.getInstance();
        Date date = sdf.parse(value);
        cal.setTime(date);
        return cal;
    }

    public static Calendar getCalendarByTime(String value) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.TIME_FORMAT_24, Locale.US);
        Calendar cal = Calendar.getInstance();
        Date date = sdf.parse(value);
        cal.setTime(date);
        return cal;
    }

    public static Calendar getCalendarByDate(String value) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(Constant.PREF_DEF_DATE, Locale.US);
        Calendar cal = Calendar.getInstance();
        Date date = sdf.parse(value);
        cal.setTime(date);
        return cal;
    }

}
