package com.exquis.app.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWalletFundedDto implements dto{
    private String payerEmail;
    private String payerName;
    private double amount;
}
