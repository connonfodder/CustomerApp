package com.aadhk.customer.ui.fragment;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.User;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.ui.activity.SettingActivity;
import com.aadhk.customer.ui.activity.UserActivity;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.PreferenceUtil;
import com.aadhk.library.rx.RxBus;
import com.aadhk.library.ui.BaseFragment;
import com.aadhk.library.utils.ToastUtil;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by jack on 28/11/2016.
 */

public class UserCategoryFragment extends BaseFragment {

    @InjectView(R.id.tvUserName)
    TextView tvUserName;
    @InjectView(R.id.tvSign)
    TextView tvSign;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.ivMasterPass)
    ImageView ivMasterPass;
    @InjectView(R.id.layAccount)
    LinearLayout layAccount;
    @InjectView(R.id.layHelp)
    LinearLayout layHelp;
    @InjectView(R.id.layRate)
    LinearLayout layRate;
    @InjectView(R.id.layContact)
    LinearLayout layContact;
    @InjectView(R.id.tvMode)
    TextView tvMode;
    private short flag ;
    PreferenceUtil preferenceUtil;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_user_category;
    }

    @Override
    public void initPresenter() {}

    @Override
    protected void initView() {
        preferenceUtil = new PreferenceUtil(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        initUseInfo();
    }

    private void initUseInfo(){
        flag = Constant.FLAG_UNLOGIN;
        User user = CustomerApplication.getInstance().getUser();
        if (user != null) {
            tvUserName.setText(user.getUserName());
            tvSign.setText(getString(R.string.lbLogout));
            flag |= Constant.FLAG_LOGIN_NO_CONNECT_MASTERPASS;
            if (TextUtils.isEmpty(user.getMasterAccount())) {
//                ivMasterPass.setVisibility(View.VISIBLE);
            } else {
                flag |= Constant.FLAG_LOGIN_COMPLETED;
            }
            layAccount.setVisibility(View.VISIBLE);
        } else {
            tvUserName.setText("");
            tvSign.setText(getString(R.string.lbLoginIn));
            layAccount.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.tvSign, R.id.layAccount, R.id.layHelp, R.id.layRate, R.id.layContact})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSign: //登陆或者退出
                if (flag == Constant.FLAG_UNLOGIN) {  //登陆
                    Intent intent = new Intent(getActivity(), UserActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getActivity().startActivity(intent);
                } else {    //退出
                    CustomerApplication.getInstance().setUser(null);
                    onResume();
                    RxBus.getInstance().post(Constant.RXBUS_FRESH_ORDER_LIST, null);
//                   ActivityManager.getInstance().AppExit(getContext());
                }
                break;
            case R.id.layAccount:
                if(CustomerApplication.getInstance().getUser()!=null){
                    //进入账户信息页面
                    Intent intent = new Intent(getActivity(), SettingActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getActivity().startActivity(intent);
                }/*else{
                    Intent intent = new Intent(getActivity(), UserActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getActivity().startActivity(intent);
                }*/
                break;
            case R.id.layHelp:  //TODO: 进入帮助页面
                ToastUtil.showLong("I'm trying");
                break;
            case R.id.layRate: //TODO: 进入评价页面
                ToastUtil.showLong("I'm trying");
                break;
            case R.id.layContact:   //TODO: 进入联系页面
                ToastUtil.showLong("I'm trying");
               /* preferenceUtil.setDevelopeMode(!preferenceUtil.getDevelopeMode());
                Intent i = getActivity().getPackageManager().getLaunchIntentForPackage(getContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);*/
                break;
        }
    }
}
