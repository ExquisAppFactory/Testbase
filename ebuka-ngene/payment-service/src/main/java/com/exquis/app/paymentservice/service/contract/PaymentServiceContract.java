package com.exquis.app.paymentservice.service.contract;

public interface PaymentServiceContract {
    void paymentResponse(String status, String transactionReference, String transactionID);
}
