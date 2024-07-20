package com.ganesh.productservice.controllers;

import com.ganesh.productservice.DTO.CategoryResponseDTO;
import com.ganesh.productservice.DTO.ProductResponseDTO;
import com.ganesh.productservice.DTO.ValidateCategoryDTO;
import com.ganesh.productservice.Exceptions.IDNotFoundException;
import com.ganesh.productservice.interfaces.Converters;
import com.ganesh.productservice.models.Category;
import com.ganesh.productservice.models.Product;
import com.ganesh.productservice.services.CategoryService;
import com.ganesh.productservice.services.SelfCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    CategoryService service;
    Converters converters;
    IDNotFoundException exception;
    CategoryController(CategoryService service,Converters converters,IDNotFoundException exception){
        this.service=service;
        this.converters=converters;
        this.exception=exception;
    }
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody ValidateCategoryDTO dto) throws IDNotFoundException {
        Category category= service.createCategory(dto);
        if(category==null)
            throw exception;
        CategoryResponseDTO dto1=converters.categoryToCategoryResponseDTO(category);
        //CategoryResponseDTO dto2=new CategoryResponseDTO();
        return new ResponseEntity<>(dto1,HttpStatus.CREATED);
        //return new ResponseEntity<>(dto2,HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<CategoryResponseDTO>> getAllCategories() throws IDNotFoundException {
        List<Category> list=service.getAllCategories();
        if(list==null)
            throw exception;
        List<CategoryResponseDTO> categoryResponseDTOList=list.stream().map(category -> converters.categoryToCategoryResponseDTO(category)).toList();
        return new ResponseEntity<>(categoryResponseDTOList,HttpStatus.OK);
    }

    @GetMapping("/{category_id}/products")
    ResponseEntity<List<ProductResponseDTO>> getProductsByCategory(@PathVariable String category_id) throws IDNotFoundException {
        List<Product> list=service.getProductsByCategory(category_id);
        if(list==null)
            throw exception;
        List<ProductResponseDTO> productResponseDTOList=list.stream().map(product -> converters.productToProductResponseDTO(product)).toList();
        //System.out.println("category controller");
        return new ResponseEntity<>(productResponseDTOList,HttpStatus.OK);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }
        return errors;
    }
}
