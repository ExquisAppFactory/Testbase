package com.example.Billing.Service.controller.Dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ItemResponse {

    private int id;

    private String itemName;

    private double itemPrice;

    private int quantity;

    private LocalDateTime dateCreated;
}
