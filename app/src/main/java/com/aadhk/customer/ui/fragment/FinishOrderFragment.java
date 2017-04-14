package com.aadhk.customer.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.Address;
import com.aadhk.customer.bean.AppSettings;
import com.aadhk.customer.bean.Company;
import com.aadhk.customer.bean.Order;
import com.aadhk.customer.bean.OrderItem;
import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.contract.TakeOrderContract;
import com.aadhk.customer.data.model.TakeOrderModel;
import com.aadhk.customer.data.presenter.TakeOrderPresenter;
import com.aadhk.customer.ui.activity.CompanyListActivity;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.ui.activity.FinishOrderActivity;
import com.aadhk.customer.ui.activity.LocationActivity;
import com.aadhk.customer.ui.activity.PaymentActivity;
import com.aadhk.customer.util.CalendarUtil;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.FormatUtil;
import com.aadhk.customer.util.money.MathUtil;
import com.aadhk.customer.util.money.MoneyUtil;
import com.aadhk.customer.widget.DateTimePickerView;
import com.aadhk.library.ui.BaseFragment;
import com.aadhk.library.utils.ImageLoaderUtils;
import com.aadhk.library.utils.ToastUtil;
import com.gc.materialdesign.views.CheckBox;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.acra.ACRA;

import butterknife.InjectView;
import butterknife.OnClick;
import rx.functions.Action1;

import static com.aadhk.customer.util.Constant.TYPE_DELIVERY_VALUE;

/**
 * Created by jack on 07/12/2016.
 */

public class FinishOrderFragment extends BaseFragment<TakeOrderPresenter, TakeOrderModel> implements TakeOrderContract.View {

    @InjectView(R.id.ivAddress)
    ImageView ivAddress;
    @InjectView(R.id.tvAddressHint)
    TextView tvAddressHint;
    @InjectView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @InjectView(R.id.tvAddress)
    TextView tvAddress;
    @InjectView(R.id.tvTel)
    TextView tvTel;
    @InjectView(R.id.layAddress)
    RelativeLayout layAddress;
    @InjectView(R.id.tvDeliveryTime)
    TextView tvDeliveryTime;
    @InjectView(R.id.cbTime)
    CheckBox cbTime;
    @InjectView(R.id.etDeliveryTime)
    EditText etDeliveryTime;
    @InjectView(R.id.layDeliveryTimeTop)
    LinearLayout layDeliveryTimeTop;
    @InjectView(R.id.cbNow)
    CheckBox cbNow;
    @InjectView(R.id.layDeliveryTimeNow)
    LinearLayout layDeliveryTimeNow;
    @InjectView(R.id.layDeliveryTime)
    RelativeLayout layDeliveryTime;
    @InjectView(R.id.tvPickUp)
    TextView tvPickUp;
    @InjectView(R.id.cbWait)
    CheckBox cbWait;
    @InjectView(R.id.layPickUpIn)
    LinearLayout layPickUpIn;
    @InjectView(R.id.cbPickUpTime)
    CheckBox cbPickUpTime;
    @InjectView(R.id.etPickUpTime)
    EditText etPickUpTime;
    @InjectView(R.id.layPickUpTime)
    LinearLayout layPickUpTime;
    @InjectView(R.id.layPickUp)
    RelativeLayout layPickUp;
    @InjectView(R.id.tvTableM)
    TextView tvTableM;
    @InjectView(R.id.tvTableId)
    TextView tvTableId;
    @InjectView(R.id.tvTableName)
    TextView tvTableName;
    @InjectView(R.id.layTable)
    RelativeLayout layTable;
    @InjectView(R.id.tvCustomerNumM)
    TextView tvCustomerNumM;
    @InjectView(R.id.etCustomerNum)
    MaterialEditText etCustomerNum;
    @InjectView(R.id.layCustomerNum)
    RelativeLayout layCustomerNum;
    @InjectView(R.id.ivLogo)
    ImageView ivLogo;
    @InjectView(R.id.tvCompanyName)
    TextView tvRestaurantName;
    @InjectView(R.id.layOrderItem)
    LinearLayout layOrderItem;
    @InjectView(R.id.tvDeliveryFee)
    TextView tvDeliveryFee;
    @InjectView(R.id.tvDeliveryFeeM)
    TextView tvDeliveryFeeM;
    @InjectView(R.id.tvTotal)
    TextView tvTotal;
    @InjectView(R.id.etRemark)
    EditText etRemark;
    @InjectView(R.id.divider0)
    View divider0;
    @InjectView(R.id.divider1)
    View divider1;
    @InjectView(R.id.divider2)
    View divider2;
    @InjectView(R.id.tvTotalBottom)
    TextView tvTotalBottom;
    @InjectView(R.id.btnConfirm)
    AppCompatButton btnConfirm;

