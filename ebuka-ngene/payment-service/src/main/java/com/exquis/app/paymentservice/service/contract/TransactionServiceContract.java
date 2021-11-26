package com.exquis.app.paymentservice.service.contract;

import com.exquis.app.paymentservice.entity.TransactionLog;

public interface TransactionServiceContract {
    TransactionLog create(TransactionLog transactionLog);
}
