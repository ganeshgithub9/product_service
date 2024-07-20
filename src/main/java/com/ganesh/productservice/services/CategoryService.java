package com.ganesh.productservice.services;

import com.ganesh.productservice.DTO.CategoryResponseDTO;
import com.ganesh.productservice.DTO.FakeStoreApiProduct;
import com.ganesh.productservice.DTO.ValidateCategoryDTO;
import com.ganesh.productservice.Exceptions.IDNotFoundException;
import com.ganesh.productservice.models.Category;
import com.ganesh.productservice.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService{

    Category createCategory(ValidateCategoryDTO dto);

    List<Category> getAllCategories();
    List<Product> getProductsByCategory(String category) throws IDNotFoundException;
}
