package com.ganesh.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@Entity(name = "categories")
public class Category extends BaseModel{
    String name;

    @OneToMany(mappedBy = "category")
    List<Product> products;
}
