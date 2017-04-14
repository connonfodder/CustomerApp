package com.aadhk.customer.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.Company;
import com.aadhk.customer.bean.Search;
import com.aadhk.customer.bean.SearchRequest;
import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.contract.SearchContract;
import com.aadhk.customer.data.model.SearchModel;
import com.aadhk.customer.data.presenter.SearchPresenter;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.ui.activity.TakeOrderActivity;
import com.aadhk.customer.ui.adapter.SearchAdapter;
import com.aadhk.customer.ui.adapter.SearchResultAdapter;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.FlowLayoutManager;
import com.aadhk.customer.util.PreferenceUtil;
import com.aadhk.customer.util.TextChangeListener;
import com.aadhk.library.ui.BaseFragment;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by jack on 05/12/2016.
 * 一天之内热点搜索是一样的，不需要每次都去拿
 * 先去本地数据库拿，如果对比时间不是当天的那么就去服务器拿
 * <p>
 * 最终认定结果是什么都从服务器拿
 */

public class SearchFragment extends BaseFragment<SearchPresenter, SearchModel> implements SearchContract.View {

    @InjectView(R.id.etSearch)
    MaterialEditText etSearch;
    @InjectView(R.id.tvSearch)
    TextView tvSearch;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.rlv)
    EasyRecyclerView rlv;
    @InjectView(R.id.rlvResult)
    EasyRecyclerView rlvResult;
    @InjectView(R.id.tvEmpty)
    TextView tvEmpty;
    private PreferenceUtil prefUtil;
    private List<Search> all ;
    private List<Search> hotList;
    private List<Search> historyList;

    private List<Company> resultList;
    private SearchAdapter mAdapter;
    private SearchResultAdapter mResultAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_search;
    }

    @Override
    public void initPresenter() {
        prefUtil = new PreferenceUtil(getContext());
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void fetchHotAndHistroySearchResult(List<Search> result) {
        all.clear();
        hotList.clear();
        historyList.clear();
        for (Search item : result) {
            if (item.getCustomerId() == 0) {
                hotList.add(item);
            } else {
                historyList.add(item);
            }
        }
        all.add(new Search(SearchAdapter.ID_HOT, "Hot Search"));
        all.addAll(hotList);
        if(historyList.size()>0){
            all.add(new Search(SearchAdapter.ID_HISTROY, "Search History"));
            all.addAll(historyList);
        }
        mAdapter.clear();
        mAdapter.addAll(all);
    }

    @Override
    public void searchResult(List<Company> resultfinal, int type) {
        Log.d("jack", "---------search-----------"+type);
        if (type == (Constant.SEARCH_C_BY_CNAME | Constant.SEARCH_FT_BY_FTNAME)) {
//            ToastUtil.showLong("---searchResult---all-----");
            //只在输入变化时全部搜索
            resultList.clear();
            resultList.addAll(resultfinal);
            mResultAdapter.clear();
            mResultAdapter.addAll(resultList);
            rlvResult.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(resultfinal.size()==0 ? View.VISIBLE : View.GONE);
        } else {
            //针对热点和个人记录式的搜索 跑到餐厅主页面去
//            ToastUtil.showLong("---searchResult---only restaurant-----");
            Intent intent = new Intent();
            intent.putParcelableArrayListExtra(Constant.BUNDLE_SEARCH_RESULT, (ArrayList<? extends Parcelable>) resultfinal);
            intent.putExtra(Constant.BUNDLE_SEARCH_KEYWORD, keyWord);
            getActivity().setResult(Activity.RESULT_OK, intent);
            getActivity().finish();
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

    @Override
    protected void initView() {
        all = new ArrayList<>();
        hotList = new ArrayList<>();
        historyList = new ArrayList<>();
        resultList = new ArrayList<>();

        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        etSearch.setFloatingLabel(MaterialEditText.FLOATING_LABEL_NONE);
        etSearch.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                String keyWord = etSearch.getText().toString();
                if (TextUtils.isEmpty(keyWord)) {
                    rlvResult.setVisibility(View.GONE);
                    tvEmpty.setVisibility(View.GONE);
                    return;
                }
                mPresenter.searchRequest(new SearchRequest(keyWord, Constant.SEARCH_C_BY_CNAME | Constant.SEARCH_FT_BY_FTNAME));
            }
        });
        mAdapter = new SearchAdapter(getContext());
        mResultAdapter = new SearchResultAdapter(getContext());
        FlowLayoutManager layoutManager = new FlowLayoutManager();
        layoutManager.setScrollEnabled(false);
        rlv.setLayoutManager(layoutManager);
        rlvResult.setLayoutManager(new GridLayoutManager(getContext(), GridLayoutManager.VERTICAL));
        rlv.setAdapter(mAdapter);
        rlvResult.setAdapter(mResultAdapter);
        mAdapter.setOnMyItemClickListener(new SearchAdapter.OnMyItemClickListener() {
            @Override
            public void onItemClick(int position, BaseViewHolder holder, int type) {
//                ToastUtil.showLong("you click me");
                if(type == SearchAdapter.TYPE_CATEGORY){    //删除订单
                    User user = CustomerApplication.getInstance().getUser();
                    if (user != null)
                        mPresenter.deleteRecordRequest(user.getId());
                }else if(type == SearchAdapter.TYPE_NORMAL){
                    Search bean = all.get(position);
                    etSearch.setText(bean.getKeyWord());
                    etSearch.setSelection(bean.getKeyWord().length());
                   /* if(bean.getCustomerId() == Constant.SEARCH_TYPE_HOT){   //根据热词搜索
                        mPresenter.searchRequest(new SearchRequest(bean.getKeyWord(), Constant.SEARCH_C_BY_CNAME_AND_FTNAME | Constant.SEARCH_C_BY_CNAME));
                    }else{ //根据个人记录搜索
                        mPresenter.searchRequest(new SearchRequest(bean.getKeyWord(), Constant.SEARCH_C_BY_CNAME_AND_FTNAME | Constant.SEARCH_C_BY_CNAME));
                    }*/
                }
            }
        });
        mResultAdapter.setOnMyItemClickListener(new SearchResultAdapter.OnMyItemClickListener() {
            @Override
            public void onItemClick(int position, BaseViewHolder holder) {
//                ToastUtil.showLong("---mResultAdapter----onItemClick----");
                rlvResult.setVisibility(View.GONE);
                Company bean = resultList.get(position);
                String keyWord = bean.getName();
                if (bean.getType() == Constant.TYPE_NONE) {
                    //根据菜式风格搜索餐厅
                    mPresenter.searchRequest(new SearchRequest(keyWord, Constant.SEARCH_C_BY_FTNAME));
                } else {
                    Intent intent = new Intent(getActivity(), TakeOrderActivity.class);
                    intent.putExtra(Constant.BUNDLE_TAKR_ORDER_COMPANY_ID, bean.getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getActivity().startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onResume() {
        mPresenter.fetchHotAndHistroySearchRequest();
        super.onResume();
    }

    private String keyWord;
    @OnClick({R.id.tvSearch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSearch:
                keyWord = etSearch.getText().toString().trim();  //不为空的字符串
                if (TextUtils.isEmpty(keyWord) || keyWord.equals("")) return;
                mPresenter.searchRequest(new SearchRequest(keyWord, Constant.SEARCH_C_BY_CNAME));
                break;

        }
    }
}
