package com.example.Billing.Service.controller.Dto;

import lombok.Data;


@Data
public class BillRequest {

    private Integer itemId;

    private double itemPrice;

    private int quantity;

    private String userId;
}
