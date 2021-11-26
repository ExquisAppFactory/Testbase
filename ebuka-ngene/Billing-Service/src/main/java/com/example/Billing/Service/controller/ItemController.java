package com.example.Billing.Service.controller;


import com.example.Billing.Service.controller.Dto.ItemRequest;
import com.example.Billing.Service.controller.Dto.ItemResponse;
import com.example.Billing.Service.service.ItemService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ItemResponse createItem(@RequestBody ItemRequest itemRequest) {
        return itemService.addItem(itemRequest);
    }

    @GetMapping(produces = "application/json")
    public List<ItemResponse> getItems(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("dateCreated"));
       return itemService.find(pageable);
    }

    @GetMapping(path="/{id}", produces = "application/json")
    public ItemResponse getItemById(@PathVariable int id){
        return itemService.get(id);
    }

    @DeleteMapping(path="/{id}")
    public void deleteItem(@PathVariable int id){
        itemService.deleteItem(id);
    }
}
