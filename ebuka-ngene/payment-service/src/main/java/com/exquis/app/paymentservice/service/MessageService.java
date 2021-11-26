package com.exquis.app.paymentservice.service;

import com.exquis.app.paymentservice.config.MQConfig;
import com.exquis.app.paymentservice.constant.Generic;
import com.exquis.app.paymentservice.dto.UserWalletFundedDto;
import com.exquis.app.paymentservice.service.contract.MessageServiceContract;
import com.exquis.app.paymentservice.utility.Helper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements MessageServiceContract {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public String publishWalletFunded(UserWalletFundedDto userWalletFundedDto) {
        if(Helper.isEmpty(userWalletFundedDto)) return null;

        //PublishMessageDto publishMessageDto = new PublishMessageDto();
        //publishMessageDto.setType("FUND");
        //publishMessageDto.setUserWalletFundedDto(userWalletFundedDto);

        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, userWalletFundedDto);
        System.out.println("WALLET FUNDING MESSAGE PUBLISHED");
        return Generic.MESSAGE_PUBLISHED;
    }
}
