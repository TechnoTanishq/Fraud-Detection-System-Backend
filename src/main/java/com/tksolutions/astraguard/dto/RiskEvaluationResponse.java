package com.tksolutions.astraguard.dto;

import lombok.Data;
import java.util.List;

@Data
public class RiskEvaluationResponse {

    private Double mlScore;
    private Double ruleScore;
    private Double anomalyScore;
    private List<String> reasons;
}
