package com.example.Ecommerce.service;

import com.example.Ecommerce.payload.OrderDTO;
import com.example.Ecommerce.payload.OrderDetailsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;


public interface OrderService {
    public ResponseEntity<OrderDTO> placeOrder(Long itemId,  Authentication authentication, OrderDetailsDTO orderDetailsDTO);
    public List<OrderDTO> getOrders(Long userId);
    public List<OrderDTO> getRecieved(Long userId);
}
