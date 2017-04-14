package com.aadhk.customer.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.Company;
import com.aadhk.customer.bean.Order;
import com.aadhk.customer.bean.TabEntity;
import com.aadhk.customer.ui.fragment.FinishOrderFragment;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.PagerListener;
import com.aadhk.customer.util.PhoneUtil;
import com.aadhk.library.rx.RxBus;
import com.aadhk.library.ui.BaseActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gc.materialdesign.widgets.Dialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by jack on 07/12/2016.
 */

public class FinishOrderActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.iv)
    ImageView iv;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.layHeader)
    AppBarLayout layHeader;
    @InjectView(R.id.layTab)
    CommonTabLayout layTab;
    @InjectView(R.id.pagers)
    ViewPager pagers;

    private Order order;
    private Company company;
    private ArrayList<FinishOrderFragment> mFragmentsList = new ArrayList<>();
    private short[] typeValue = {Constant.TYPE_DELIVERY_VALUE, Constant.TYPE_TAKEOUT_VALUE, Constant.TYPE_DINEIN_VALUE};
    private int[] mTitleList = {R.string.lbDelivery, R.string.lbTakeout, R.string.lbDineIn};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_toolbar;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.inject(this);
        order = getIntent().getParcelableExtra(Constant.BUNDLE_FINISH_ORDER);
        company = getIntent().getParcelableExtra(Constant.BUNDLE_FINISH_COMPANY);
        tv.setText(order.getCompanyName());
        for (int i = 0; i < typeValue.length; i++) {
            if ((order.getOrderType() & typeValue[i]) == typeValue[i]) {
                mTabEntities.add(new TabEntity(getString(mTitleList[i]), 0, 0));
                mFragmentsList.add(FinishOrderFragment.newInstance(typeValue[i]));
            }
        }
        pagers.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        layTab.setTabData(mTabEntities);
        layTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                pagers.setCurrentItem(position);
                boolean isScan = mFragmentsList.get(position).getTypeValue() == Constant.TYPE_DINEIN_VALUE;
                iv.setImageResource(isScan ? R.mipmap.ic_qr_scanner : R.mipmap.ic_phone);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        pagers.addOnPageChangeListener(new PagerListener() {
            @Override
            public void onPageSelected(int position) {
                layTab.setCurrentTab(position);
                boolean isScan = mFragmentsList.get(position).getTypeValue() == Constant.TYPE_DINEIN_VALUE;
                iv.setImageResource(isScan ? R.mipmap.ic_qr_scanner : R.mipmap.ic_phone);
            }
        });
        if (!TextUtils.isEmpty(order.getTableName())) {
            for (int i = 0; i < mFragmentsList.size(); i++) {
                FinishOrderFragment fragment = mFragmentsList.get(i);
                Log.d("jack", "I:" + i);
                Log.d("jack", "getTypeValue:" + fragment.getTypeValue());
                if (fragment.getTypeValue() == Constant.TYPE_DINEIN_VALUE) {
                    pagers.setCurrentItem(i);
                    break;
                }
            }
        }
    }

    @OnClick(R.id.iv)
    public void onClick() {
        int position = pagers.getCurrentItem();
        boolean isScan = mFragmentsList.get(position).getTypeValue() == Constant.TYPE_DINEIN_VALUE;
        //分为扫描和打电话
//        ToastUtil.showShort(isScan ? getString(R.string.qr_scan) : getString(R.string.give_a_call));
        if (isScan) {  //扫描
            Intent intent = new Intent(FinishOrderActivity.this, ScannerActivity.class);
            startActivityForResult(intent, Constant.REQUEST_QR_CODE);
        } else {
            Dialog dialog = new Dialog(FinishOrderActivity.this, getString(R.string.contact_the_store), getString(R.string.phone_number) + order.getCompanyPhone());
            dialog.setOnAcceptButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    call();
                }
            });
            dialog.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.REQUEST_QR_CODE) {
            String result = intent.getStringExtra(Constant.BUNDLE_QR_CODE);
            if (!TextUtils.isEmpty(result))
                RxBus.getInstance().post(Constant.RXBUS_FINISH_ORDER_QR_SCAN, result);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        switch (requestCode) {
            case RC_PHONE_CALL:
                call();
                break;
            default:
                break;
        }
    }

    private final static int RC_PHONE_CALL = 1001;

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        switch (requestCode) {
            case RC_PHONE_CALL:
                String[] perms = {Manifest.permission.CALL_PHONE};
                if (EasyPermissions.hasPermissions(this, perms)) return;
                if (EasyPermissions.somePermissionPermanentlyDenied(this, list)) {
                    new AppSettingsDialog.Builder(this, getString(R.string.lbPermissionAskAgain))
                            .setTitle(getString(R.string.permission_phone_call_denied))
                            .setPositiveButton(getString(R.string.lbGetPermission))
                            .setNegativeButton(getString(R.string.cancel), null)
                            .setRequestCode(RC_PHONE_CALL)
                            .build()
                            .show();
                }
                break;
        }
    }

    private void call() {
        String[] perms = {Manifest.permission.CALL_PHONE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            PhoneUtil.call(this, order.getCompanyPhone());
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.permission_rationale_location), RC_PHONE_CALL, perms);
        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragmentsList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getString(mTitleList[position]);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentsList.get(position);
        }
    }

    public Order getOrder() {
        return order.clone();
    }

    public Company getCompany() {
        return company;
    }
}
