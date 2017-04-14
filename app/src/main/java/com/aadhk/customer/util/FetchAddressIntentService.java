package com.aadhk.customer.util;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;

import com.aadhk.library.rx.RxBus;
import com.aadhk.library.rx.RxManager;
import com.aadhk.library.utils.LogUtil;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * 只用来查询地址
 */
public class FetchAddressIntentService extends IntentService{
    private RxManager rxManager;
    private PreferenceUtil prefUtil;
    private Geocoder geocoder;

    public FetchAddressIntentService() {
        super("FetchAddressIntentService");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        geocoder = new Geocoder(this, Locale.getDefault());
        prefUtil = new PreferenceUtil(this);
        rxManager = new RxManager();
        super.onStart(intent, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String errorMessage = "service not available";
        try {
            LogUtil.d("------------getDetailAddress-------------lat="+prefUtil.getLastLocationLAT() + ", lng="+prefUtil.getLastLocationLNG());
            List<Address> addresses = geocoder.getFromLocation(prefUtil.getLastLocationLAT(), prefUtil.getLastLocationLNG(), 1);
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                LogUtil.d("--------getDetailAddress-------success------------");
                RxBus.getInstance().post(Constant.RXBUS_GOT_LOCATION_SUCCEED, address);
                errorMessage = "";
            }
        } catch (IOException | IllegalArgumentException ioException) {
            ioException.printStackTrace();
        }
        if (!errorMessage.isEmpty()) {
            LogUtil.d("------getDetailAddress---------fail------------");
            rxManager.post(Constant.RXBUS_GOT_LOCATION_SUCCEED, null);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        rxManager.clear();
        LogUtil.d("------onDestroy------");
    }
}
