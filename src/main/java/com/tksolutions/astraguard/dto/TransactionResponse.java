package com.tksolutions.astraguard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionResponse {

    private String transactionId;
    private String status;
    private Double riskScore;
    private String message;
}
