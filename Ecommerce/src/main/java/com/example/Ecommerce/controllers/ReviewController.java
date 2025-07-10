package com.example.Ecommerce.controllers;

import com.example.Ecommerce.payload.ReviewDTO;
import com.example.Ecommerce.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @PostMapping("/post/{itemId}")
    public ResponseEntity<ReviewDTO> saveReview(@PathVariable Long itemId, Authentication authentication , @RequestBody ReviewDTO reviewDTO){
        return reviewService.saveReview(itemId,authentication,reviewDTO);
    }
    @GetMapping("/get/{itemId}")
    public int getRating(@PathVariable Long itemId){
        return reviewService.getRating(itemId);
    }
    @GetMapping("/getall/{itemId}")
    public List<ReviewDTO> getAllReviews(@PathVariable Long itemId){return reviewService.getAllReview(itemId);      }
}
