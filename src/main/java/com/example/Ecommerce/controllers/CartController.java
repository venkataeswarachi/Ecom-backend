package com.example.Ecommerce.controllers;

import com.example.Ecommerce.payload.CartDTO;
import com.example.Ecommerce.payload.ItemDTO;
import com.example.Ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping()
    public String eG(){
        return "Hello";
    }
    @PostMapping("/add/{item_id}")
    public ResponseEntity<CartDTO> addCartItem(@PathVariable Long item_id, @AuthenticationPrincipal UserDetails userDetails){
        String email = userDetails.getUsername();
        return cartService.addToCart(item_id,email);
    }
    @GetMapping("/get/{user_id}")
    public List<CartDTO> cartItems(@PathVariable Long user_id){
        return cartService.cartItems(user_id);
    }
    @DeleteMapping("/delete/{cart_id}")
    public ResponseEntity<String> deleteCartItem(@PathVariable Long cart_id){
        return cartService.deleteCartItem(cart_id);
    }
}
