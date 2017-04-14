package com.aadhk.customer.util.money;

import android.text.TextUtils;

import com.aadhk.customer.bean.OrderItem;
import com.aadhk.customer.bean.PromotionDiscount;
import com.aadhk.customer.util.Constant;

import java.util.List;

public class PromotionPriceDiscount implements PromotionDiscountInterface {

	@Override
	public void discount( List<OrderItem> orderItems, PromotionDiscount schedule) {
		int type = schedule.getDiscountType();
		double discountAmt = schedule.getAmtRate();
		String discountName = schedule.getName();
		List<Long> itemIds = schedule.getItemIds();
		for (OrderItem item : orderItems) {
			if ( itemIds.contains(item.getItemId()) && item.getDiscountAmt() == 0 && TextUtils.isEmpty(item.getDiscountName()) && item.getDiscountType()<= Constant.DISCOUNT_TYPE_PROMOTION) {
				 discountOrderItem(item, discountName, type, discountAmt);
				item.setDiscountName(discountName); //用与区分折上折的条件
			}
		}
	}

	public static void discountOrderItem(OrderItem item, String discountName, int type, double discountAmt) {
		double price = item.getOriginalPrice();
		double discount;
		item.setDiscountName(discountName);
		item.setDiscountType(Constant.DISCOUNT_TYPE_PROMOTION);
		if (type == Constant.PROMOTION_DISCOUNT_TYPE_AMOUNT) {
			discount = discountAmt;
			price = price - discountAmt;
		} else if (type == Constant.PROMOTION_DISCOUNT_TYPE_FIXED_PRICE) {
			discount = price - discountAmt;
			price = discountAmt;
		} else {
			discount = MathUtil.roundAmount(price, discountAmt);
			price = price - discount;
		}
		if (price < 0) {
			price = 0;
			discount = item.getOriginalPrice();
		}
		item.setPrice(price);
		item.setDiscountAmt(discount);
	}
}
