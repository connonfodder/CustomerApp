package com.aadhk.customer.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cheng on 16-11-10.
 */
public class Category implements Parcelable {

    private long id;
    private int companyId;
    private String name;
    private int sequence;
    private String backgroundColor;
    private String fontColor;
    private boolean enable;
    private List<Item> itemList;


    public Category() {
        this.itemList = new ArrayList<>();
    }

    public Category(String name, ArrayList dishList) {
        this.name = name;
        this.itemList = dishList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Category clone() {
        Parcel p = Parcel.obtain();
        p.writeValue(this);
        p.setDataPosition(0);
        Category newItem = (Category) p.readValue(Category.class.getClassLoader());
        p.recycle();
        return newItem;
    }

    @Override
    public boolean equals(Object o) {
        Category other = (Category) o;
        if (this != other) return false;
        if (this.id != other.id) return false;
        return this.name.equals(other.getName());
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", name='" + name + '\'' +
                ", sequence=" + sequence +
                ", backgroundColor='" + backgroundColor + '\'' +
                ", fontColor='" + fontColor + '\'' +
                ", enable=" + enable +
                ", itemList=" + itemList +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeInt(this.companyId);
        dest.writeString(this.name);
        dest.writeInt(this.sequence);
        dest.writeString(this.backgroundColor);
        dest.writeString(this.fontColor);
        dest.writeByte(this.enable ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.itemList);
    }

    protected Category(Parcel in) {
        this.id = in.readLong();
        this.companyId = in.readInt();
        this.name = in.readString();
        this.sequence = in.readInt();
        this.backgroundColor = in.readString();
        this.fontColor = in.readString();
        this.enable = in.readByte() != 0;
        this.itemList = in.createTypedArrayList(Item.CREATOR);
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
