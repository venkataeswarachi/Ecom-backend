package com.example.Ecommerce.service;

import com.example.Ecommerce.payload.PaymentVerficationDTO;
import com.razorpay.RazorpayException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface PaymentService {
    public ResponseEntity<String> createOrder( Map<String, Object> data) throws RazorpayException;
    public ResponseEntity<String> verifyPayment(PaymentVerficationDTO dto);
}
