package com.example.Billing.Service.model.exception;

public class ItemNotFoundException  extends RuntimeException{

    public ItemNotFoundException(String message) {
        super(message);
    }
}
