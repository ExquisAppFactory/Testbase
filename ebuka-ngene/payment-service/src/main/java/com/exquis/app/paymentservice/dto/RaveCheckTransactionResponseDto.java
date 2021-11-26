package com.exquis.app.paymentservice.dto;

import lombok.Data;

@Data
public class RaveCheckTransactionResponseDto implements dto{
    private String status;
    private String message;
    private RaveCheckTransactionResponseBodyDto data;
}
