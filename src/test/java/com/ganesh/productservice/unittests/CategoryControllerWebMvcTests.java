package com.ganesh.productservice.unittests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ganesh.productservice.DTO.CategoryResponseDTO;
import com.ganesh.productservice.DTO.ProductResponseDTO;
import com.ganesh.productservice.DTO.ValidateCategoryDTO;
import com.ganesh.productservice.Exceptions.IDNotFoundException;
import com.ganesh.productservice.controllers.CategoryController;
import com.ganesh.productservice.interfaces.Converters;
import com.ganesh.productservice.models.Category;
import com.ganesh.productservice.models.Product;
import com.ganesh.productservice.services.CategoryService;
import com.ganesh.productservice.services.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
public class CategoryControllerWebMvcTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CategoryService service;

    @MockBean
    IDNotFoundException exception;

    @MockBean
    Converters converters;

    static String expectedIDNotFoundMessage;

    static ValidateCategoryDTO categoryRequestDTO;
    static Category category1;

    static CategoryResponseDTO categoryResponseDTO1;

    static List<CategoryResponseDTO> categoryResponseDTOList;

    static List<Category> categoryList;

    static ProductResponseDTO productResponseDto1;

    static List<ProductResponseDTO> productResponseDTOList;

    static List<Product> productList;

    static Map<String,String> messages;

    @BeforeAll
    static void beforeAll(){
        expectedIDNotFoundMessage="ID/IDs not found";

        categoryRequestDTO=new ValidateCategoryDTO();
        categoryRequestDTO.setName("trimmers");

        category1=new Category();

        categoryResponseDTO1 =new CategoryResponseDTO();
        categoryResponseDTO1.setCategory("trimmers");
        categoryResponseDTO1.setUuid(UUID.fromString("123e4567-e89b-12d3-a456-426655440000"));
        categoryResponseDTOList=new ArrayList<>();
        categoryResponseDTOList.add(categoryResponseDTO1);

        CategoryResponseDTO categoryResponseDTO2=new CategoryResponseDTO();
        categoryResponseDTO2.setCategory("trimmers");
        categoryResponseDTO2.setUuid(UUID.fromString("123e4567-e89b-12d3-a456-426655440000"));
        categoryResponseDTOList.add(categoryResponseDTO2);

        categoryList=new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(new Category());

        messages=new HashMap<>();
        messages.put("name","Category name is mandatory");

        Product product1=new Product();
        product1.setName("Prestige cooker 5L");
        product1.setDescription("Prestige brand cooker with capacity of 5L with 2 years of warranty");
        product1.setRating(4.3);
        productList=new ArrayList<>();
        productList.add(product1);

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

        ProductResponseDTO productResponseDto2 =new ProductResponseDTO();
        productResponseDto2.setId("1");
        productResponseDto2.setCategory("cookers");
        productResponseDto2.setPrice(650.99);
        productResponseDto2.setTitle("Prestige cooker 5L");
        productResponseDto2.setRating(4.3);
        productResponseDto2.setCurrency("rupees");
        productResponseDto2.setDescription("Prestige brand cooker with capacity of 5L with 2 years of warranty");
        productResponseDTOList=new ArrayList<>();
        productResponseDTOList.add(productResponseDto1);
        productResponseDTOList.add(productResponseDto2);
    }

    //test cases for createCategory method

    @Test
    void givenInvalidRequestBody_whenCreateCategory_thenReturnsExceptionMessageThatDataIsMandatory() throws Exception {
        ValidateCategoryDTO invalidRequestBody=new ValidateCategoryDTO();
        mockMvc.perform(post("/categories").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(invalidRequestBody)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(objectMapper.writeValueAsString(messages)));
    }

    @Test
    void givenRequestBody_whenCreateCategory_returnsExceptionThatIDNotFound() throws Exception {

        when(service.createCategory(any(ValidateCategoryDTO.class))).thenReturn(null);
        when(exception.getMessage()).thenReturn(expectedIDNotFoundMessage);

        mockMvc.perform(post("/categories").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryRequestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(expectedIDNotFoundMessage));
    }

    @Test
    void givenRequestBody_whenCreateCategory_returnsCategoryResponseDTO() throws Exception {

        when(service.createCategory(any(ValidateCategoryDTO.class))).thenReturn(category1);
        //when(exception.getMessage()).thenReturn(expectedIDNotFoundMessage);
        when(converters.categoryToCategoryResponseDTO(any(Category.class))).thenReturn(categoryResponseDTO1);
        mockMvc.perform(post("/categories").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().string(objectMapper.writeValueAsString(categoryResponseDTO1)));
    }

    //test cases for getAllCategories method

    @Test
    void givenNothing_whenGetAllCategories_returnsExceptionThatIDNotFound() throws Exception {
        when(service.getAllCategories()).thenReturn(null);
        when(exception.getMessage()).thenReturn(expectedIDNotFoundMessage);
        mockMvc.perform(get("/categories").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(expectedIDNotFoundMessage));
    }

    @Test
    void givenNothing_whenGetAllCategories_returnsCategoryResponseDTOList() throws Exception {
        when(service.getAllCategories()).thenReturn(categoryList);
        when(converters.categoryToCategoryResponseDTO(any(Category.class))).thenReturn(categoryResponseDTO1);

        mockMvc.perform(get("/categories").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(categoryResponseDTOList)));
    }

    //tests for getProductsByCategory method

   @Test
    void givenCategoryId_whenGetProductsByCategory_returnsExceptionThatIDNotFound() throws Exception {
        when(service.getProductsByCategory(anyString())).thenReturn(null);
        when(exception.getMessage()).thenReturn(expectedIDNotFoundMessage);
        String id="123e4567-e89b-12d3-a456-426655440000";


        mockMvc.perform(get("/categories/{category_id}/products",id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(expectedIDNotFoundMessage));
    }

    @Test
    void givenCategoryId_whenGetProductsByCategory_returnsProductResponseDTOList() throws Exception {
        when(service.getProductsByCategory(anyString())).thenReturn(productList);
        //when(exception.getMessage()).thenReturn(expectedIDNotFoundMessage);
        when(converters.productToProductResponseDTO(any(Product.class))).thenReturn(productResponseDto1);
        String id="123e4567-e89b-12d3-a456-426655440000";

        mockMvc.perform(get("/categories/{category_id}/products",id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productResponseDTOList)));
    }

}
