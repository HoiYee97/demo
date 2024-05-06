package com.example.demo.controller;

import com.example.demo.model.entity.Item;
import com.example.demo.service.GetSingleItemService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class GetSingleItemController {
    //Assume this controller layer is in another microservice

    private final GetSingleItemService getSingleItemService;

    @GetMapping("/get/{id}")
    public Item getItem(@PathVariable UUID id) {
        return getSingleItemService.getItem(id);
    }

}