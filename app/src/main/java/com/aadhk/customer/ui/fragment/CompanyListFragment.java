package com.aadhk.customer.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.Company;
import com.aadhk.customer.bean.CompanyRequest;
import com.aadhk.customer.bean.FoodStyles;
import com.aadhk.customer.data.contract.CompanyListContract;
import com.aadhk.customer.data.model.CompanyListModel;
import com.aadhk.customer.data.presenter.CompanyListPresenter;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.ui.activity.LocationActivity;
import com.aadhk.customer.ui.activity.ScannerActivity;
import com.aadhk.customer.ui.activity.SearchActivity;
import com.aadhk.customer.ui.activity.TakeOrderActivity;
import com.aadhk.customer.ui.adapter.CompanyAdapter;
import com.aadhk.customer.ui.dialog.BaseDialog;
import com.aadhk.customer.ui.dialog.RestaurantFilterDialog;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.FormatUtil;
import com.aadhk.customer.util.PreferenceUtil;
import com.aadhk.library.ui.BaseFragment;
import com.aadhk.library.utils.LogUtil;
import com.aadhk.library.utils.NetWorkUtils;
import com.aadhk.library.utils.ToastUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.acra.ACRA;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.InjectView;
import butterknife.OnClick;
import rx.functions.Action1;


public class CompanyListFragment extends BaseFragment<CompanyListPresenter, CompanyListModel> implements CompanyListContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {

