package com.ganesh.productservice.controllers;

import com.ganesh.productservice.models.Category;
import com.ganesh.productservice.models.Product;
import com.ganesh.productservice.repositories.CategoryRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/test")
public class TestingController {
    CategoryRepository repository;
    TestingController(CategoryRepository repository){
        this.repository=repository;
    }

    @GetMapping("/{category_id}")
    ResponseEntity<List<String>> test(@PathVariable UUID category_id){
        Optional<Category> category=repository.findById(category_id);
        Category category1=category.orElse(null);
        List<Product> list=category1.getProducts();
        List<String> result=new ArrayList<>();
        for(Product p:list)
            result.add(p.getName());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
