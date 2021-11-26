package com.exquis.app.paymentservice.service;

import com.exquis.app.paymentservice.dto.RaveCheckTransactionResponseDto;
import com.exquis.app.paymentservice.dto.UserWalletFundedDto;
import com.exquis.app.paymentservice.entity.TransactionLog;
import com.exquis.app.paymentservice.exception.HttpBadRequestException;
import com.exquis.app.paymentservice.exception.HttpGatewayException;
import com.exquis.app.paymentservice.exception.HttpNotFoundException;
import com.exquis.app.paymentservice.repository.TransactionLogRepository;
import com.exquis.app.paymentservice.service.contract.MessageServiceContract;
import com.exquis.app.paymentservice.service.contract.PaymentServiceContract;
import com.exquis.app.paymentservice.service.contract.RaveServiceContract;
import com.exquis.app.paymentservice.service.contract.TransactionServiceContract;
import com.exquis.app.paymentservice.utility.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class PaymentService implements PaymentServiceContract {
    @Autowired private TransactionLogRepository transactionLogRepository;
    @Autowired private RaveServiceContract raveService;
    @Autowired private TransactionServiceContract transactionService;
    @Autowired private MessageServiceContract messageService;

    @Override
    public void paymentResponse(@NotNull String status, @NotNull String transactionReference, @NotNull String transactionID)
    {
        System.out.println("here to confirm transaction");
        if(transactionLogRepository.findByTransactionReference(transactionReference) == null)
        {
            System.out.println("not in transaction_log");
            if(status.equalsIgnoreCase("successful") && transactionReference != null)
            {
                System.out.println("transaction ref::" + transactionReference);
                RaveCheckTransactionResponseDto raveVerifyTransactionReference = raveService.checkTransactionStatus(transactionReference);
                if (raveVerifyTransactionReference == null)
                    throw new HttpNotFoundException("Unrecognized Transaction reference on rave payment gateway");

                // make sure transaction is successful
                if(Helper.isNotEmpty(raveVerifyTransactionReference.getData()) && !raveVerifyTransactionReference.getData().getStatus().equalsIgnoreCase("successful") && !raveVerifyTransactionReference.getStatus().equalsIgnoreCase("success"))
                    throw new HttpGatewayException(raveVerifyTransactionReference.getMessage());

                TransactionLog transactionLog = new TransactionLog();
                transactionLog.setTransactionID(raveVerifyTransactionReference.getData().getTxid());
                transactionLog.setTransactionReference(raveVerifyTransactionReference.getData().getTxref());
                transactionLog.setDateTransactionLoggedAt(raveVerifyTransactionReference.getData().getCreated());
                transactionLog.setTransactionOrderNumber(raveVerifyTransactionReference.getData().getOrderref());
                transactionLog.setAmount(raveVerifyTransactionReference.getData().getAmount());
                transactionLog.setPayee(raveVerifyTransactionReference.getData().getCustname());
                transactionLog.setPayeeEmail(raveVerifyTransactionReference.getData().getCustemail());
                transactionLog.setCharge(raveVerifyTransactionReference.getData().getChargedamount());
                transactionLog.setResponseBody(Helper.convertToJson(raveVerifyTransactionReference.getData()));
                transactionLog.setTransactionOrderNumber(raveVerifyTransactionReference.getData().getOrderref());

                transactionService.create(transactionLog);

                // send message to wallet service via rabbit-mq
                UserWalletFundedDto userWalletFundedDto = new UserWalletFundedDto();
                userWalletFundedDto.setAmount(raveVerifyTransactionReference.getData().getAmount());
                userWalletFundedDto.setPayerName(raveVerifyTransactionReference.getData().getCustname());
                userWalletFundedDto.setPayerEmail(raveVerifyTransactionReference.getData().getCustemail());
                messageService.publishWalletFunded(userWalletFundedDto);

            }
            else {
                System.out.println("unable to confirm transaction");
            }
        }
    }

}
