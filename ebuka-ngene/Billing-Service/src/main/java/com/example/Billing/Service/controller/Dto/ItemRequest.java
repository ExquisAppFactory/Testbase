package com.example.Billing.Service.controller.Dto;

import lombok.Data;


@Data
public class ItemRequest{
    private String itemName;
    private double itemPrice;
    private int quantity;
}
