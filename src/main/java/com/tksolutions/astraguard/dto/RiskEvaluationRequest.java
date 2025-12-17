package com.tksolutions.astraguard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RiskEvaluationRequest {
    private Double amount;
    private String location;
    private String deviceId;

    public RiskEvaluationRequest(Double amount, TransactionTransferRequest.Location location, Object deviceId) {
    }
}
