package com.example.Ecommerce.payload;

public class JwtAuthenticationResponse {
    private String token;
    private String tokenType = "Bearer";
    public JwtAuthenticationResponse(String token){
        this.token = token;

    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
