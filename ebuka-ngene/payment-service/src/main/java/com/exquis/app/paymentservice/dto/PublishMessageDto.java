package com.exquis.app.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishMessageDto implements dto {
    private String type;
    private UserWalletFundedDto userWalletFundedDto;
}
