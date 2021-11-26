package com.exquis.app.paymentservice.service.contract;

import com.exquis.app.paymentservice.dto.RaveCheckTransactionResponseDto;

public interface RaveServiceContract {
    RaveCheckTransactionResponseDto checkTransactionStatus(String transactionRef);
}
