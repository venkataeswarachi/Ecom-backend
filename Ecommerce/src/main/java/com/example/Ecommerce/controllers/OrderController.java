package com.example.Ecommerce.controllers;

import com.example.Ecommerce.payload.OrderDTO;
import com.example.Ecommerce.payload.OrderDetailsDTO;
import com.example.Ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class    OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeorder/{itemId}")
    public ResponseEntity<OrderDTO> placeOrder(@PathVariable Long itemId, Authentication authentication, @RequestBody OrderDetailsDTO detailsDTO){
        return orderService.placeOrder(itemId,authentication,detailsDTO);
    }
    @GetMapping("getorders/{userId}")
    public List<OrderDTO> getMyOrders(@PathVariable Long userId){
        return orderService.getOrders(userId);
    }
    @GetMapping("getrecieved/{userId}")
    public List<OrderDTO> getRecieved(@PathVariable Long userId){
        return orderService.getRecieved(userId);
    }


}
