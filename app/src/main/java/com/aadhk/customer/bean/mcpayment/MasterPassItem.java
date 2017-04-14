package com.aadhk.customer.bean.mcpayment;

/**
 * Created by jack on 30/12/2016.
 */

public class MasterPassItem {
    /**
     * item : Iphone Cover
     * price : 2.51
     * quantity : 1
     * imageurl :
     */

    private String item;
    private double price;
    private double quantity;
    private String imageurl;

    public MasterPassItem(String item, double price, double quantity) {
        this.item = item;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    @Override
    public String toString() {
        return "MasterPassItem{" +
                "item='" + item + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", imageurl='" + imageurl + '\'' +
                '}';
    }
}
