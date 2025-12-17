package com.tksolutions.astraguard.dto;

public class LoginResponse {

    public String token;
    public UserResponse user;

    public LoginResponse(String token, UserResponse user) {
        this.token = token;
        this.user = user;
    }
}