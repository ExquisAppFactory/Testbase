package com.exquis.app.walletservice.entity;

import com.exquis.app.walletservice.dto.dto;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(
        uniqueConstraints = {
        @UniqueConstraint(name = "UniqueWalletAndUser", columnNames = {"number", "userId"})},
        indexes = {@Index(name = "index_wallet_number", columnList = "number", unique = true)})
public class Wallet implements dto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String number;
    private double amount;
    @Column(nullable = false, unique = true)
    private UUID userId;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @PrePersist
    private void onCreate()
    {
        amount = 0D; //initialize wallet to zero when it's created
        createdAt = LocalDateTime.now();
    }
}
