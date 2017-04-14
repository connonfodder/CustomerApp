package com.aadhk.customer.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.aadhk.customer.BuildConfig;
import com.aadhk.customer.R;
import com.aadhk.customer.bean.AppSettings;
import com.aadhk.customer.bean.Order;
import com.aadhk.customer.bean.OrderItem;
import com.aadhk.customer.bean.OrderPayment;
import com.aadhk.customer.bean.mcpayment.MasterPassItem;
import com.aadhk.customer.bean.mcpayment.PaymentData;
import com.aadhk.customer.data.contract.PaymentContract;
import com.aadhk.customer.data.model.PaymentModel;
import com.aadhk.customer.data.presenter.PaymentPresenter;
import com.aadhk.customer.ui.activity.CompanyListActivity;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.ui.activity.MCPaymentActivity;
import com.aadhk.customer.ui.activity.PaymentActivity;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.FormatUtil;
import com.aadhk.customer.util.money.MoneyUtil;
import com.aadhk.library.rx.RxBus;
import com.aadhk.library.ui.BaseFragment;
import com.aadhk.library.utils.ImageLoaderUtils;
import com.aadhk.library.utils.ToastUtil;
import com.gc.materialdesign.views.ButtonRectangle;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by jack on 15/12/2016.
 */

public class PaymentFragment extends BaseFragment<PaymentPresenter, PaymentModel> implements PaymentContract.View {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.ivLogo)
    ImageView ivLogo;
    @InjectView(R.id.tvCompanyName)
    TextView tvCompanyName;
    @InjectView(R.id.tvOrderNumber)
    TextView tvOrderNumber;
    @InjectView(R.id.tvTotalAmount)
    TextView tvTotalAmount;
    @InjectView(R.id.radio)
    AppCompatRadioButton radio;
    @InjectView(R.id.btnConfirm)
    ButtonRectangle btnConfirm;

    private AppSettings settings;
    private Order order;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_payment;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        settings = CustomerApplication.getSetting();
        order = ((PaymentActivity) getActivity()).getOrder();
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        ImageLoaderUtils.display(getContext(), ivLogo, Constant.URL +  order.getLogoPath());
        tvCompanyName.setText(order.getCompanyName());
        tvOrderNumber.setText(order.getOrderNum());
        tvTotalAmount.setText(FormatUtil.displayAmount(settings.getCurrencyPosition(), settings.getDecimalPlace(), order.getAmount(), settings.getCurrencyStr()));
        radio.setChecked(true);
        btnConfirm.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//        btnConfirm.setVisibility(View.INVISIBLE);
        radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btnConfirm.setBackgroundColor(getResources().getColor(isChecked? R.color.colorPrimary : R.color.secondartText));
//                    if(isChecked)   btnConfirm.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void payResult() {
//        ToastUtil.showLong("you have succeed pay for the order");
        Log.e("jack", "payResult: " + "you have succeed pay for the order");
        startActivity(CompanyListActivity.class, null);
        RxBus.getInstance().post(Constant.RXBUS_FRESH_ORDER_LIST, null);
    }

    @Override
    public void onDestroy() {
        RxBus.getInstance().post(Constant.RXBUS_FRESH_ORDER_LIST, null);
        super.onDestroy();
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

    @OnClick(R.id.btnConfirm)
    public void onClick() {
        if(radio.isChecked()){
            if(!BuildConfig.DEBUG){
                PaymentData data = generateMCPaymentData(order);          //TODO: 可能有什么潜在性的错误吗
                CustomerApplication.getInstance().setPaymentData(data);   //T
                Intent intent = new Intent(getActivity(), MCPaymentActivity.class);
                startActivityForResult(intent, Constant.REQUEST_MC_PAYMENT);
            }else{
                order.setWaiterName("Customer");
                order.setCashierName("Customer");
                OrderPayment orderPayment = new OrderPayment();
                orderPayment.setPaymentMethodType(-1);
                orderPayment.setPaymentMethodName("masterpass");
                orderPayment.setAmount(order.getAmount());
                orderPayment.setPaidAmt(order.getAmount());
                orderPayment.setCashierName("Customer");
                orderPayment.setChangeAmt(0);
                List<OrderPayment> orderPaymentList = new ArrayList<>();
                orderPaymentList.add(orderPayment);
                order.setOrderPayments(orderPaymentList);
                mPresenter.payRequest(order);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.REQUEST_MC_PAYMENT) {
            PaymentData data = CustomerApplication.getInstance().getPaymentData();
            if(data!=null && data.isFinished()){
                //TODO: 这里只是简单的，没有任何安全措施的支付，需要TransactionId, Token, Hash验证, 时间戳
                order.setWaiterName("Customer");
                order.setCashierName("Customer");
                OrderPayment orderPayment = new OrderPayment();
                orderPayment.setPaymentMethodType(-1);
                orderPayment.setPaymentMethodName("masterpass");
                orderPayment.setAmount(order.getAmount());
                orderPayment.setPaidAmt(order.getAmount());
                orderPayment.setCashierName("Customer");
                orderPayment.setChangeAmt(0);
                List<OrderPayment> orderPaymentList = new ArrayList<>();
                orderPaymentList.add(orderPayment);
                order.setOrderPayments(orderPaymentList);
                mPresenter.payRequest(order);
            }
        }
    }

    /**
     * 生成合适的，严谨的付款数据给支付使用
     * 菜式小计
     * 税
     * 外送费
     * 参照{@link MoneyUtil#setupAmount }
     * TODO: 有没有可能前后不一致 ?
     * TODO: 会不会出现进度丢失的情况 ?
     */
    public PaymentData generateMCPaymentData(Order order) {
        PaymentData data = new PaymentData();
        data.setOrderid(order.getId());
        data.setFinished(false);
        double amount = 0;
        List<MasterPassItem> itemList = new ArrayList<>();
        // 菜式小计
        for(OrderItem item : order.getOrderItems()) {
            amount+= item.getQty()*item.getPrice();
            itemList.add(new MasterPassItem(item.getItemName(), item.getPrice(), item.getQty()));
        }
        //税
        if(order.getTax1Amt() > 0) {
            amount+= order.getTax1Amt();
            itemList.add(new MasterPassItem(order.getTax1Name(), order.getTax1Amt(), 1));
        }
        if(order.getTax2Amt() > 0) {
            amount+= order.getTax2Amt();
            itemList.add(new MasterPassItem(order.getTax2Name(), order.getTax2Amt(), 1));
        }
        if(order.getTax3Amt() > 0) {
            amount+= order.getTax3Amt();
            itemList.add(new MasterPassItem(order.getTax3Name(), order.getTax3Amt(), 1));
        }
        //外送费
        if(order.getDeliveryFee() > 0){
            amount+= order.getDeliveryFee();
            itemList.add(new MasterPassItem(getString(R.string.delivery_fee) ,order.getDeliveryFee(), 1));
        }
        data.setAmount(amount);
        data.setItemList(itemList);
        return data;
    }
}
