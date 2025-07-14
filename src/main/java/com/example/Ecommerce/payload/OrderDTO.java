package com.example.Ecommerce.payload;

import com.example.Ecommerce.models.ItemModel;

import java.time.LocalDate;

public class OrderDTO {
    private Long orderId;
    private Long buyerId;
    private boolean status;
    private Long itemId;
    private Long mobile;
    private String address;
    private LocalDate orderDate;
    private Long sellerId;
    private int quantity;

    public Long getOrderId() {
        return orderId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getOrderDate() {return orderDate;}

    public void setOrderDate(LocalDate orderDate) {this.orderDate = orderDate;}

    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) {this.quantity = quantity;}

    public boolean getStatus() {return status;}

    public void setStatus(boolean status) {this.status = status;}
}
