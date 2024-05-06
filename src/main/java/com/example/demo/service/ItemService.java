package com.example.demo.service;

import com.example.demo.client.GetSingleItemClientService;
import com.example.demo.model.dto.ItemRequestDTO;
import com.example.demo.model.dto.ItemResponseDTO;
import com.example.demo.model.entity.Item;
import com.example.demo.repository.ItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ObjectMapper objectMapper;
    private final GetSingleItemClientService getSingleItemClientService;

    @Transactional
    public ItemResponseDTO createItem(ItemRequestDTO itemRequestDTO) {
        Item newItem = Item.builder()
                .id(UUID.randomUUID())
                .name(itemRequestDTO.getName())
                .price(itemRequestDTO.getPrice())
                .build();
        return objectMapper.convertValue(itemRepository.save(newItem), ItemResponseDTO.class);
    }

    @Transactional
    public ItemResponseDTO updateItem(UUID id, ItemRequestDTO itemRequestDTO) {
        Item existingItem = getSingleItemClientService.getItem(id);
        existingItem.setName(itemRequestDTO.getName());
        existingItem.setPrice(itemRequestDTO.getPrice());
        return objectMapper.convertValue(itemRepository.save(existingItem), ItemResponseDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<ItemResponseDTO> getItems(Pageable pageable) {
        Page<Item> itemPage = itemRepository.findAll(pageable);
        return itemPage.map(item -> ItemResponseDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .build());
    }

}