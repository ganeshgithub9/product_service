package com.ganesh.productservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiProduct {
    Long id;
    String title,description,image,category;
    Double price;
}
