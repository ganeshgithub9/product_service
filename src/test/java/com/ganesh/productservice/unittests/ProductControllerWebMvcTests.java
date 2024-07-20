package com.ganesh.productservice.unittests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ganesh.productservice.DTO.ProductResponseDTO;
import com.ganesh.productservice.DTO.ValidateProductDTO;
import com.ganesh.productservice.Exceptions.IDNotFoundException;
import com.ganesh.productservice.controllers.ProductController;
import com.ganesh.productservice.interfaces.Converters;
import com.ganesh.productservice.models.Product;
import com.ganesh.productservice.services.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
//@SpringBootTest
public class ProductControllerWebMvcTests {
    @Autowired
    MockMvc mockMvc;


    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    IDNotFoundException exception;

    @MockBean
    ProductService productService;

    @MockBean
    Converters converters;

    static ValidateProductDTO requestBody;
    static ProductResponseDTO productResponseDto1;

    static Product product;
    static String expectedIDNotFoundMessage;
    static String productId;
    static List<ProductResponseDTO> productResponseDTOList;

    static List<Product> productList;

    @BeforeAll
    static void beforeAll(){
        requestBody=new ValidateProductDTO();
        requestBody.setCategory("cookers");
        requestBody.setPrice(650.99);
        requestBody.setTitle("Prestige cooker 5L");
        requestBody.setRating(4.3);
        requestBody.setCurrency("rupees");
        requestBody.setDescription("Prestige brand cooker with capacity of 5L with 2 years of warranty");

        product=new Product();
        product.setName("Prestige cooker 5L");
        product.setDescription("Prestige brand cooker with capacity of 5L with 2 years of warranty");
        product.setRating(4.3);
        productList=new ArrayList<>();
        productList.add(product);

        Product product2=new Product();
        product2.setName("Adidas shoes");
        product2.setRating(3.5);
        product2.setDescription("Adidas branded shoes with pure leather");

        productList.add(product2);

        productResponseDto1 =new ProductResponseDTO();
        productResponseDto1.setId("1");
        productResponseDto1.setCategory("cookers");
        productResponseDto1.setPrice(650.99);
        productResponseDto1.setTitle("Prestige cooker 5L");
        productResponseDto1.setRating(4.3);
        productResponseDto1.setCurrency("rupees");
        productResponseDto1.setDescription("Prestige brand cooker with capacity of 5L with 2 years of warranty");

        expectedIDNotFoundMessage="ID/IDs not found";
        productId="abc";
        productResponseDTOList =new ArrayList<>();
        productResponseDTOList.add(productResponseDto1);
//        ProductResponseDTO productResponseDto2=new ProductResponseDTO();
//        productResponseDto2.setId("2");
//        productResponseDto2.setCategory("kid's shoes");
//        productResponseDto2.setPrice(789.9);
//        productResponseDto2.setTitle("Adidas shoes for Kids");
//        productResponseDto2.setRating(3.9);
//        productResponseDto2.setCurrency("rupees");
//        productResponseDto2.setDescription("Adidas branded shoes for kids with 100% pure leather");
//        productResponseDTOList.add(productResponseDto2);

        ProductResponseDTO productResponseDto2 =new ProductResponseDTO();
        productResponseDto2.setId("1");
        productResponseDto2.setCategory("cookers");
        productResponseDto2.setPrice(650.99);
        productResponseDto2.setTitle("Prestige cooker 5L");
        productResponseDto2.setRating(4.3);
        productResponseDto2.setCurrency("rupees");
        productResponseDto2.setDescription("Prestige brand cooker with capacity of 5L with 2 years of warranty");
        productResponseDTOList.add(productResponseDto2);
    }

