package com.ganesh.productservice.services;

import com.ganesh.productservice.interfaces.ModelMapperConverters;
import com.ganesh.productservice.DTO.*;
import com.ganesh.productservice.ThirdPartyClients.ProductService.ThirdPartyClientProductServiceAdapter;
import com.ganesh.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("thirdPartyApiProductService")
public class ThirdPartyApiProductService implements ProductService{
    ThirdPartyClientProductServiceAdapter thirdPartyClientProductServiceAdapter;
    ModelMapperConverters converters;
    @Autowired
    ThirdPartyApiProductService(@Qualifier("fakeStoreApiThirdPartyClientProductServiceAdapter") ThirdPartyClientProductServiceAdapter thirdPartyClientProductServiceAdapter
            , ModelMapperConverters converters){
        this.thirdPartyClientProductServiceAdapter=thirdPartyClientProductServiceAdapter;
        this.converters=converters;
    }
    @Override
    public Product getProductById(String id) throws NumberFormatException {
        //System.out.println("Third party api product service");
        //return thirdPartyClientProductServiceAdapter.getProductById(id);
        Long id1=Long.parseLong(id);
        ApiProduct product=thirdPartyClientProductServiceAdapter.getProductById(id1).getBody();
//        try {
//            if (productResponseEntity.getBody() == null)
//                throw new ProductNotFoundException("Product with id " + id + "not found");
//        }
//        catch (ProductNotFoundException exception){
//            System.out.println(exception.getMessage());
//        }
        //return converters.apiProductToProductResponseDTO(product);
        return converters.apiProductToProduct(product);
    }

    @Override
    public Product createProduct(ValidateProductDTO productDTO) {
        ApiProduct product= thirdPartyClientProductServiceAdapter.createProduct(productDTO).getBody();
        //return converters.apiProductToProductResponseDTO(product);
        return converters.apiProductToProduct(product);
    }

    @Override
    public Product updateProduct(ValidateProductDTO productDTO, String id) throws NumberFormatException{
        Long id1=Long.parseLong(id);
        ApiProduct product= thirdPartyClientProductServiceAdapter.updateProduct(productDTO,id1).getBody();
        //return converters.apiProductToProductResponseDTO(product);
        return converters.apiProductToProduct(product);
    }


    @Override
    public Product deleteProductById(String id) {
        Long id1=Long.parseLong(id);
        ApiProduct product= thirdPartyClientProductServiceAdapter.deleteProductById(id1).getBody();
        //return converters.apiProductToProductResponseDTO(product);
        return converters.apiProductToProduct(product);
    }

    @Override
    public List<Product> getProducts() {
        List<ApiProduct> products=thirdPartyClientProductServiceAdapter.getProducts().getBody();
        return apiProductListToProductList(products);
    }

//    @Override
//    public List<Product> getProductsByCategory(String category) {
//        List<ApiProduct> products= thirdPartyClientProductServiceAdapter.getProductsByCategory(category).getBody();
//        return apiProductListToProductList(products);
//    }

    @Override
    public Product assignCategoryToProduct(String product_id, String category_id) {
        return null;
    }

    List<Product> apiProductListToProductList(List<ApiProduct> products){
        if(products==null)
            return null;
        List<Product> list=new ArrayList<>();
        for(ApiProduct p:products)
            list.add(converters.apiProductToProduct(p));
        return list;
    }
}
