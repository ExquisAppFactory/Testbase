package com.exquis.app.user.dto;

import com.exquis.app.user.utility.Dto;
import lombok.Data;

import java.util.UUID;

@Data
public class Wallet implements Dto {
    private String number;
    private Double amount;
    private UUID userId;
    private Long recordId;
}
