package com.example.demo.controller;

import com.example.demo.model.dto.ItemRequestDTO;
import com.example.demo.model.dto.ItemResponseDTO;
import com.example.demo.service.ItemService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/create")
    public ResponseEntity<ItemResponseDTO> createItem(@RequestBody ItemRequestDTO itemRequestDTO) {
        return new ResponseEntity<>(itemService.createItem(itemRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ItemResponseDTO> updateItem(@PathVariable UUID id,
                                                      @RequestBody ItemRequestDTO itemRequestDTO) {
        return new ResponseEntity<>(itemService.updateItem(id, itemRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<ItemResponseDTO>> getItems(Pageable pageable) {
        return new ResponseEntity<>(itemService.getItems(pageable), HttpStatus.OK);
    }
}