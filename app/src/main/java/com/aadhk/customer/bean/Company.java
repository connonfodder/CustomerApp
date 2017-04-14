package com.aadhk.customer.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Company implements Parcelable {

    private long id;
    private String logoPath;
    private String name;
    private int type;                   //支持点餐类型  1=delivery 2=takeout 4=dinein
    private String contactNum;          //接单电话
    private String operatorTel;         //店铺负责人电话
    private String address;             //店铺地址
    private double longitude;
    private double latitude;
    private String businessStartTime;
    private String businessEndTime;
    private double aveCharge;           //人均消费
    private double minimumOrder;
    private double cashOnDelivery;
    private boolean businessStatus;
    private List<String> styles;        //菜式风格
    private int distance;               //距离
    private List<Category> categoryList; //菜式
    private double serviceCharge;       //附加费
    private List<Delivery> deliveryList;  //外送费
    private List<PromotionDiscount> promotionDiscountList;  //时段售价
    //税
    private double tax1;
    private double tax2;
    private double tax3;
    private String tax1Name;
    private String tax2Name;
    private String tax3Name;
    private boolean itemPriceIncludeTax;
    private boolean deliveryAfterTax;
    private boolean enableTax;

    private long tableId;   //只是传参作用

    public Company(){}

    public Company(String name, int type){
        this.name = name;
        this.type = type;
    }

    public Company(String logoPath, String name, List<String> styles, int distance, int type) {
        this.logoPath = logoPath;
        this.name = name;
        this.styles = styles;
        this.distance = distance;
        this.type = type;
    }

    public Company(String logoPath, String name, double aveCharge, List<String> styles, int distance, int type, boolean businessStatus) {
        this.logoPath = logoPath;
        this.name = name;
        this.aveCharge = aveCharge;
        this.styles = styles;
        this.distance = distance;
        this.type = type;
        this.businessStatus = businessStatus;
    }

    public Company clone() {
        Parcel p = Parcel.obtain();
        p.writeValue(this);
        p.setDataPosition(0);
        Company newItem = (Company) p.readValue(Company.class.getClassLoader());
        p.recycle();
        return newItem;
    }

    public double getMinimumOrder() {
        return minimumOrder;
    }

    public void setMinimumOrder(double minimumOrder) {
        this.minimumOrder = minimumOrder;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getOperatorTel() {
        return operatorTel;
    }

    public void setOperatorTel(String operatorTel) {
        this.operatorTel = operatorTel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getBusinessStartTime() {
        return businessStartTime;
    }

    public void setBusinessStartTime(String businessStartTime) {
        this.businessStartTime = businessStartTime;
    }

    public String getBusinessEndTime() {
        return businessEndTime;
    }

    public void setBusinessEndTime(String businessEndTime) {
        this.businessEndTime = businessEndTime;
    }

    public double getAveCharge() {
        return aveCharge;
    }

    public void setAveCharge(double aveCharge) {
        this.aveCharge = aveCharge;
    }

    public double getCashOnDelivery() {
        return cashOnDelivery;
    }

    public void setCashOnDelivery(double cashOnDelivery) {
        this.cashOnDelivery = cashOnDelivery;
    }

    public boolean isBusinessStatus() {
        return !businessStatus;
    }

    public void setBusinessStatus(boolean businessStatus) {
        this.businessStatus = businessStatus;
    }

    public List<String> getStyles() {
        return styles;
    }

    public void setStyles(List<String> styles) {
        this.styles = styles;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public List<Delivery> getDeliveryList() {
        return deliveryList;
    }

    public void setDeliveryList(List<Delivery> deliveryList) {
        this.deliveryList = deliveryList;
    }

    public List<PromotionDiscount> getPromotionDiscountList() {
        return promotionDiscountList;
    }

    public void setPromotionDiscountList(List<PromotionDiscount> promotionDiscountList) {
        this.promotionDiscountList = promotionDiscountList;
    }

    public double getTax1() {
        return tax1;
    }

    public void setTax1(double tax1) {
        this.tax1 = tax1;
    }

    public double getTax2() {
        return tax2;
    }

    public void setTax2(double tax2) {
        this.tax2 = tax2;
    }

    public double getTax3() {
        return tax3;
    }

    public void setTax3(double tax3) {
        this.tax3 = tax3;
    }

    public String getTax1Name() {
        return tax1Name;
    }

    public void setTax1Name(String tax1Name) {
        this.tax1Name = tax1Name;
    }

    public String getTax2Name() {
        return tax2Name;
    }

    public void setTax2Name(String tax2Name) {
        this.tax2Name = tax2Name;
    }

    public String getTax3Name() {
        return tax3Name;
    }

    public void setTax3Name(String tax3Name) {
        this.tax3Name = tax3Name;
    }

    public boolean isItemPriceIncludeTax() {
        return itemPriceIncludeTax;
    }

    public void setItemPriceIncludeTax(boolean itemPriceIncludeTax) {
        this.itemPriceIncludeTax = itemPriceIncludeTax;
    }

    public boolean isDeliveryAfterTax() {
        return deliveryAfterTax;
    }

    public void setDeliveryAfterTax(boolean deliveryAfterTax) {
        this.deliveryAfterTax = deliveryAfterTax;
    }

    public boolean isEnableTax() {
        return enableTax;
    }

    public void setEnableTax(boolean enableTax) {
        this.enableTax = enableTax;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", logoPath='" + logoPath + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", contactNum='" + contactNum + '\'' +
                ", operatorTel='" + operatorTel + '\'' +
                ", address='" + address + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", businessStartTime='" + businessStartTime + '\'' +
                ", businessEndTime='" + businessEndTime + '\'' +
                ", aveCharge=" + aveCharge +
                ", cashOnDelivery=" + cashOnDelivery +
                ", businessStatus=" + businessStatus +
                ", styles=" + styles +
                ", distance=" + distance +
                ", categoryList=" + categoryList +
                ", serviceCharge=" + serviceCharge +
                ", deliveryList=" + deliveryList +
                ", promotionDiscountList=" + promotionDiscountList +
                ", tax1=" + tax1 +
                ", tax2=" + tax2 +
                ", tax3=" + tax3 +
                ", tax1Name='" + tax1Name + '\'' +
                ", tax2Name='" + tax2Name + '\'' +
                ", tax3Name='" + tax3Name + '\'' +
                ", itemPriceIncludeTax=" + itemPriceIncludeTax +
                ", deliveryAfterTax=" + deliveryAfterTax +
                ", enableTax=" + enableTax +
                ", tableId=" + tableId +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.logoPath);
        dest.writeString(this.name);
        dest.writeInt(this.type);
        dest.writeString(this.contactNum);
        dest.writeString(this.operatorTel);
        dest.writeString(this.address);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.latitude);
        dest.writeString(this.businessStartTime);
        dest.writeString(this.businessEndTime);
        dest.writeDouble(this.aveCharge);
        dest.writeDouble(this.cashOnDelivery);
        dest.writeByte(this.businessStatus ? (byte) 1 : (byte) 0);
        dest.writeStringList(this.styles);
        dest.writeInt(this.distance);
        dest.writeTypedList(this.categoryList);
        dest.writeDouble(this.serviceCharge);
        dest.writeTypedList(this.deliveryList);
        dest.writeTypedList(this.promotionDiscountList);
        dest.writeDouble(this.tax1);
        dest.writeDouble(this.tax2);
        dest.writeDouble(this.tax3);
        dest.writeString(this.tax1Name);
        dest.writeString(this.tax2Name);
        dest.writeString(this.tax3Name);
        dest.writeByte(this.itemPriceIncludeTax ? (byte) 1 : (byte) 0);
        dest.writeByte(this.deliveryAfterTax ? (byte) 1 : (byte) 0);
        dest.writeByte(this.enableTax ? (byte) 1 : (byte) 0);
        dest.writeLong(this.tableId);
        dest.writeDouble(this.minimumOrder);
    }

    protected Company(Parcel in) {
        this.id = in.readLong();
        this.logoPath = in.readString();
        this.name = in.readString();
        this.type = in.readInt();
        this.contactNum = in.readString();
        this.operatorTel = in.readString();
        this.address = in.readString();
        this.longitude = in.readDouble();
        this.latitude = in.readDouble();
        this.businessStartTime = in.readString();
        this.businessEndTime = in.readString();
        this.aveCharge = in.readDouble();
        this.cashOnDelivery = in.readDouble();
        this.businessStatus = in.readByte() != 0;
        this.styles = in.createStringArrayList();
        this.distance = in.readInt();
        this.categoryList = in.createTypedArrayList(Category.CREATOR);
        this.serviceCharge = in.readDouble();
        this.deliveryList = in.createTypedArrayList(Delivery.CREATOR);
        this.promotionDiscountList = in.createTypedArrayList(PromotionDiscount.CREATOR);
        this.tax1 = in.readDouble();
        this.tax2 = in.readDouble();
        this.tax3 = in.readDouble();
        this.tax1Name = in.readString();
        this.tax2Name = in.readString();
        this.tax3Name = in.readString();
        this.itemPriceIncludeTax = in.readByte() != 0;
        this.deliveryAfterTax = in.readByte() != 0;
        this.enableTax = in.readByte() != 0;
        this.tableId = in.readLong();
        this.minimumOrder = in.readDouble();
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel source) {
            return new Company(source);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    public String toString1() {
        return "minimumOrder="+minimumOrder;
    }
}
