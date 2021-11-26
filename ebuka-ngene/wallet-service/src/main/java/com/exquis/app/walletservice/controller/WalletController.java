package com.exquis.app.walletservice.controller;

import com.exquis.app.walletservice.entity.ActivityLog;
import com.exquis.app.walletservice.entity.Wallet;
import com.exquis.app.walletservice.repository.ActivityLogRepository;
import com.exquis.app.walletservice.service.contract.WalletServiceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("wallets/")
public class WalletController {
    @Autowired private WalletServiceContract walletService;
    @Autowired private ActivityLogRepository activityLogRepository;

    @GetMapping
    public List<Wallet> getAll()
    {
        return walletService.findAll();
    }

    @GetMapping("{id}")
    public Wallet getByUser(@PathVariable("id") String userId)
    {
        UUID id = UUID.fromString(userId);
        return walletService.findByUserId(id);
    }

    @GetMapping("/logs")
    public List<ActivityLog> getLogs()
    {
        return activityLogRepository.findAll();
    }
}
