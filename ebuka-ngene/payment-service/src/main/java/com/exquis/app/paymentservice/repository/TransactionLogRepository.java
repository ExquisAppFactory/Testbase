package com.exquis.app.paymentservice.repository;

import com.exquis.app.paymentservice.entity.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionLogRepository extends JpaRepository<TransactionLog, Long> {
    TransactionLog findByTransactionReference(String transactionReference);
}
