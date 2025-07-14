package com.example.Ecommerce.service;

import com.example.Ecommerce.payload.ReviewDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ReviewService {
    public ResponseEntity<ReviewDTO> saveReview(Long itemId, Authentication authentication, ReviewDTO reviewDTO);
    public int getRating(Long itemId);
    public List<ReviewDTO> getAllReview(Long itemId);
}
