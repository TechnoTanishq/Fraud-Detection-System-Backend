package com.tksolutions.astraguard.controller;

import com.tksolutions.astraguard.dto.TransactionRequest;
import com.tksolutions.astraguard.dto.TransactionResponse;
import com.tksolutions.astraguard.dto.TransactionTransferRequest;
import com.tksolutions.astraguard.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionTransferRequest request) {

        TransactionResponse response =
                transactionService.processTransaction(request);

        return ResponseEntity.ok(response);
    }
}