    //tests for createProduct method
    @Test
    void givenInvalidRequestBody_whenCreateProduct_thenReturnsExceptionMessageThatDataIsMandatory() throws Exception {
        ValidateProductDTO invalidRequestBody=new ValidateProductDTO();
        invalidRequestBody.setCategory("cookers");
        invalidRequestBody.setPrice(650.99);
        invalidRequestBody.setTitle("Prestige cooker 5L");
        invalidRequestBody.setRating(4.3);
        invalidRequestBody.setCurrency("rupees");

        Map<String,String> response=new HashMap<>();
        response.put("description","Product description is mandatory");

        mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(invalidRequestBody)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(objectMapper.writeValueAsString(response)));
    }

    @Test
    void givenRequestBody_whenCreateProduct_thenReturnsExceptionMessageThatIDNotFound() throws Exception {
//        ValidateProductDTO requestBody=new ValidateProductDTO();
//        requestBody.setCategory("cookers");
//        requestBody.setPrice(650.99);
//        requestBody.setTitle("Prestige cooker 5L");
//        requestBody.setRating(4.3);
//        requestBody.setCurrency("rupees");
//        requestBody.setDescription("Prestige brand cooker with capacity of 5L with 2 years of warranty");

        //String expectedMsg="ID not found";

        when(productService.createProduct(any(ValidateProductDTO.class))).thenReturn(null);
        when(exception.getMessage()).thenReturn(expectedIDNotFoundMessage);

        mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(expectedIDNotFoundMessage));
    }

    @Test
    void givenRequestBody_whenCreateProduct_thenReturnsProductResponseDTO() throws Exception {
//        ValidateProductDTO requestBody=new ValidateProductDTO();
//        requestBody.setCategory("cookers");
//        requestBody.setPrice(650.99);
//        requestBody.setTitle("Prestige cooker 5L");
//        requestBody.setRating(4.3);
//        requestBody.setCurrency("rupees");
//        requestBody.setDescription("Prestige brand cooker with capacity of 5L with 2 years of warranty");

//        ProductResponseDTO responseBody=new ProductResponseDTO();
//        responseBody.setId("1");
//        responseBody.setCategory("cookers");
//        responseBody.setPrice(650.99);
//        responseBody.setTitle("Prestige cooker 5L");
//        responseBody.setRating(4.3);
//        responseBody.setCurrency("rupees");
//        responseBody.setDescription("Prestige brand cooker with capacity of 5L with 2 years of warranty");

        when(productService.createProduct(any(ValidateProductDTO.class))).thenReturn(product);
       // when(exception.getMessage()).thenReturn("ID not found");
        when(converters.productToProductResponseDTO(any())).thenReturn(productResponseDto1);

        mockMvc.perform(post("/products").content(objectMapper.writeValueAsString(requestBody)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(objectMapper.writeValueAsString(productResponseDto1)));
    }

    //tests for getProductById method

    @Test
    void givenProductID_whenGetProductByID_thenReturnsExceptionMessageThatIDNotFound() throws Exception {
        //String id="abc";
        //String expectedMsg="ID not found";

        when(productService.getProductById(anyString())).thenReturn(null);
        when(exception.getMessage()).thenReturn(expectedIDNotFoundMessage);

        mockMvc.perform(get("/products/{id}",productId))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(expectedIDNotFoundMessage));
    }

    @Test
    void givenProductID_whenGetProductByID_thenReturnsProductResponseDTO() throws Exception {
        //String id="abc";
        when(productService.getProductById(anyString())).thenReturn(product);
        when(converters.productToProductResponseDTO(any())).thenReturn(productResponseDto1);

        mockMvc.perform(get("/products/{id}",productId).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productResponseDto1)));
    }

    //tests for deleteProductById method

    @Test
    void givenProductIDThatDoesNotExist_whenDeleteProductByID_returnsExceptionMessageThatIDNotFound() throws Exception {
        //String id="abc";
        when(productService.deleteProductById(anyString())).thenReturn(null);
        when(exception.getMessage()).thenReturn(expectedIDNotFoundMessage);

        mockMvc.perform(delete("/products/{id}",productId).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(expectedIDNotFoundMessage));
    }

    @Test
    void givenProductID_whenDeleteProductByID_returnsProductResponseDTO() throws Exception {
        //String id="abc";
        when(productService.deleteProductById(anyString())).thenReturn(product);
        when(converters.productToProductResponseDTO(any())).thenReturn(productResponseDto1);

        mockMvc.perform(delete("/products/{id}",productId).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productResponseDto1)));
    }

    //tests for getAllProducts method

    @Test
    void givenNothing_whenGetAllProducts_returnsExceptionThatNoProductsFound() throws Exception {
        //String id="abc";
        when(productService.getProducts()).thenReturn(null);
        when(exception.getMessage()).thenReturn(expectedIDNotFoundMessage);

        mockMvc.perform(get("/products").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(expectedIDNotFoundMessage));
    }

    @Test
    void givenNothing_whenGetAllProducts_returnsListOfProductResponseDTOs() throws Exception {
        //String id="abc";
        when(productService.getProducts()).thenReturn(productList);
        //when(exception.getMessage()).thenReturn(expectedIDNotFoundMessage);
        when(converters.productToProductResponseDTO(any())).thenReturn(productResponseDto1);

        mockMvc.perform(get("/products").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productResponseDTOList)));
    }

    //tests for assignCategoryToProduct method

    @Test
    void givenCategoryAndProductIDs_whenAssignCategoryToProduct_returnsExceptionThatProductOrCategoryIDIsInvalid() throws Exception {
        String productId="123e4567-e89b-12d3-a456-426655440000",categoryId="123e4567-e89b-12d3-a456-426655440000";

        when(productService.assignCategoryToProduct(anyString(),anyString())).thenReturn(null);
        when(exception.getMessage()).thenReturn(expectedIDNotFoundMessage);

        mockMvc.perform(patch("/products/{product_id}/categories/{category_id}",productId,categoryId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(expectedIDNotFoundMessage));
    }

    @Test
    void givenCategoryAndProductIDs_whenAssignCategoryToProduct_returnsProductResponseDTO() throws Exception {
        String productId="123e4567-e89b-12d3-a456-426655440000",categoryId="123e4567-e89b-12d3-a456-426655440000";

        when(productService.assignCategoryToProduct(anyString(),anyString())).thenReturn(product);
        //when(exception.getMessage()).thenReturn(expectedIDNotFoundMessage);
        when(converters.productToProductResponseDTO(any(Product.class))).thenReturn(productResponseDto1);

        mockMvc.perform(patch("/products/{product_id}/categories/{category_id}",productId,categoryId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productResponseDto1)));
    }

}
