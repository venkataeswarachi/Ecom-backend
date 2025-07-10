package com.example.Ecommerce.models;

import jakarta.persistence.*;

@Entity
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @Column(unique = true)
    private String email;
    private String name;
    private String password;
    private Long number;
    @Column( nullable = false)
    private boolean isSeller=false;

    public UserModel(String email, String name, String password, Long number,boolean isSeller) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.number = number;
        this.isSeller = isSeller;
    }

    public UserModel() {
    }


    public Long getUser_id() {
        return user_id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public boolean getIsSeller() { return isSeller; } // ✅ better naming
    public void setIsSeller(boolean isSeller) { this.isSeller = isSeller; } // ✅
}
