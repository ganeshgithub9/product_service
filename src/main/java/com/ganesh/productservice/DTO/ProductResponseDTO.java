package com.ganesh.productservice.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ProductResponseDTO {
    String id;
    String title;
    String description;
    Double rating;
    Double price;
    String category;
    String currency;
    String image;
}
