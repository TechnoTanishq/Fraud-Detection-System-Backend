package com.tksolutions.astraguard.dto;

public class UserResponse {

    public String upiId;
    public String name;

    public UserResponse(String upiId, String name) {
        this.upiId = upiId;
        this.name = name;
    }
}