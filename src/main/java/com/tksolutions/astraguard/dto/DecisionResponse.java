package com.tksolutions.astraguard.dto;

import lombok.Data;

@Data
public class DecisionResponse {

    private String action;        // APPROVE, FLAG, BLOCK, OTP
    private String riskLevel;     // LOW, MEDIUM, HIGH
    private String explanation;   // Human-readable reason
    private boolean notifyUser;
    private boolean freezeAccount;
}
