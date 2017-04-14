package com.aadhk.customer.bean.mcpayment;

import java.util.List;

/**
 * Created by jack on 30/12/2016.
 */

public class PaymentData {

    private long orderid;
    private double amount;
    private List<MasterPassItem> itemList;
    private boolean finished;

    public long getOrderid() {
        return orderid;
    }

    public void setOrderid(long orderid) {
        this.orderid = orderid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<MasterPassItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<MasterPassItem> itemList) {
        this.itemList = itemList;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "PaymentData{" +
                "orderid=" + orderid +
                ", amount=" + amount +
                ", itemList=" + itemList +
                ", finished=" + finished +
                '}';
    }
}
