package com.exquis.app.paymentservice.service.contract;

import com.exquis.app.paymentservice.dto.UserWalletFundedDto;

public interface MessageServiceContract {
    String publishWalletFunded(UserWalletFundedDto userWalletFundedDto);
}
