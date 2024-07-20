package com.ganesh.productservice.services;


import com.ganesh.productservice.interfaces.Converters;
import com.ganesh.productservice.interfaces.ModelMapperConverters;
import com.ganesh.productservice.DTO.CategoryResponseDTO;
import com.ganesh.productservice.DTO.ValidateCategoryDTO;
import com.ganesh.productservice.Exceptions.IDNotFoundException;
import com.ganesh.productservice.models.Category;
import com.ganesh.productservice.models.Product;
import com.ganesh.productservice.repositories.CategoryRepository;
import com.ganesh.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("selfCategoryService")
@Primary
public class SelfCategoryService implements CategoryService {
    CategoryRepository repository;
    ProductRepository productRepository;
    //IDNotFoundException exception;
    Converters converters;
    SelfCategoryService(CategoryRepository repository, ProductRepository productRepository, ModelMapperConverters converters){
        this.repository=repository;
        this.productRepository=productRepository;
        //this.exception=exception;
        this.converters=converters;
    }

    public Category createCategory(ValidateCategoryDTO dto){
        Category category=new Category();
        category.setName(dto.getName());
        repository.save(category);
        return category;
    }
    public List<Category> getAllCategories() {
        List<Category> list=repository.findAll();
//        List<CategoryResponseDTO> result=new ArrayList<>();
//        for(Category c:list)
//            result.add(toCategoryResponseDTO(c));
        return list;
    }

//    private CategoryResponseDTO toCategoryResponseDTO(Category c) {
//        CategoryResponseDTO dto= CategoryResponseDTO.builder().uuid(c.getUuid()).category(c.getName()).build();
//        return dto;
//    }


    public List<Product> getProductsByCategory(String uuid){
        UUID id=UUID.fromString(uuid);
        Optional<Category> categoryOptional=repository.findById(id);
        Category category=categoryOptional.orElse(null);
        if(category==null)
            return null;
        //System.out.println("self category service");
        //List<FindAllProductsDA0> list=productRepository.findAllByCategory(id);
        List<Product> list=productRepository.findAllByCategory(category);
//        List<ProductResponseDTO> list1=new ArrayList<>();
//        for(Product product:list)
//            list1.add(toProductResponseDTO(product));
//        return list1;
//        List<ProductResponseDTO> list1=new ArrayList<>();
//        for(FindAllProductsDA0 da0:list)
//            list1.add(converters.findAllProductDaoToProductResponseDTO(da0));
        return  list;
    }
}
