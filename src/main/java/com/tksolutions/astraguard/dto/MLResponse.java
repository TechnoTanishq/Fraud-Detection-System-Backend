package com.tksolutions.astraguard.dto;

import lombok.Data;

@Data
public class MLResponse {
    private Double fraud_probability;
    private Double anomaly_score;
    private String model_reason;
}
