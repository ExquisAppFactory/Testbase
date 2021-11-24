package com.exquis.app.user.dto;

import com.exquis.app.user.utility.Dto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserWalletResponseDto implements Dto {
    private String userId;
    private String email;
    private String contactPhone;
    private String walletNumber;
    private Double walletAmount;
}
