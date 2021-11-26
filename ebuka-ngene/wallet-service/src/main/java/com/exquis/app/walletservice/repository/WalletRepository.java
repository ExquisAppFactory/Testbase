package com.exquis.app.walletservice.repository;

import com.exquis.app.walletservice.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByNumber(String walletNumber);
    Wallet findByUserId(UUID userId);
    Wallet findByEmail(String email);
}
