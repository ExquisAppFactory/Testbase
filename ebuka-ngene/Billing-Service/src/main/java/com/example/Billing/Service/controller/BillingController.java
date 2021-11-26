package com.example.Billing.Service.controller;

import com.example.Billing.Service.controller.Dto.BillRequest;
import com.example.Billing.Service.model.Billing;
import com.example.Billing.Service.service.BillingService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequestMapping("/bills")
@RestController
public final class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }


    @PostMapping(produces = "application/json", consumes = "application/json")
    public Billing createBill (@RequestBody BillRequest billRequest){
        return billingService.bill(billRequest);
    }

    @GetMapping(produces = "application/json")
    public List<Billing> getAllUsersBill(@RequestParam String userId, @RequestParam  int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("dateCreated"));

        return billingService.find(pageable,userId);
    }
}
