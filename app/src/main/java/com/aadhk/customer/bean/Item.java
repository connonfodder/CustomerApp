package com.aadhk.customer.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {

    private long id;
    private long categoryId;
    private String name;
    private String kitchenItemName;
    private double price;
    private double cost;
    private double qty;
    private String modifierGroupIds;
    private String kitchenNoteGroupIds;
    private String printerIds;
    private String kitchenDisplayIds;
    private String picture;
    private String background;
    private String fontColor;
    private String description;
    private int sequence;
    private int tax1Id;
    private int tax2Id;
    private int tax3Id;
    private int takeoutTax1Id;
    private int takeoutTax2Id;
    private int takeoutTax3Id;
    private boolean picked;//for selector only
    private boolean modifierMust;//for selector only
    private boolean kitchenNoteMust;//for selector only

    private double orderQty;
    private boolean stopSale;
    private boolean askPrice, askQuantity, scale;
    private boolean stopSaleZeroQty;
    private double warnQty;
    private int modifierMaximum;

    private String imagePath;
    private double takeOutPrice;
    private double deliveryPrice;
    private String barCode1;
    private String barCode2;
    private String barCode3;
    private double memberPrice1;
    private double memberPrice2;
    private double memberPrice3;
    private boolean enable;
    private boolean discountable;
    private boolean isGift;
    private boolean isCustomerApp;
    private boolean isHideInfo;

    //    private List<InventoryDishRecipe> recipes;
    private int locationId;
    private double purchasePrice;

    private int amount;
    private String logoPath;


    public Item(long id, String name, String description, double price, int amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

    public Item(long id){
        this.id = id;
    }

    public Item(long id, String name, String description, double price, int i1, String logoPath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.logoPath = logoPath;
    }

    public Item clone() {
        Parcel p = Parcel.obtain();
        p.writeValue(this);
        p.setDataPosition(0);
        Item newItem = (Item) p.readValue(Item.class.getClassLoader());
        p.recycle();
        return newItem;
    }

    public int getTax2Id() {
        return tax2Id;
    }

    public void setTax2Id(int tax2Id) {
        this.tax2Id = tax2Id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKitchenItemName() {
        return kitchenItemName;
    }

    public void setKitchenItemName(String kitchenItemName) {
        this.kitchenItemName = kitchenItemName;
    }

    public double getPrice() {
        return price;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public String getModifierGroupIds() {
        return modifierGroupIds;
    }

    public void setModifierGroupIds(String modifierGroupIds) {
        this.modifierGroupIds = modifierGroupIds;
    }

    public String getKitchenNoteGroupIds() {
        return kitchenNoteGroupIds;
    }

    public void setKitchenNoteGroupIds(String kitchenNoteGroupIds) {
        this.kitchenNoteGroupIds = kitchenNoteGroupIds;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getTax1Id() {
        return tax1Id;
    }

    public void setTax1Id(int tax1Id) {
        this.tax1Id = tax1Id;
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

    public boolean isPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
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

    public double getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(double orderQty) {
        this.orderQty = orderQty;
    }

    public boolean isStopSale() {
        return stopSale;
    }

    public void setStopSale(boolean stopSale) {
        this.stopSale = stopSale;
    }

    public boolean isAskPrice() {
        return askPrice;
    }

    public void setAskPrice(boolean askPrice) {
        this.askPrice = askPrice;
    }

    public boolean isAskQuantity() {
        return askQuantity;
    }

    public void setAskQuantity(boolean askQuantity) {
        this.askQuantity = askQuantity;
    }

    public boolean isScale() {
        return scale;
    }

    public void setScale(boolean scale) {
        this.scale = scale;
    }

    public boolean isStopSaleZeroQty() {
        return stopSaleZeroQty;
    }

    public void setStopSaleZeroQty(boolean stopSaleZeroQty) {
        this.stopSaleZeroQty = stopSaleZeroQty;
    }

    public double getWarnQty() {
        return warnQty;
    }

    public void setWarnQty(double warnQty) {
        this.warnQty = warnQty;
    }

    public int getModifierMaximum() {
        return modifierMaximum;
    }

    public void setModifierMaximum(int modifierMaximum) {
        this.modifierMaximum = modifierMaximum;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getTakeOutPrice() {
        return takeOutPrice;
    }

    public void setTakeOutPrice(double takeOutPrice) {
        this.takeOutPrice = takeOutPrice;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getBarCode1() {
        return barCode1;
    }

    public void setBarCode1(String barCode1) {
        this.barCode1 = barCode1;
    }

    public String getBarCode2() {
        return barCode2;
    }

    public void setBarCode2(String barCode2) {
        this.barCode2 = barCode2;
    }

    public String getBarCode3() {
        return barCode3;
    }

    public void setBarCode3(String barCode3) {
        this.barCode3 = barCode3;
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

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isDiscountable() {
        return discountable;
    }

    public void setDiscountable(boolean discountable) {
        this.discountable = discountable;
    }

    public boolean isGift() {
        return isGift;
    }

    public void setGift(boolean gift) {
        isGift = gift;
    }

    public boolean isCustomerApp() {
        return isCustomerApp;
    }

    public void setCustomerApp(boolean customerApp) {
        isCustomerApp = customerApp;
    }

    public boolean isHideInfo() {
        return isHideInfo;
    }

    public void setHideInfo(boolean hideInfo) {
        isHideInfo = hideInfo;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.categoryId);
        dest.writeString(this.name);
        dest.writeString(this.kitchenItemName);
        dest.writeDouble(this.price);
        dest.writeDouble(this.cost);
        dest.writeDouble(this.qty);
        dest.writeString(this.modifierGroupIds);
        dest.writeString(this.kitchenNoteGroupIds);
        dest.writeString(this.printerIds);
        dest.writeString(this.kitchenDisplayIds);
        dest.writeString(this.picture);
        dest.writeString(this.background);
        dest.writeString(this.fontColor);
        dest.writeString(this.description);
        dest.writeInt(this.sequence);
        dest.writeInt(this.tax1Id);
        dest.writeInt(this.tax2Id);
        dest.writeInt(this.tax3Id);
        dest.writeInt(this.takeoutTax1Id);
        dest.writeInt(this.takeoutTax2Id);
        dest.writeInt(this.takeoutTax3Id);
        dest.writeByte(this.picked ? (byte) 1 : (byte) 0);
        dest.writeByte(this.modifierMust ? (byte) 1 : (byte) 0);
        dest.writeByte(this.kitchenNoteMust ? (byte) 1 : (byte) 0);
        dest.writeDouble(this.orderQty);
        dest.writeByte(this.stopSale ? (byte) 1 : (byte) 0);
        dest.writeByte(this.askPrice ? (byte) 1 : (byte) 0);
        dest.writeByte(this.askQuantity ? (byte) 1 : (byte) 0);
        dest.writeByte(this.scale ? (byte) 1 : (byte) 0);
        dest.writeByte(this.stopSaleZeroQty ? (byte) 1 : (byte) 0);
        dest.writeDouble(this.warnQty);
        dest.writeInt(this.modifierMaximum);
        dest.writeString(this.imagePath);
        dest.writeDouble(this.takeOutPrice);
        dest.writeDouble(this.deliveryPrice);
        dest.writeString(this.barCode1);
        dest.writeString(this.barCode2);
        dest.writeString(this.barCode3);
        dest.writeDouble(this.memberPrice1);
        dest.writeDouble(this.memberPrice2);
        dest.writeDouble(this.memberPrice3);
        dest.writeByte(this.enable ? (byte) 1 : (byte) 0);
        dest.writeByte(this.discountable ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isGift ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isCustomerApp ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isHideInfo ? (byte) 1 : (byte) 0);
        dest.writeInt(this.locationId);
        dest.writeDouble(this.purchasePrice);
        dest.writeInt(this.amount);
    }

    protected Item(Parcel in) {
        this.id = in.readLong();
        this.categoryId = in.readLong();
        this.name = in.readString();
        this.kitchenItemName = in.readString();
        this.price = in.readDouble();
        this.cost = in.readDouble();
        this.qty = in.readDouble();
        this.modifierGroupIds = in.readString();
        this.kitchenNoteGroupIds = in.readString();
        this.printerIds = in.readString();
        this.kitchenDisplayIds = in.readString();
        this.picture = in.readString();
        this.background = in.readString();
        this.fontColor = in.readString();
        this.description = in.readString();
        this.sequence = in.readInt();
        this.tax1Id = in.readInt();
        this.tax2Id = in.readInt();
        this.tax3Id = in.readInt();
        this.takeoutTax1Id = in.readInt();
        this.takeoutTax2Id = in.readInt();
        this.takeoutTax3Id = in.readInt();
        this.picked = in.readByte() != 0;
        this.modifierMust = in.readByte() != 0;
        this.kitchenNoteMust = in.readByte() != 0;
        this.orderQty = in.readDouble();
        this.stopSale = in.readByte() != 0;
        this.askPrice = in.readByte() != 0;
        this.askQuantity = in.readByte() != 0;
        this.scale = in.readByte() != 0;
        this.stopSaleZeroQty = in.readByte() != 0;
        this.warnQty = in.readDouble();
        this.modifierMaximum = in.readInt();
        this.imagePath = in.readString();
        this.takeOutPrice = in.readDouble();
        this.deliveryPrice = in.readDouble();
        this.barCode1 = in.readString();
        this.barCode2 = in.readString();
        this.barCode3 = in.readString();
        this.memberPrice1 = in.readDouble();
        this.memberPrice2 = in.readDouble();
        this.memberPrice3 = in.readDouble();
        this.enable = in.readByte() != 0;
        this.discountable = in.readByte() != 0;
        this.isGift = in.readByte() != 0;
        this.isCustomerApp = in.readByte() != 0;
        this.isHideInfo = in.readByte() != 0;
        this.locationId = in.readInt();
        this.purchasePrice = in.readDouble();
        this.amount = in.readInt();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
