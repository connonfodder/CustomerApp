package com.aadhk.customer.util.money;

import com.aadhk.customer.bean.Address;
import com.aadhk.customer.bean.Company;
import com.aadhk.customer.bean.Delivery;
import com.aadhk.customer.bean.Order;
import com.aadhk.customer.bean.OrderItem;
import com.aadhk.customer.util.Constant;
import com.aadhk.library.utils.LogUtil;

import org.acra.ACRA;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.sort;


/**
 * Created by jack on 22/12/2016.
 */

public class MoneyUtil {

    public static void setupAmount(Order order, Company company) {
        List<OrderItem> orderedItems = order.getOrderItems();
        //时段售价
        priceSchedule(orderedItems, company);
        //小计
        setupSubtotal(order, orderedItems);
        //税
        if (company.isEnableTax()) setupTax(order, orderedItems, company);
        //外送费
        if (order.getOrderType() == Constant.ORDER_TYPE_DELIVERY_CUSTOMER)
            setupDeliveryFee(order, company);
        //计算总额
        setupTotalAmount(order, company);
    }

    /**
     * 按照距离(km)的阶段来计算外送费
     */
    private static void setupDeliveryFee(Order order, Company company) {
        List<Delivery> deliveryList = company.getDeliveryList();
        if (deliveryList == null || deliveryList.size() == 0) return;
        Collections.sort(deliveryList, new DeliveryDistance());   //对配送距离进行排序 从小到大
        Address address = order.getAddress();
        if (address == null) return;
        //店铺的经纬度
        double longitude = company.getLongitude();
        double latitude = company.getLatitude();
        //地址的经纬度
        double longitudeT = address.getLongitude();
        double latitudeT = address.getLatitude();
        LogUtil.d("longitude=" + longitude + ", latitude=" + latitude + ", longitudeT" + longitudeT + ", latitudeT=" + latitudeT);
        int distance = (int) MathUtil.earthDistance(longitude, latitude, longitudeT, latitudeT);  //单位m
        LogUtil.d("distance=" + distance);
//        ToastUtil.showLong("distance="+distance);
        int index = 0;
        try {
            //正序进行  3  5   4  7   d=3.6
            for (int i = deliveryList.size() - 1; i >= 0; i--) {
                LogUtil.d("-----sequence-----" + i + "   " + deliveryList.get(i));
                double deliveryDistance = Double.parseDouble(deliveryList.get(i).getDistance());
                LogUtil.d("deliveryDistance=" + deliveryDistance);
                if (i == deliveryList.size() - 1 && distance > deliveryDistance) {  //超出范围内的  一般来说不可能
                    index = i;
                    LogUtil.d("-------max---------");
                    break;
                }
                /*if (i == 0 && distance < deliveryDistance) {      //在最小范围内
                    index = i;
                    LogUtil.d("-------min---------");
                    break;
                }*/
                if (distance < deliveryDistance) {
                    LogUtil.d("-------normal---------" + i);
                    index = i;
                } else {
                    break;
                }
            }
            double deliveryFee = Double.parseDouble(deliveryList.get(index).getDeliveryFee());
            LogUtil.d("index=" + index + ", deliveryFee=" + deliveryFee);
            order.setDeliveryFee(deliveryFee);
        } catch (Exception e) {
            e.printStackTrace();
			ACRA.getErrorReporter().handleException(e);
        }
    }

    public static double maxDistance(List<Delivery> deliveryList) {
        double max = 0;
        if (deliveryList != null && deliveryList.size() > 0) {
            for (Delivery item : deliveryList) {
                try {
                    Integer d = Integer.parseInt(item.getDistance());
                    if (max < d) {
                        max = d;
                    }
                }catch (NumberFormatException e){
                    continue;
                }
            }
        }
        return max;
    }

    public static void priceSchedule(List<OrderItem> orderItems, Company company) {
        setOrderItemSenquence(orderItems); // set sequence for sort
        PromotionContext priceScheduleContext = new PromotionContext();
        priceScheduleContext.discountOrderItem(orderItems, company);
        sort(orderItems, new OrderItemSenquence());
    }

    public static void setOrderItemSenquence(List<OrderItem> orderItems) {
        for (int i = 0; i < orderItems.size(); i++) {
            OrderItem tem = orderItems.get(i);
            tem.setSequence(i);
        }
    }


    public static class OrderItemSenquence implements Comparator<OrderItem> {

        @Override
        public int compare(OrderItem o1, OrderItem o2) {
            return o1.getSequence() - o2.getSequence();
        }
    }

    public static class DeliveryDistance implements Comparator<Delivery> {

