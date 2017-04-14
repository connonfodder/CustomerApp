package com.aadhk.customer.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.aadhk.customer.bean.AppSettings;
import com.aadhk.customer.bean.User;


public class PreferenceUtil {

    protected SharedPreferences preference;

    public PreferenceUtil(Context context) {
        preference = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveSettings(AppSettings appSettings) {
        if(appSettings==null) return;
        savePreference(Constant.PREF_SETTING_CURRENCY, appSettings.getCurrency());
        savePreference(Constant.PREF_SETTING_CURRENCY_POSITION, appSettings.getCurrencyPosition());
        savePreference(Constant.PREF_SETTING_DECIMAL_PLACE, appSettings.getDecimalPlace());
        savePreference(Constant.PREF_SETTING_WITHDRAW_INTERVAL, appSettings.getWithdrawalInterval());
        savePreference(Constant.PREF_SETTING_WITHDRAW_MINIMUM, (float) appSettings.getWithdrawalMinimim());
    }

    public AppSettings getSetting(){
        AppSettings appSettings = new AppSettings();
        appSettings.setCurrency(preference.getInt(Constant.PREF_SETTING_CURRENCY, 1));
        appSettings.setDecimalPlace(preference.getInt(Constant.PREF_SETTING_DECIMAL_PLACE, 2));
        appSettings.setCurrencyPosition(preference.getInt(Constant.PREF_SETTING_CURRENCY_POSITION, 2));
        appSettings.setWithdrawalInterval(preference.getInt(Constant.PREF_SETTING_WITHDRAW_INTERVAL, 0));
        appSettings.setWithdrawalMinimim(preference.getFloat(Constant.PREF_SETTING_WITHDRAW_MINIMUM, 0));
        return appSettings;
    }

    //保存最后一次地址位置
    public void saveLastLocation(double lng, double lat) {
        if (lng == 0 || lat == 0) return;
        savePreference(Constant.PREF_LAST_LOCATION_LNG, (float) lng);
        savePreference(Constant.PREF_LAST_LOCATION_LAT, (float) lat);
    }

    //1.2915100000,103.8506940000
    public double getLastLocationLNG() {
        return preference.getFloat(Constant.PREF_LAST_LOCATION_LNG, (float) 103.8506940000);
    }

    public double getLastLocationLAT() {
        return preference.getFloat(Constant.PREF_LAST_LOCATION_LAT, (float) 1.2915100000);
    }

    public void saveUser(User user) {
        if (user == null) {
            clearUserInfo();
            return;
        }
        savePreference(Constant.PREF_USER_ID, user.getId());
        savePreference(Constant.PREF_USER_NAME, user.getUserName());
        savePreference(Constant.PREF_USER_PASSWORD, user.getPassword());
        savePreference(Constant.PREF_USER_EMAIL, user.getEmail());
        savePreference(Constant.PREF_USER_TELEPHONE, user.getTelephone());
        savePreference(Constant.PREF_USER_ADDRESS_1, user.getContactAddress1());
        savePreference(Constant.PREF_USER_ADDRESS_2, user.getContactAddress2());
        savePreference(Constant.PREF_USER_ADDRESS_3, user.getContactAddress3());
    }

    private void clearUserInfo() {
        savePreference(Constant.PREF_USER_ID, 0);
        savePreference(Constant.PREF_USER_NAME, "");
        savePreference(Constant.PREF_USER_PASSWORD, "");
        savePreference(Constant.PREF_USER_EMAIL, "");
        savePreference(Constant.PREF_USER_TELEPHONE, "");
        savePreference(Constant.PREF_USER_ADDRESS_1, "");
        savePreference(Constant.PREF_USER_ADDRESS_2, "");
        savePreference(Constant.PREF_USER_ADDRESS_3, "");
    }

    public User getUser() {
        if (TextUtils.isEmpty(preference.getString(Constant.PREF_USER_EMAIL, ""))) return null;
        User user = new User();
        user.setId(preference.getLong(Constant.PREF_USER_ID, 0));
        user.setUserName(preference.getString(Constant.PREF_USER_NAME, ""));
        user.setPassword(preference.getString(Constant.PREF_USER_PASSWORD, ""));
        user.setEmail(preference.getString(Constant.PREF_USER_EMAIL, ""));
        user.setTelephone(preference.getString(Constant.PREF_USER_TELEPHONE, ""));
        user.setContactAddress1(preference.getString(Constant.PREF_USER_ADDRESS_1, ""));
        user.setContactAddress2(preference.getString(Constant.PREF_USER_ADDRESS_2, ""));
        user.setContactAddress3(preference.getString(Constant.PREF_USER_ADDRESS_3, ""));
        return user;
    }



    public void savePreference(String key, boolean value) {
        Editor editor = preference.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void savePreference(String key, int value) {
        Editor editor = preference.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void savePreference(String key, float value) {
        Editor editor = preference.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public void savePreference(String key, long value) {
        Editor editor = preference.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public void savePreference(String key, String value) {
        Editor editor = preference.edit();
        editor.putString(key, value);
//        editor.commit();  Consider using apply() instead; commit writes its data to persistent storage immediately, whereas apply will handle it in the background
        editor.apply();
    }

    public void removePreference(String key) {
        Editor editor = preference.edit();
        editor.remove(key);
//        editor.commit();   Consider using apply() instead; commit writes its data to persistent storage immediately, whereas apply will handle it in the background
        editor.apply();
    }

}
