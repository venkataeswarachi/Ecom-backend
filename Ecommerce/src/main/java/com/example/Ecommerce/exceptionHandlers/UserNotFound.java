package com.example.Ecommerce.exceptionHandlers;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String message){
        super(message);
    }
}
