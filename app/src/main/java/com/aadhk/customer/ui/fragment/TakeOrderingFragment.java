package com.aadhk.customer.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.PointF;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.Address;
import com.aadhk.customer.bean.AppSettings;
import com.aadhk.customer.bean.Category;
import com.aadhk.customer.bean.Company;
import com.aadhk.customer.bean.Item;
import com.aadhk.customer.bean.Order;
import com.aadhk.customer.bean.OrderItem;
import com.aadhk.customer.bean.User;
import com.aadhk.customer.data.contract.TakeOrderingContract;
import com.aadhk.customer.data.model.TakeOrderingModel;
import com.aadhk.customer.data.presenter.TakeOrderingPresenter;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.ui.activity.FinishOrderActivity;
import com.aadhk.customer.ui.activity.TakeOrderActivity;
import com.aadhk.customer.ui.activity.UserActivity;
import com.aadhk.customer.ui.adapter.CategoryAdapter;
import com.aadhk.customer.ui.adapter.ItemAdapter;
import com.aadhk.customer.ui.adapter.ItemSearchAdapter;
import com.aadhk.customer.ui.adapter.ProductAdapter;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.FormatUtil;
import com.aadhk.customer.util.ShopCartImp;
import com.aadhk.customer.util.TextChangeListener;
import com.aadhk.customer.widget.FakeAddImageView;
import com.aadhk.customer.widget.MyListView;
import com.aadhk.customer.widget.PointFTypeEvaluator;
import com.aadhk.library.ui.BaseFragment;
import com.aadhk.library.utils.DisplayUtil;
import com.aadhk.library.utils.ImageLoaderUtils;
import com.aadhk.library.utils.LogUtil;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.OnSheetDismissedListener;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by jack on 01/12/2016.
 * http://www.jianshu.com/p/6fc1160ddce7
 * http://blog.csdn.net/f917386389/article/details/51893822
 */