    @InjectView(R.id.recyclerView)
    EasyRecyclerView recyclerView;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.ivFilter)
    ImageView ivFilter;
    @InjectView(R.id.ivQR)
    ImageView ivQR;
    @InjectView(R.id.et)
    AppCompatEditText et;
    @InjectView(R.id.tvNearByRestaurantM)
    TextView tvNearByRestaurantM;
    private CustomerApplication app;
    private ArrayList<Company> data;
    private Company curr;
    private static int mStartPage = 1;
    private CompanyAdapter mAdapter;
    private PreferenceUtil prefUtil;
    private CompanyRequest request;
    private String keyword;
    private boolean isChoosenAddress;
    private Address address;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_company_list;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        prefUtil = new PreferenceUtil(getContext());
        app = CustomerApplication.getInstance();
        data = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), GridLayoutManager.VERTICAL));
        mAdapter = new CompanyAdapter(getContext());
        recyclerView.setAdapterWithProgress(mAdapter);
        mAdapter.setMore(R.layout.view_more, this);
        mAdapter.setNoMore(R.layout.view_nomore, new RecyclerArrayAdapter.OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {
                mAdapter.resumeMore();
            }

            @Override
            public void onNoMoreClick() {
                mAdapter.resumeMore();
            }
        });
        recyclerView.setRefreshListener(this);
        mAdapter.setOnMyItemClickListener(new CompanyAdapter.OnMyItemClickListener() {
            @Override
            public void onItemClick(int position, BaseViewHolder holder) {
                curr = data.get(position);
                if (curr.isBusinessStatus()) {
                    ToastUtil.showShort(getString(R.string.not_business));
                    return;
                }
                goCompanyDetail(curr.getId(), 0, "");
            }
        });
        //使用RxBus用来处理成功获取地址后再加载列表数据
        mRxManager.on(Constant.RXBUS_GOT_LOCATION_SUCCEED, new Action1<Address>() {
            @Override
            public void call(Address address) {
                if (address == null && !isChoosenAddress) {
                    ToastUtil.showLong(getString(R.string.fail_access_location));
                    tvNearByRestaurantM.setText(R.string.fail_access_location);
                    return;
                }
                initAddress(address);
            }
        });
        mRxManager.post(Constant.RXBUS_REQUEST_LOCATION, null);
    }

    /**
     * Address[addressLines=[
     * 0:"广东省深圳市福田区泰然九路1",
     * 1:"天地源·盛唐大厦东座",
     * 2:"深圳希玛林顺潮眼科医院",
     * ......
     * feature=null,
     * admin=广东省,
     * locality=深圳市,
     * thoroughfare=泰然九路
     */
    private void initAddress(Address address) {
        this.address = address;
        if (address != null) {
            String knownName = address.getFeatureName();
            String thoroughfare = address.getThoroughfare();
            String addressDetail0 = address.getAddressLine(0);
            String addressDetail1 = address.getAddressLine(1);
            String addressDetail2 = address.getAddressLine(2);
            tvNearByRestaurantM.setText(FormatUtil.getNoEmpty(addressDetail1, addressDetail2, knownName, thoroughfare, addressDetail0));
            prefUtil.saveLastLocation(address.getLongitude(), address.getLatitude());
        }
        onRefresh();
    }

    private void goCompanyDetail(long companyId, long tableId, String tableName) {
        Bundle bundle = new Bundle();
        bundle.putLong(Constant.BUNDLE_TAKR_ORDER_COMPANY_ID, companyId);
        bundle.putLong(Constant.BUNDLE_TAKR_ORDER_TABLE_ID, tableId);
        bundle.putString(Constant.BUNDLE_TAKR_ORDER_TABLE_NAME, tableName);
        startActivity(TakeOrderActivity.class, bundle);
    }

    @Override
    public void returnCompanyListData(List<Company> datas) {
        LogUtil.d("--------returnCompanyListData----------"+datas.size());
        if (mStartPage == 1) {
            data.clear();
            mAdapter.clear();
        } else if (datas.size() == 0) {
            mAdapter.stopMore();
            return;
        }
        data.addAll(datas);
        mAdapter.addAll(datas);
        if (data.size() == 0) showErrorTip(getString(R.string.lbEmpty));
    }
    private boolean isReset;

    @Override
    public void onRefresh() {
        mStartPage = 1;
        keyword = "";
        request = new CompanyRequest(mStartPage);
        load(request);
        isReset = true;
    }


    @Override
    public void onLoadMore() {
        mStartPage++;
        request.setPage(mStartPage);
        if (!TextUtils.isEmpty(keyword)) request.setKeyWord(keyword);
        load(request);
    }

    private void load(CompanyRequest request) {
        if (!NetWorkUtils.isNetConnected(getContext())) {
            mAdapter.clear();
            showErrorTip(getString(R.string.error_network));
            return;
        }
        if (address == null) {
            mRxManager.post(Constant.RXBUS_REQUEST_LOCATION, null);
            recyclerView.setRefreshing(false);
        } else {
            mPresenter.getCompanyListDataRequest(request);
        }
    }

    @Override
    public void showLoading(String title) {
        recyclerView.setRefreshing(true);
    }

    @Override
    public void stopLoading() {
        recyclerView.setRefreshing(false);
    }

    @Override
    public void showErrorTip(String msg) {
        View view = recyclerView.getEmptyView();
        TextView tv = (TextView) view.findViewById(R.id.tv);
        tv.setText(msg);
        recyclerView.showEmpty();
    }

    @OnClick({R.id.layNearByRestaurant, R.id.ivFilter, R.id.ivQR, R.id.et})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layNearByRestaurant:
                Intent picker = new Intent(getActivity(), LocationActivity.class);
                picker.putExtra(Constant.BUNDLE_LOCATION_TYPE, Constant.LOCATION_TYPE_LOCATION);
                picker.putExtra(Constant.BUNDLE_LOCATION_VALUE, address);
                startActivityForResult(picker, Constant.REQUEST_ADDRESS);
                break;
            case R.id.ivFilter:
                showFilterDialog();
                break;
            case R.id.ivQR:
                Intent intent = new Intent(getActivity(), ScannerActivity.class);
                startActivityForResult(intent, Constant.REQUEST_QR_CODE);
                break;
            case R.id.et:
                Intent searchIntent = new Intent(getActivity(), SearchActivity.class);
                startActivityForResult(searchIntent, Constant.REQUEST_COE_SEARCH);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.REQUEST_QR_CODE) {
            String result = intent.getStringExtra(Constant.BUNDLE_QR_CODE);  //type=1&rid=1001
            try {   /**  companyId=xx&tabled=xx*/
                String[] arrays = result.split("\\&");
                String companyIdStr = arrays[0].split("\\=")[1];
                String tableIdStr = arrays[1].split("\\=")[1];
                String tableName = arrays[2].split("\\=")[1];
                goCompanyDetail(Long.parseLong(companyIdStr), Long.parseLong(tableIdStr), tableName);
            } catch (Exception e) {
				ACRA.getErrorReporter().handleException(e);
                ToastUtil.showLong(getString(R.string.errorQRCode) + "result=" + result);
            }
        } else if (resultCode == Activity.RESULT_OK && requestCode == Constant.REQUEST_COE_SEARCH) {
            //这里处理搜索后的数据
            List<Company> result = intent.getParcelableArrayListExtra(Constant.BUNDLE_SEARCH_RESULT);
            data.clear();
            data.addAll(result);
            mAdapter.clear();
            mAdapter.addAll(result);
            mAdapter.stopMore();
            if (result.size() == 0) showErrorTip(getString(R.string.restaurantEmpty));
            keyword = intent.getStringExtra(Constant.BUNDLE_SEARCH_KEYWORD);
        } else if (resultCode == Activity.RESULT_OK && requestCode == Constant.REQUEST_ADDRESS) {
            Object obj = intent.getParcelableExtra(Constant.BUNDLE_LOCATION);
            if (obj instanceof Address) {
                Address location = (Address) obj;
                if (location == null) return;
                isChoosenAddress = true;
                initAddress(location);
            } else if (obj instanceof com.aadhk.customer.bean.Address) {
                com.aadhk.customer.bean.Address address = (com.aadhk.customer.bean.Address) obj;
                if (address == null) return;
                isChoosenAddress = true;
                Address location = new Address(Locale.getDefault());
                location.setFeatureName(FormatUtil.getNoEmpty(address.getAddress(), address.getAddressLine()));
                location.setLongitude(address.getLongitude());
                location.setLatitude(address.getLatitude());
                CustomerApplication.getInstance().setAddress(address); //放入到全局变量中在下单时直接智能化填写
                initAddress(location);
            }
        }
    }

    private RestaurantFilterDialog dialog;

    private void showFilterDialog() {
        List<FoodStyles> list = app.getFoodStylesList();
        if (list == null) {
            app.initFoodStyles(null);
            ToastUtil.showLong(getString(R.string.prepareing_data));
        } else if (dialog == null) {
            dialog = new RestaurantFilterDialog(getContext(), list);
            dialog.setOnConfirmListener(new BaseDialog.OnConfirmListener() {
                @Override
                public void onConfirm(Object object) {
                    //得到筛选条件  然后进行网络请求
                    request = (CompanyRequest) object;
                    mStartPage = 1;
                    request.setPage(mStartPage);
                    load(request);
                    isReset = false;
                }
            });
            dialog.setAnchorView(ivFilter);
            dialog.show();
        } else {
            if(isReset) dialog.reset();
            dialog.show();
        }
    }

/*    @Override
    public void onResume() {
        super.onResume();
        if (data.size() == 0) {
            onRefresh();
        }
    }*/

    @Override
    public void onDestroy() {
        if (dialog != null && dialog.isShowing()) dialog.dismiss();
        super.onDestroy();
    }

}
