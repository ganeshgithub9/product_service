package com.ganesh.productservice.services;


import com.ganesh.productservice.interfaces.Converters;
import com.ganesh.productservice.interfaces.ModelMapperConverters;
import com.ganesh.productservice.DTO.*;
import com.ganesh.productservice.Exceptions.IDNotFoundException;
import com.ganesh.productservice.models.Category;
import com.ganesh.productservice.models.Price;
import com.ganesh.productservice.models.Product;
import com.ganesh.productservice.repositories.CategoryRepository;
import com.ganesh.productservice.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("selfProductService")
@Primary
public class SelfProductService implements ProductService{
    ProductRepository repository;
    CategoryRepository categoryRepository;
    Converters converters;
    SelfProductService(ProductRepository repository, CategoryRepository categoryRepository, ModelMapper mapper, ModelMapperConverters converters){
        this.repository=repository;
        this.categoryRepository=categoryRepository;
        this.converters=converters;
    }



    public Product getProductById(String id){
        UUID uuid=UUID.fromString(id);
        Optional<Product> productOptional=repository.findById(uuid);
        Product product=productOptional.orElse(null);
        return product;
        //return converters.productToProductResponseDTO(product);
    }

    public Product createProduct(ValidateProductDTO productDTO){
//        Price price=new Price();
//        price.setCurrency(productDTO.getCurrency());
//        price.setPrice(productDTO.getPrice());
        Price price=converters.validateProductDTOToPrice(productDTO);
//        Product product=new Product();
//        product.setName(productDTO.getTitle());
//        product.setDescription(productDTO.getDescription());
//        product.setRating(productDTO.getRating());
        Product product=converters.validateProductDTOToProduct(productDTO);
        product.setPrice(price);
        price.setProduct(product);
        product=repository.save(product);
        //ProductResponseDTO dto=converters.productToProductResponseDTO(product);
        return product;
    }

    public Product assignCategoryToProduct(String product_id, String category_id) {
        UUID productId=UUID.fromString(product_id),categoryId=UUID.fromString(category_id);
        Optional<Category> category=categoryRepository.findById(categoryId);
        Optional<Product> product=repository.findById(productId);
        //System.out.println("assignProductToCategory method start");
        Category category1= category.orElse(null);
        Product product1=product.orElse(null);
        if(product1==null||category1==null)
            return null;
        product1.setCategory(category1);
        //category1.getProducts().add(product1);
        //categoryRepository.save(category1);
        product1=repository.save(product1);
        //return converters.productToProductResponseDTO(product1);
        return product1;
    }

    public Product updateProduct(ValidateProductDTO productDTO, String id){
        UUID uuid=UUID.fromString(id);
        Optional<Product> productOptional=repository.findById(uuid);
        Product product=productOptional.orElse(null);
        //return converters.productToProductResponseDTO(product);
        return product;
    }


    public Product deleteProductById(String id) {
        UUID uuid=UUID.fromString(id);
        Optional<Product> productOptional=repository.findById(uuid);
        Product product=productOptional.orElse(null);
        //return converters.productToProductResponseDTO(product);
        return product;
    }

    public List<Product> getProducts(){
        //List<Product> products=repository.findAll();
        List<Product> products=repository.findAllProducts();
//        List<ProductResponseDTO> responseDTOS=new ArrayList<>();
//        for(FindAllProductsDA0 p:products) {
//            responseDTOS.add(converters.findAllProductDaoToProductResponseDTO(p));
//        }
        //return responseDTOS;
        return products;
    }

//    @Override
//    public List<Product> getProductsByCategory(String category) {
//        return null;
//    }

}
