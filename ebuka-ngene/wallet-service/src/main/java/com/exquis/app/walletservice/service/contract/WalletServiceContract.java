package com.exquis.app.walletservice.service.contract;

import com.exquis.app.walletservice.dto.CreateWalletRequestDto;
import com.exquis.app.walletservice.entity.Wallet;

import java.util.List;
import java.util.UUID;

public interface WalletServiceContract {
    List<Wallet> findAll();
    Wallet findByWalletNumber(String walletNumber);
    Wallet findByUserId(UUID userId);
    Wallet findByEmail(String email);
    Wallet create(CreateWalletRequestDto createWalletRequestDto);
    Wallet createOrUpdate(Wallet wallet);
}
