package com.ganesh.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@Entity(name = "orders")
public class Order extends BaseModel {
    Integer amount;
    @ManyToMany
    @JoinTable(name = "orders_products",joinColumns ={ @JoinColumn(name = "product_id")},inverseJoinColumns ={@JoinColumn(name = "order_id")} )
    List<Product> productList;
}
