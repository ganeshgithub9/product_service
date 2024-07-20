package com.ganesh.productservice.services;

import com.ganesh.productservice.DTO.ApiProduct;
import com.ganesh.productservice.DTO.CategoryResponseDTO;
import com.ganesh.productservice.DTO.FakeStoreApiProduct;
import com.ganesh.productservice.DTO.ValidateCategoryDTO;
import com.ganesh.productservice.ThirdPartyClients.CategoryService.ThirdPartyClientCategoryServiceAdapter;
import com.ganesh.productservice.interfaces.Converters;
import com.ganesh.productservice.models.Category;
import com.ganesh.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("thirdPartyApiCategoryService")
public class ThirdPartyApiCategoryService implements CategoryService{
    ThirdPartyClientCategoryServiceAdapter thirdPartyClientCategoryServiceAdapter;
    Converters converters;
    @Autowired
    ThirdPartyApiCategoryService(@Qualifier("fakeStoreApiCategoryServiceClient") ThirdPartyClientCategoryServiceAdapter thirdPartyClientCategoryServiceAdapter, Converters converters){
        this.thirdPartyClientCategoryServiceAdapter=thirdPartyClientCategoryServiceAdapter;
        this.converters=converters;
    }

    @Override
    public Category createCategory(ValidateCategoryDTO dto) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        String[] list= thirdPartyClientCategoryServiceAdapter.getAllCategories().getBody();
        if(list==null)
            return null;
        List<Category> result= Arrays.stream(list).map(name->{
            Category c=new Category();
            c.setName(name);
            return c;
        }).toList();
        return result;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        ApiProduct[] list= thirdPartyClientCategoryServiceAdapter.getProductsByCategory(category).getBody();
        if(list==null)
            return null;
        List<Product> result=new ArrayList<>();
        return Arrays.stream(list).map(apiProduct->converters.apiProductToProduct(apiProduct)).toList();
    }
}