        @Override
        public int compare(Delivery o1, Delivery o2) {
            double d1 = Double.parseDouble(o1.getDistance());
            double d2 = Double.parseDouble(o2.getDistance());
            return (int) (d1 - d2);
//            return o1.getDistance().compareTo(o2.getDistance());
        }
    }


    public static void setupSubtotal(Order order, List<OrderItem> orderItems) {
        double subTotal = 0;
        for (OrderItem orderItem : orderItems) {
            subTotal += orderItem.getPrice() * orderItem.getQty();
        }
        order.setSubTotal(subTotal);
    }

    /**
     * 因为APP这边目前没有和POS完全信息一致，这里税的处理方式为不管外卖，带走税，菜式Item有什么税就累加
     * last-modify: 2016-12-27 15:10:50
     * author: jack chen
     */
    public static void setupTax(Order order, List<OrderItem> orderItemList, Company company) {
        // reset
        double tax1Amount = 0;
        double tax2Amount = 0;
        double tax3Amount = 0; //TODO
        LogUtil.d("company.getTax1()=" + company.getTax1() + ", company.getTax2()=" + company.getTax2() + ", company.getTax3()=" + company.getTax3());
        if (order.getTaxStatus() != Constant.NO_TAX && orderItemList != null) {
            for (OrderItem orderItem : orderItemList) {
                double itemAmt = orderItem.getPrice() * orderItem.getQty();
                if (orderItem.getTax1Id() == Constant.TAX_ID_1)
                    tax1Amount += MathUtil.roundTax(itemAmt, company.getTax1(), company.isItemPriceIncludeTax());
                if (orderItem.getTax2Id() == Constant.TAX_ID_2)
                    tax2Amount += MathUtil.roundTax(itemAmt, company.getTax2(), company.isItemPriceIncludeTax());
                if (orderItem.getTax3Id() == Constant.TAX_ID_3)
                    tax3Amount += MathUtil.roundTax(itemAmt, company.getTax3(), company.isItemPriceIncludeTax());
                /*tax1Amount += setupOrderItemTax(company, orderItem, Constant.TAX_ID_1, order.getTableId());
                tax2Amount += setupOrderItemTax(company, orderItem, Constant.TAX_ID_2, order.getTableId());
                tax3Amount += setupOrderItemTax(company, orderItem, Constant.TAX_ID_3, order.getTableId());*/
            }
        }
        order.setTax1Name(company.getTax1Name());
        order.setTax2Name(company.getTax2Name());
        order.setTax3Name(company.getTax3Name());
        if (tax1Amount == 0) order.setTax1Name("");
        if (tax2Amount == 0) order.setTax2Name("");
        if (tax3Amount == 0) order.setTax3Name("");
        order.setTax1Amt(tax1Amount);
        order.setTax2Amt(tax2Amount);
        order.setTax3Amt(tax3Amount);
    }

    private static double setupOrderItemTax(Company company, OrderItem orderItem, int taxId, long tableId) {
        double acmount = 0;
        double itemAmt = orderItem.getPrice() * orderItem.getQty();
        if (tableId == Constant.TABLE_ID_TAKEOUT || tableId == Constant.TABLE_ID_DELIVERY) {
            if (orderItem.getTakeoutTax1Id() == taxId) {
                acmount = MathUtil.roundTax(itemAmt, company.getTax1(), company.isItemPriceIncludeTax());
            } else if (orderItem.getTakeoutTax2Id() == taxId) {
                acmount = MathUtil.roundTax(itemAmt, company.getTax2(), company.isItemPriceIncludeTax());
            } else if (orderItem.getTakeoutTax3Id() == taxId) {
                acmount = MathUtil.roundTax(itemAmt, company.getTax3(), company.isItemPriceIncludeTax());
            }
        } else {
            if (orderItem.getTax1Id() == taxId) {
                acmount = MathUtil.roundTax(itemAmt, company.getTax1(), company.isItemPriceIncludeTax());
            } else if (orderItem.getTax2Id() == taxId) {
                acmount = MathUtil.roundTax(itemAmt, company.getTax2(), company.isItemPriceIncludeTax());
            } else if (orderItem.getTax3Id() == taxId) {
                acmount = MathUtil.roundTax(itemAmt, company.getTax3(), company.isItemPriceIncludeTax());
            }
        }
        return acmount;
    }

    public static void setupTotalAmount(Order order, Company company) {
        double totalAmount = MathUtil.roundTotal(order.getSubTotal(), order.getTax1Amt() + order.getTax2Amt() + order.getTax3Amt(), order.getServiceAmt(), order.getDeliveryFee(), order.getGratuity(), order.getDiscountAmt(), company.isItemPriceIncludeTax());
        order.setAmount(totalAmount);
    }

}
