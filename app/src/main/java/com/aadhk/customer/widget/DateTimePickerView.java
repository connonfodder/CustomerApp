/*******************************************************************************
 * Copyright (C) Hong Kong Android Technology Co.
 * All right reserved.
 ******************************************************************************/
package com.aadhk.customer.widget;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;

import com.aadhk.customer.util.CalendarUtil;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;

import org.acra.ACRA;

import java.text.ParseException;
import java.util.Calendar;

public class DateTimePickerView {

    private static DateTimePickerView instance;

    static {
        instance = new DateTimePickerView();
    }

    private DateTimePickerView() {

    }

    public static DateTimePickerView getInstance() {
        if (instance == null) {
            instance = new DateTimePickerView();
        }
        return instance;
    }

    public interface OnBackLisener {
        void onConfirm(String chooseDateTime);
    }

    public interface OnBackCListener extends OnBackLisener{
        void onCancel();
    }

    public interface OnBackDetailListener {
        void onConfirm(String date, String time);
    }

    //返回选择日期(2016-08-22)
    public void dialogDate(AppCompatActivity activity, String initDate, final OnBackLisener listener) {
        SublimePickerFragment pickerFrag = new SublimePickerFragment();
        Calendar cal;
        try {
            cal = CalendarUtil.getCalendarByDate(initDate);
        } catch (ParseException e1) {
            cal = null;
            e1.printStackTrace();
			ACRA.getErrorReporter().handleException(e1);
        }
        Pair<Boolean, SublimeOptions> optionsPair = pickerFrag.getDateOptions(cal);
        Bundle bundle = new Bundle();
        bundle.putParcelable("SUBLIME_OPTIONS", optionsPair.second);
        pickerFrag.setArguments(bundle);
        pickerFrag.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        pickerFrag.setCallback(new SublimePickerFragment.Callback() {
            @Override
            public void onDateTimeRecurrenceSet(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute, SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule) {
                String month = monthOfYear < 9 ? "0" + (monthOfYear + 1) : (monthOfYear + 1) + "";
                String day = dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth + "";
                String chooseDate = year + "-" + month + "-" + day;
                if (null != listener) listener.onConfirm(chooseDate);
            }

            @Override
            public void onCancelled() {
                if (null != listener && listener instanceof OnBackCListener) ((OnBackCListener) listener).onCancel();
            }
        });
        pickerFrag.show(activity.getSupportFragmentManager(), "SUBLIME_PICKER");
    }

    //返回具体时间(16:36)
    public void dialogTime(AppCompatActivity activity, String initTime, final OnBackLisener listener) {
        SublimePickerFragment pickerFrag = new SublimePickerFragment();
        Calendar cal;
        try {
            cal =  CalendarUtil.getCalendarByTime(initTime);
        } catch (ParseException e1) {
            cal = null;
            e1.printStackTrace();
			ACRA.getErrorReporter().handleException(e1);
        }
        Pair<Boolean, SublimeOptions> optionsPair = pickerFrag.getTimeOptions(cal);
        Bundle bundle = new Bundle();
        bundle.putParcelable("SUBLIME_OPTIONS", optionsPair.second);
        pickerFrag.setArguments(bundle);
        pickerFrag.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        pickerFrag.setCallback(new SublimePickerFragment.Callback() {
            @Override
            public void onDateTimeRecurrenceSet(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute, SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule) {
                String hour = hourOfDay < 10 ? "0" + hourOfDay : hourOfDay + "";
                String minut = minute < 10 ? "0" + minute : minute + "";
                String chooseTime = hour + ":" + minut;
                if (null != listener) listener.onConfirm(chooseTime);
            }

            @Override
            public void onCancelled() {
                if (null != listener && listener instanceof OnBackCListener) ((OnBackCListener) listener).onCancel();
            }
        });
        pickerFrag.show(activity.getSupportFragmentManager(), "SUBLIME_PICKER");
    }

    // 单个获取日期和时间(2016-08-22 16:45)
    public void dialogDateTime(AppCompatActivity activity, String initDateTime, final OnBackDetailListener listener) {
        SublimePickerFragment pickerFrag = new SublimePickerFragment();
        Calendar cal;
        try {
            cal =  CalendarUtil.getCalendarByDateAndTime(initDateTime);
        } catch (ParseException e1) {
            cal = null;
            e1.printStackTrace();
			ACRA.getErrorReporter().handleException(e1);
        }
        Pair<Boolean, SublimeOptions> optionsPair = pickerFrag.getOptions(cal);
        Bundle bundle = new Bundle();
        bundle.putParcelable("SUBLIME_OPTIONS", optionsPair.second);
        pickerFrag.setArguments(bundle);
        pickerFrag.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        pickerFrag.setCallback(new SublimePickerFragment.Callback() {
            @Override
            public void onDateTimeRecurrenceSet(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute, SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule) {
                String month = monthOfYear < 9 ? "0" + (monthOfYear + 1) : (monthOfYear + 1) + "";
                String day = dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth + "";
                String hour = hourOfDay < 10 ? "0" + hourOfDay : hourOfDay + "";
                String minut = minute < 10 ? "0" + minute : minute + "";
                String date = year + "-" + month + "-" + day;
                String time = hour + ":" + minut;
                if (null != listener) listener.onConfirm(date, time);
            }

            @Override
            public void onCancelled() {}
        });
        pickerFrag.show(activity.getSupportFragmentManager(), "SUBLIME_PICKER");
    }
}
