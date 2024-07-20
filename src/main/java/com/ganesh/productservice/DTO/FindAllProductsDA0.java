package com.ganesh.productservice.DTO;

import com.ganesh.productservice.models.Category;
import com.ganesh.productservice.models.Price;

import java.util.UUID;

public interface FindAllProductsDA0 {
    UUID getUuid();
    String getName();
    String getDescription();
    Double getRating();
    Double getPrice();
    String getCategory();
    String getCurrency();
}
