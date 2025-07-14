package com.example.Ecommerce.payload;

public class UserResponseDTO {
    private Long user_id;
    private String email;
    private String name;
    private boolean isSeller;
    public UserResponseDTO() {
    }

    public UserResponseDTO(Long user_id, String email, String name,boolean isSeller) {
        this.user_id = user_id;
        this.email = email;
        this.name = name;
        this.isSeller = isSeller;
    }

    public Long getId() {
        return user_id;
    }

    public void setId(Long user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUser_id() {return user_id;}

    public void setUser_id(Long user_id) {this.user_id = user_id;}

    public boolean getIsSeller() {return isSeller;}

    public void setIsSeller(boolean seller) {isSeller = seller;}
}
