package com.exquis.app.walletservice.service.implementation;

import com.exquis.app.walletservice.dto.CreateWalletRequestDto;
import com.exquis.app.walletservice.entity.ActivityLog;
import com.exquis.app.walletservice.entity.Wallet;
import com.exquis.app.walletservice.repository.ActivityLogRepository;
import com.exquis.app.walletservice.repository.WalletRepository;
import com.exquis.app.walletservice.service.contract.ActivityLogServiceContract;
import com.exquis.app.walletservice.service.contract.WalletServiceContract;
import com.exquis.app.walletservice.utility.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class WalletService implements WalletServiceContract {
    @Autowired private WalletRepository walletRepository;
    @Autowired private ActivityLogServiceContract activityLogService;

    @Override
    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet findByWalletNumber(String walletNumber) {
        return walletRepository.findByNumber(walletNumber);
    }

    @Override
    public Wallet findByUserId(UUID userId) {
        return walletRepository.findByUserId(userId);
    }

    @Override
    public Wallet findByEmail(String email)
    {
        return walletRepository.findByEmail(email);
    }

    @Override
    public Wallet create(CreateWalletRequestDto createWalletRequestDto) {
        Wallet wallet = new Wallet();
        wallet.setUserId(UUID.fromString(createWalletRequestDto.getUserId()));
        wallet.setEmail(createWalletRequestDto.getEmail());
        wallet.setNumber(Helper.getNumeric(10));

        Wallet newWallet = createOrUpdate(wallet);
        //log
        activityLogService.log(newWallet);

        return newWallet;
    }

    @Transactional
    @Override
    public Wallet createOrUpdate(Wallet wallet) {
        return walletRepository.save(wallet);
    }
}
