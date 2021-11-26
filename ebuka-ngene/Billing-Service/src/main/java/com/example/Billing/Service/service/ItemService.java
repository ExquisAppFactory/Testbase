package com.example.Billing.Service.service;

import com.example.Billing.Service.controller.Dto.ItemRequest;
import com.example.Billing.Service.controller.Dto.ItemResponse;
import com.example.Billing.Service.model.Item;
import com.example.Billing.Service.model.exception.ItemNotFoundException;
import com.example.Billing.Service.repository.ItemRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;


    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public ItemResponse addItem(ItemRequest item){
        Item itemToBeCreated = Item.builder()
                .itemName(item.getItemName())
                .itemPrice(item.getItemPrice())
                .quantity(item.getQuantity())
                .dateCreated(LocalDateTime.now())
                .build();

        return  mapToItemResponse(itemRepository.save(itemToBeCreated));
    }

    public ItemResponse get(int id){
        Optional<Item> item = itemRepository.findById(id);

        if (!item.isPresent()){
            throw new ItemNotFoundException("item not found");
        }

        return mapToItemResponse(item.get());
    }

    public List<ItemResponse> find(Pageable pageable){
        return mapToMapToItemResponses(itemRepository.findAll(pageable).getContent());
    }

    public void deleteItem(int id){
        Optional<Item> item = itemRepository.findById(id);

        if (!item.isPresent()){
            throw new ItemNotFoundException("item not found");
        }

        itemRepository.delete(item.get());
    }

    private ItemResponse mapToItemResponse(Item item){
        return ItemResponse.builder()
                .id(item.getId())
                .itemName(item.getItemName())
                .itemPrice(item.getItemPrice())
                .quantity(item.getQuantity())
                .dateCreated(item.getDateCreated())
                .build();
    }

    private List<ItemResponse> mapToMapToItemResponses(List<Item> items) {

        if (items.isEmpty()) return Arrays.asList();

       return items.stream()
               .map(this::mapToItemResponse)
               .collect(Collectors.toList());
    }
}
