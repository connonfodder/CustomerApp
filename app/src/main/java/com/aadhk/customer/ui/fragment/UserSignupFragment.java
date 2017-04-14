package com.aadhk.customer.ui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.contract.UserSignupContract;
import com.aadhk.customer.data.model.UserSignupModel;
import com.aadhk.customer.data.presenter.UserSignupPresenter;
import com.aadhk.customer.ui.activity.UserActivity;
import com.aadhk.customer.util.Encrypt;
import com.aadhk.customer.util.FormatUtil;
import com.aadhk.library.ui.BaseFragment;
import com.aadhk.library.utils.ToastUtil;
import com.gc.materialdesign.views.ButtonRectangle;
import com.gc.materialdesign.widgets.Dialog;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by jack on 28/11/2016.
 */

public class UserSignupFragment extends BaseFragment<UserSignupPresenter, UserSignupModel> implements UserSignupContract.View {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tvLogin)
    TextView tvLogin;
    @InjectView(R.id.etEmail)
    MaterialEditText etEmail;
    @InjectView(R.id.etUserName)
    MaterialEditText etUserName;
    @InjectView(R.id.etPhone)
    MaterialEditText etPhone;
    @InjectView(R.id.etPwd1)
    MaterialEditText etPwd1;
    @InjectView(R.id.etPwd2)
    MaterialEditText etPwd2;
    @InjectView(R.id.btnSignup)
    ButtonRectangle btnSignup;

    private User user;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_user_signup;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void signupResult() {
        Dialog dialog = new Dialog(getContext(), getString(R.string.last_step), getString(R.string.check_email));
        dialog.setOnAcceptButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        dialog.show();
    }

    /*@Override
    public void loginResult(User user) {
        CustomerApplication.getInstance().setUser(user);
        getActivity().finish();
    }*/

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
                getActivity().finish();
            }
        });
        etPwd1.addValidator(new RegexpValidator(getString(R.string.start_with_a_letter), "^[a-zA-Z]+.*$"))
                .addValidator(new RegexpValidator(getString(R.string.pwd_length_6_16), ".{6,16}$"))
                .addValidator(new RegexpValidator(getString(R.string.digit_must_contain), ".*[0-9]{1}.*"))
                .addValidator(new RegexpValidator(getString(R.string.no_whitespace), "[^\\\\s]*"));

        etPwd2.addValidator(new RegexpValidator(getString(R.string.start_with_a_letter), "^[a-zA-Z]+.*$"))
                .addValidator(new RegexpValidator(getString(R.string.pwd_length_6_16), ".{6,16}$"))
                .addValidator(new RegexpValidator(getString(R.string.digit_must_contain), ".*[0-9]{1}.*"))
                .addValidator(new RegexpValidator(getString(R.string.no_whitespace), "[^\\\\s]*"));
    }

    @OnClick({R.id.tvLogin, R.id.btnSignup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvLogin:
                ((UserActivity) getActivity()).goLogoin();
                break;
            case R.id.btnSignup:
                if (validate())
                    mPresenter.signupRequest(user);
                break;
        }
    }

    private boolean validate() {
        if (isEmpty(etEmail)) return false;
        if (isEmpty(etUserName)) return false;
        if (isEmpty(etPhone)) return false;
        if (isEmpty(etPwd1)) return false;
        if (isEmpty(etPwd2)) return false;

        String email = etEmail.getText().toString();
        String userName = etUserName.getText().toString();
        String phone = etPhone.getText().toString();
        String pwd1 = etPwd1.getText().toString();
        String pwd2 = etPwd2.getText().toString();
        if (FormatUtil.isEmail(email)) {
            etEmail.setError(getString(R.string.lbEmailFormatterError));
            return false;
        }

        if (!etPwd1.validate()) return false;
        if (!etPwd2.validate()) return false;

        if (!pwd1.equals(pwd2)) {
            etPwd2.setError(getString(R.string.errorUnsame));
            return false;
        }
        user = new User();
        user.setEmail(email);
        user.setUserName(userName);
        user.setTelephone(phone);
        user.setPassword(Encrypt.md5(pwd1));
        return true;
    }

    private boolean isEmpty(MaterialEditText et) {
        String re = et.getText().toString();
        if (TextUtils.isEmpty(re)) {
            et.setError(getString(R.string.errorEmpty));
            return true;
        }
        return false;
    }
}
