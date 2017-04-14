package com.aadhk.customer.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.aadhk.customer.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class Order implements Parcelable {

    private long id;
    private String orderNum;
    private String invoiceNum;

    private long billId;
    private String orderTime;
    private String endTime;
    private long tableId;
    private long customerId;
    private String customerName;
    private int status;
    /**
     * -1 预下单 {@link Constant#STATUS_ORDER_NEW}
     */
    private int cookStatus;
    private int deliveryStatus;             //配送状态
    private String deliveryTime;            //开始配送时间
    private String deliveriedTime;          //结束配送时间
    private String deliveryMan;             //配送员
    private String deliveryArriveTime;    //选择送达的时间

    private String takeoutTime;     //带走时间

    private short splitType;
    private boolean hasUnpaidBill;
    private boolean hasVoidItem;
    private boolean printReceipt;
    private int personNum;
    private String tableName;
    private String cancelReason;
    private String cancelPerson;
    private String waiterName;
    private String cashierName;
    private String kitchenRemark;

    private double discountAmt;
    private String discountReason;
    private double discountPercentage;
    private String serviceFeeName;
    private double serviceAmt;
    private double servicePercentage;
    private String gratuityName;
    private double gratuity;
    private double gratuityPercentage;
    private String receiptNote;

    private double tax1Amt;
    private double tax2Amt;
    private double tax3Amt;
    private String tax1Name;
    private String tax2Name;
    private String tax3Name;

    private int taxStatus;      //only for calculation
    private double subTotal;    //orderItem的总和
    private double amount;      //订单的总价 也就是消费金额
    private double cancelTotal;
    private int orderCount;
    private double rounding; //TODO The setting rounding is for payment method, so the rounding field should be in payment method
    private int qty;
    private int receiptPrinterId;

    private String customerPhone;
    private int orderType;
    private int orderMemberType;

    private List<OrderItem> orderItems;
    private List<OrderPayment> orderPayments;
    private Customer customer;
    //    private Customer oldCustomer;
//    private Map<String, InventoryDishRecipe> inventoryDishRecipeMap;
    private String refundTime;
    private String refundReason;
    private boolean isPayLater;
    private double deliveryFee;

    private String gratuityNote;

    private double minimumCharge;
    private int minimumChargeType;
    private double minimumChargeSet;

    private long companyId;                 //餐厅ID
    private String logoPath;                //餐厅logo
    private String companyName;             //餐厅名称
    private String companyPhone;            //餐厅联系电话
    private String masterpassAccount;       //商家支付账号

    private String deliveryAddress;
    private int customerOrderStatus;    //1. 待接单  2. 已接单  3. 拒绝接单  4. 待退款  5. 已退款   6. 拒绝退款  7. 已完成
    private Address address;

    public Order() {
        this.amount = 0;
        this.qty = 0;
        this.orderItems = new ArrayList<>();
    }

    public Order(List<OrderItem> orderItems, int orderType, String deliveryTime, String customerName, int orderStauts, double amount, int qty, String deliveryAddress, String logoPath, String companyName, String companyPhone) {
        this.orderItems = orderItems;
        this.orderType = orderType;
        this.deliveryTime = deliveryTime;
        this.customerName = customerName;
        this.status = orderStauts;
        this.amount = amount;
        this.qty = qty;
        this.deliveryAddress = deliveryAddress;
        this.logoPath = logoPath;
        this.customerName = companyName;
        this.companyPhone = companyPhone;
    }

    public Order clone() {
        Parcel p = Parcel.obtain();
        p.writeValue(this);
        p.setDataPosition(0);
        Order newItem = (Order) p.readValue(Order.class.getClassLoader());
        p.recycle();
        return newItem;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
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

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCookStatus() {
        return cookStatus;
    }

    public void setCookStatus(int cookStatus) {
        this.cookStatus = cookStatus;
    }

    public int getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(int deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveriedTime() {
        return deliveriedTime;
    }

    public void setDeliveriedTime(String deliveriedTime) {
        this.deliveriedTime = deliveriedTime;
    }

    public String getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(String deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public String getDeliveryArriveTime() {
        return deliveryArriveTime;
    }

    public void setDeliveryArriveTime(String deliveryArriveTime) {
        this.deliveryArriveTime = deliveryArriveTime;
    }

    public short getSplitType() {
        return splitType;
    }

    public void setSplitType(short splitType) {
        this.splitType = splitType;
    }

    public boolean isHasUnpaidBill() {
        return hasUnpaidBill;
    }

    public void setHasUnpaidBill(boolean hasUnpaidBill) {
        this.hasUnpaidBill = hasUnpaidBill;
    }

    public boolean isHasVoidItem() {
        return hasVoidItem;
    }

    public void setHasVoidItem(boolean hasVoidItem) {
        this.hasVoidItem = hasVoidItem;
    }

    public boolean isPrintReceipt() {
        return printReceipt;
    }

    public void setPrintReceipt(boolean printReceipt) {
        this.printReceipt = printReceipt;
    }

    public int getPersonNum() {
        return personNum;
    }

    public void setPersonNum(int personNum) {
        this.personNum = personNum;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getCancelPerson() {
        return cancelPerson;
    }

    public void setCancelPerson(String cancelPerson) {
        this.cancelPerson = cancelPerson;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public String getKitchenRemark() {
        return kitchenRemark;
    }

    public void setKitchenRemark(String kitchenRemark) {
        this.kitchenRemark = kitchenRemark;
    }

    public double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(double discountAmt) {
        this.discountAmt = discountAmt;
    }

    public String getDiscountReason() {
        return discountReason;
    }

    public void setDiscountReason(String discountReason) {
        this.discountReason = discountReason;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getServiceFeeName() {
        return serviceFeeName;
    }

    public void setServiceFeeName(String serviceFeeName) {
        this.serviceFeeName = serviceFeeName;
    }

    public double getServiceAmt() {
        return serviceAmt;
    }

    public void setServiceAmt(double serviceAmt) {
        this.serviceAmt = serviceAmt;
    }

    public double getServicePercentage() {
        return servicePercentage;
    }

    public void setServicePercentage(double servicePercentage) {
        this.servicePercentage = servicePercentage;
    }

    public String getGratuityName() {
        return gratuityName;
    }

    public void setGratuityName(String gratuityName) {
        this.gratuityName = gratuityName;
    }

    public double getGratuity() {
        return gratuity;
    }

    public void setGratuity(double gratuity) {
        this.gratuity = gratuity;
    }

    public double getGratuityPercentage() {
        return gratuityPercentage;
    }

    public void setGratuityPercentage(double gratuityPercentage) {
        this.gratuityPercentage = gratuityPercentage;
    }

    public String getReceiptNote() {
        return receiptNote;
    }

    public void setReceiptNote(String receiptNote) {
        this.receiptNote = receiptNote;
    }

    public double getTax1Amt() {
        return tax1Amt;
    }

    public void setTax1Amt(double tax1Amt) {
        this.tax1Amt = tax1Amt;
    }

    public double getTax2Amt() {
        return tax2Amt;
    }

    public void setTax2Amt(double tax2Amt) {
        this.tax2Amt = tax2Amt;
    }

    public double getTax3Amt() {
        return tax3Amt;
    }

    public void setTax3Amt(double tax3Amt) {
        this.tax3Amt = tax3Amt;
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

    public int getTaxStatus() {
        return taxStatus;
    }

    public void setTaxStatus(int taxStatus) {
        this.taxStatus = taxStatus;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getCancelTotal() {
        return cancelTotal;
    }

    public void setCancelTotal(double cancelTotal) {
        this.cancelTotal = cancelTotal;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public double getRounding() {
        return rounding;
    }

    public void setRounding(double rounding) {
        this.rounding = rounding;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getReceiptPrinterId() {
        return receiptPrinterId;
    }

    public void setReceiptPrinterId(int receiptPrinterId) {
        this.receiptPrinterId = receiptPrinterId;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public int getOrderMemberType() {
        return orderMemberType;
    }

    public void setOrderMemberType(int orderMemberType) {
        this.orderMemberType = orderMemberType;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderPayment> getOrderPayments() {
        return orderPayments;
    }

    public void setOrderPayments(List<OrderPayment> orderPayments) {
        this.orderPayments = orderPayments;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public boolean isPayLater() {
        return isPayLater;
    }

    public void setPayLater(boolean payLater) {
        isPayLater = payLater;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getGratuityNote() {
        return gratuityNote;
    }

    public void setGratuityNote(String gratuityNote) {
        this.gratuityNote = gratuityNote;
    }

    public double getMinimumCharge() {
        return minimumCharge;
    }

    public void setMinimumCharge(double minimumCharge) {
        this.minimumCharge = minimumCharge;
    }

    public int getMinimumChargeType() {
        return minimumChargeType;
    }

    public void setMinimumChargeType(int minimumChargeType) {
        this.minimumChargeType = minimumChargeType;
    }

    public double getMinimumChargeSet() {
        return minimumChargeSet;
    }

    public void setMinimumChargeSet(double minimumChargeSet) {
        this.minimumChargeSet = minimumChargeSet;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getMasterpassAccount() {
        return masterpassAccount;
    }

    public void setMasterpassAccount(String masterpassAccount) {
        this.masterpassAccount = masterpassAccount;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getCustomerOrderStatus() {
        return customerOrderStatus;
    }

    public void setCustomerOrderStatus(int customerOrderStatus) {
        this.customerOrderStatus = customerOrderStatus;
    }

    public String getTakeoutTime() {
        return takeoutTime;
    }

    public void setTakeoutTime(String takeoutTime) {
        this.takeoutTime = takeoutTime;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", personNum=" + personNum +
                ", tableName='" + tableName + '\'' +
                ", tax1Amt=" + tax1Amt +
                ", tax2Amt=" + tax2Amt +
                ", tax3Amt=" + tax3Amt +
                ", tax1Name='" + tax1Name + '\'' +
                ", tax2Name='" + tax2Name + '\'' +
                ", tax3Name='" + tax3Name + '\'' +
                ", taxStatus=" + taxStatus +
                ", subTotal=" + subTotal +
                ", amount=" + amount +
                ", qty=" + qty +
                ", orderType=" + orderType +
                ", orderItems=" + orderItems +
                ", orderTime=" + orderTime +
                ", takeoutTime=" + takeoutTime +
                ", orderPayments=" + orderPayments +
                ", customer=" + customer +
                ", deliveryFee=" + deliveryFee +
                ", companyName='" + companyName + '\'' +
                ", customerOrderStatus='" + customerOrderStatus + '\'' +
                ", status='" + status + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.orderNum);
        dest.writeString(this.invoiceNum);
        dest.writeLong(this.billId);
        dest.writeString(this.orderTime);
        dest.writeString(this.endTime);
        dest.writeLong(this.tableId);
        dest.writeLong(this.customerId);
        dest.writeString(this.customerName);
        dest.writeInt(this.status);
        dest.writeInt(this.cookStatus);
        dest.writeInt(this.deliveryStatus);
        dest.writeString(this.deliveryTime);
        dest.writeString(this.deliveriedTime);
        dest.writeString(this.deliveryMan);
        dest.writeString(this.deliveryArriveTime);
        dest.writeString(this.takeoutTime);
        dest.writeInt(this.splitType);
        dest.writeByte(this.hasUnpaidBill ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hasVoidItem ? (byte) 1 : (byte) 0);
        dest.writeByte(this.printReceipt ? (byte) 1 : (byte) 0);
        dest.writeInt(this.personNum);
        dest.writeString(this.tableName);
        dest.writeString(this.cancelReason);
        dest.writeString(this.cancelPerson);
        dest.writeString(this.waiterName);
        dest.writeString(this.cashierName);
        dest.writeString(this.kitchenRemark);
        dest.writeDouble(this.discountAmt);
        dest.writeString(this.discountReason);
        dest.writeDouble(this.discountPercentage);
        dest.writeString(this.serviceFeeName);
        dest.writeDouble(this.serviceAmt);
        dest.writeDouble(this.servicePercentage);
        dest.writeString(this.gratuityName);
        dest.writeDouble(this.gratuity);
        dest.writeDouble(this.gratuityPercentage);
        dest.writeString(this.receiptNote);
        dest.writeDouble(this.tax1Amt);
        dest.writeDouble(this.tax2Amt);
        dest.writeDouble(this.tax3Amt);
        dest.writeString(this.tax1Name);
        dest.writeString(this.tax2Name);
        dest.writeString(this.tax3Name);
        dest.writeInt(this.taxStatus);
        dest.writeDouble(this.subTotal);
        dest.writeDouble(this.amount);
        dest.writeDouble(this.cancelTotal);
        dest.writeInt(this.orderCount);
        dest.writeDouble(this.rounding);
        dest.writeInt(this.qty);
        dest.writeInt(this.receiptPrinterId);
        dest.writeString(this.customerPhone);
        dest.writeInt(this.orderType);
        dest.writeInt(this.orderMemberType);
        dest.writeTypedList(this.orderItems);
        dest.writeTypedList(this.orderPayments);
        dest.writeParcelable(this.customer, flags);
        dest.writeString(this.refundReason);
        dest.writeByte(this.isPayLater ? (byte) 1 : (byte) 0);
        dest.writeDouble(this.deliveryFee);
        dest.writeString(this.gratuityNote);
        dest.writeDouble(this.minimumCharge);
        dest.writeInt(this.minimumChargeType);
        dest.writeDouble(this.minimumChargeSet);
        dest.writeLong(this.companyId);
        dest.writeString(this.logoPath);
        dest.writeString(this.companyName);
        dest.writeString(this.companyPhone);
        dest.writeString(this.masterpassAccount);
        dest.writeString(this.deliveryAddress);
        dest.writeInt(this.customerOrderStatus);
        dest.writeParcelable(this.address, flags);
        dest.writeString(this.refundTime);
    }

    protected Order(Parcel in) {
        this.id = in.readLong();
        this.orderNum = in.readString();
        this.invoiceNum = in.readString();
        this.billId = in.readLong();
        this.orderTime = in.readString();
        this.endTime = in.readString();
        this.tableId = in.readLong();
        this.customerId = in.readLong();
        this.customerName = in.readString();
        this.status = in.readInt();
        this.cookStatus = in.readInt();
        this.deliveryStatus = in.readInt();
        this.deliveryTime = in.readString();
        this.deliveriedTime = in.readString();
        this.deliveryMan = in.readString();
        this.deliveryArriveTime = in.readString();
        this.takeoutTime = in.readString();
        this.splitType = (short) in.readInt();
        this.hasUnpaidBill = in.readByte() != 0;
        this.hasVoidItem = in.readByte() != 0;
        this.printReceipt = in.readByte() != 0;
        this.personNum = in.readInt();
        this.tableName = in.readString();
        this.cancelReason = in.readString();
        this.cancelPerson = in.readString();
        this.waiterName = in.readString();
        this.cashierName = in.readString();
        this.kitchenRemark = in.readString();
        this.discountAmt = in.readDouble();
        this.discountReason = in.readString();
        this.discountPercentage = in.readDouble();
        this.serviceFeeName = in.readString();
        this.serviceAmt = in.readDouble();
        this.servicePercentage = in.readDouble();
        this.gratuityName = in.readString();
        this.gratuity = in.readDouble();
        this.gratuityPercentage = in.readDouble();
        this.receiptNote = in.readString();
        this.tax1Amt = in.readDouble();
        this.tax2Amt = in.readDouble();
        this.tax3Amt = in.readDouble();
        this.tax1Name = in.readString();
        this.tax2Name = in.readString();
        this.tax3Name = in.readString();
        this.taxStatus = in.readInt();
        this.subTotal = in.readDouble();
        this.amount = in.readDouble();
        this.cancelTotal = in.readDouble();
        this.orderCount = in.readInt();
        this.rounding = in.readDouble();
        this.qty = in.readInt();
        this.receiptPrinterId = in.readInt();
        this.customerPhone = in.readString();
        this.orderType = in.readInt();
        this.orderMemberType = in.readInt();
        this.orderItems = in.createTypedArrayList(OrderItem.CREATOR);
        this.orderPayments = in.createTypedArrayList(OrderPayment.CREATOR);
        this.customer = in.readParcelable(Customer.class.getClassLoader());
        this.refundReason = in.readString();
        this.isPayLater = in.readByte() != 0;
        this.deliveryFee = in.readDouble();
        this.gratuityNote = in.readString();
        this.minimumCharge = in.readDouble();
        this.minimumChargeType = in.readInt();
        this.minimumChargeSet = in.readDouble();
        this.companyId = in.readLong();
        this.logoPath = in.readString();
        this.companyName = in.readString();
        this.companyPhone = in.readString();
        this.masterpassAccount = in.readString();
        this.deliveryAddress = in.readString();
        this.customerOrderStatus = in.readInt();
        this.address = in.readParcelable(Address.class.getClassLoader());
        this.refundTime = in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {
            return new Order(source);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}
