package com.exquis.app.walletservice.service.implementation;

import com.exquis.app.walletservice.entity.ActivityLog;
import com.exquis.app.walletservice.entity.Wallet;
import com.exquis.app.walletservice.repository.ActivityLogRepository;
import com.exquis.app.walletservice.service.contract.ActivityLogServiceContract;
import com.exquis.app.walletservice.type.ActivityLogType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ActivityLogService implements ActivityLogServiceContract {
    @Autowired
    private ActivityLogRepository activityLogRepository;

    @Override
    public ActivityLog log(Wallet wallet) {
        ActivityLog activityLog = new ActivityLog();
        activityLog.setUserId(wallet.getUserId());
        activityLog.setWalletNumber(wallet.getNumber());
        activityLog.setAmount(wallet.getAmount());
        activityLog.setRemark(ActivityLogType.WALLET_CREATED.name());

        return create(activityLog);
    }

    @Transactional
    @Override
    public ActivityLog create(ActivityLog activityLog) {
        return activityLogRepository.save(activityLog);
    }
}
