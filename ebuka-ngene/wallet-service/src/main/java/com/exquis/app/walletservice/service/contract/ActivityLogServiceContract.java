package com.exquis.app.walletservice.service.contract;

import com.exquis.app.walletservice.entity.ActivityLog;
import com.exquis.app.walletservice.entity.Wallet;

public interface ActivityLogServiceContract {
    ActivityLog log(Wallet wallet);
    ActivityLog create(ActivityLog activityLog);
}
