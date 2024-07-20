package com.ganesh.productservice.ThirdPartyClients.ProductService;

import com.ganesh.productservice.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component("fakeStoreApiProductServiceClient")
public class FakeStoreApiProductServiceClient{
    RestTemplate restTemplate;
    String productUrl="https://fakestoreapi.com/products",productUrlWithId="https://fakestoreapi.com/products/{id}";
    @Autowired
    FakeStoreApiProductServiceClient(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    public ResponseEntity<FakeStoreApiProduct> getProductById(Long id){
        //System.out.println(" fakestore api product service client getProductById method");
        ResponseEntity<FakeStoreApiProduct> product=restTemplate.getForEntity(productUrlWithId, FakeStoreApiProduct.class,id);
        //ResponseEntity<Product> product=restTemplate.getForEntity("https://fakestoreapi.com/products/"+id,Product.class);
        return product;
    }

    public ResponseEntity<FakeStoreApiProduct> createProduct(ValidateProductDTO productDTO){
        ResponseEntity<FakeStoreApiProduct> product=restTemplate.postForEntity(productUrl,productDTO,FakeStoreApiProduct.class);
        return product;
    }

    public ResponseEntity<FakeStoreApiProduct> updateProduct(ValidateProductDTO productDTO,Long id){
        restTemplate.put(productUrlWithId,productDTO,id);
        //return getProductById(id);
        //System.out.println("product updated");
//        p.setId(Long.toString(id));
//        p.setTitle(productDTO.getTitle());
//        p.setDescription(productDTO.getDescription());
//        p.setImage(productDTO.getImage());
//        p.setCategory(productDTO.getCategory());
//        p.setPrice(productDTO.getPrice());
//        p.setRating(productDTO.getRating().getRate());

        FakeStoreApiProduct p=new FakeStoreApiProduct();
        p.setId(id);p.setTitle(productDTO.getTitle());p.setDescription(productDTO.getDescription());p.setImage(productDTO.getImage());
        p.setCategory(productDTO.getCategory());p.setPrice(productDTO.getPrice());
        return new ResponseEntity<>(p,HttpStatus.OK);
    }

    public ResponseEntity<FakeStoreApiProduct> updatePrice(PriceDTO priceDTO, Long id) {
        ResponseEntity<FakeStoreApiProduct> productResponseEntity=getProductById(id);
        FakeStoreApiProduct product=productResponseEntity.getBody();
//        if(product==null)
//            throw new ProductNotFoundException("Product with id "+id+" not found");
        if(product==null)
            return new ResponseEntity<>(null,HttpStatus.OK);;
        product.setPrice(priceDTO.getPrice());
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    public ResponseEntity<FakeStoreApiProduct> deleteProductById(Long id){
        ResponseEntity<FakeStoreApiProduct> productResponseEntity=getProductById(id);
        restTemplate.delete(productUrlWithId,id);
        return productResponseEntity;
    }


    public  ResponseEntity<List<FakeStoreApiProduct>> getProducts() {
        System.out.println("Executing service getProducts method");
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity=new HttpEntity<>("parameters",headers);
        //ResponseEntity<ProductList> listResponseEntity=restTemplate.exchange(getProductsUrl, HttpMethod.GET,entity, ProductList.class);
        ResponseEntity<FakeStoreApiProduct[]> listResponseEntity=restTemplate.exchange(productUrl, HttpMethod.GET,entity, FakeStoreApiProduct[].class);
        if(listResponseEntity.getBody()==null)
            return new ResponseEntity<>(null,HttpStatus.OK);
        List<FakeStoreApiProduct> list=new ArrayList<>();
        Collections.addAll(list, listResponseEntity.getBody());
        return new ResponseEntity<>(list,HttpStatus.OK);
    }


    public ResponseEntity<List<FakeStoreApiProduct>> getProductsByCategory(String category){
        ResponseEntity<FakeStoreApiProduct[]> responseEntity=restTemplate.getForEntity(productUrl+"/category/"+category,FakeStoreApiProduct[].class);
        if(responseEntity.getBody()==null)
            return new ResponseEntity<>(null,HttpStatus.OK);;
        List<FakeStoreApiProduct> list=new ArrayList<>();
        Collections.addAll(list,responseEntity.getBody());
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
