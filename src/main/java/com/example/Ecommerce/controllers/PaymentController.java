package com.example.Ecommerce.controllers;

import com.example.Ecommerce.payload.PaymentVerficationDTO;
import com.example.Ecommerce.service.PaymentService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/payment")

public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody Map<String, Object> data) throws RazorpayException{
        return paymentService.createOrder(data);
    }
    @PostMapping("/verify")
    public  ResponseEntity<String> verifyPayment(@RequestBody PaymentVerficationDTO dto){
        return paymentService.verifyPayment(dto);
    }


}
