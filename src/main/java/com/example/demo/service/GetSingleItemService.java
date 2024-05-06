package com.example.demo.service;

import com.example.demo.model.entity.Item;
import com.example.demo.repository.ItemRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class GetSingleItemService {
    //Assume this service layer is in another microservice

    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public Item getItem(UUID id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Item not found with id " + id));
    }

}