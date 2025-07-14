package com.example.Ecommerce.service;

import com.example.Ecommerce.payload.CartDTO;
import com.example.Ecommerce.payload.ItemDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {
    public ResponseEntity<CartDTO> addToCart(Long item_id,String email);
    public List<CartDTO> cartItems(Long user_id);
    public ResponseEntity<String> deleteCartItem(Long itemId);
}
