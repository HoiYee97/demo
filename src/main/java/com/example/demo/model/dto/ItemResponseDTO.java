package com.example.demo.model.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemResponseDTO {

    private UUID id;
    private String name;
    private double price;

}