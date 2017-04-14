package com.aadhk.customer.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jack on 01/12/2016.
 */

public class CompanyRequest implements Parcelable {

    private String keyWord;
    private int restaurantType;
    private List<FoodStyles> foodStyles;
    public double latitude;
    public double longitude;
    public int count;
    public int page;

    public CompanyRequest(int page){
        this.page = page;
    }
    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(int restaurantType) {
        this.restaurantType = restaurantType;
    }

    public List<FoodStyles> getFoodStyles() {
        return foodStyles;
    }

    public void setFoodStyles(List<FoodStyles> foodStyles) {
        this.foodStyles = foodStyles;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.keyWord);
        dest.writeInt(this.restaurantType);
        dest.writeList(this.foodStyles);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeInt(this.count);
        dest.writeInt(this.page);
    }

    public CompanyRequest() {
    }

    protected CompanyRequest(Parcel in) {
        this.keyWord = in.readString();
        this.restaurantType = in.readInt();
        this.foodStyles = new ArrayList<>();
        in.readList(this.foodStyles, FoodStyles.class.getClassLoader());
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.count = in.readInt();
        this.page = in.readInt();
    }

    public static final Parcelable.Creator<CompanyRequest> CREATOR = new Parcelable.Creator<CompanyRequest>() {
        @Override
        public CompanyRequest createFromParcel(Parcel source) {
            return new CompanyRequest(source);
        }

        @Override
        public CompanyRequest[] newArray(int size) {
            return new CompanyRequest[size];
        }
    };
}
