package com.exquis.app.paymentservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class TransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "transaction_reference", unique = true)
    private String transactionReference;

    private Long transactionID;

    private String transactionOrderNumber;
    @Column(name = "response_body", columnDefinition = "TEXT")
    private String responseBody; // after transaction

    private Double amount; // actual amount

    private Double charge;

    @Column(name = "date_transaction_logged")
    private String dateTransactionLoggedAt;

    @Column(name = "payee")
    private String payee;
    private String payeeEmail;

}
