package com.ganesh.productservice.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidateProductDTO {
    @NotBlank(message = "Product name is mandatory")
    String title;
    @NotBlank(message = "Product description is mandatory")
    String description;

    @NotBlank(message = "Price currency is mandatory")
    String currency;
    @NotNull(message = "Price should not be null")
    @Min(value = 1,message = "Price should be greater than zero")
    Double price;

    String image;
    Double rating;
    String category;
}
