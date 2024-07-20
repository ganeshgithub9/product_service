package com.ganesh.productservice.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidatePriceDTO {
    @NotBlank(message = "Price currency is mandatory")
    String currency;
    @NotNull
            @Min(value = 1)
    Double price;
}
