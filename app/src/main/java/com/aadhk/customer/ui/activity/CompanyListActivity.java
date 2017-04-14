package com.aadhk.customer.ui.activity;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.TabEntity;
import com.aadhk.customer.ui.fragment.CompanyListFragment;
import com.aadhk.customer.ui.fragment.OrderListFragment;
import com.aadhk.customer.ui.fragment.UserCategoryFragment;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.FetchAddressIntentService;
import com.aadhk.customer.util.PreferenceUtil;
import com.aadhk.customer.util.location.LocationTracker;
import com.aadhk.customer.util.location.TrackerSettings;
import com.aadhk.library.rx.RxBus;
import com.aadhk.library.ui.BaseActivity;
import com.aadhk.library.utils.LogUtil;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gc.materialdesign.widgets.Dialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.aadhk.library.ui.BaseApplication.getAppContext;
import static com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.L;

/**
 * Created by jack on 24/11/2016.
 */

public class CompanyListActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    @InjectView(R.id.tab_layout)
    CommonTabLayout tabLayout;

    private Integer[] mTitles = {R.string.lbRestaurants, R.string.lbOrders, R.string.lbUser};
    private int[] mIconUnselectIds = {R.mipmap.ic_restaurant, R.mipmap.ic_order, R.mipmap.ic_user};
    private int[] mIconSelectIds = {R.mipmap.ic_restaurant_selected, R.mipmap.ic_order_selected, R.mipmap.ic_user_selected};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private CompanyListFragment companyListFragment;
    private OrderListFragment orderFragment;
    private UserCategoryFragment userFragment;
    private static final int RC_LOCATION_LAST_LOCATION_V1 = 1101;
    private static final int RC_LOCATION_LAST_LOCATION_V2 = 1102;
    private LocationTracker tracker;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private PreferenceUtil prefUtil;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_restaurant_list;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.lay;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.inject(this);
        initFragment(savedInstanceState);
        tabLayout.measure(0, 0);
        initTab();
        prefUtil = new PreferenceUtil(this);
        mRxManager.on(Constant.RXBUS_REQUEST_LOCATION, new Action1<Object>() {
            @Override
            public void call(Object o) {
                initAddress();
            }
        });

        mRxManager.on(Constant.RXBUS_LOGIN_ACTION, new Action1<Object>() {
           @Override
            public void call(Object o) {
                Intent intent = new Intent(getAppContext(), UserActivity.class);
                startActivity(intent, null);
            }
        });
    }

    //初始化tab
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(getString(mTitles[i]), mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    //初始化碎片
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currPos = 0;
        if (savedInstanceState != null) {
            companyListFragment = (CompanyListFragment) getSupportFragmentManager().findFragmentByTag("companyListFragment");
            orderFragment = (OrderListFragment) getSupportFragmentManager().findFragmentByTag("orderFragment");
            userFragment = (UserCategoryFragment) getSupportFragmentManager().findFragmentByTag("userFragment");
            currPos = savedInstanceState.getInt(Constant.HOME_CURRENT_TAB_POSITION);
        } else {
            companyListFragment = new CompanyListFragment();
            orderFragment = new OrderListFragment();
            userFragment = new UserCategoryFragment();
            transaction.add(getFragmentContentId(), companyListFragment, "companyListFragment");
            transaction.add(getFragmentContentId(), orderFragment, "orderFragment");
            transaction.add(getFragmentContentId(), userFragment, "userFragment");
        }
        transaction.commit();
        SwitchTo(currPos);
    }

    // 切换
    private void SwitchTo(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0: //餐厅
                transaction.show(companyListFragment);
                transaction.hide(orderFragment);
                transaction.hide(userFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 1: //订单
                transaction.hide(companyListFragment);
                transaction.show(orderFragment);
                transaction.hide(userFragment);
                transaction.commitAllowingStateLoss();
                RxBus.getInstance().post(Constant.RXBUS_FRESH_ORDER_LIST, null);
                break;
            case 2:  //用户
                transaction.hide(companyListFragment);
                transaction.hide(orderFragment);
                transaction.show(userFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //奔溃前保存位置
        if (tabLayout != null) {
            outState.putInt(Constant.HOME_CURRENT_TAB_POSITION, tabLayout.getCurrentTab());
        }
    }

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, CompanyListActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        switch (requestCode) {
            case RC_LOCATION_LAST_LOCATION_V1:
                locationV1(false);
                break;
            case RC_LOCATION_LAST_LOCATION_V2:
                locationV2();
            default:
                break;
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        switch (requestCode) {
            case RC_LOCATION_LAST_LOCATION_V1:
                String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
                if (EasyPermissions.hasPermissions(this, perms)) return;
                if (EasyPermissions.somePermissionPermanentlyDenied(this, list)) {
                    new AppSettingsDialog.Builder(this, getString(R.string.lbPermissionAskAgain))
                            .setTitle(getString(R.string.permission_retionale_location_denied))
                            .setPositiveButton(getString(R.string.lbGetPermission))
                            .setNegativeButton(getString(R.string.cancel), null  /** click listener */)
                            .setRequestCode(RC_LOCATION_LAST_LOCATION_V1)
                            .build()
                            .show();
                }
                break;
            case RC_LOCATION_LAST_LOCATION_V2:
                String[] perms2 = {Manifest.permission.ACCESS_FINE_LOCATION};
                if (EasyPermissions.hasPermissions(this, perms2)) return;
                if (EasyPermissions.somePermissionPermanentlyDenied(this, list)) {
                    new AppSettingsDialog.Builder(this, getString(R.string.lbPermissionAskAgain))
                            .setTitle(getString(R.string.permission_retionale_location_denied))
                            .setPositiveButton(getString(R.string.lbGetPermission))
                            .setNegativeButton(getString(R.string.cancel), null  /** click listener */)
                            .setRequestCode(RC_LOCATION_LAST_LOCATION_V2)
                            .build()
                            .show();
                }
                break;
        }
    }

    // hasPermissions: API version < M, returning true by pic_default   API>=21才会有权限提示
    private void initAddress() {
        locationV2();
    }

    @AfterPermissionGranted(RC_LOCATION_LAST_LOCATION_V1)
    private void locationV1(boolean isOnlyGps) {
        if (tracker != null && tracker.isListening()) return;
//        LogUtil.d("----------locationV1-----------------");
        String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {
            TrackerSettings settings = new TrackerSettings()
                    .setUseGPS(true)
                    .setUseNetwork(!isOnlyGps)
                    .setUsePassive(!isOnlyGps)
                    .setTimeBetweenUpdates(5 * 1000)  //5s更新一次
                    .setTimeout(30 * 1000)          //45s超时停止搜索位置
                    .setMetersBetweenUpdates(10);   //10米内变化
            if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            }
            tracker = new LocationTracker(getBaseContext(), settings) {
                @Override
                public void onLocationFound(@NonNull Location location) {
//                    LogUtil.d("------onLocationFound------" + location.toString());
                    mLastLocation = location;
                    fetchDetailAddress(location);
                }

                @Override
                public void onTimeout() {
                    tracker.stopListening();      //TODO  超时没有获取到
                    mRxManager.post(Constant.RXBUS_GOT_LOCATION_SUCCEED, null);
                }
            };
            //如果高版本不能通过GoogleApiClient获取地址的话那么wifi也没用,这时候必须开启GPS
            if ((Integer.valueOf(android.os.Build.VERSION.SDK) >= 21 && !tracker.isEnableGPS()) || tracker.disableV1()) {
                showSettingsAlert();
            }
//            LogUtil.d("------startListening------");
            tracker.startListening();
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.permission_rationale_location), RC_LOCATION_LAST_LOCATION_V1, perms);
        }
    }


    @AfterPermissionGranted(RC_LOCATION_LAST_LOCATION_V2)
    private void locationV2() {
//        LogUtil.d("----------locationV2-----------------");
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();

            mGoogleApiClient.connect();
            //预防连接超时
            Observable.timer(10, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                            if (mGoogleApiClient.isConnecting() && mLastLocation == null) {
//                                LogUtil.d("----stop google client---and start for LocationV1()---------");
                                locationV1(false);
                                mGoogleApiClient.disconnect();
                            }
                        }
                    });
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.permission_rationale_location), RC_LOCATION_LAST_LOCATION_V2, perms);
        }
    }

    private void fetchDetailAddress(Location location) {
        prefUtil.saveLastLocation(location.getLongitude(), location.getLatitude());
        Intent intent = new Intent(CompanyListActivity.this, FetchAddressIntentService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected())
            mGoogleApiClient.disconnect();
        if (tracker != null) tracker.stopListening();
        super.onDestroy();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        LogUtil.d("------Google Service onConnected------" + mLastLocation.toString());
        if (mLastLocation != null) {
            fetchDetailAddress(mLastLocation);
        } else {
            LogUtil.d("------Google Service location is null------");
            if (mLastLocation == null)
                locationV1(false);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        LogUtil.d("------Google Service onConnectionFailed------");
        if (mLastLocation == null)
            locationV1(false);
    }

    public void showSettingsAlert() {
        final Dialog dialog = new Dialog(this, getString(R.string.lbGPSSettings), getString(R.string.hintEnableGPS));
        dialog.setOnAcceptButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(intent, Constant.REQUEST_GPS);
            }
        });
        dialog.setOnCancelButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.REQUEST_GPS) {
            initAddress();
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private long exitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Snackbar.make(tabLayout, "press one more time to exit", Snackbar.LENGTH_LONG).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
