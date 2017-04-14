package com.aadhk.customer.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.Address;
import com.aadhk.customer.ui.fragment.LocationEditFragment;
import com.aadhk.customer.ui.fragment.LocationListFragment;
import com.aadhk.customer.util.Constant;
import com.aadhk.library.ui.BaseActivity;

/**
 * Created by jack on 08/12/2016.
 * 因为在下单处需要选择地址，所以将地址编辑放入一个Activity中
 */

public class LocationActivity extends BaseActivity {
    private LocationListFragment locationListFragment;
    private LocationEditFragment locationEditFragment;
    private short type;
    private Address address;
    private android.location.Address location;
    private double longitude, latitude, maxDistance;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.lay;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        this.type = getIntent().getShortExtra(Constant.BUNDLE_LOCATION_TYPE, Constant.LOCATION_TYPE_EDIT);
        this.location = getIntent().getParcelableExtra(Constant.BUNDLE_LOCATION_VALUE);
        this.longitude = getIntent().getDoubleExtra(Constant.BUNDLE_LOCATION_LONGITUDE, 0);
        this.latitude = getIntent().getDoubleExtra(Constant.BUNDLE_LOCATION_LATITUDE, 0);
        this.maxDistance = getIntent().getDoubleExtra(Constant.BUNDLE_LOCATION_MAX_DISTANCE, 0);
        goList();
    }

    public void goList() {
        addFragment(locationListFragment == null ? locationListFragment = new LocationListFragment() : locationListFragment);
    }

    public void goEdit(Address bean) {
        this.address = bean;
        locationEditFragment = new LocationEditFragment();
//        addFragment(locationEditFragment == null ? locationEditFragment = new LocationEditFragment() : locationEditFragment);
        addFragment(locationEditFragment);
    }

    public short getType() {
        return type;
    }

    public android.location.Address getLocation() {
        return location;
    }

    public Address getBean() {
        return address;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getMaxDistance() {
        return maxDistance;
    }
}
