package com.exquis.app.walletservice.dto;

import lombok.Data;

@Data
public class CreateWalletRequestDto implements dto{
    private String userId;
    private String email;
}