    private short typeValue;
    private Order order;
    private double maxDistance;
    private Company company;
    private User user;
    private AppSettings settings;

    private String timeValue;       // Delivery 选择外送时间 1:20
    private String dateTimeValue;  //  Take Out 选择带走时间 7月3号7:22

    public static FinishOrderFragment newInstance(short type) {
        FinishOrderFragment f = new FinishOrderFragment();
        Bundle args = new Bundle();
        args.putShort(Constant.BUNDLE_FINISH_ORDER_TYPE, type);
        f.setTypeValue(type);
        f.setArguments(args);
        return f;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_finish_order;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    /**
     * 共有分为等级的几个布局，不同的模式有不同的显示方式
     * layAddress                                       I 级
     * layDeliveryTime  layPickUp   layTable           II 级
     * layCustomerNum                                 III 级
     * <p>
     * Delivery  = layAddress + layDeliveryTime + layCustomerNum
     * TakeOut   =              layPickUp       + layCustomerNum
     * DineIn    =              layTable        + layCustomerNum
     */
    @Override
    protected void initView() {
        settings = CustomerApplication.getSetting();
        order = ((FinishOrderActivity) getActivity()).getOrder();
        company = ((FinishOrderActivity) getActivity()).getCompany();
        user = CustomerApplication.getInstance().getUser();
        Bundle args = getArguments();
        typeValue = args.getShort(Constant.BUNDLE_FINISH_ORDER_TYPE, Constant.TYPE_DELIVERY);
        switch (typeValue) {
            case TYPE_DELIVERY_VALUE:
                layPickUp.setVisibility(View.GONE);
                layTable.setVisibility(View.GONE);
                order.setOrderType(Constant.ORDER_TYPE_DELIVERY_CUSTOMER);
                order.setTableId(Constant.TABLE_ID_DELIVERY);
                initDeliveryData();
                break;
            case Constant.TYPE_TAKEOUT_VALUE:
                layAddress.setVisibility(View.GONE);
                layDeliveryTime.setVisibility(View.GONE);
                layTable.setVisibility(View.GONE);
                order.setOrderType(Constant.ORDER_TYPE_TAKEOUT_CUSTOMER);
                order.setTableId(Constant.TABLE_ID_TAKEOUT);
                initTakeOutData();
                break;
            case Constant.TYPE_DINEIN_VALUE:
                layAddress.setVisibility(View.GONE);
                layDeliveryTime.setVisibility(View.GONE);
                layPickUp.setVisibility(View.GONE);
                order.setOrderType(Constant.ORDER_TYPE_DINEIN_CUSTOMER);
                initDineInData();
                break;
        }
        initOrderData();
    }

    /**
     * 填充Delivery数据，包括默认地址  送餐时间 俩个CheckBox的日期选择
     */
    private void initDeliveryData() {
        Address address = CustomerApplication.getInstance().getAddress();
        Address address1 = order.getAddress();
        if (address != null && address.getId() > 0) initAddress(address);  //自己选择的地址所以不需要智能选择
        if (address1 != null && address1.getId() > 0) initAddress(address1);  //定位后获取智能地址
        if (order.getAddress() == null) {
            tvAddressHint.setVisibility(View.VISIBLE);
        }
        timeValue = CalendarUtil.getTime24();
        etDeliveryTime.setText(CalendarUtil.getTime12());
        cbTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbNow.setChecked(!cbTime.isCheck());
            }
        });
        cbNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbTime.setChecked(!cbNow.isCheck());
            }
        });
        maxDistance = MoneyUtil.maxDistance(company.getDeliveryList());
        Address now = order.getAddress();
        if (now != null) {
            if (MathUtil.earthDistance(now.getLongitude(), now.getLatitude(), company.getLongitude(), company.getLatitude()) > maxDistance) {
                initAddress(null);
                btnConfirm.setBackgroundColor(getResources().getColor(R.color.secondartText));
            }
        }
    }

    /**
     * 填充TakeOut数据，包括带走时间
     */
    private void initTakeOutData() {
        dateTimeValue = CalendarUtil.getCurrentDateTime();
        etPickUpTime.setText(CalendarUtil.displayNoYearTime(dateTimeValue));
        cbWait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbPickUpTime.setChecked(!cbWait.isCheck());
            }
        });
        cbPickUpTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbWait.setChecked(!cbPickUpTime.isCheck());
            }
        });
    }

    /**
     * 填充DineIn数据
     */
    private void initDineInData() {
        if (order.getTableId() > 0) {
            tvTableId.setText(String.valueOf(order.getTableId()));
            tvTableName.setText(order.getTableName());
        }
        mRxManager.on(Constant.RXBUS_FINISH_ORDER_QR_SCAN, new Action1<String>() {
            @Override
            public void call(String result) {
                try {
                    String[] arrays = result.split("\\&");
                    String tableIds = arrays[1].split("\\=")[1];
                    String tableName = arrays[2].split("\\=")[1];
                    tvTableId.setText(tableIds);
                    tvTableName.setText(tableName);
                    tvTableName.setError(null);
                } catch (Exception e) {
                    ToastUtil.showLong(getString(R.string.errorQRCode) + "result=" + result);
                    ACRA.getErrorReporter().handleException(e);
                }
            }
        });
    }

    /**
     * 填充菜式数据列表
     */
    private void initOrderData() {
        MoneyUtil.setupAmount(order, company);  //计算价格
        ImageLoaderUtils.displayCircle(getContext(), ivLogo, Constant.URL + order.getLogoPath());
        tvRestaurantName.setText(order.getCompanyName());
        layOrderItem.removeAllViews();
        for (OrderItem item : order.getOrderItems()) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item_order_item, null);
            TextView tvName = (TextView) view.findViewById(R.id.tvName);
            TextView tvQty = (TextView) view.findViewById(R.id.tvQty);
            TextView tvAmount = (TextView) view.findViewById(R.id.tvAmount);
            double amount = item.getQty() * item.getPrice();
            tvName.setText(String.valueOf(item.getItemName() + (TextUtils.isEmpty(item.getDiscountName()) ? "" : "(" + item.getDiscountName() + ")")));
            tvQty.setText("X " + FormatUtil.displayQty(item.getQty()));
            tvAmount.setText(String.valueOf(FormatUtil.displayAmount(settings.getCurrencyPosition(), settings.getDecimalPlace(), amount, settings.getCurrencyStr())));
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

        if (order.getTax1Amt() > 0 || order.getTax2Amt() > 0 || order.getTax3Amt() > 0) {
            View line2 = LayoutInflater.from(getContext()).inflate(R.layout.line, null);
            layOrderItem.addView(line2);
        }
        //外送时需要显示
        if (typeValue == TYPE_DELIVERY_VALUE && order.getDeliveryFee() > 0) {
            tvDeliveryFeeM.setVisibility(View.VISIBLE);
            tvDeliveryFee.setVisibility(View.VISIBLE);
            tvDeliveryFee.setText(FormatUtil.displayAmount(settings.getCurrencyPosition(), settings.getDecimalPlace(), order.getDeliveryFee(), settings.getCurrencyStr()));
            divider2.setVisibility(View.VISIBLE);
        } else {
            tvDeliveryFeeM.setVisibility(View.GONE);
            tvDeliveryFee.setVisibility(View.GONE);
            divider2.setVisibility(View.GONE);
        }

        if (typeValue == TYPE_DELIVERY_VALUE && ((order.getAmount() - order.getDeliveryFee()) < company.getMinimumOrder())) {
            btnConfirm.setBackgroundColor(getResources().getColor(R.color.secondartText));
        }

        //总额
        String totalAmountStr = FormatUtil.displayAmount(settings.getCurrencyPosition(), settings.getDecimalPlace(), order.getAmount(), settings.getCurrencyStr());
        tvTotal.setText(totalAmountStr);
        tvTotalBottom.setText(totalAmountStr + " to pay");
    }

    public short getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(short typeValue){
        this.typeValue = typeValue;
    }

    @OnClick({R.id.layAddress, R.id.etDeliveryTime, R.id.etPickUpTime, R.id.btnConfirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layAddress:
                Intent intent = new Intent(getActivity(), LocationActivity.class);
                intent.putExtra(Constant.BUNDLE_LOCATION_TYPE, Constant.LOCATION_TYPE_PICKER);
                intent.putExtra(Constant.BUNDLE_LOCATION_LONGITUDE, company.getLongitude());
                intent.putExtra(Constant.BUNDLE_LOCATION_LATITUDE, company.getLatitude());
                intent.putExtra(Constant.BUNDLE_LOCATION_MAX_DISTANCE, maxDistance);
                startActivityForResult(intent, Constant.REQUEST_ADDRESS);
                break;
            case R.id.etDeliveryTime:
                DateTimePickerView.getInstance().dialogTime((AppCompatActivity) getActivity(), timeValue, new DateTimePickerView.OnBackLisener() {
                    @Override
                    public void onConfirm(String chooseDateTime) {
                        timeValue = chooseDateTime;
                        etDeliveryTime.setText(CalendarUtil.displayTime(chooseDateTime, Constant.TIME_FORMAT_12));
                    }
                });
                break;
            case R.id.etPickUpTime:
                DateTimePickerView.getInstance().dialogDateTime((AppCompatActivity) getActivity(), dateTimeValue, new DateTimePickerView.OnBackDetailListener() {
                    @Override
                    public void onConfirm(String date, String time) {
                        dateTimeValue = date + " " + time;
                        etPickUpTime.setText(CalendarUtil.displayNoYearTime(dateTimeValue));
                    }
                });
                break;
            case R.id.btnConfirm:
                Log.e("jack", "onClick: come here");
                if (validate()) {
                    Log.e("jack", "onClick:2 come here");
                    mPresenter.takeOrderRequest(order);  //下单
                }
                break;
        }
    }

    @Override
    public void takeOrderRequestResult(Order order) {
        Log.e("jack", "onClick:4 come here");
//        ToastUtil.showLong("success init order now go to payment");
        if (order.getOrderType() == Constant.ORDER_TYPE_DINEIN_CUSTOMER) {
            Log.e("jack", "onClick:5 come here");
            startActivity(CompanyListActivity.class);
        } else {
            Log.e("jack", "onClick:6 come here");
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constant.BUNDLE_PAYMENT_ORDER, order);
            startActivity(PaymentActivity.class, bundle);
        }
    }

    @Override
    public void showLoading(String title) {
    }

    @Override
    public void stopLoading() {
    }

    @Override
    public void showErrorTip(String msg) {
//        ToastUtil.showLong(msg);
    }

    /**
     * 这里包含TableId    {@link Constant#TABLE_ID_TAKEOUT }
     * orderType  {@link Constant#ORDER_TYPE_DINEIN_CUSTOMER }
     */
    private boolean validate() {
        switch (typeValue) {
            case TYPE_DELIVERY_VALUE:
                order.setTableId(Constant.TABLE_ID_DELIVERY);
                order.setTableName(getString(R.string.lbDelivery));
//                order.setDeliveryAddress(timeValue);
                if (cbNow.isCheck()) {
                    order.setDeliveryArriveTime(getString(R.string.lbNow));
                } else {
                    order.setDeliveryArriveTime(timeValue);
                }
                String address = tvAddress.getText().toString().trim();
                String tel = tvTel.getText().toString().trim();
                if (FormatUtil.isEmpty(address)) {
                    ToastUtil.showLong(getString(R.string.input_delivery_location));
//                    tvAddress.setError(getString(R.string.errorEmpty));
                    return false;
                }
                if (FormatUtil.isEmpty(tel)) {
                    tvTel.setError(getString(R.string.errorEmpty));
                    return false;
                }
                if ((order.getAmount() - order.getDeliveryFee()) < company.getMinimumOrder()) {
                    ToastUtil.showLong(getString(R.string.under_start_price));
                    return false;
                }
                Address address1 = order.getAddress();
                double distance = MathUtil.earthDistance(address1.getLongitude(), address1.getLatitude(), company.getLongitude(), company.getLatitude());
                if (distance > maxDistance) {
                    ToastUtil.showLong(getString(R.string.beyond_delivery_arrange));
                    return false;
                }
                break;
            case Constant.TYPE_TAKEOUT_VALUE:
                order.setTableId(Constant.TABLE_ID_TAKEOUT);
                order.setTableName(getString(R.string.lbTakeout));
                if (!cbWait.isCheck()) {      //如果为空则表示waiting in the restaurant
                    order.setDeliveryArriveTime(dateTimeValue);
                    order.setTableId(Constant.TABLE_ID_PICK_UP);
                } else {
                    order.setDeliveryArriveTime(CalendarUtil.getCurrentDateTime());
                }
                break;
            case Constant.TYPE_DINEIN_VALUE:
                String tableNumber = tvTableId.getText().toString().trim();
                String tableName = tvTableName.getText().toString().trim();

                if (TextUtils.isEmpty(tableName)) {
                    ToastUtil.showLong(getString(R.string.scan_qr_code));
//                    tvTableName.setError(getString(R.string.errorEmpty));
                    return false;
                }
                long tableId = 0;
                try {
                    tableId = Long.parseLong(tableNumber);
                } catch (Exception e) {
                    ToastUtil.showLong(getString(R.string.scan_qr_code_again));
//                    tvTableName.setError(getString(R.string.errorEmpty));
                    return false;
                }
                order.setTableId(tableId);
                order.setTableName(tableName);
                break;
        }
        order.setStatus(Constant.STATUS_ORDER_PRE);
        String num = etCustomerNum.getText().toString();
        String remark = etRemark.getText().toString();
        if (FormatUtil.isEmpty(num)) {
            etCustomerNum.setError(getString(R.string.errorEmpty));
            return false;
        }
        order.setPersonNum(Integer.parseInt(num));
        order.setKitchenRemark(remark);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.REQUEST_ADDRESS) {
            Address address = intent.getParcelableExtra(Constant.BUNDLE_LOCATION);
            initAddress(address);
            initOrderData();
        }
    }

    /**
     * 如果在首页定位时选择一个已有地址作为中心点，那么进入这个页面时会自动选择该地址作为默认地址
     * 如果在首页定位时没有选择，那么不会有默认地址的出现，需要手动去选择一个地址作为收货地址
     * 手动选择地址时需要筛选掉距离不符合规定的，因为一旦超过合理范围就不会去送的，默认为5km
     * 所以在选择地址时需要将餐厅的经纬度传过去便于筛选
     */
    private void initAddress(Address address) {
        order.setAddress(address);
        if (address != null) {
            tvCustomerName.setText(address.getUserName());
            tvTel.setText(address.getPhone());
            tvAddress.setText(address.getAddress() + " " + address.getAddressLine());
            tvAddress.setError(null);
            tvAddressHint.setVisibility(View.GONE);
        } else {
            tvCustomerName.setText("");
            tvTel.setText("");
            tvAddress.setText("");
            tvAddressHint.setVisibility(View.VISIBLE);
        }
    }


}
