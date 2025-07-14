package com.example.Ecommerce.payload;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDate;

public class OrderDetailsDTO {
    private String address;
    private Long number;
    private int Quantity;
    @Temporal(TemporalType.DATE)
    private LocalDate orderDate;
    public OrderDetailsDTO(String address, Long number, int quantity,LocalDate orderDate) {
        this.address = address;
        this.number = number;
        Quantity = quantity;
        this.orderDate = orderDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public int getQuantity() {return Quantity;}

    public void setQuantity(int quantity) {Quantity = quantity;}

    public LocalDate getOrderDate() {return orderDate;}

    public void setOrderDate(LocalDate orderDate) {this.orderDate = orderDate;}
}
