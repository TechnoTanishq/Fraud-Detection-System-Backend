package com.tksolutions.astraguard.service;

import com.tksolutions.astraguard.dto.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {

    private final RiskEngineClientService riskEngineClientService;
    private final DecisionAgentClientService decisionAgentClientService;

    public TransactionService(
            RiskEngineClientService riskEngineClientService,
            DecisionAgentClientService decisionAgentClientService) {
        this.riskEngineClientService = riskEngineClientService;
        this.decisionAgentClientService = decisionAgentClientService;
    }

    public TransactionResponse processTransaction(TransactionTransferRequest request) {

        // 1️⃣ Generate transaction reference
        String transactionId = UUID.randomUUID().toString();

        // 2️⃣ Build request for Python Risk Engine (ML + Rules)
        RiskEvaluationRequest riskRequest = new RiskEvaluationRequest(
                request.getAmount(),
                request.getLocation(),
                request.getDevice().getDeviceId()
        );

        // 3️⃣ Call Python Risk Engine
        RiskEvaluationResponse riskResponse =
                riskEngineClientService.evaluateRisk(riskRequest);

        // 4️⃣ Convert external response → internal RiskPacket
        RiskPacket riskPacket = new RiskPacket();
        riskPacket.setMlScore(riskResponse.getMlScore());
        riskPacket.setRuleScore(riskResponse.getRuleScore());
        riskPacket.setAnomalyScore(riskResponse.getAnomalyScore());
        riskPacket.setReasons(riskResponse.getReasons());

        // (Optional) log for debugging / demo
        System.out.println("Risk Packet: " + riskPacket);

        // 5️⃣ Send RiskPacket to Decision Agent
        DecisionResponse decision =
                decisionAgentClientService.evaluate(riskPacket);

        // (Optional) log decision
        System.out.println("Decision Taken: " + decision.getAction());

        double finalRiskScore =
                (riskPacket.getMlScore() * 0.5) +
                        (riskPacket.getRuleScore() * 0.3) +
                        (riskPacket.getAnomalyScore() * 0.2);


        // 6️⃣ Return final response to transaction app
        return new TransactionResponse(
                transactionId,
                decision.getAction(),
                finalRiskScore,
                decision.getExplanation()
        );
    }
}
