package com.tksolutions.astraguard.service;

import com.tksolutions.astraguard.dto.DecisionResponse;
import com.tksolutions.astraguard.dto.RiskPacket;
import org.springframework.stereotype.Service;

@Service
public class DecisionAgentClientService {

    public DecisionResponse evaluate(RiskPacket riskPacket) {

        // This will later call AI Agent (LangGraph / LLM)
        // For now: intelligent deterministic logic

        DecisionResponse decision = new DecisionResponse();

        double combinedRisk =
                (riskPacket.getMlScore() * 0.5) +
                        (riskPacket.getRuleScore() * 0.3) +
                        (riskPacket.getAnomalyScore() * 0.2);

        if (combinedRisk < 0.3) {
            decision.setAction("APPROVE");
            decision.setRiskLevel("LOW");
            decision.setExplanation("Low risk transaction");
            decision.setNotifyUser(false);
            decision.setFreezeAccount(false);

        } else if (combinedRisk < 0.6) {
            decision.setAction("FLAG");
            decision.setRiskLevel("MEDIUM");
            decision.setExplanation("Suspicious patterns detected");
            decision.setNotifyUser(true);
            decision.setFreezeAccount(false);

        } else {
            decision.setAction("BLOCK");
            decision.setRiskLevel("HIGH");
            decision.setExplanation(
                    "High fraud risk due to: " + riskPacket.getReasons()
            );
            decision.setNotifyUser(true);
            decision.setFreezeAccount(true);
        }

        return decision;
    }
}
