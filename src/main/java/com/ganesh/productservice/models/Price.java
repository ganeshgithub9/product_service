package com.ganesh.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "prices")
public class Price extends BaseModel{
    String currency;
    Double price;
    @OneToOne(mappedBy = "price")
    Product product;
}
