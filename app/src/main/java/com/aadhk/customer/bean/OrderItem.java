package com.aadhk.customer.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jack on 05/12/2016.
 */

public class OrderItem implements Parcelable {

    private long id;
    private long itemId;
    private long orderId;
    private long billId;
    private long categoryId;
    private String categoryName;
    private int categorySequence;
    protected String itemName;
    protected String kitchenItemName;
    private String tableName;
    protected double price;
    protected double originalPrice;

    protected double cost;
    protected boolean stopSaleZeroQty;
    private double qty;
    private String remark;
    private String orderTime;
    private String endTime;
    private String cancelReason;
    private int status;
    //    private List<OrderModifier> orderModifiers;
    private String printerIds;
    private String kitchenDisplayIds;
    private int tax1Id;
    private int tax2Id;
    private int tax3Id;
    private int takeoutTax1Id;
    private int takeoutTax2Id;
    private int takeoutTax3Id;
    private String modifierGroupId;
    private String kitchenNoteGroupId;
    private String discountName;
    private int discountType;   //0:正常无打折   	1:客户类型打折  (按照优先级) 2:时段售价   3:用户修改
    private List<Long> combineItemIds; //for kitchen display
    private double discountAmt;

    private double orderQty;
    private boolean warn;
    private double warnQty;
    private boolean modifierMust;//
    private boolean kitchenNoteMust;
    private double memberPrice1;
    private double memberPrice2;
    private double memberPrice3;
    private boolean discountable;
    private String discountableName;
    private boolean hasLine;
    private double modifierMaximum;

    // for split bill
    private boolean selected;
    private int sequence;
    private boolean isGift;

