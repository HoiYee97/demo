package com.example.demo.client;

import com.example.demo.model.entity.Item;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GetSingleItemClientService {
    //Assume this rest client layer is in another microservice

    private final RestTemplate restTemplate;

    public Item getItem(UUID id) {
        return restTemplate.getForObject("http://localhost:8080/item/get/" + id, Item.class);
    }
}