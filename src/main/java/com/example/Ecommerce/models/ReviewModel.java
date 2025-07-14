package com.example.Ecommerce.models;

import jakarta.persistence.*;


@Entity
@Table(name = "ReviewModel", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user", "item"})
})
public class ReviewModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    private String comment;
    private int rating;
    @ManyToOne
    @JoinColumn(name = "item")
    private ItemModel item;
    @ManyToOne
    @JoinColumn(name = "user")
    private UserModel user;

    public ReviewModel(Long reviewId, String comment, int rating, ItemModel item, UserModel user) {
        this.reviewId = reviewId;
        this.comment = comment;
        this.rating = rating;
        this.item = item;
        this.user = user;
    }

    public ReviewModel() {

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

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
