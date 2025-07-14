package com.example.Ecommerce.payload;


import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {
    private Long user_id;

    private String email;
    private String name;
    private String password;
    private Long number;

    @JsonProperty("isSeller")
    private boolean isSeller;

    public Long getUser_id() { return user_id; }
    public void setUser_id(Long user_id) { this.user_id = user_id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Long getNumber() { return number; }
    public void setNumber(Long number) { this.number = number; }

    public boolean getIsSeller() { return isSeller; } // ✅ better naming
    public void setIsSeller(boolean isSeller) { this.isSeller = isSeller; } // ✅
}