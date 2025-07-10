package com.example.Ecommerce.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

@Entity
public class ItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private String category;
    private String item_name;
    private String item_desc;

    private Long item_price;

    private boolean available;

    @Lob
    @Column(name = "images", columnDefinition = "TEXT")
    private String imagesJson; // Stored as JSON string in DB

    @Transient
    private String[] images; // Used only in Java layer

    @ManyToOne
    @JoinColumn(name = "user_id") // Foreign Key in item table
    private UserModel user;

    // ======= Constructors =======

    public ItemModel() {}

    public ItemModel(Long itemId, String category, String item_name, String item_desc,
                     Long item_price, boolean available,
                     String[] images, UserModel user) {
        this.itemId = itemId;
        this.category = category;
        this.item_name = item_name;
        this.item_desc = item_desc;

        this.item_price = item_price;

        this.available = available;
        setImages(images); // will update imagesJson too
        this.user = user;
    }

    // ======= Images Conversion =======

    public String[] getImages() {
        if (images == null && imagesJson != null) {
            try {
                images = new ObjectMapper().readValue(imagesJson, String[].class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
        try {
            this.imagesJson = new ObjectMapper().writeValueAsString(images);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getImagesJson() {
        return imagesJson;
    }

    public void setImagesJson(String imagesJson) {
        this.imagesJson = imagesJson;
        try {
            this.images = new ObjectMapper().readValue(imagesJson, String[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ======= Other Getters & Setters =======

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }


    public Long getItem_price() {
        return item_price;
    }

    public void setItem_price(Long item_price) {
        this.item_price = item_price;
    }


    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
