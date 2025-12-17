package com.tksolutions.astraguard.model;

public class AuthUser {

    private String userId;
    private String upiId;

    public AuthUser(String userId, String upiId) {
        this.userId = userId;
        this.upiId = upiId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUpiId() {
        return upiId;
    }
}
