package com.aadhk.customer.bean.mcpayment;

/**
 * Created by jack on 30/12/2016.
 */

public class CheckoutData {
    /**
     * Card : {"BrandId":"master","BrandName":"MasterCard","AccountNumber":"","BillingAddress":{"City":"Singapore","Country":"SG","CountrySubdivision":"SG-Singapore","Line1":"60 Ubi Cres","Line2":"Singapore","Line3":null,"PostalCode":"408569"},"CardHolderName":"viet duong","ExpiryMonth":0,"ExpiryMonthSpecified":true,"ExpiryYear":0,"ExpiryYearSpecified":true}
     * TransactionId : 674562122
     * Contact : {"FirstName":"viet","MiddleName":null,"LastName":"duong","GenderSpecified":false,"DateOfBirth":{"Year":0,"Month":0,"Day":0},"NationalID":null,"Country":"SG","EmailAddress":"pisv.viet.duong@gmail.com","PhoneNumber":"6544444"}
     * ShippingAddress : {"RecipientName":"viet duong","RecipientPhoneNumber":"6544444","ExtensionPoint":{"Any":[],"AnyAttr":[]},"City":"Singapore","Country":"SG","CountrySubdivision":"SG-Singapore","Line1":"60 Ubi Cres","Line2":"Singapore","Line3":null,"PostalCode":"408569"}
     * AuthenticationOptions : {"AuthenticateMethod":null,"CardEnrollmentMethod":null,"CAvv":null,"EciFlag":null,"MasterCardAssignedID":null,"PaResStatus":null,"SCEnrollmentStatus":null,"SignatureVerification":null,"Xid":null}
     * RewardProgram : {"RewardNumber":null,"RewardId":null,"RewardName":null,"ExpiryMonthSpecified":false,"ExpiryYearSpecified":false}
     * WalletID : 101
     * PreCheckoutTransactionId : null
     */

    private Card Card;
    private String TransactionId;
    private Contact Contact;
    private ShippingAddress ShippingAddress;
    private AuthenticationOptions AuthenticationOptions;
    private RewardProgram RewardProgram;
    private String WalletID;
    private String PreCheckoutTransactionId;

    public Card getCard() {
        return Card;
    }

    public void setCard(Card Card) {
        this.Card = Card;
    }

    public String getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(String TransactionId) {
        this.TransactionId = TransactionId;
    }

    public Contact getContact() {
        return Contact;
    }

    public void setContact(Contact Contact) {
        this.Contact = Contact;
    }

    public ShippingAddress getShippingAddress() {
        return ShippingAddress;
    }

    public void setShippingAddress(ShippingAddress ShippingAddress) {
        this.ShippingAddress = ShippingAddress;
    }

    public AuthenticationOptions getAuthenticationOptions() {
        return AuthenticationOptions;
    }

    public void setAuthenticationOptions(AuthenticationOptions AuthenticationOptions) {
        this.AuthenticationOptions = AuthenticationOptions;
    }

    public RewardProgram getRewardProgram() {
        return RewardProgram;
    }

    public void setRewardProgram(RewardProgram RewardProgram) {
        this.RewardProgram = RewardProgram;
    }

    public String getWalletID() {
        return WalletID;
    }

    public void setWalletID(String WalletID) {
        this.WalletID = WalletID;
    }

    public String getPreCheckoutTransactionId() {
        return PreCheckoutTransactionId;
    }

    public void setPreCheckoutTransactionId(String PreCheckoutTransactionId) {
        this.PreCheckoutTransactionId = PreCheckoutTransactionId;
    }

    @Override
    public String toString() {
        return "Checkout{" +
                "Card=" + Card +
                ", TransactionId='" + TransactionId + '\'' +
                ", Contact=" + Contact +
                ", ShippingAddress=" + ShippingAddress +
                ", AuthenticationOptions=" + AuthenticationOptions +
                ", RewardProgram=" + RewardProgram +
                ", WalletID='" + WalletID + '\'' +
                ", PreCheckoutTransactionId='" + PreCheckoutTransactionId + '\'' +
                '}';
    }
}