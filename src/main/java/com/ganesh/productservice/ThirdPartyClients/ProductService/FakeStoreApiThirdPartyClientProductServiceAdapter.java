package com.ganesh.productservice.ThirdPartyClients.ProductService;

import com.ganesh.productservice.interfaces.ModelMapperConverters;
import com.ganesh.productservice.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("fakeStoreApiThirdPartyClientProductServiceAdapter")
public class FakeStoreApiThirdPartyClientProductServiceAdapter implements ThirdPartyClientProductServiceAdapter{

    FakeStoreApiProductServiceClient fakeStoreApiProductServiceClient;
    ModelMapperConverters converters;
    @Autowired
    FakeStoreApiThirdPartyClientProductServiceAdapter(FakeStoreApiProductServiceClient fakeStoreApiProductServiceClient, ModelMapperConverters converters){
        this.fakeStoreApiProductServiceClient=fakeStoreApiProductServiceClient;
        this.converters=converters;
    }
    @Override
    public ResponseEntity<ApiProduct> createProduct(ValidateProductDTO productDTO) {
        ResponseEntity<FakeStoreApiProduct> entity=fakeStoreApiProductServiceClient.createProduct(productDTO);
        return fakeStoreApiProductEntityToApiProductEntity(entity);
    }

    @Override
    public ResponseEntity<ApiProduct> updateProduct(ValidateProductDTO productDTO, Long id) {
        ResponseEntity<FakeStoreApiProduct> entity=fakeStoreApiProductServiceClient.updateProduct(productDTO,id);
        return fakeStoreApiProductEntityToApiProductEntity(entity);
    }

    @Override
    public ResponseEntity<ApiProduct> updatePrice(PriceDTO priceDTO, Long id){
        ResponseEntity<FakeStoreApiProduct> entity=fakeStoreApiProductServiceClient.updatePrice(priceDTO,id);
        return fakeStoreApiProductEntityToApiProductEntity(entity);
    }

    @Override
    public ResponseEntity<ApiProduct> deleteProductById(Long id) {
        ResponseEntity<FakeStoreApiProduct> entity=fakeStoreApiProductServiceClient.deleteProductById(id);
        return fakeStoreApiProductEntityToApiProductEntity(entity);
    }

    @Override
    public ResponseEntity<List<ApiProduct>> getProducts() {
        ResponseEntity<List<FakeStoreApiProduct>> entity=fakeStoreApiProductServiceClient.getProducts();
        return fakeStoreApiProductListEntityToApiProductListEntity(entity);
    }

    @Override
    public ResponseEntity<ApiProduct> getProductById(Long id) {
        //System.out.println("Fakestore api third party client product service adapter");
        ResponseEntity<FakeStoreApiProduct> entity= fakeStoreApiProductServiceClient.getProductById(id);
        return fakeStoreApiProductEntityToApiProductEntity(entity);
    }

    @Override
    public ResponseEntity<List<ApiProduct>> getProductsByCategory(String category) {
        ResponseEntity<List<FakeStoreApiProduct>> entity= fakeStoreApiProductServiceClient.getProductsByCategory(category);
        return fakeStoreApiProductListEntityToApiProductListEntity(entity);
    }



    ResponseEntity<ApiProduct> fakeStoreApiProductEntityToApiProductEntity(ResponseEntity<FakeStoreApiProduct> entity){
        if(entity.getBody()==null)
            return new ResponseEntity<>(null,HttpStatus.OK);
        return new ResponseEntity<>(converters.fakeStoreApiProductToApiProduct(entity.getBody()),HttpStatus.OK);
    }

    ResponseEntity<List<ApiProduct>> fakeStoreApiProductListEntityToApiProductListEntity(ResponseEntity<List<FakeStoreApiProduct>> entity){
        if(entity.getBody()==null)
            return new ResponseEntity<>(null,HttpStatus.OK);
        List<ApiProduct> list=new ArrayList<>();
        for(FakeStoreApiProduct product:entity.getBody())
            list.add(converters.fakeStoreApiProductToApiProduct(product));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
