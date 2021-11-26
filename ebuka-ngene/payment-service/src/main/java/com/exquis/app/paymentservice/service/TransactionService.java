package com.exquis.app.paymentservice.service;

import com.exquis.app.paymentservice.entity.TransactionLog;
import com.exquis.app.paymentservice.repository.TransactionLogRepository;
import com.exquis.app.paymentservice.service.contract.TransactionServiceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionService implements TransactionServiceContract {
    @Autowired private TransactionLogRepository transactionLogRepository;

    @Transactional
    @Override
    public TransactionLog create(TransactionLog transactionLog) {
        return transactionLogRepository.save(transactionLog);
    }
}
