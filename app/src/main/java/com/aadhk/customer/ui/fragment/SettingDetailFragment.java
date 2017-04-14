package com.aadhk.customer.ui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.contract.UserInfoContract;
import com.aadhk.customer.data.model.UserInfoModel;
import com.aadhk.customer.data.presenter.UserInfoPersenter;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.library.ui.BaseFragment;
import com.aadhk.library.utils.ToastUtil;
import com.gc.materialdesign.views.ButtonRectangle;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by jack on 29/11/2016.
 */

public class SettingDetailFragment extends BaseFragment<UserInfoPersenter, UserInfoModel> implements UserInfoContract.View {

    @InjectView(R.id.tvTitle)
    TextView tvTitle;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.etName)
    MaterialEditText etName;
    @InjectView(R.id.etPhone)
    MaterialEditText etPhone;
    @InjectView(R.id.etAddress1)
    MaterialEditText etAddress1;
    @InjectView(R.id.etAddress2)
    MaterialEditText etAddress2;
    @InjectView(R.id.etAddress3)
    MaterialEditText etAddress3;
    @InjectView(R.id.etEmail)
    MaterialEditText etEmail;
    @InjectView(R.id.btnSave)
    ButtonRectangle btnSave;
    private User user;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_setting_details;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void updateInfoResult() {
        CustomerApplication.getInstance().setUser(user);
        ToastUtil.showLong("success update info");
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
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        user = CustomerApplication.getInstance().getUser();
        etName.setText(user.getUserName());
        etPhone.setText(user.getTelephone());
        etAddress1.setText(user.getContactAddress1());
        etAddress2.setText(user.getContactAddress2());
        etAddress3.setText(user.getContactAddress3());
        etEmail.setText(user.getEmail());
    }

    @OnClick(R.id.btnSave)
    public void onClick() {
        if(validate()){
            mPresenter.updateInfoRequest(user);
        }
    }

    private boolean validate() {
        if(isEmpty(etName) || isEmpty(etPhone) || isEmpty(etAddress1) || isEmpty(etEmail)) return false;
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        String address1 = etAddress1.getText().toString();
        String address2 = etAddress2.getText().toString();
        String address3 = etAddress3.getText().toString();
        user.setUserName(name);
        user.setTelephone(phone);
        user.setContactAddress1(address1);
        user.setContactAddress2(address2);
        user.setContactAddress3(address3);
        return true;
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
