package com.aadhk.customer.bean;

/**
 * Created by jack on 30/11/2016.
 */

public class FoodStyles {
    private int id;
    private int appId;          //	app设置id
    private String foodstyle; //	菜式风格

    public FoodStyles(int appId, String foodstyle) {
        this.appId = appId;
        this.foodstyle = foodstyle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getFoodstyle() {
        return foodstyle;
    }

    public void setFoodstyle(String foodstyle) {
        this.foodstyle = foodstyle;
    }
}
