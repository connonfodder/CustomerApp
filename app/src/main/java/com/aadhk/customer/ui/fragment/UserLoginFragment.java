package com.aadhk.customer.ui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.contract.UserLoginContract;
import com.aadhk.customer.data.model.UserLoginModel;
import com.aadhk.customer.data.presenter.UserLoginPresenter;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.ui.activity.UserActivity;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.Encrypt;
import com.aadhk.customer.util.FormatUtil;
import com.aadhk.customer.util.PreferenceUtil;
import com.aadhk.library.rx.RxBus;
import com.aadhk.library.ui.BaseFragment;
import com.aadhk.library.utils.ToastUtil;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonRectangle;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by jack on 28/11/2016.
 */

public class UserLoginFragment extends BaseFragment<UserLoginPresenter, UserLoginModel> implements UserLoginContract.View {
    @InjectView(R.id.tvSignup)
    TextView tvSignup;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.etEmail)
    MaterialEditText etEmail;
    @InjectView(R.id.etPwd)
    MaterialEditText etPwd;
    @InjectView(R.id.btnLogin)
    ButtonRectangle btnLogin;
    @InjectView(R.id.btnForgetPwd)
    ButtonFlat btnForgetPwd;

    private User user;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_user_login;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void vertifyUserResult(User user) {
        CustomerApplication.getInstance().setUser(user);
        RxBus.getInstance().post(Constant.RXBUS_FRESH_ORDER_LIST, null);
        getActivity().finish();
    }

    @Override
    public void showLoading(String title) {}

    @Override
    public void stopLoading() {}

    @Override
    public void showErrorTip(String msg) {
        ToastUtil.showLong("" + msg);
    }

    @Override
    protected void initView() {
        /*
            * (?=.*[0-16])      # a digit must occur at least once              包含一个数字
            * (?=.*[a-z])       # a lower case letter must occur at least once   至少包含一个小写字母
            * (?=.*[A-Z])       # an upper case letter must occur at least once  至少包含一个大写字母
            * (?=.*[@#$%^&+=])  # a special character must occur at least once   至少包含一个特殊字符
            * (?=\S+$)          # no whitespace allowed in the entire string     没有空格
            * .{8,}             # anything, at least eight places though         至少8个字符
            * $                 # end-of-string                                  以字母结尾
            * 需求: 密码验证
             * 1.以字母开头     		^[a-zA-Z]+.*$
             * 2.6-16个字符     		.{6,16}$
             * 3.必须包含一个数字    .*[0-9]{1}.*
             * 4.没有空格			[^\\s]*
        */
        etPwd.addValidator(new RegexpValidator(getString(R.string.start_with_a_letter), "^[a-zA-Z]+.*$"))
                .addValidator(new RegexpValidator(getString(R.string.pwd_length_6_16), ".{6,16}$"))
                .addValidator(new RegexpValidator(getString(R.string.digit_must_contain), ".*[0-9]{1}.*"))
                .addValidator(new RegexpValidator(getString(R.string.no_whitespace), "[^\\\\s]*"));
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        PreferenceUtil prefUtil = new PreferenceUtil(getContext());
        etEmail.setText(prefUtil.getUser()!=null ? prefUtil.getUser().getEmail() : "");
    }

    @OnClick({R.id.tvSignup, R.id.btnLogin, R.id.btnForgetPwd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSignup:
                ((UserActivity) getActivity()).goSignup();
                break;
            case R.id.btnLogin:
                if (validate())
                    mPresenter.vertifyUserRequest(user, true);
                break;
            case R.id.btnForgetPwd:
                ((UserActivity) getActivity()).goReset();
                break;
        }
    }

    private boolean validate() {
        String email = etEmail.getText().toString();
        String pwd = etPwd.getText().toString();
        if (TextUtils.isEmpty(email)) {
            etEmail.setError(getString(R.string.errorEmpty));
            return false;
        }
        if (TextUtils.isEmpty(pwd)) {
            etPwd.setError(getString(R.string.errorEmpty));
            return false;
        }
        if (FormatUtil.isEmail(email)) {
            etEmail.setError(getString(R.string.errorEmail));
            return false;
        }
        if (!etPwd.validate()) return false;
        user = new User();
        user.setEmail(email);
        user.setPassword(Encrypt.md5(pwd));
        return true;
    }
}
