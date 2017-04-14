package com.aadhk.customer.util.money;


import com.aadhk.customer.bean.Company;
import com.aadhk.customer.bean.OrderItem;
import com.aadhk.customer.bean.PromotionDiscount;
import com.aadhk.customer.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class PromotionContext {

    private PromotionDiscountInterface strategy;

    public void discountOrderItem( List<OrderItem> orderItems, Company company) {
        List<PromotionDiscount> priceScheduleList = company.getPromotionDiscountList();
        if (priceScheduleList != null) {
            if (priceScheduleList.size() > 0) {
                recoveryOrderItem(orderItems, Constant.DISCOUNT_TYPE_PROMOTION);
            }
            for (PromotionDiscount schedule : priceScheduleList) {
                int model = schedule.getPromotionType();
                switch (model) {
                    case Constant.PromotionActivity_PROMOTION_DISCOUNT_PRICE:
                        strategy = new PromotionPriceDiscount();
                        break;

                  /*  case Constant.PromotionActivity_PROMOTION_DISCOUNT_QUANTITY:
                        if (schedule.getDiscountItemType() == Constant.DISCOUNT_ITEM_TYPE_ALL) {
                            strategy = new PromotionQuantityAllDiscount();
                        } else if (schedule.getDiscountItemType() == Constant.DISCOUNT_ITEM_TYPE_OVER) {
                            strategy = new PromotionQuantityOverDiscount();
                        } else if (schedule.getDiscountItemType() == Constant.DISCOUNT_ITEM_TYPE_SPECIFIC) {
                            strategy = new PromotionQuantityDiscount();
                        } else {
                            strategy = new PromotionQuantityDiscount();
                        }
                        break;

                    case Constant.PromotionActivity_PROMOTION_DISCOUNT_COMBINATION:
                        strategy = new PromotionCombineDiscount();
                        break;*/
                }
                strategy.discount(orderItems, schedule);
            }
        }
    }

    public static void recoveryOrderItem( List<OrderItem> orderItems, int discountType) {
        List<OrderItem> tem = new ArrayList<>();
        tem.addAll(orderItems);
        orderItems.clear();
        for (OrderItem it : tem) {
            if (it.getDiscountType() <= discountType) {  //0:正常无打折   	1:客户类型打折   2:时段售价   3:用户修改 (按照优先级)
                it.setDiscountName(null);
                it.setDiscountAmt(0.0);
                it.setPrice(it.getOriginalPrice());

                /*if (!useSeparateItem) {
                    int index = orderItems.indexOf(it);
                    if (index >= 0) {
                        OrderItem oldOrderItem = orderItems.get(index);
                        if (oldOrderItem.equals(it)) {
                            oldOrderItem.setQty(oldOrderItem.getQty() + it.getQty());
                            continue;
                        }
                    }
                }*/
            }
            orderItems.add(it);
        }
    }
}
