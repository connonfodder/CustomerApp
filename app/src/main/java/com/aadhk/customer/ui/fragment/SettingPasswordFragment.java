package com.aadhk.customer.ui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.contract.UserPwdContract;
import com.aadhk.customer.data.model.UserPwdModel;
import com.aadhk.customer.data.presenter.UserPwdPreseneter;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.util.Encrypt;
import com.aadhk.customer.util.FormatUtil;
import com.aadhk.customer.util.PreferenceUtil;
import com.aadhk.library.ui.BaseFragment;
import com.aadhk.library.utils.ToastUtil;
import com.gc.materialdesign.views.ButtonRectangle;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by jack on 29/11/2016.
 */

public class SettingPasswordFragment extends BaseFragment<UserPwdPreseneter, UserPwdModel> implements UserPwdContract.View {

    @InjectView(R.id.tvTitle)
    TextView tvTitle;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.etCurrent)
    MaterialEditText etCurrent;
    @InjectView(R.id.etNew)
    MaterialEditText etNew;
    @InjectView(R.id.etConfirm)
    MaterialEditText etConfirm;
    @InjectView(R.id.btnUpdate)
    ButtonRectangle btnUpdate;

    private User user;
    private PreferenceUtil prefUtil;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_setting_password;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void updateResult() {
        user = CustomerApplication.getInstance().getUser();
        user.setPassword(newPwd);
        CustomerApplication.getInstance().setUser(user);
        ToastUtil.showLong("succeed change the password!");
        getActivity().onBackPressed();
    }

    @Override
    public void showLoading(String title) {}

    @Override
    public void stopLoading() {}

    @Override
    public void showErrorTip(String msg) {
        ToastUtil.showLong("Sorry, something wrong here, please repeat again!");
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
        prefUtil = new PreferenceUtil(getContext());
        etNew.addValidator(new RegexpValidator(getString(R.string.start_with_a_letter), "^[a-zA-Z]+.*$"))
                .addValidator(new RegexpValidator(getString(R.string.pwd_length_6_16), ".{6,16}$"))
                .addValidator(new RegexpValidator(getString(R.string.digit_must_contain), ".*[0-9]{1}.*"))
                .addValidator(new RegexpValidator(getString(R.string.no_whitespace), "[^\\\\s]*"));

        etCurrent.addValidator(new RegexpValidator(getString(R.string.start_with_a_letter), "^[a-zA-Z]+.*$"))
                .addValidator(new RegexpValidator(getString(R.string.pwd_length_6_16), ".{6,16}$"))
                .addValidator(new RegexpValidator(getString(R.string.digit_must_contain), ".*[0-9]{1}.*"))
                .addValidator(new RegexpValidator(getString(R.string.no_whitespace), "[^\\\\s]*"));
    }
    private String newPwd;
    private boolean validate() {
        if (isEmpty(etCurrent) || isEmpty(etNew) || isEmpty(etConfirm)) return false;
        if(!etCurrent.validate()) return false;
        if(!etNew.validate()) return false;
        String curr = Encrypt.md5(etCurrent.getText().toString());
        if(!curr.equals(prefUtil.getUser().getPassword()))  {
            etCurrent.setError(getString(R.string.errorPwd));
            return false;
        }
        newPwd = Encrypt.md5(etNew.getText().toString());
        String confirmPwd = Encrypt.md5(etConfirm.getText().toString());
        if (!FormatUtil.isEqual(newPwd, confirmPwd)) { //确保俩者相同
            etConfirm.setError(getString(R.string.errorUnsame));
            return false;
        }
        User u = CustomerApplication.getInstance().getUser();
        user = new User();
        user.setId(u.getId());
        user.setPassword(newPwd);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        etCurrent.setText("");
        etConfirm.setText("");
        etNew.setText("");
    }

    private boolean isEmpty(EditText et) {
        String str = et.getText().toString();
        if (str == null || TextUtils.isEmpty(str)) {
            et.setError(getString(R.string.errorEmpty));
            return true;
        }
        return false;
    }

    @OnClick(R.id.btnUpdate)
    public void onClick() {
        if(validate()){
            mPresenter.updatePwdRequest(user);
        }
    }
}
