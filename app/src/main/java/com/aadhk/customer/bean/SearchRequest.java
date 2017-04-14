package com.aadhk.customer.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jack on 12/12/2016.
 */

public class SearchRequest implements Parcelable {
    private String keyword;
    private int maxCount;
    private long userId;
    private int type;
    private double lng;
    private double lat;

    public SearchRequest(String keyword, int type) {
        this.keyword = keyword;
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "keyword='" + keyword + '\'' +
                ", maxCount=" + maxCount +
                ", userId=" + userId +
                ", type=" + type +
                ", lng=" + lng +
                ", lat=" + lat +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.keyword);
        dest.writeInt(this.maxCount);
        dest.writeLong(this.userId);
        dest.writeInt(this.type);
        dest.writeDouble(this.lng);
        dest.writeDouble(this.lat);
    }

    public SearchRequest() {
    }

    protected SearchRequest(Parcel in) {
        this.keyword = in.readString();
        this.maxCount = in.readInt();
        this.userId = in.readLong();
        this.type = in.readInt();
        this.lng = in.readDouble();
        this.lat = in.readDouble();
    }

    public static final Parcelable.Creator<SearchRequest> CREATOR = new Parcelable.Creator<SearchRequest>() {
        @Override
        public SearchRequest createFromParcel(Parcel source) {
            return new SearchRequest(source);
        }

        @Override
        public SearchRequest[] newArray(int size) {
            return new SearchRequest[size];
        }
    };
}
