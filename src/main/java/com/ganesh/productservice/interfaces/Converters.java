package com.ganesh.productservice.interfaces;

import com.ganesh.productservice.DTO.*;
import com.ganesh.productservice.models.Category;
import com.ganesh.productservice.models.Price;
import com.ganesh.productservice.models.Product;

public interface Converters {
    ProductResponseDTO findAllProductDaoToProductResponseDTO(FindAllProductsDA0 da0);
    ProductResponseDTO productToProductResponseDTO(Product product);
    ProductResponseDTO apiProductToProductResponseDTO(ApiProduct product);
    ApiProduct fakeStoreApiProductToApiProduct(FakeStoreApiProduct product);
    Product apiProductToProduct(ApiProduct product);
    CategoryResponseDTO categoryToCategoryResponseDTO(Category c);
    Price validateProductDTOToPrice(ValidateProductDTO dto);
    Product validateProductDTOToProduct(ValidateProductDTO dto);
}
