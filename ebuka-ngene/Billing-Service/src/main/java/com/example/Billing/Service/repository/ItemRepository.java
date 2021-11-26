package com.example.Billing.Service.repository;

import com.example.Billing.Service.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item,Integer> {
}
