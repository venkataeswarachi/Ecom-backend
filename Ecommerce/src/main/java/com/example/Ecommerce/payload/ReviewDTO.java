package com.example.Ecommerce.payload;

public class ReviewDTO {
    private Long reviewId;
    private String comment;
    private int rating;
    private Long userId;
    private Long itemId;
    private String userName;
    public ReviewDTO() {
    }

    public ReviewDTO(Long reviewId, String comment, int rating, Long userId, Long itemId) {
        this.reviewId = reviewId;
        this.comment = comment;
        this.rating = rating;
        this.userId = userId;
        this.itemId = itemId;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
