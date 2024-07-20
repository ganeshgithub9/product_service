package com.ganesh.productservice.ThirdPartyClients.CategoryService;

import com.ganesh.productservice.DTO.ApiProduct;
import com.ganesh.productservice.DTO.FakeStoreApiProduct;
import org.springframework.http.ResponseEntity;

public interface ThirdPartyClientCategoryServiceAdapter {
    public ResponseEntity<String[]> getAllCategories();
    public ResponseEntity<ApiProduct[]> getProductsByCategory(String category);
}
