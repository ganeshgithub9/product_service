package com.ganesh.productservice.services;

import com.ganesh.productservice.DTO.*;
import com.ganesh.productservice.Exceptions.IDNotFoundException;
import com.ganesh.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(String id) throws NumberFormatException;
    Product createProduct(ValidateProductDTO productDTO);
    Product updateProduct(ValidateProductDTO productDTO,String id);

    Product deleteProductById(String id);

     List<Product> getProducts();

   // List<Product> getProductsByCategory(String category);

    Product assignCategoryToProduct(String product_id, String category_id);
}
