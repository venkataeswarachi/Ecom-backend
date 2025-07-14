package com.example.Ecommerce.payload;

import com.example.Ecommerce.models.ItemModel;

public class CartDTO {
    private Long cart_id;
    private Long user_id;
    private ItemDTO item;

    public CartDTO() {
    }

    public CartDTO(Long cart_id, Long user_id, ItemDTO item) {
        this.cart_id = cart_id;
        this.user_id = user_id;
        this.item = item;
    }

    public Long getCart_id() {
        return cart_id;
    }

    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }
}
