package com.aadhk.customer.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.AppSettings;
import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.contract.UserLoginContract;
import com.aadhk.customer.data.model.UserLoginModel;
import com.aadhk.customer.data.presenter.UserLoginPresenter;
import com.aadhk.customer.ui.activity.AmountOperateActivity;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.ui.activity.SettingActivity;
import com.aadhk.customer.ui.activity.UserActivity;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.FormatUtil;
import com.aadhk.library.ui.BaseFragment;
import com.aadhk.library.utils.ToastUtil;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by jack on 29/11/2016.
 */

public class SettingCategoryFragment extends BaseFragment<UserLoginPresenter, UserLoginModel> implements UserLoginContract.View {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.layBalance)
    LinearLayout layBalance;
    @InjectView(R.id.layAddress)
    LinearLayout layAddress;
    @InjectView(R.id.layPwd)
    LinearLayout layPwd;
    @InjectView(R.id.layMasterPass)
    LinearLayout layMasterPass;
    @InjectView(R.id.layLocation)
    LinearLayout layLocation;
    @InjectView(R.id.tvBalance)
    TextView tvBalance;
    private User user;
    private AppSettings settings;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_setting_category;
    }

    @Override
    public void initPresenter() { mPresenter.setVM(this, mModel);}

    @Override
    protected void initView() {
        settings = CustomerApplication.getSetting();
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        user = CustomerApplication.getInstance().getUser();
        if(user!=null && user.getAccountBalance()>0)
            tvBalance.setText(FormatUtil.displayAmount(settings.getCurrencyPosition(), settings.getDecimalPlace(), user.getAccountBalance(), settings.getCurrencyStr()));
    }

    @Override
    public void onResume() {
        mPresenter.vertifyUserRequest(user, false);
        super.onResume();
    }


    @Override
    public void vertifyUserResult(User user) {
        CustomerApplication.getInstance().setUser(user);
        initView();
    }

    @Override
    public void showLoading(String title) {
    }

    @Override
    public void stopLoading() {
    }

    @Override
    public void showErrorTip(String msg) {
        // 清除记录并重新登陆
        CustomerApplication.getInstance().setUser(null);
        Intent intent = new Intent(getActivity(), UserActivity.class);
        getActivity().startActivityForResult(intent, Constant.REQUEST_LOGIN);
    }

    @OnClick({R.id.layBalance, R.id.layAddress, R.id.layPwd, R.id.layMasterPass, R.id.layLocation })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layBalance:
                startActivity(AmountOperateActivity.class);  //提现操作
                break;
            case R.id.layAddress:
                ((SettingActivity)getActivity()).goDetail();
                break;
            case R.id.layPwd:
                ((SettingActivity)getActivity()).goPassword();
                break;
            case R.id.layMasterPass:  //TODO: 连接MasterPass账号
                ToastUtil.showLong("I'm trying");
                break;
            case R.id.layLocation:
                ((SettingActivity)getActivity()).goLocations();
                break;
        }
    }
}
