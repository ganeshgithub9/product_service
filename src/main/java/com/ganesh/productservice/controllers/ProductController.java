package com.ganesh.productservice.controllers;


import com.ganesh.productservice.DTO.ProductResponseDTO;
import com.ganesh.productservice.DTO.ValidatePriceDTO;
import com.ganesh.productservice.DTO.ValidateProductDTO;
import com.ganesh.productservice.Exceptions.IDNotFoundException;
import com.ganesh.productservice.Exceptions.ProductNotFoundException;
import com.ganesh.productservice.interfaces.Converters;
import com.ganesh.productservice.models.Product;
import com.ganesh.productservice.services.ProductService;
import com.ganesh.productservice.services.SelfProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController{
    ProductService productService;
    IDNotFoundException exception;
    Converters converters;
    ProductController(ProductService productService,IDNotFoundException exception,Converters converters){
        this.productService=productService;
        this.exception=exception;
        this.converters=converters;
    }
    @GetMapping("/{id}")
    ResponseEntity<ProductResponseDTO> getProductById(@PathVariable String id) throws IDNotFoundException, NumberFormatException {
        //System.out.println("product controller getProductById method");
        Product product=productService.getProductById(id);
        if(product==null)
            throw exception;
        ProductResponseDTO dto=converters.productToProductResponseDTO(product);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ValidateProductDTO productDTO) throws IDNotFoundException {
        Product product=productService.createProduct(productDTO);
        if(product==null)
            throw exception;
        ProductResponseDTO dto=converters.productToProductResponseDTO(product);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

    @PatchMapping("/{product_id}/categories/{category_id}")
    ResponseEntity<ProductResponseDTO> assignCategoryToProduct(@PathVariable String product_id, @PathVariable String category_id) throws IDNotFoundException {
        Product product=productService.assignCategoryToProduct(product_id,category_id);
        if(product==null)
            throw exception;
        ProductResponseDTO dto=converters.productToProductResponseDTO(product);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<ProductResponseDTO> updateProduct(@Valid @RequestBody ValidateProductDTO productDTO,@PathVariable String id) throws IDNotFoundException {
        Product product=productService.updateProduct(productDTO,id);
        if(product==null)
            throw exception;
        ProductResponseDTO dto=converters.productToProductResponseDTO(product);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

//    @PatchMapping("/{id}")
//    ResponseEntity<Product> updatePrice(@Valid @RequestBody ValidatePriceDTO priceDTO, @PathVariable UUID id) throws IDNotFoundException {
//        return selfProductService.updatePrice(priceDTO,id);
//    }

    @DeleteMapping("/{id}")
    ResponseEntity<ProductResponseDTO> deleteProductById(@PathVariable String id) throws IDNotFoundException {
        Product product=productService.deleteProductById(id);
        if(product==null)
            throw exception;
        ProductResponseDTO dto=converters.productToProductResponseDTO(product);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<ProductResponseDTO>> getProducts() throws IDNotFoundException{
        List<Product> products=productService.getProducts();
        if(products==null)
            throw exception;
        List<ProductResponseDTO> dtos=products.stream().map(product -> converters.productToProductResponseDTO(product)).toList();
        return new ResponseEntity<>(dtos,HttpStatus.OK);
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

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<String> throwProductNotFoundException(ProductNotFoundException exception){
//        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
//    }
}
