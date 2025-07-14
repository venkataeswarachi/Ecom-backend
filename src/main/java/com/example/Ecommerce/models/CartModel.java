package com.example.Ecommerce.models;

import jakarta.persistence.*;

@Entity
public class CartModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_id;
    @ManyToOne
    @JoinColumn(name = "user")
    private UserModel user;
    @ManyToOne
    @JoinColumn(name = "item")
    private ItemModel item;

    public CartModel(Long cart_id, UserModel user, ItemModel item) {
        this.cart_id = cart_id;
        this.user = user;
        this.item = item;
    }

    public CartModel() {
    }

    public Long getCart_id() {
        return cart_id;
    }

    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }
}
