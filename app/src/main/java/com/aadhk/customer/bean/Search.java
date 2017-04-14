package com.aadhk.customer.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jack on 06/12/2016.
 * 搜索: 分为热点和个人纪录
 *
 *  只有成功获取数据并点击了才将关键词存入服务器的个人纪录表中，否则表示无效搜索
 *
 "success":"true",
 "msg":"查询成功",
 "data":[
 {
 "customerId":"1001",
 "keyWord","meat",
 },
 {
 "customerId":"1001",
 "keyWord","rice",
 },
 .......
 ]
 */

public class Search implements Parcelable {

    private int customerId;
    private String keyWord;

    public Search(int customerId, String keyWord) {
        this.customerId = customerId;
        this.keyWord = keyWord;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public String toString() {
        return "Search{" +
                "customerId=" + customerId +
                ", keyWord='" + keyWord + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.customerId);
        dest.writeString(this.keyWord);
    }

    protected Search(Parcel in) {
        this.customerId = in.readInt();
        this.keyWord = in.readString();
    }

    public static final Parcelable.Creator<Search> CREATOR = new Parcelable.Creator<Search>() {
        @Override
        public Search createFromParcel(Parcel source) {
            return new Search(source);
        }

        @Override
        public Search[] newArray(int size) {
            return new Search[size];
        }
    };
}
