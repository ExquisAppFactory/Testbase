package com.exquis.app.walletservice.service;

import com.exquis.app.walletservice.config.MQConfig;
import com.exquis.app.walletservice.dto.CreateWalletRequestDto;
import com.exquis.app.walletservice.dto.PublishMessageDto;
import com.exquis.app.walletservice.dto.UserWalletFundedDto;
import com.exquis.app.walletservice.entity.Wallet;
import com.exquis.app.walletservice.service.contract.WalletServiceContract;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MessageListener {
    static final String QUEUE_WALLET_FUNDING = "message_queue_fund_wallet";

    @Autowired private WalletServiceContract walletService;

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listenerFromUserService(PublishMessageDto publishMessageDto)
    {
        System.out.println("message from topic_exchange ::" + publishMessageDto);
        Wallet wallet = walletService.findByUserId(UUID.fromString(publishMessageDto.getUserLiteDto().getId()));
        if(wallet == null)
        {
            CreateWalletRequestDto createWalletRequestDto = new CreateWalletRequestDto();
            createWalletRequestDto.setUserId(publishMessageDto.getUserLiteDto().getId());
            createWalletRequestDto.setEmail(publishMessageDto.getUserLiteDto().getEmail());
            walletService.create(createWalletRequestDto);
        }
    }

    @RabbitListener(queues = QUEUE_WALLET_FUNDING)
    public void listenerPaymentService(UserWalletFundedDto userWalletFundedDto)
    {
        System.out.println("message from topic_exchange_fund_wallet ::" + userWalletFundedDto);
        Wallet wallet = walletService.findByEmail(userWalletFundedDto.getPayerEmail());
        if(wallet != null)
        {
            // credit wallet
            wallet.setAmount(wallet.getAmount() + userWalletFundedDto.getAmount());
            walletService.createOrUpdate(wallet);
        }
        else {
            System.out.println("unable to credit wallet");
        }
    }
}