public class TakeOrderingFragment extends BaseFragment<TakeOrderingPresenter, TakeOrderingModel> implements TakeOrderingContract.View, CategoryAdapter.onItemSelectedListener, ShopCartImp {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tvTitle)
    TextView tvTitle;
    @InjectView(R.id.tvTotalAmount)
    TextView tvTotalAmount;
    @InjectView(R.id.shopping_cart_bottom)
    LinearLayout shoppingCartBottom;
    @InjectView(R.id.rlvLeft)
    RecyclerView rlvLeft;
    @InjectView(R.id.tvError)
    TextView tvError;
    @InjectView(R.id.rlvRight)
    RecyclerView rlvRight;
    @InjectView(R.id.tvRightMenu)
    TextView tvRightMenu;
    @InjectView(R.id.layRightMenu)
    LinearLayout layRightMenu;
    @InjectView(R.id.ivShoppingCar)
    ImageView ivShoppingCar;
    @InjectView(R.id.shopping_cart_layout)
    FrameLayout shoppingCartLayout;
    @InjectView(R.id.tvTotalQty)
    TextView tvTotalQty;
    @InjectView(R.id.layMain)
    RelativeLayout layMain;
    @InjectView(R.id.btnConfirm)
    AppCompatButton btnConfirm;
    @InjectView(R.id.etSearch)
    MaterialEditText etSearch;
    @InjectView(R.id.layHeader)
    View layHeader;
    @InjectView(R.id.ivSearch)
    ImageView ivSearch;
    @InjectView(R.id.ivCompanyLogo)
    ImageView ivCompanyLogo;
    @InjectView(R.id.tvCompanyAddress)
    TextView tvCompanyAddress;
    @InjectView(R.id.tvDeliveryFee)
    TextView tvDeliveryFee;
    @InjectView(R.id.layDeliveryFee)
    LinearLayout layDeliveryFee;
    @InjectView(R.id.tvTimeClock)
    TextView tvTimeClock;
    @InjectView(R.id.tvCompanyPhone)
    TextView tvCompanyPhone;
    @InjectView(R.id.layInfo)
    RelativeLayout layInfo;

    private AppSettings settings;
    private long companyId, tableId;
    private String tableName;
    private Company bean;
    private Category headMenu;
    private CategoryAdapter categoryAdapter;
    private ItemAdapter itemAdapter;
    private List<Category> categoryList;//数据源

    private boolean leftClickType = false;  //左侧菜单点击引发的右侧联动
    private Order order;
    private Company company;
    private List<OrderItem> orderingList;
    private boolean isSearching = false;

    //底部数据
    @InjectView(R.id.layBottomSheet)
    BottomSheetLayout layBottomSheet;
    private View bottomSheet;
    //分类和商品
    ProductAdapter productAdapter;//底部购物车的adapter

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_take_ordering;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
        settings = CustomerApplication.getSetting();
        companyId = ((TakeOrderActivity) getActivity()).getCompanyId();
        tableId = ((TakeOrderActivity) getActivity()).getTableId();
        tableName = ((TakeOrderActivity) getActivity()).getTableName();
        order = new Order();
        orderingList = new ArrayList<>();     //
        categoryList = new ArrayList<>();
        mPresenter.companyDetailRequest(companyId);
    }

    @Override
    public void returnChooseAddress(Address address) {
        order.setAddress(address);
        goFinishOrder();
    }

    @Override
    public void returnCompanyDetailData(Company data) {
        if(data==null) return;
        this.bean = data;
        categoryList.addAll(bean.getCategoryList());
        for (Iterator<Category> iterator = categoryList.iterator(); iterator.hasNext(); ) {
            Category item = iterator.next();
            List<Item> itemList = item.getItemList();
            if (itemList == null || itemList.size() == 0) iterator.remove();
        }
        tvError.setVisibility(View.GONE);
        initAdapter();  //确保一定要有数据否则不用进来了
        tvTitle.setText(data.getName());
        ivSearch.setVisibility(View.VISIBLE);
        initHeader();
    }

    @Override
    public void showLoading(String title) {
    }

    @Override
    public void stopLoading() {
    }

    @Override
    public void showErrorTip(String msg) {
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(msg);
    }

    @Override
    protected void initView() {
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSearching) {
                    layHeader.setVisibility(View.VISIBLE);
                    layInfo.setVisibility(View.VISIBLE);
                    rlvLeft.setVisibility(View.VISIBLE);
                    etSearch.setVisibility(View.GONE);
                    rlvRight.setAdapter(itemAdapter);
                    isSearching = false;
                } else {
                    getActivity().finish();
                }
            }
        });

        layInfo.setVisibility(View.GONE);
        /**
         * 点击搜索按钮，只显示rlvRight(菜式数据列表)
         * 当搜索框输入文字时，生成新的菜式集合并用一个新的适配器来显示UI，用旧的适配器的数据以及数据操作方法(为了保证数据的同一性)
         * 搜索完毕，点击返回按钮，将UI复原，同时rlv舍弃新的适配器使用旧的适配器,完成搜索
         * 再次点击搜索进入时将上一次的搜索记录重置为空
         */
        etSearch.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                String keyWord = etSearch.getText().toString();
                keyWord = TextUtils.isEmpty(keyWord) ? "" : keyWord;
                List<Item> temp = filterSource(keyWord);
                ItemSearchAdapter searchAdapter = new ItemSearchAdapter(getContext(), temp, itemAdapter);
                rlvRight.setAdapter(searchAdapter);
                searchAdapter.setShopCartImp(TakeOrderingFragment.this);
                isSearching = true;
            }
        });

        rlvLeft.setLayoutManager(new LinearLayoutManager(getContext()));
        rlvRight.setLayoutManager(new LinearLayoutManager(getContext()));
        //为菜式列表添加滑动监听，用来滑动左边菜类的列表
        rlvRight.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {     //无法下滑
                    showHeadView();
                    return;
                }
                View underView = null;
                if (dy > 0)
                    underView = rlvRight.findChildViewUnder(layRightMenu.getX(), layRightMenu.getMeasuredHeight() + 1);
                else
                    underView = rlvRight.findChildViewUnder(layRightMenu.getX(), 0);  //通过锚点下方的View获取RecyclerView的子View  再获取位置  然后再获取Category
                if (underView != null && underView.getContentDescription() != null) {
                    int position = Integer.parseInt(underView.getContentDescription().toString());
                    Category menu = itemAdapter.getMenuOfMenuByPosition(position); //
                    if (leftClickType || !menu.getName().equals(headMenu.getName())) {
                        if (dy > 0 && layRightMenu.getTranslationY() <= 1 && layRightMenu.getTranslationY() >= -1 * layRightMenu.getMeasuredHeight() * 4 / 5 && !leftClickType) {// underView.getTop()>9
                            int dealtY = underView.getTop() - layRightMenu.getMeasuredHeight();
                            layRightMenu.setTranslationY(dealtY);
                        } else if (dy < 0 && layRightMenu.getTranslationY() <= 0 && !leftClickType) {
                            tvRightMenu.setText(menu.getName());
                            int dealtY = underView.getBottom() - layRightMenu.getMeasuredHeight();
                            layRightMenu.setTranslationY(dealtY);
                        } else {   //点击事件
                            layRightMenu.setTranslationY(0);
                            if (leftClickType) {   //表示左边点击的，不按照常规的滚动来
                                headMenu = categoryAdapter.getClikedCategory();
                            } else {
                                headMenu = menu;
                            }
                            tvRightMenu.setText(headMenu.getName());
                            for (int i = 0; i < categoryList.size(); i++) {
                                Category c = categoryList.get(i);
                                if (c.getId() == headMenu.getId() && c.getName().equals(headMenu.getName())) {
                                    categoryAdapter.setSelectedNum(i);
                                    break;
                                }
                            }
                            if (leftClickType) leftClickType = false;
                        }
                    }
                }
            }
        });
