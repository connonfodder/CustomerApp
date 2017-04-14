package com.aadhk.customer.util.money;


import com.aadhk.customer.bean.OrderItem;
import com.aadhk.customer.bean.PromotionDiscount;

import java.util.List;

/**
 * 时段售价的抽象类
 * 这里仅仅手机端使用
 * @author jack
 * last modify : 2016-12-22 10:20:45
 */
public interface PromotionDiscountInterface {
	void discount(List<OrderItem> orderItems, PromotionDiscount schedule);
}