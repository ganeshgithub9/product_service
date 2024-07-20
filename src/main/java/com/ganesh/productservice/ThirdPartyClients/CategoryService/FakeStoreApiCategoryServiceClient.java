package com.ganesh.productservice.ThirdPartyClients.CategoryService;

import com.ganesh.productservice.DTO.ApiProduct;
import com.ganesh.productservice.DTO.FakeStoreApiProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("fakeStoreApiCategoryServiceClient")
public class FakeStoreApiCategoryServiceClient implements ThirdPartyClientCategoryServiceAdapter{
   // String categoriesUrl="https://fakestoreapi.com/products/categories",categoryUrl="https://fakestoreapi.com/products/category/{category}";
    @Value("${fakestore.api.categories_url}")
    String categoriesUrl;
    @Value("${fakestore.api.category_url}")
    String categoryUrl;
    RestTemplate template;
    @Autowired
    FakeStoreApiCategoryServiceClient(RestTemplate template){
        this.template=template;
    }
    public ResponseEntity<String[]> getAllCategories(){
        ResponseEntity<String[]> responseEntity=template.getForEntity(categoriesUrl,String[].class);
        return responseEntity;
    }
    public ResponseEntity<ApiProduct[]> getProductsByCategory(String category){
        ResponseEntity<ApiProduct[]> responseEntity=template.getForEntity(categoryUrl, ApiProduct[].class,category);
        return responseEntity;
    }
}
