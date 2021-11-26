package com.example.Billing.Service.service;

import com.example.Billing.Service.controller.Dto.BillRequest;
import com.example.Billing.Service.model.Billing;
import com.example.Billing.Service.model.Item;
import com.example.Billing.Service.model.exception.ItemQuantityException;
import com.example.Billing.Service.repository.BillingRepository;
import com.example.Billing.Service.repository.ItemRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class BillingService {

    private final ItemRepository itemRepository;

    private final BillingRepository billingRepository;

    public BillingService(ItemRepository itemRepository, BillingRepository billingRepository) {
        this.itemRepository = itemRepository;
        this.billingRepository = billingRepository;
    }

    public Billing bill(BillRequest billRequest) {
        return billItem(billRequest);
    }

    public List<Billing> find(Pageable pageable, String userId) {
        return billingRepository.findByUserId(pageable, UUID.fromString(userId));
    }

    private Billing billItem(BillRequest billRequest ){

        Optional<Item> item = itemRepository.findById(billRequest.getItemId());
        if (billRequest.getQuantity() > item.get().getQuantity()){
            throw new ItemQuantityException(" The number of item you are trying to buy is not available please try again later. ");
        }

        // TODO: 21/11/2021  use the payment service to make the payment for the item


        // TODO: 21/11/2021  the  call to the payment method should be on this line

        Billing billingToBeCreated = Billing.builder()
                .billingAmount(billRequest.getItemPrice())
                .invoiceNumber(ThreadLocalRandom.current().nextInt())
                .userId(UUID.fromString(billRequest.getUserId()))
                .item(item.get())
                .status(true) // todo change this an enum. note status of the payment from payment service
                .build();


         return billingRepository.save(billingToBeCreated);
    }
}
