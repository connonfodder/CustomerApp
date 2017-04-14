package com.aadhk.customer.bean.mcpayment;

/**
 * Created by jack on 30/12/2016.
 */

public class Card {
    /**
     * BrandId : master
     * BrandName : MasterCard
     * AccountNumber :
     * BillingAddress : {"City":"Singapore","Country":"SG","CountrySubdivision":"SG-Singapore","Line1":"60 Ubi Cres","Line2":"Singapore","Line3":null,"PostalCode":"408569"}
     * CardHolderName : viet duong
     * ExpiryMonth : 0
     * ExpiryMonthSpecified : true
     * ExpiryYear : 0
     * ExpiryYearSpecified : true
     */

    private String BrandId;
    private String BrandName;
    private String AccountNumber;
    private BillingAddress BillingAddress;
    private String CardHolderName;
    private int ExpiryMonth;
    private boolean ExpiryMonthSpecified;
    private int ExpiryYear;
    private boolean ExpiryYearSpecified;

    public String getBrandId() {
        return BrandId;
    }

    public void setBrandId(String BrandId) {
        this.BrandId = BrandId;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String AccountNumber) {
        this.AccountNumber = AccountNumber;
    }

    public BillingAddress getBillingAddress() {
        return BillingAddress;
    }

    public void setBillingAddress(BillingAddress BillingAddress) {
        this.BillingAddress = BillingAddress;
    }

    public String getCardHolderName() {
        return CardHolderName;
    }

    public void setCardHolderName(String CardHolderName) {
        this.CardHolderName = CardHolderName;
    }

    public int getExpiryMonth() {
        return ExpiryMonth;
    }

    public void setExpiryMonth(int ExpiryMonth) {
        this.ExpiryMonth = ExpiryMonth;
    }

    public boolean isExpiryMonthSpecified() {
        return ExpiryMonthSpecified;
    }

    public void setExpiryMonthSpecified(boolean ExpiryMonthSpecified) {
        this.ExpiryMonthSpecified = ExpiryMonthSpecified;
    }

    public int getExpiryYear() {
        return ExpiryYear;
    }

    public void setExpiryYear(int ExpiryYear) {
        this.ExpiryYear = ExpiryYear;
    }

    public boolean isExpiryYearSpecified() {
        return ExpiryYearSpecified;
    }

    public void setExpiryYearSpecified(boolean ExpiryYearSpecified) {
        this.ExpiryYearSpecified = ExpiryYearSpecified;
    }

    @Override
    public String toString() {
        return "Card{" +
                "BrandId='" + BrandId + '\'' +
                ", BrandName='" + BrandName + '\'' +
                ", AccountNumber='" + AccountNumber + '\'' +
                ", BillingAddress=" + BillingAddress +
                ", CardHolderName='" + CardHolderName + '\'' +
                ", ExpiryMonth=" + ExpiryMonth +
                ", ExpiryMonthSpecified=" + ExpiryMonthSpecified +
                ", ExpiryYear=" + ExpiryYear +
                ", ExpiryYearSpecified=" + ExpiryYearSpecified +
                '}';
    }
}
