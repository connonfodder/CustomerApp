package com.aadhk.customer.bean.mcpayment;

/**
 * Created by jack on 30/12/2016.
 */
public class Contact {
    /**
     * FirstName : viet
     * MiddleName : null
     * LastName : duong
     * GenderSpecified : false
     * DateOfBirth : {"Year":0,"Month":0,"Day":0}
     * NationalID : null
     * Country : SG
     * EmailAddress : pisv.viet.duong@gmail.com
     * PhoneNumber : 6544444
     */

    private String FirstName;
    private String MiddleName;
    private String LastName;
    private boolean GenderSpecified;
    private DateOfBirth DateOfBirth;
    private String NationalID;
    private String Country;
    private String EmailAddress;
    private String PhoneNumber;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String MiddleName) {
        this.MiddleName = MiddleName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public boolean isGenderSpecified() {
        return GenderSpecified;
    }

    public void setGenderSpecified(boolean GenderSpecified) {
        this.GenderSpecified = GenderSpecified;
    }

    public DateOfBirth getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(DateOfBirth DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }

    public String getNationalID() {
        return NationalID;
    }

    public void setNationalID(String NationalID) {
        this.NationalID = NationalID;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String EmailAddress) {
        this.EmailAddress = EmailAddress;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "FirstName='" + FirstName + '\'' +
                ", MiddleName='" + MiddleName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", GenderSpecified=" + GenderSpecified +
                ", DateOfBirth=" + DateOfBirth +
                ", NationalID='" + NationalID + '\'' +
                ", Country='" + Country + '\'' +
                ", EmailAddress='" + EmailAddress + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                '}';
    }
}
