package com.example.Billing.Service.repository;

import com.example.Billing.Service.model.Billing;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BillingRepository  extends JpaRepository<Billing, Integer> {

    // todo write a qury for this
    List<Billing>findByUserId(Pageable pageable, UUID userId);
}
