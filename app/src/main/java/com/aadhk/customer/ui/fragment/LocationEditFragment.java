package com.aadhk.customer.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.Address;
import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.contract.LocationEditContract;
import com.aadhk.customer.data.model.LocationEditModel;
import com.aadhk.customer.data.presenter.LocationEditPresenter;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.ui.activity.LocationActivity;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.FormatUtil;
import com.aadhk.library.ui.BaseFragment;
import com.aadhk.library.utils.ToastUtil;
import com.gc.materialdesign.views.ButtonRectangle;
import com.gc.materialdesign.widgets.Dialog;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.acra.ACRA;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by jack on 29/11/2016.
 */

public class LocationEditFragment extends BaseFragment<LocationEditPresenter, LocationEditModel> implements LocationEditContract.View {

    @InjectView(R.id.tvTitle)
    TextView tvTitle;
    @InjectView(R.id.btnDelete)
    ImageView btnDelete;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.etName)
    MaterialEditText etName;
    @InjectView(R.id.etPhone)
    MaterialEditText etPhone;
    @InjectView(R.id.etAddress)
    MaterialEditText etAddress;
    @InjectView(R.id.layAddress)
    RelativeLayout layAddress;
    @InjectView(R.id.etAddressLine1)
    MaterialEditText etAddressLine1;
    @InjectView(R.id.etZipCode)
    MaterialEditText etZipCode;
    @InjectView(R.id.etEmail)
    MaterialEditText etEmail;
    @InjectView(R.id.btnSave)
    ButtonRectangle btnSave;
    @InjectView(R.id.radioGroup)
    RadioGroup radioGroup;
    @InjectView(R.id.ivLocation)
    ImageView ivLocation;
    @InjectView(R.id.radio0)
    RadioButton radio0;
    @InjectView(R.id.radio1)
    RadioButton radio1;
    @InjectView(R.id.radio2)
    RadioButton radio2;
    @InjectView(R.id.radio3)
    RadioButton radio3;
    private Address bean;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_location_edit;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void saveAddressResult() {
        ToastUtil.showShort("save address success!");
        getActivity().onBackPressed();
    }

    @Override
    public void deleteAddressResult() {
        ToastUtil.showShort("delete address success!");
        getActivity().onBackPressed();
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
    }

    @Override
    protected void initView() {
        bean = ((LocationActivity) getActivity()).getBean();
        if (bean == null){
            User user = CustomerApplication.getInstance().getUser();
            bean = new Address(user.getId());
            bean.setUserName(user.getUserName());
            bean.setPhone(user.getTelephone());
            bean.setEmail(user.getEmail());
            tvTitle.setText(R.string.lbAddLocation);
        }
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        etName.setText(bean.getUserName());
        etPhone.setText(bean.getPhone());
        etAddress.setText(bean.getAddress());
        etAddressLine1.setText(bean.getAddressLine());
        etZipCode.setText(bean.getZipCode());
        etEmail.setText(bean.getEmail());
        btnDelete.setVisibility(bean.getId() <= 0 ? View.INVISIBLE : View.VISIBLE);
        RadioButton[] btns = new RadioButton[]{radio0, radio1, radio2, radio3};
        btns[bean.getLabel()].setChecked(true);
//        radioGroup.getChildAt(bean.getLabel()).setSelected(true);
    }

    @OnClick({R.id.btnDelete, R.id.ivLocation, R.id.radioGroup, R.id.btnSave})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDelete:
                Dialog dialog = new Dialog(getContext(), "delete this location", "are you sure?");
                dialog.setOnAcceptButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.deleteAddressRequest(bean.getId());  //删除这个地址
                    }
                });
                dialog.show();
                break;
            case R.id.ivLocation:
                //进入GOOGLE MAP PLACE PICKER
                try {
                    PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                    Intent intent = intentBuilder.build(getActivity());
                    startActivityForResult(intent, REQUEST_PLACE_PICKER);
                } catch (GooglePlayServicesRepairableException e) {
                    ToastUtil.showShort("Google Play Services need update");
//                    etAddress.setText("Test Address");
                    e.printStackTrace();
					ACRA.getErrorReporter().handleException(e);
                } catch (GooglePlayServicesNotAvailableException e) {
                    ToastUtil.showShort("Google Play Services NotAvailable");
//                    etAddress.setText("测试地址");
                    e.printStackTrace();
					ACRA.getErrorReporter().handleException(e); 
                }
                break;
            case R.id.btnSave:
                if (validata()) {
                    mPresenter.saveAddressRequest(bean);
                }
                break;
        }
    }

    private boolean validata() {
        if (isEmpty(etName) || isEmpty(etPhone) || isEmpty(etAddress) || isEmpty(etAddressLine1))
            return false;
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        String address = etAddress.getText().toString();
        String addressLine = etAddressLine1.getText().toString();
        String zipcode = etZipCode.getText().toString();
        String email = etEmail.getText().toString();
        bean.setLabel(Constant.LABEL_NO);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        switch (selectedId) {
            case R.id.radio1:
                bean.setLabel(Constant.LABEL_HOME);
                break;
            case R.id.radio2:
                bean.setLabel(Constant.LABEL_SCHOOL);
                break;
            case R.id.radio3:
                bean.setLabel(Constant.LABEL_COMPANY);
                break;
        }
        if (FormatUtil.isEmail(email)) {
            etEmail.setError(getString(R.string.errorEmail));
            return false;
        }
        bean.setUserName(name);
        bean.setPhone(phone);
        bean.setAddress(address);
        bean.setAddressLine(addressLine);
        bean.setZipCode(zipcode);
        bean.setEmail(email);
        return true;
    }

    private final static int REQUEST_PLACE_PICKER = 3;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PLACE_PICKER && resultCode == Activity.RESULT_OK) {
            // The user has selected a place. Extract the name and address.
            final Place place = PlacePicker.getPlace(data, getContext());
            final CharSequence name = place.getName();
            final CharSequence address = place.getAddress();
            String attributions = PlacePicker.getAttributions(data);
            if (attributions == null) attributions = "";
//            etAddress.setText( "name=" + name + ", address="+address + ", html=" +  Html.fromHtml(attributions));
            etAddress.setText(address);
            //TODO: 地图编辑
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private boolean isEmpty(EditText et) {
        String str = et.getText().toString();
        if (str == null || TextUtils.isEmpty(str)) {
            et.setError(getString(R.string.errorEmpty));
            return true;
        }
        return false;
    }
}
