package com.aadhk.customer.ui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.AppSettings;
import com.aadhk.customer.bean.Order;
import com.aadhk.customer.data.contract.RefundListContract;
import com.aadhk.customer.data.model.RefundListModel;
import com.aadhk.customer.data.presenter.RefundListPresenter;
import com.aadhk.customer.ui.activity.AmountOperateActivity;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.FormatUtil;
import com.aadhk.library.rx.RxBus;
import com.aadhk.library.ui.BaseFragment;
import com.aadhk.library.utils.ToastUtil;
import com.gc.materialdesign.views.ButtonRectangle;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by jack on 29/11/2016.
 */

public class RefundListFragment extends BaseFragment<RefundListPresenter, RefundListModel> implements RefundListContract.View {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tvAmount)
    TextView tvAmount;
    @InjectView(R.id.etContent)
    EditText editContent;
    @InjectView(R.id.btnConfirm)
    ButtonRectangle btnConfirm;
    private AppSettings settings;
    private Order order;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_refund;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
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
        settings = CustomerApplication.getSetting();
        order = ((AmountOperateActivity)getActivity()).getOrder();
        tvAmount.setText(String.format(getString(R.string.refundAmount), FormatUtil.displayAmount(settings.getCurrencyPosition(), settings.getDecimalPlace(), order.getAmount(), settings.getCurrencyStr())));
    }

    @Override
    public void refundResult() {
        RxBus.getInstance().post(Constant.RXBUS_FRESH_ORDER_LIST, null);  //刷新
        getActivity().finish();
    }

    @Override
    public void showLoading(String title) {}

    @Override
    public void stopLoading() {}

    @Override
    public void showErrorTip(String msg) {
        ToastUtil.showLong(msg);
    }

    @OnClick(R.id.btnConfirm)
    public void onClick() {
        String reason = editContent.getText().toString().trim();
        reason = TextUtils.isEmpty(reason) ? "" : reason;
        mPresenter.refundRequest(order.getId(),  reason);
     }
}
