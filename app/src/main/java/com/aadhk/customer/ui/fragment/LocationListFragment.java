package com.aadhk.customer.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.Address;
import com.aadhk.customer.data.contract.LocationListContract;
import com.aadhk.customer.data.model.LocationListModel;
import com.aadhk.customer.data.presenter.LocationListPresenter;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.ui.activity.LocationActivity;
import com.aadhk.customer.ui.adapter.LocationListAdapter;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.FormatUtil;
import com.aadhk.library.ui.BaseFragment;
import com.aadhk.library.utils.ToastUtil;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import org.acra.ACRA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by jack on 29/11/2016.
 */

public class LocationListFragment extends BaseFragment<LocationListPresenter, LocationListModel> implements LocationListContract.View {

    @InjectView(R.id.tvTitle)
    TextView tvTitle;
    @InjectView(R.id.btnAdd)
    ImageView btnAdd;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.rlv)
    EasyRecyclerView rlv;
    @InjectView(R.id.tvCurrM)
    TextView tvCurrM;
    @InjectView(R.id.tvAddress)
    TextView tvAddress;
    @InjectView(R.id.layPicker)
    RelativeLayout layPicker;
    @InjectView(R.id.layHeader)
    LinearLayout layHeader;
    @InjectView(R.id.ivPicker)
    ImageView ivPicker;
    @InjectView(R.id.tvDeliveryAddressM)
    View tvDeliveryAddressM;

    private List<Address> data;
    private LocationListAdapter mAdapter;
    private android.location.Address location;
    private static double longitude, latitude, maxDistance;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_list;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void onResume() {
        super.onResume();
        longitude = ((LocationActivity) getActivity()).getLongitude();
        latitude = ((LocationActivity) getActivity()).getLatitude();
        maxDistance = ((LocationActivity) getActivity()).getMaxDistance();
        if (longitude != 0 && latitude != 0) {
            mAdapter.disableUnServiceAddress(longitude, latitude, maxDistance);
        } else {
            mAdapter.disableUnServiceAddress(0, 0, 0);
        }
        if(CustomerApplication.getInstance().getUser() != null)
            mPresenter.fetchAllLocationRequest();
    }

    @Override
    public void fetchAllLocationResult(final List<Address> result) {
        mAdapter.clear();
        if (result == null && result.size() == 0) {
            mAdapter.stopMore();
            tvDeliveryAddressM.setVisibility(View.GONE);
            return;
        }
        if (longitude != 0 && latitude != 0)
            Collections.sort(result, new AddressDistance());
        data.addAll(result);
        mAdapter.addAll(result);
    }

    public static class AddressDistance implements Comparator<Address> {

        @Override
        public int compare(Address o1, Address o2) {
            double d1 = Math.pow((o1.getLongitude() - longitude), 2) + Math.pow((o1.getLatitude() - latitude), 2);
            double d2 = Math.pow((o2.getLongitude() - longitude), 2) + Math.pow((o2.getLatitude() - latitude), 2);
            return (int) (d1 - d2);
        }
    }

    @Override
    public void showLoading(String title) {
    }

    @Override
    public void stopLoading() {
    }

    @Override
    public void showErrorTip(String msg) {
        ToastUtil.showLong(msg);
        tvDeliveryAddressM.setVisibility(View.GONE);
    }

    @Override
    protected void initView() {
        toolbar.setTitle(R.string.lbLocationList);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        data = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), GridLayoutManager.VERTICAL);
        rlv.setLayoutManager(layoutManager);
        mAdapter = new LocationListAdapter(getContext());
        rlv.setAdapter(mAdapter);
        mAdapter.setOnMyItemClickListener(new LocationListAdapter.OnMyItemClickListener() {
            @Override
            public void onItemClick(int position, BaseViewHolder holder) {
                Address bean = data.get(position);
                int type = ((LocationActivity) getActivity()).getType();
                if (type == Constant.LOCATION_TYPE_PICKER || type == Constant.LOCATION_TYPE_LOCATION) {
                    Intent intent = new Intent();
                    intent.putExtra(Constant.BUNDLE_LOCATION, bean);
                    getActivity().setResult(Activity.RESULT_OK, intent);
                    getActivity().finish();
                } else {
                    ((LocationActivity) getActivity()).goEdit(bean);
                }
            }
        });
        if (((LocationActivity) getActivity()).getType() == Constant.LOCATION_TYPE_LOCATION) {
            layHeader.setVisibility(View.VISIBLE);
            location = ((LocationActivity) getActivity()).getLocation();
            String re = "";
            if (location != null) {
                re = FormatUtil.getNoEmpty(location.getAddressLine(1), location.getAddressLine(2), location.getFeatureName(), location.getThoroughfare(), location.getAddressLine(0));
            }
            re = TextUtils.isEmpty(re) ? "location service error" : re;
            tvAddress.setText(re);
            if (CustomerApplication.getInstance().getUser() == null) {
                btnAdd.setVisibility(View.GONE);
                tvDeliveryAddressM.setVisibility(View.GONE);
            }
        }
    }

    private final static int REQUEST_PLACE_PICKER = 3;

    @OnClick({R.id.btnAdd, R.id.ivPicker})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                ((LocationActivity) getActivity()).goEdit(null);
                break;
            case R.id.ivPicker:  //进入GOOGLE MAP PLACE PICKER
                try {
                    PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                    Intent intent = intentBuilder.build(getActivity());
                    startActivityForResult(intent, REQUEST_PLACE_PICKER);
                } catch (GooglePlayServicesRepairableException e) {
                    ToastUtil.showShort("Google Play Services need update");
                    e.printStackTrace();
					ACRA.getErrorReporter().handleException(e);
                } catch (GooglePlayServicesNotAvailableException e) {
                    ToastUtil.showShort("Google Play Services NotAvailable");
                    e.printStackTrace();
					ACRA.getErrorReporter().handleException(e); 
                }
                break;
        }
    }

    /**
     * place=PlaceEntity{id=ChIJs6UO6mHxAzQRESZKuYwTrc0,
     * placeTypes=[86, 1013, 34], locale=null,
     * name=皇冠体育中心,
     * address=中国广东省深圳市福田区竹子林立交,
     * phoneNumber=,
     * latlng=lat/lng: (22.532362,114.020527), viewport=null, websiteUri=null, isPermanentlyClosed=false,
     * priceLevel=-1}
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PLACE_PICKER && resultCode == Activity.RESULT_OK) {
            // The user has selected a place. Extract the name and address.
            final Place place = PlacePicker.getPlace(data, getContext());
//            Log.d("jack", "-------place="+place);
            location.setLongitude(place.getLatLng().longitude);
            location.setLatitude(place.getLatLng().latitude);
//            location.setFeatureName(place.getAddress() +  " " + place.getName());
            location.setFeatureName("" + place.getName());
            location.setAddressLine(0, String.valueOf(place.getAddress().charAt(0)));
            location.setAddressLine(1, String.valueOf(place.getAddress().charAt(1)));
            Intent intent = new Intent();
            intent.putExtra(Constant.BUNDLE_LOCATION, location);
            getActivity().setResult(Activity.RESULT_OK, intent);
            getActivity().finish();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
