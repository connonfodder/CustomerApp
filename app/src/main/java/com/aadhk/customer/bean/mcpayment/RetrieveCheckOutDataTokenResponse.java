package com.aadhk.customer.bean.mcpayment;

/**
 * Created by jack on 30/12/2016.
 */

public class RetrieveCheckOutDataTokenResponse {

    /**
     * ResCode : 200
     * ResMsg : Success.
     * CustomerId : 2fce4f55-ca2e-4f6f-8fc9-5eb15fa71f19
     * CheckoutData : {"Card":{"BrandId":"master","BrandName":"MasterCard","AccountNumber":"","BillingAddress":{"City":"Singapore","Country":"SG","CountrySubdivision":"SG-Singapore","Line1":"60 Ubi Cres","Line2":"Singapore","Line3":null,"PostalCode":"408569"},"CardHolderName":"viet duong","ExpiryMonth":0,"ExpiryMonthSpecified":true,"ExpiryYear":0,"ExpiryYearSpecified":true},"TransactionId":"674562122","Contact":{"FirstName":"viet","MiddleName":null,"LastName":"duong","GenderSpecified":false,"DateOfBirth":{"Year":0,"Month":0,"Day":0},"NationalID":null,"Country":"SG","EmailAddress":"pisv.viet.duong@gmail.com","PhoneNumber":"6544444"},"ShippingAddress":{"RecipientName":"viet duong","RecipientPhoneNumber":"6544444","ExtensionPoint":{"Any":[],"AnyAttr":[]},"City":"Singapore","Country":"SG","CountrySubdivision":"SG-Singapore","Line1":"60 Ubi Cres","Line2":"Singapore","Line3":null,"PostalCode":"408569"},"AuthenticationOptions":{"AuthenticateMethod":null,"CardEnrollmentMethod":null,"CAvv":null,"EciFlag":null,"MasterCardAssignedID":null,"PaResStatus":null,"SCEnrollmentStatus":null,"SignatureVerification":null,"Xid":null},"RewardProgram":{"RewardNumber":null,"RewardId":null,"RewardName":null,"ExpiryMonthSpecified":false,"ExpiryYearSpecified":false},"WalletID":"101","PreCheckoutTransactionId":null}
     * Version : 1.0
     * TimeStamp : 2016-12-29T17:52:30+08:00
     */
    private String ResCode;
    private String ResMsg;
    private String CustomerId;
    private CheckoutData CheckoutData;
    private String Version;
    private String TimeStamp;

    public String getResCode() {
        return ResCode;
    }

    public void setResCode(String ResCode) {
        this.ResCode = ResCode;
    }

    public String getResMsg() {
        return ResMsg;
    }

    public void setResMsg(String ResMsg) {
        this.ResMsg = ResMsg;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String CustomerId) {
        this.CustomerId = CustomerId;
    }

    public CheckoutData getCheckoutData() {
        return CheckoutData;
    }

    public void setCheckoutData(CheckoutData CheckoutData) {
        this.CheckoutData = CheckoutData;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String TimeStamp) {
        this.TimeStamp = TimeStamp;
    }

    @Override
    public String toString() {
        return "RetrieveCheckOutDataTokenResponse{" +
                "ResCode='" + ResCode + '\'' +
                ", ResMsg='" + ResMsg + '\'' +
                ", CustomerId='" + CustomerId + '\'' +
                ", Checkout=" + CheckoutData +
                ", Version='" + Version + '\'' +
                ", TimeStamp='" + TimeStamp + '\'' +
                '}';
    }

}