//        initAdapter();
    }

    public void initHeader() {
        if (bean == null) return;
        layInfo.setVisibility(View.VISIBLE);
        ImageLoaderUtils.display(getContext(), ivCompanyLogo, Constant.URL + bean.getLogoPath());
        tvCompanyAddress.setText(bean.getAddress());
        tvTimeClock.setText(bean.getBusinessStartTime() + " - " + bean.getBusinessEndTime());
        tvCompanyPhone.setText(bean.getContactNum());
        if (bean.getMinimumOrder() > 0) {
            layDeliveryFee.setVisibility(View.VISIBLE);
            tvDeliveryFee.setText("  " + FormatUtil.displayAmount(settings.getCurrencyPosition(), settings.getDecimalPlace(), bean.getMinimumOrder(), settings.getCurrencyStr()));
        } else {
            layDeliveryFee.setVisibility(View.GONE);
        }
    }

    public List<Item> filterSource(String filter) {
        List<Item> temp = new ArrayList<>();
        for (Category category : categoryList) {
            List<Item> tempList = category.getItemList();
            for (Item item : tempList) {
                boolean needRemove = false;
                if (!TextUtils.isEmpty(filter)) {  //表示需要过滤
                    needRemove = !isMatch(item.getName(), filter);
                }
                if (!needRemove) temp.add(item);
            }
        }
        return temp;
    }

    private boolean isMatch(String source, String target) {
        Pattern pattern = Pattern.compile(Pattern.quote(target), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(source);
        return matcher.find();
    }

    private void initAdapter() {
        categoryAdapter = new CategoryAdapter(getContext(), categoryList);
        itemAdapter = new ItemAdapter(getContext(), categoryList, orderingList, order);
        DividerDecoration itemDecoration = new DividerDecoration(getResources().getColor(R.color.secondBg), DisplayUtil.dip2px(0.4f), 0, 0);
        rlvRight.addItemDecoration(itemDecoration);
        rlvRight.setAdapter(itemAdapter);
        rlvLeft.setAdapter(categoryAdapter);
        categoryAdapter.addItemSelectedListener(this);
        itemAdapter.setShopCartImp(this);
        initHeadView();
        mRxManager.on(Constant.RXBUS_FRESH_CAR_SHOP_DATA, new Action1<String>() {
            @Override
            public void call(String result) {
                showTotalPrice();
            }
        });
    }

    private void initHeadView() {
        headMenu = itemAdapter.getMenuOfMenuByPosition(0);
        layRightMenu.setContentDescription("0");
        tvRightMenu.setText(headMenu.getName());
    }

    private void showHeadView() {
        layRightMenu.setTranslationY(0);
        View underView = rlvRight.findChildViewUnder(tvRightMenu.getX(), 0);
        if (underView != null && underView.getContentDescription() != null) {
            int position = Integer.parseInt(underView.getContentDescription().toString());
            Category menu = itemAdapter.getMenuOfMenuByPosition(position + 1);
            if (leftClickType) {      //表示左边点击的，不按照常规的滚动来
                headMenu = categoryAdapter.getClikedCategory();
            } else {
                headMenu = menu;
            }
            tvRightMenu.setText(headMenu.getName());
            for (int i = 0; i < categoryList.size(); i++) {
                if (categoryList.get(i) == headMenu) {
                    categoryAdapter.setSelectedNum(i);
                    break;
                }
            }
        }
    }

    @Override
    public void onLeftItemSelected(int position, Category menu) {
        int sum = 0;
        for (int i = 0; i < position; i++) {
            sum += categoryList.get(i).getItemList().size() + 1;
        }
        leftClickType = true;
        LinearLayoutManager layoutManager = (LinearLayoutManager) rlvRight.getLayoutManager();
        layoutManager.scrollToPositionWithOffset(sum, 0);
    }

    @Override
    public void add(View view, int postion) {
        int[] addLocation = new int[2];
        int[] cartLocation = new int[2];
        int[] recycleLocation = new int[2];
        view.getLocationInWindow(addLocation);
        ivShoppingCar.getLocationInWindow(cartLocation);
        rlvRight.getLocationInWindow(recycleLocation);

        PointF startP = new PointF();
        PointF endP = new PointF();
        PointF controlP = new PointF();

        startP.x = addLocation[0];
        startP.y = addLocation[1] - recycleLocation[1];
        endP.x = cartLocation[0];
        endP.y = cartLocation[1] - recycleLocation[1];
        controlP.x = endP.x;
        controlP.y = startP.y;

        final FakeAddImageView fakeAddImageView = new FakeAddImageView(getContext());
        layMain.addView(fakeAddImageView);
        fakeAddImageView.setImageResource(R.drawable.ic_plus_24dp);
        fakeAddImageView.getLayoutParams().width = getResources().getDimensionPixelSize(R.dimen.item_dish_circle_size);
        fakeAddImageView.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.item_dish_circle_size);
        fakeAddImageView.setVisibility(View.VISIBLE);
        ObjectAnimator addAnimator = ObjectAnimator.ofObject(fakeAddImageView, "mPointF", new PointFTypeEvaluator(controlP), startP, endP);
        addAnimator.setInterpolator(new AccelerateInterpolator());
        addAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                fakeAddImageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                fakeAddImageView.setVisibility(View.GONE);
                layMain.removeView(fakeAddImageView);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        ObjectAnimator scaleAnimatorX = ObjectAnimator.ofFloat(ivShoppingCar, "scaleX", 0.6f, 1.0f);
        ObjectAnimator scaleAnimatorY = ObjectAnimator.ofFloat(ivShoppingCar, "scaleY", 0.6f, 1.0f);
        scaleAnimatorX.setInterpolator(new AccelerateInterpolator());
        scaleAnimatorY.setInterpolator(new AccelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleAnimatorX).with(scaleAnimatorY).after(addAnimator);
        animatorSet.setDuration(800);
        animatorSet.start();
    }

    @Override
    public void remove(View view, int postion) {
    }

    private void showTotalPrice() {
        AppSettings settings = CustomerApplication.getSetting();
        if (order != null && order.getAmount() > 0) {
            tvTotalAmount.setVisibility(View.VISIBLE);
            tvTotalAmount.setText(FormatUtil.displayAmount(settings.getCurrencyPosition(), settings.getDecimalPlace(), order.getAmount(), settings.getCurrencyStr()));
            tvTotalQty.setVisibility(View.VISIBLE);
            tvTotalQty.setText(FormatUtil.displayQty(order.getQty()));
            btnConfirm.setBackgroundColor(getResources().getColor(R.color.btnConfirm));
        } else {
            btnConfirm.setBackgroundColor(getResources().getColor(R.color.secondartText));
            tvTotalAmount.setVisibility(View.GONE);
            tvTotalQty.setVisibility(View.GONE);
            if (layBottomSheet != null && layBottomSheet.isSheetShowing())
                layBottomSheet.dismissSheet();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (categoryAdapter != null)
            categoryAdapter.removeItemSelectedListener(this);
        ButterKnife.reset(this);
    }


    @OnClick({R.id.btnConfirm, R.id.shopping_cart_layout, R.id.ivSearch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnConfirm:
                if (orderingList.size() == 0) return;
                User user = CustomerApplication.getInstance().getUser();
                if (user == null) {   //这里需要登陆
                    Intent intent = new Intent(getActivity(), UserActivity.class);
                    getActivity().startActivityForResult(intent, Constant.REQUEST_LOGIN);
                    return;
                }
                double subTotal = 0;
                for (OrderItem item : orderingList) {
                    subTotal += item.getPrice() * item.getQty();
                }
                order.setSubTotal(subTotal);
                order.setCustomerId(user.getId());
                order.setCustomerName(user.getUserName());
                order.setCustomerPhone(user.getTelephone());
                order.setOrderItems(orderingList);
                order.setCompanyId(bean.getId());
                order.setCompanyName(bean.getName());
                order.setCompanyPhone(bean.getContactNum());
                order.setLogoPath(bean.getLogoPath());
                order.setOrderType(bean.getType());
                order.setTableId(tableId);
                order.setTableName(tableName);
                company = bean.clone();
                company.setCategoryList(null);  //减轻bundle传输负担
                company.setStyles(null);
                LogUtil.d("-------1----------" + company);
                if (CustomerApplication.getInstance().getAddress() != null) {
                    goFinishOrder();
                } else {
                    mPresenter.chooseAddressRequest(user.getId(), companyId, company.getLongitude(), company.getLatitude());
                }
                break;
            case R.id.shopping_cart_layout:
                showBottomSheet();
                break;
            case R.id.ivSearch:
                etSearch.setText("");
                layHeader.setVisibility(View.GONE);
                layInfo.setVisibility(View.GONE);
                rlvLeft.setVisibility(View.GONE);
                etSearch.setVisibility(View.VISIBLE);
                isSearching = true;
                break;
        }
    }

    private void goFinishOrder() {
        Intent intent = new Intent(getActivity(), FinishOrderActivity.class);
        intent.putExtra(Constant.BUNDLE_FINISH_ORDER, order);
        intent.putExtra(Constant.BUNDLE_FINISH_COMPANY, company);
        getActivity().startActivity(intent);
    }

    //创建购物车view
    private void showBottomSheet() {
        bottomSheet = createBottomSheetView();
        if (layBottomSheet.isSheetShowing()) {
            layBottomSheet.dismissSheet();
        } else {
            if (order.getQty() != 0) {
                layBottomSheet.showWithSheetView(bottomSheet);
            }
        }
    }

    //查看购物车布局
    private View createBottomSheetView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_sheet, (ViewGroup) getActivity().getWindow().getDecorView(), false);
        MyListView lv_product = (MyListView) view.findViewById(R.id.lv_product);
        TextView clear = (TextView) view.findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCart();
            }
        });
        productAdapter = new ProductAdapter(getActivity(), itemAdapter, orderingList, order);
        lv_product.setAdapter(productAdapter);
        return view;
    }

    //清空购物车
    public void clearCart() {
        layBottomSheet.setPeekOnDismiss(true);
        layBottomSheet.addOnSheetDismissedListener(new OnSheetDismissedListener() {
            @Override
            public void onDismissed(BottomSheetLayout bottomSheetLayout) {
                orderingList.clear();
                itemAdapter.updateItemList();
                showTotalPrice();
                categoryAdapter.setSelection(0);
                itemAdapter.notifyDataSetChanged();
                categoryAdapter.notifyDataSetChanged();
            }
        });
        if (layBottomSheet.isSheetShowing()) layBottomSheet.dismissSheet();
    }
}