    public OrderItem(String itemName, double price, double qty) {
        this.itemName = itemName;
        this.price = price;
        this.qty = qty;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategorySequence() {
        return categorySequence;
    }

    public void setCategorySequence(int categorySequence) {
        this.categorySequence = categorySequence;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getKitchenItemName() {
        return kitchenItemName;
    }

    public void setKitchenItemName(String kitchenItemName) {
        this.kitchenItemName = kitchenItemName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isStopSaleZeroQty() {
        return stopSaleZeroQty;
    }

    public void setStopSaleZeroQty(boolean stopSaleZeroQty) {
        this.stopSaleZeroQty = stopSaleZeroQty;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPrinterIds() {
        return printerIds;
    }

    public void setPrinterIds(String printerIds) {
        this.printerIds = printerIds;
    }

    public String getKitchenDisplayIds() {
        return kitchenDisplayIds;
    }

    public void setKitchenDisplayIds(String kitchenDisplayIds) {
        this.kitchenDisplayIds = kitchenDisplayIds;
    }

    public int getTax1Id() {
        return tax1Id;
    }

    public void setTax1Id(int tax1Id) {
        this.tax1Id = tax1Id;
    }

    public int getTax2Id() {
        return tax2Id;
    }

    public void setTax2Id(int tax2Id) {
        this.tax2Id = tax2Id;
    }

    public int getTax3Id() {
        return tax3Id;
    }

    public void setTax3Id(int tax3Id) {
        this.tax3Id = tax3Id;
    }

    public int getTakeoutTax1Id() {
        return takeoutTax1Id;
    }

    public void setTakeoutTax1Id(int takeoutTax1Id) {
        this.takeoutTax1Id = takeoutTax1Id;
    }

    public int getTakeoutTax2Id() {
        return takeoutTax2Id;
    }

    public void setTakeoutTax2Id(int takeoutTax2Id) {
        this.takeoutTax2Id = takeoutTax2Id;
    }

    public int getTakeoutTax3Id() {
        return takeoutTax3Id;
    }

    public void setTakeoutTax3Id(int takeoutTax3Id) {
        this.takeoutTax3Id = takeoutTax3Id;
    }

    public String getModifierGroupId() {
        return modifierGroupId;
    }

    public void setModifierGroupId(String modifierGroupId) {
        this.modifierGroupId = modifierGroupId;
    }

    public String getKitchenNoteGroupId() {
        return kitchenNoteGroupId;
    }

    public void setKitchenNoteGroupId(String kitchenNoteGroupId) {
        this.kitchenNoteGroupId = kitchenNoteGroupId;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public int getDiscountType() {
        return discountType;
    }

    public void setDiscountType(int discountType) {
        this.discountType = discountType;
    }

    public List<Long> getCombineItemIds() {
        return combineItemIds;
    }

    public void setCombineItemIds(List<Long> combineItemIds) {
        this.combineItemIds = combineItemIds;
    }

    public double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(double discountAmt) {
        this.discountAmt = discountAmt;
    }

    public double getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(double orderQty) {
        this.orderQty = orderQty;
    }

    public boolean isWarn() {
        return warn;
    }

    public void setWarn(boolean warn) {
        this.warn = warn;
    }

    public double getWarnQty() {
        return warnQty;
    }

    public void setWarnQty(double warnQty) {
        this.warnQty = warnQty;
    }

    public boolean isModifierMust() {
        return modifierMust;
    }

    public void setModifierMust(boolean modifierMust) {
        this.modifierMust = modifierMust;
    }

    public boolean isKitchenNoteMust() {
        return kitchenNoteMust;
    }

    public void setKitchenNoteMust(boolean kitchenNoteMust) {
        this.kitchenNoteMust = kitchenNoteMust;
    }

    public double getMemberPrice1() {
        return memberPrice1;
    }

    public void setMemberPrice1(double memberPrice1) {
        this.memberPrice1 = memberPrice1;
    }

    public double getMemberPrice2() {
        return memberPrice2;
    }

    public void setMemberPrice2(double memberPrice2) {
        this.memberPrice2 = memberPrice2;
    }

    public double getMemberPrice3() {
        return memberPrice3;
    }

    public void setMemberPrice3(double memberPrice3) {
        this.memberPrice3 = memberPrice3;
    }

    public boolean isDiscountable() {
        return discountable;
    }

    public void setDiscountable(boolean discountable) {
        this.discountable = discountable;
    }

    public String getDiscountableName() {
        return discountableName;
    }

    public void setDiscountableName(String discountableName) {
        this.discountableName = discountableName;
    }

    public boolean isHasLine() {
        return hasLine;
    }

    public void setHasLine(boolean hasLine) {
        this.hasLine = hasLine;
    }

    public double getModifierMaximum() {
        return modifierMaximum;
    }

    public void setModifierMaximum(double modifierMaximum) {
        this.modifierMaximum = modifierMaximum;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public boolean isGift() {
        return isGift;
    }

    public void setGift(boolean gift) {
        isGift = gift;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OrderItem)) return false;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        OrderItem other = (OrderItem) o;
        if (other.getId() != this.getId()) return false;
        if (other.getCategoryId() != this.getCategoryId()) return false;
        return other.getItemId() == this.getItemId();
    }

    public OrderItem() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.itemId);
        dest.writeLong(this.orderId);
        dest.writeLong(this.billId);
        dest.writeLong(this.categoryId);
        dest.writeString(this.categoryName);
        dest.writeInt(this.categorySequence);
        dest.writeString(this.itemName);
        dest.writeString(this.kitchenItemName);
        dest.writeString(this.tableName);
        dest.writeDouble(this.price);
        dest.writeDouble(this.originalPrice);
        dest.writeDouble(this.cost);
        dest.writeByte(this.stopSaleZeroQty ? (byte) 1 : (byte) 0);
        dest.writeDouble(this.qty);
        dest.writeString(this.remark);
        dest.writeString(this.orderTime);
        dest.writeString(this.endTime);
        dest.writeString(this.cancelReason);
        dest.writeInt(this.status);
        dest.writeString(this.printerIds);
        dest.writeString(this.kitchenDisplayIds);
        dest.writeInt(this.tax1Id);
        dest.writeInt(this.tax2Id);
        dest.writeInt(this.tax3Id);
        dest.writeInt(this.takeoutTax1Id);
        dest.writeInt(this.takeoutTax2Id);
        dest.writeInt(this.takeoutTax3Id);
        dest.writeString(this.modifierGroupId);
        dest.writeString(this.kitchenNoteGroupId);
        dest.writeString(this.discountName);
        dest.writeInt(this.discountType);
        dest.writeList(this.combineItemIds);
        dest.writeDouble(this.discountAmt);
        dest.writeDouble(this.orderQty);
        dest.writeByte(this.warn ? (byte) 1 : (byte) 0);
        dest.writeDouble(this.warnQty);
        dest.writeByte(this.modifierMust ? (byte) 1 : (byte) 0);
        dest.writeByte(this.kitchenNoteMust ? (byte) 1 : (byte) 0);
        dest.writeDouble(this.memberPrice1);
        dest.writeDouble(this.memberPrice2);
        dest.writeDouble(this.memberPrice3);
        dest.writeByte(this.discountable ? (byte) 1 : (byte) 0);
        dest.writeString(this.discountableName);
        dest.writeByte(this.hasLine ? (byte) 1 : (byte) 0);
        dest.writeDouble(this.modifierMaximum);
        dest.writeByte(this.selected ? (byte) 1 : (byte) 0);
        dest.writeInt(this.sequence);
        dest.writeByte(this.isGift ? (byte) 1 : (byte) 0);
    }

    protected OrderItem(Parcel in) {
        this.id = in.readLong();
        this.itemId = in.readLong();
        this.orderId = in.readLong();
        this.billId = in.readLong();
        this.categoryId = in.readLong();
        this.categoryName = in.readString();
        this.categorySequence = in.readInt();
        this.itemName = in.readString();
        this.kitchenItemName = in.readString();
        this.tableName = in.readString();
        this.price = in.readDouble();
        this.originalPrice = in.readDouble();
        this.cost = in.readDouble();
        this.stopSaleZeroQty = in.readByte() != 0;
        this.qty = in.readDouble();
        this.remark = in.readString();
        this.orderTime = in.readString();
        this.endTime = in.readString();
        this.cancelReason = in.readString();
        this.status = in.readInt();
        this.printerIds = in.readString();
        this.kitchenDisplayIds = in.readString();
        this.tax1Id = in.readInt();
        this.tax2Id = in.readInt();
        this.tax3Id = in.readInt();
        this.takeoutTax1Id = in.readInt();
        this.takeoutTax2Id = in.readInt();
        this.takeoutTax3Id = in.readInt();
        this.modifierGroupId = in.readString();
        this.kitchenNoteGroupId = in.readString();
        this.discountName = in.readString();
        this.discountType = in.readInt();
        this.combineItemIds = new ArrayList<>();
        in.readList(this.combineItemIds, Long.class.getClassLoader());
        this.discountAmt = in.readDouble();
        this.orderQty = in.readDouble();
        this.warn = in.readByte() != 0;
        this.warnQty = in.readDouble();
        this.modifierMust = in.readByte() != 0;
        this.kitchenNoteMust = in.readByte() != 0;
        this.memberPrice1 = in.readDouble();
        this.memberPrice2 = in.readDouble();
        this.memberPrice3 = in.readDouble();
        this.discountable = in.readByte() != 0;
        this.discountableName = in.readString();
        this.hasLine = in.readByte() != 0;
        this.modifierMaximum = in.readDouble();
        this.selected = in.readByte() != 0;
        this.sequence = in.readInt();
        this.isGift = in.readByte() != 0;
    }

    public static final Parcelable.Creator<OrderItem> CREATOR = new Parcelable.Creator<OrderItem>() {
        @Override
        public OrderItem createFromParcel(Parcel source) {
            return new OrderItem(source);
        }

        @Override
        public OrderItem[] newArray(int size) {
            return new OrderItem[size];
        }
    };
}
