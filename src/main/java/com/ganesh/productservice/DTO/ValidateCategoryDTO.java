package com.ganesh.productservice.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateCategoryDTO {
    @NotBlank(message = "Category name is mandatory")
    String name;
}
