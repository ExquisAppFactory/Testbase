package com.exquis.app.walletservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishMessageDto implements dto {
    private String type;
    private UserLiteDto userLiteDto;
}
