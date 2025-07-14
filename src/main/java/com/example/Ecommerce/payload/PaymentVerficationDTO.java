package com.example.Ecommerce.payload;

public class PaymentVerficationDTO {
    private String orderID;
    private String paymentID;
    private String signature;
    private Long buyerID;
    private Long itemID;
    private Long orderRefID;
    private Long amount;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getSignature() {
        return signature;
    }

    public Long getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(Long buyerID) {
        this.buyerID = buyerID;
    }

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public Long getOrderRefID() {
        return orderRefID;
    }

    public void setOrderRefID(Long orderRefID) {
        this.orderRefID = orderRefID;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
