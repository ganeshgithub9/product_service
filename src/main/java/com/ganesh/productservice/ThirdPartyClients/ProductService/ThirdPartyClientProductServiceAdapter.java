package com.ganesh.productservice.ThirdPartyClients.ProductService;

import com.ganesh.productservice.DTO.*;
import com.ganesh.productservice.Exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ThirdPartyClientProductServiceAdapter {
    public ResponseEntity<ApiProduct> createProduct(ValidateProductDTO productDTO);
    public ResponseEntity<ApiProduct> updateProduct(ValidateProductDTO productDTO,Long id);
    public ResponseEntity<ApiProduct> updatePrice(PriceDTO priceDTO, Long id);
    public ResponseEntity<ApiProduct> deleteProductById(Long id);
    public ResponseEntity<List<ApiProduct>> getProducts();
    ResponseEntity<ApiProduct> getProductById(Long id);
    ResponseEntity<List<ApiProduct>> getProductsByCategory(String category);
}
