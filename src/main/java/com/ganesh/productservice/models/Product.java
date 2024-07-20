package com.ganesh.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity(name = "products")
public class Product extends BaseModel{
    String name;
    String description;
    Double rating;
    @ManyToOne
            @Fetch(value = FetchMode.JOIN)
    Category category;
    @ManyToMany(mappedBy = "productList")
    List<Order> orderList;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @Fetch(value = FetchMode.JOIN)
    Price price;
}
