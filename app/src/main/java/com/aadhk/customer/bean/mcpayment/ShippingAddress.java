package com.aadhk.customer.bean.mcpayment;

/**
 * Created by jack on 30/12/2016.
 */

public class ShippingAddress {
    /**
     * RecipientName : viet duong
     * RecipientPhoneNumber : 6544444
     * ExtensionPoint : {"Any":[],"AnyAttr":[]}
     * City : Singapore
     * Country : SG
     * CountrySubdivision : SG-Singapore
     * Line1 : 60 Ubi Cres
     * Line2 : Singapore
     * Line3 : null
     * PostalCode : 408569
     */

    private String RecipientName;
    private String RecipientPhoneNumber;
    private ExtensionPoint ExtensionPoint;
    private String City;
    private String Country;
    private String CountrySubdivision;
    private String Line1;
    private String Line2;
    private String Line3;
    private String PostalCode;

    public String getRecipientName() {
        return RecipientName;
    }

    public void setRecipientName(String RecipientName) {
        this.RecipientName = RecipientName;
    }

    public String getRecipientPhoneNumber() {
        return RecipientPhoneNumber;
    }

    public void setRecipientPhoneNumber(String RecipientPhoneNumber) {
        this.RecipientPhoneNumber = RecipientPhoneNumber;
    }

    public ExtensionPoint getExtensionPoint() {
        return ExtensionPoint;
    }

    public void setExtensionPoint(ExtensionPoint ExtensionPoint) {
        this.ExtensionPoint = ExtensionPoint;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getCountrySubdivision() {
        return CountrySubdivision;
    }

    public void setCountrySubdivision(String CountrySubdivision) {
        this.CountrySubdivision = CountrySubdivision;
    }

    public String getLine1() {
        return Line1;
    }

    public void setLine1(String Line1) {
        this.Line1 = Line1;
    }

    public String getLine2() {
        return Line2;
    }

    public void setLine2(String Line2) {
        this.Line2 = Line2;
    }

    public String getLine3() {
        return Line3;
    }

    public void setLine3(String Line3) {
        this.Line3 = Line3;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String PostalCode) {
        this.PostalCode = PostalCode;
    }

    @Override
    public String toString() {
        return "ShippingAddress{" +
                "RecipientName='" + RecipientName + '\'' +
                ", RecipientPhoneNumber='" + RecipientPhoneNumber + '\'' +
                ", ExtensionPoint=" + ExtensionPoint +
                ", City='" + City + '\'' +
                ", Country='" + Country + '\'' +
                ", CountrySubdivision='" + CountrySubdivision + '\'' +
                ", Line1='" + Line1 + '\'' +
                ", Line2='" + Line2 + '\'' +
                ", Line3='" + Line3 + '\'' +
                ", PostalCode='" + PostalCode + '\'' +
                '}';
    }
}
