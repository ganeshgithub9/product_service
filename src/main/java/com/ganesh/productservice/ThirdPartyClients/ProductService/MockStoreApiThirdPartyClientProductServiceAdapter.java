package com.ganesh.productservice.ThirdPartyClients.ProductService;

import com.ganesh.productservice.DTO.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("mockStoreApiThirdPartyClientProductServiceAdapter")
public class MockStoreApiThirdPartyClientProductServiceAdapter implements ThirdPartyClientProductServiceAdapter{


    @Override
    public ResponseEntity<ApiProduct> createProduct(ValidateProductDTO productDTO) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiProduct> updateProduct(ValidateProductDTO productDTO, Long id) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiProduct> updatePrice(PriceDTO priceDTO, Long id) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiProduct> deleteProductById(Long id) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ApiProduct>> getProducts() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiProduct> getProductById(Long id) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ApiProduct>> getProductsByCategory(String category) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
