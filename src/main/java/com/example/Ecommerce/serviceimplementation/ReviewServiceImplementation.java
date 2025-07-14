package com.example.Ecommerce.serviceimplementation;

import com.example.Ecommerce.exceptionHandlers.UserNotFound;
import com.example.Ecommerce.models.ItemModel;
import com.example.Ecommerce.models.ReviewModel;
import com.example.Ecommerce.models.UserModel;
import com.example.Ecommerce.payload.ReviewDTO;
import com.example.Ecommerce.repository.ItemRepository;
import com.example.Ecommerce.repository.ReviewRepository;
import com.example.Ecommerce.repository.UserRepository;
import com.example.Ecommerce.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ModelMapper modelMapper;
    public int getRating(Long itemId){
        ItemModel item = itemRepository.findById(itemId).orElseThrow(()-> new RuntimeException("Item Not Found"));
        int rating = reviewRepository.findRating(item);
        return rating;
    }



    public ResponseEntity<ReviewDTO> saveReview(Long itemId, Authentication authentication, ReviewDTO reviewDTO){
        UserModel userModel = userRepository.findByEmail(authentication.getName()).orElseThrow(()->new UserNotFound("User Not Found"));
        ItemModel item = itemRepository.findById(itemId).orElseThrow(()-> new RuntimeException("Item Not Found"));
        ReviewModel reviewModel = new ReviewModel();
        reviewModel.setItem(item);
        reviewModel.setUser(userModel);
        reviewModel.setComment(reviewDTO.getComment());
        reviewModel.setRating(reviewDTO.getRating());
        ReviewModel savedReview = reviewRepository.save(reviewModel);
        modelMapper.typeMap(ReviewModel.class,ReviewDTO.class)
                .addMappings(mapper-> {
                    mapper.map(src -> src.getUser().getUser_id(), ReviewDTO::setUserId);
                    mapper.map(src -> src.getUser().getName(), ReviewDTO::setUserName);
                });
        ReviewDTO review  = modelMapper.map(savedReview,ReviewDTO.class);
        return ResponseEntity.ok(review);

    }

    @Override
    public List<ReviewDTO> getAllReview(Long itemId) {
        ItemModel item = itemRepository.findById(itemId).orElseThrow(()-> new RuntimeException("Item Not Found"));
        List<ReviewModel> reviewModels = reviewRepository.findByItem(item);
        List<ReviewDTO> reviews = reviewModels.stream()
                .map(reviewModel -> {
                    modelMapper.typeMap(ReviewModel.class, ReviewDTO.class)
                            .addMappings(mapper -> {
                                mapper.map(src -> src.getUser().getUser_id(), ReviewDTO::setUserId);
                                mapper.map(src -> src.getUser().getName(), ReviewDTO::setUserName);
                            });
                    return modelMapper.map(reviewModel, ReviewDTO.class);
                })
                .toList();
        return reviews;
    }
}
