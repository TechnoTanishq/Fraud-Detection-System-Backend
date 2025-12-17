package com.tksolutions.astraguard.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class TransactionRequest {

    @NotNull
    private Long userId;

  //  @Positive
    private Double amount;

    @NotNull
    private String currency;

    @NotNull
    private String deviceId;

    @NotNull
    private String location;

    @NotNull
    private String merchant;

    @NotNull
    private String timestamp;
}
