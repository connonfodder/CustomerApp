package com.aadhk.customer.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aadhk.customer.R;
import com.aadhk.customer.bean.AppSettings;
import com.aadhk.customer.bean.Category;
import com.aadhk.customer.bean.Item;
import com.aadhk.customer.bean.Order;
import com.aadhk.customer.bean.OrderItem;
import com.aadhk.customer.ui.activity.CustomerApplication;
import com.aadhk.customer.util.Constant;
import com.aadhk.customer.util.CustomerUtil;
import com.aadhk.customer.util.FormatUtil;
import com.aadhk.customer.util.ShopCartImp;
import com.aadhk.library.rx.RxBus;
import com.aadhk.library.utils.ImageLoaderUtils;
import com.aadhk.library.utils.LogUtil;

import org.acra.ACRA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cheng on 16-11-10.
 */
public class ItemAdapter extends RecyclerView.Adapter {
    private final int MENU_TYPE = 0;
    private final int DISH_TYPE = 1;
    private final int HEAD_TYPE = 2;

    private Context mContext;
    private List<Category> oldList;
    private List<Category> categoryArrayList;
    private List<OrderItem> orderingList;
    private Order order;
    private int mItemCount;
    private int clickPosition;
    private ShopCartImp shopCartImp;
    public Map<Long, OrderItem> orderItemMap;
    private AppSettings settings ;

    public ItemAdapter(Context mContext, List<Category> categoryList, List<OrderItem> orderingList, Order order ) {
        this.mContext = mContext;
        this.oldList = categoryList;
        this.categoryArrayList = CustomerUtil.cloneCategoryList(oldList);
        this.orderingList = orderingList;
        this.order = order;
        this.mItemCount = categoryList.size();
        for (Category menu : categoryList) {
            mItemCount += menu.getItemList().size();
        }
        updateItemList();
        settings = CustomerApplication.getSetting();
    }

    //初始化Map对象
    public void updateItemList() {
        orderItemMap = new HashMap<>();
        double qty = 0;
        double amount = 0;
        for (OrderItem item : orderingList) {
            orderItemMap.put(item.getItemId(), item);
            qty += item.getQty();
            amount += item.getQty() * item.getPrice();  //TODO: 可能会出现精度丢失
        }
        order.setQty((int)qty);
        order.setAmount(amount);
        //使用RxBus来实现刷新数据 啊哈
        RxBus.getInstance().post(Constant.RXBUS_FRESH_CAR_SHOP_DATA, null);
    }

