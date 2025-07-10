package com.example.Ecommerce.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne
    @JoinColumn(name = "item")
    private ItemModel item;
    @ManyToOne
    @JoinColumn(name = "seller")
    private UserModel seller;
    @ManyToOne
    @JoinColumn(name = "Buyer")
    private UserModel buyer;
    private String address;
    private Long mobile;
    private int quantity;
    @Temporal(TemporalType.DATE)
    private LocalDate orderDate;
    private boolean status;
    public OrderModel() {
    }

    public OrderModel(Long orderId, ItemModel item, UserModel seller, UserModel buyer, String address, Long mobile, int quantity,LocalDate orderDate) {
        this.orderId = orderId;
        this.item = item;
        this.seller = seller;
        this.buyer = buyer;
        this.address = address;
        this.mobile = mobile;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }

    public UserModel getSeller() {
        return seller;
    }

    public void setSeller(UserModel seller) {this.seller = seller;}

    public UserModel getBuyer() {
        return buyer;
    }

    public void setBuyer(UserModel buyer) {
        this.buyer = buyer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getMobile() {return mobile;}

    public void setMobile(Long mobile) {this.mobile = mobile;}

    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) {this.quantity = quantity;}

    public LocalDate getOrderDate() {return orderDate;}

    public boolean getStatus() {return status;}

    public void setStatus(boolean status) {this.status = status;}

    public void setOrderDate(LocalDate orderDate) {this.orderDate = orderDate;}
}
