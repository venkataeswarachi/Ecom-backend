package com.example.Ecommerce.payload;

public class ItemDTO {

    private Long itemId;
    private String category;
    private String item_name;
    private String item_desc;

    private Long item_price;

    private boolean available;
    private String[] images = new String[5]; // No JSON logic here
    private Long seller_id;

    public ItemDTO() {}

    public ItemDTO(Long itemId, String category, String item_name, String item_desc,
                    Long item_price, boolean available,
                   String[] images, Long seller_id) {
        this.itemId = itemId;
        this.category = category;
        this.item_name = item_name;
        this.item_desc = item_desc;

        this.item_price = item_price;

        this.available = available;
        this.images = images;
        this.seller_id = seller_id;
    }

    // ======= Getters & Setters =======

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

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public Long getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Long seller_id) {
        this.seller_id = seller_id;
    }
}