    @Override
    public int getItemViewType(int position) {
        int sum = 0;
        for (Category menu : categoryArrayList) {
            if (position == sum ) return MENU_TYPE;
            sum += menu.getItemList().size() +  1;
        }
        return DISH_TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == MENU_TYPE ) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
            return new CategoryViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_right_item, parent, false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == MENU_TYPE) {
            CategoryViewHolder menuholder = (CategoryViewHolder) holder;
            if (menuholder != null) {
                menuholder.right_menu_title.setText(getCategoryByPosition(position).getName());
                menuholder.right_menu_layout.setContentDescription(position + "");
            }
        } else {
            final ItemViewHolder itemHolder = (ItemViewHolder) holder;
            if (itemHolder != null) {
                final Item item = getItemByPosition(position);
                itemHolder.tvItemName.setText(item.getName());

                itemHolder.tvPrice.setText(FormatUtil.displayAmount(settings.getCurrencyPosition(), settings.getDecimalPlace(), item.getPrice(), settings.getCurrencyStr()));
                itemHolder.layAdapterItem.setContentDescription(position + "");
                try {
//                        ImageLoaderUtils.displayByteImage(mContext, itemHolder.ivLogo, item.getImagePath());
                    LogUtil.d("-----------------"+Constant.URL + item.getImagePath());
                    ImageLoaderUtils.display(mContext, itemHolder.ivLogo, Constant.URL + item.getImagePath());
                }catch (Exception e){
                    //TODO:
					ACRA.getErrorReporter().handleException(e);
                }
                itemHolder.tvIntroduce.setText(item.getDescription());
                int count = orderItemMap.containsKey(item.getId()) ? (int) orderItemMap.get(item.getId()).getQty() : 0;
                if (count <= 0) {
                    itemHolder.ivSubtract.setVisibility(View.GONE);
                    itemHolder.tvQty.setVisibility(View.GONE);
                } else {
                    itemHolder.ivSubtract.setVisibility(View.VISIBLE);
                    itemHolder.tvQty.setVisibility(View.VISIBLE);
                    itemHolder.tvQty.setText(String.valueOf(count));
                }
                itemHolder.ivAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (handleData(true, item)) {
                            notifyItemChanged(position);
                            clickPosition = position;
                            if (shopCartImp != null) shopCartImp.add(view, position);  //动画效果展示
                        }
                    }
                });

                itemHolder.ivSubtract.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (handleData(false, item)) {
                            notifyItemChanged(position);
                            if (shopCartImp != null) shopCartImp.remove(view, position);
                        }
                    }
                });
            }
        }
    }


    //将数据更新到OrderItem List 中去，同时将Map更新
    public boolean handleData(boolean isAdd, Item item) {
        long key = item.getId();
        if (orderItemMap.containsKey(key)) {
            OrderItem temp = orderItemMap.get(key);
            int index = orderingList.indexOf(temp);
            if (!isAdd && temp.getQty() - 1 <= 0) {
                orderingList.remove(index);
            } else {
                OrderItem temp1 = orderingList.get(index);
                temp1.setQty(isAdd ? (temp1.getQty() + 1) : (temp1.getQty() - 1));
            }
        } else {   //减去不会跑到这里来
            OrderItem orderItem = createOrderItem(item, 1);
            orderingList.add(orderItem);
//            orderItemMap.put(key, orderItem);
        }
        updateItemList();
        return true;
    }

    private OrderItem createOrderItem(Item item, double qty) {
        OrderItem data = new OrderItem();
        Category category = getMenuOfMenuByPosition(clickPosition);
        if(category!=null){
            data.setCategoryName(category.getName());
            data.setCategoryId(category.getId());
            data.setCategorySequence(category.getSequence());
        }
        data.setQty(qty);
        data.setCategoryId(item.getCategoryId());
        data.setItemId(item.getId());
        data.setPrice(item.getPrice());
        data.setItemName(item.getName());
        data.setCost(item.getCost());
        data.setOriginalPrice(item.getPrice());
        data.setTax1Id(item.getTax1Id());
        data.setTax2Id(item.getTax2Id());
        data.setTax3Id(item.getTax3Id());
        data.setPrinterIds(item.getPrinterIds());
        data.setKitchenDisplayIds(item.getKitchenDisplayIds());
        return data;
    }

    //根据位置得到Category
    public Category getCategoryByPosition(int position) {
        int sum = 0;
        for (Category menu : categoryArrayList) {
            if (position == sum) return menu;
            sum += menu.getItemList().size() + 1;
        }
        return null;
    }

    //根据位置得到Item
    public Item getItemByPosition(int position) {
        for (Category menu : categoryArrayList) {
            if (position > 0 && position <= menu.getItemList().size()) {
                return menu.getItemList().get(position - 1);
            } else {
                position -= menu.getItemList().size() + 1;
            }
        }
        return null;
    }

    public Category getMenuOfMenuByPosition(int position) {
        for (Category menu : categoryArrayList) {
            if (position == 0) return menu;
            if (position > 0 && position <= menu.getItemList().size()) {
                return menu;
            } else {
                position -= menu.getItemList().size() + 1;
            }
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }

    public void setShopCartImp(ShopCartImp shopCartImp) {
        this.shopCartImp = shopCartImp;
    }

    private class CategoryViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout right_menu_layout;
        private TextView right_menu_title;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            right_menu_layout = (LinearLayout) itemView.findViewById(R.id.layRightMenu);
            right_menu_title = (TextView) itemView.findViewById(R.id.tvRightMenu);
        }
    }
}
