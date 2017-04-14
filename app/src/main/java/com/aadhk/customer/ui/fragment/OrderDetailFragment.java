package com.aadhk.customer.ui.fragment;

import android.Manifest;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.AppSettings;
import com.aadhk.customer.bean.Order;
import com.aadhk.customer.bean.OrderItem;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.ui.activity.OrderDetailActivity;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.DateUtil;
import com.aadhk.customer.util.FormatUtil;
import com.aadhk.customer.util.PhoneUtil;
import com.aadhk.library.ui.BaseFragment;
import com.aadhk.library.utils.ImageLoaderUtils;
import com.aadhk.library.utils.LogUtil;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by jack on 08/12/2016.
 */

public class OrderDetailFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks {

    @InjectView(R.id.layOrderStutusM)
    LinearLayout layOrderStutusM;
    @InjectView(R.id.tvOrderTakenM)
    TextView tvOrderTakenM;
    @InjectView(R.id.tvOrderTakenStatus)
    TextView tvOrderTakenStatus;
    @InjectView(R.id.tvOrderTakenTime)
    TextView tvOrderTakenTime;
    @InjectView(R.id.tvDeliveryingM)
    TextView tvDeliveryingM;
    @InjectView(R.id.tvDeliveryingStatus)
    TextView tvDeliveryingStatus;
    @InjectView(R.id.tvDeliveryingTime)
    TextView tvDeliveryingTime;
    @InjectView(R.id.tvFinishM)
    TextView tvFinishM;
    @InjectView(R.id.tvFinishStatus)
    TextView tvFinishStatus;
    @InjectView(R.id.tvFinishTime)
    TextView tvFinishTime;
    @InjectView(R.id.layAddress)
    LinearLayout layAddress;
    @InjectView(R.id.ivLogo)
    ImageView ivLogo;
    @InjectView(R.id.tvCompanyName)
    TextView tvRestaurantName;
    @InjectView(R.id.divider0)
    View divider0;
    @InjectView(R.id.layOrderItem)
    LinearLayout layOrderItem;
    @InjectView(R.id.divider1)
    View divider1;
    @InjectView(R.id.tvDeliveryFee)
    TextView tvDeliveryFee;
    @InjectView(R.id.tvDeliveryFeeM)
    TextView tvDeliveryFeeM;
    @InjectView(R.id.divider2)
    View divider2;
    @InjectView(R.id.tvTotal)
    TextView tvTotal;
    @InjectView(R.id.tvDeliveryCustomerName)
    TextView tvDeliveryCustomerName;
    @InjectView(R.id.tvDeliveryTel)
    TextView tvDeliveryTel;
    @InjectView(R.id.tvDeliveryAddress)
    TextView tvDeliveryAddress;
    @InjectView(R.id.layDeliveryAddress)
    LinearLayout layDeliveryAddress;
    @InjectView(R.id.tvOrderNumber)
    TextView tvOrderNumber;
    @InjectView(R.id.layStartTime)
    View layStartTime;
    @InjectView(R.id.tvStartTime)
    TextView tvStartTime;
    @InjectView(R.id.lineStartTime)
    View lineStartTime;
    @InjectView(R.id.linePickUp)
    View linePickUp;
    @InjectView(R.id.lineOrderNumber)
    View lineOrderNumber;
    @InjectView(R.id.tvPickUpTime)
    TextView tvPickUpTime;
    @InjectView(R.id.layPickUp)
    LinearLayout layPickUp;
    @InjectView(R.id.tvTableNumber)
    TextView tvTableNumber;
    @InjectView(R.id.layTable)
    LinearLayout layTable;
    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.iv)
    ImageView iv;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    private AppSettings settings;
    private Order order;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_detail_order;
    }

    @Override
    public void initPresenter() {
    }

    /**
     * layOrderStutusM
     * layAddress
     * layDeliveryAddress
     * layPickUp
     * layTable
     * linePickUp
     */
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
        order = ((OrderDetailActivity) getActivity()).getOrder();
        LogUtil.d(order.toString());
        switch (order.getOrderType()) {
            case Constant.ORDER_TYPE_DELIVERY_CUSTOMER:
                layStartTime.setVisibility(View.GONE);
                lineStartTime.setVisibility(View.GONE);
                layPickUp.setVisibility(View.GONE);
                linePickUp.setVisibility(View.GONE);
                layTable.setVisibility(View.GONE);
                lineOrderNumber.setVisibility(View.GONE);
                initDeliveryData();
                break;
            case Constant.ORDER_TYPE_TAKEOUT_CUSTOMER:
                layOrderStutusM.setVisibility(View.GONE);
                layAddress.setVisibility(View.GONE);
                layDeliveryAddress.setVisibility(View.GONE);
                layTable.setVisibility(View.GONE);
                linePickUp.setVisibility(View.GONE);
                divider2.setVisibility(View.GONE);
                initTakeOutData();
                break;
            case Constant.ORDER_TYPE_DINEIN_CUSTOMER:
                layOrderStutusM.setVisibility(View.GONE);
                layAddress.setVisibility(View.GONE);
                layDeliveryAddress.setVisibility(View.GONE);
                layPickUp.setVisibility(View.GONE);
                linePickUp.setVisibility(View.GONE);
                divider2.setVisibility(View.GONE);
                initDineInData();
                break;
        }
        initOrderData();
    }

    /**
     * 时间  收货信息
     * STATUS_ORDERED = 1;
     * STATUS_WAITING = 2;
     * STATUS_DELIVERING = 3;
     * STATUS_DELIVERED = 4;
     * STATUS_FINISH = 5;
     */
    private void initDeliveryData() {
        int v1 = (order.getDeliveryStatus() >= Constant.DELIVERY_STATUS_NO) ? getResources().getColor(R.color.colorAccent) : getResources().getColor(R.color.secondartText);
        tvOrderTakenM.setTextColor(v1);
        tvOrderTakenStatus.setTextColor(v1);
        tvOrderTakenTime.setTextColor(v1);
        tvOrderTakenTime.setText(TextUtils.isEmpty(order.getOrderTime()) ? "" : DateUtil.displayTime(order.getOrderTime()));

        int v2 = (order.getDeliveryStatus() >= Constant.DELIVERY_STATUS_ING) ? getResources().getColor(R.color.colorAccent) : getResources().getColor(R.color.secondartText);
        tvDeliveryingM.setTextColor(v2);
        tvDeliveryingStatus.setTextColor(v2);
        tvDeliveryingTime.setTextColor(v2);
        tvDeliveryingTime.setText(TextUtils.isEmpty(order.getDeliveryTime()) ? "" : DateUtil.displayTime(order.getDeliveryTime()));

        int v3 = (order.getDeliveryStatus() >= Constant.DELIVERY_STATUS_FINISH) ? getResources().getColor(R.color.colorAccent) : getResources().getColor(R.color.secondartText);
        tvFinishM.setTextColor(v3);
        tvFinishStatus.setTextColor(v3);
        tvFinishTime.setTextColor(v3);
        tvFinishTime.setText(TextUtils.isEmpty(order.getDeliveriedTime()) ? "" : DateUtil.displayTime(order.getDeliveriedTime()));

        tvDeliveryCustomerName.setText(order.getCustomerName());
        tvDeliveryTel.setText(order.getCustomerPhone());
        tvDeliveryAddress.setText(order.getDeliveryAddress());

        if (order.getDeliveryFee() > 0) {
            tvDeliveryFeeM.setVisibility(View.VISIBLE);
            tvDeliveryFee.setVisibility(View.VISIBLE);
            tvDeliveryFee.setText(FormatUtil.displayAmount(settings.getCurrencyPosition(), settings.getDecimalPlace(), order.getDeliveryFee(), settings.getCurrencyStr()));
        }
    }

    /**
     * 带走时间
     */
    private void initTakeOutData() {
        tvStartTime.setText(order.getOrderTime());
        tvPickUpTime.setText(order.getTakeoutTime());
    }

    /**
     * 餐桌号码
     */
    private void initDineInData() {
        tvStartTime.setText(order.getOrderTime());
        tvTableNumber.setText(order.getTableName());
    }

    /**
     * 填充菜式数据列表
     */
    private void initOrderData() {
        tv.setText(TextUtils.isEmpty(order.getCompanyName()) ? "  " : order.getCompanyName());
        tvOrderNumber.setText(order.getInvoiceNum());
        ImageLoaderUtils.displayCircle(getContext(), ivLogo, Constant.URL + order.getLogoPath());
        tvRestaurantName.setText(order.getCompanyName());
        layOrderItem.removeAllViews();
        for (OrderItem item : order.getOrderItems()) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item_order_item, null);
            TextView tvName = (TextView) view.findViewById(R.id.tvName);
            TextView tvQty = (TextView) view.findViewById(R.id.tvQty);
            TextView tvAmount = (TextView) view.findViewById(R.id.tvAmount);
            double amount = item.getQty() * item.getPrice();
            tvName.setText(item.getItemName() + (TextUtils.isEmpty(item.getDiscountName()) ? "" : "(" + item.getDiscountName() + ")"));
            tvQty.setText("X " + FormatUtil.displayQty(item.getQty()));
            tvAmount.setText(FormatUtil.displayAmount(settings.getCurrencyPosition(), settings.getDecimalPlace(), amount, settings.getCurrencyStr()));
            layOrderItem.addView(view);
        }

        View line1 = LayoutInflater.from(getContext()).inflate(R.layout.line, null);
        layOrderItem.addView(line1);

        if (order.getTax1Amt() > 0) {
            View vt1 = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item_order_item, null);
            ((TextView) vt1.findViewById(R.id.tvName)).setText(order.getTax1Name());
            ((TextView) vt1.findViewById(R.id.tvAmount)).setText(FormatUtil.displayAmount(settings.getCurrencyPosition(), settings.getDecimalPlace(), order.getTax1Amt(), settings.getCurrencyStr()));
            layOrderItem.addView(vt1);
        }

        if (order.getTax2Amt() > 0) {
            View vt2 = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item_order_item, null);
            ((TextView) vt2.findViewById(R.id.tvName)).setText(order.getTax2Name());
            ((TextView) vt2.findViewById(R.id.tvAmount)).setText(FormatUtil.displayAmount(settings.getCurrencyPosition(), settings.getDecimalPlace(), order.getTax2Amt(), settings.getCurrencyStr()));
            layOrderItem.addView(vt2);
        }

        if (order.getTax3Amt() > 0) {
            View vt3 = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item_order_item, null);
            ((TextView) vt3.findViewById(R.id.tvName)).setText(order.getTax3Name());
            ((TextView) vt3.findViewById(R.id.tvAmount)).setText(FormatUtil.displayAmount(settings.getCurrencyPosition(), settings.getDecimalPlace(), order.getTax3Amt(), settings.getCurrencyStr()));
            layOrderItem.addView(vt3);
        }

        if(order.getTax1Amt() > 0 || order.getTax2Amt() > 0  || order.getTax3Amt() > 0 ){
            View line2 = LayoutInflater.from(getContext()).inflate(R.layout.line, null);
            layOrderItem.addView(line2);
        }

        tvTotal.setText(FormatUtil.displayAmount(settings.getCurrencyPosition(), settings.getDecimalPlace(), order.getAmount(), settings.getCurrencyStr()));
    }

    @OnClick(R.id.iv)
    public void onClick() {
        call();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        switch (requestCode) {
            case RC_PHONE_CALL:
                call();
                break;
            default:
                break;
        }
    }

    private final static int RC_PHONE_CALL = 1001;

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        switch (requestCode) {
            case RC_PHONE_CALL:
                String[] perms = {Manifest.permission.CALL_PHONE};
                if (EasyPermissions.hasPermissions(getContext(), perms)) return;
                if (EasyPermissions.somePermissionPermanentlyDenied(this, list)) {
                    new AppSettingsDialog.Builder(this, getString(R.string.lbPermissionAskAgain))
                            .setTitle(getString(R.string.permission_phone_call_denied))
                            .setPositiveButton(getString(R.string.lbGetPermission))
                            .setNegativeButton(getString(R.string.cancel), null)
                            .setRequestCode(RC_PHONE_CALL)
                            .build()
                            .show();
                }
                break;
        }
    }

    private void call() {
        String[] perms = {Manifest.permission.CALL_PHONE};
        if (EasyPermissions.hasPermissions(getContext(), perms)) {
            PhoneUtil.call(getActivity(), order.getCompanyPhone());
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.permission_rationale_location), RC_PHONE_CALL, perms);
        }
    }
}
