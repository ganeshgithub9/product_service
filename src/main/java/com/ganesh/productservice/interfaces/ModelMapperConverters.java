package com.ganesh.productservice.interfaces;

import com.ganesh.productservice.DTO.*;
import com.ganesh.productservice.Exceptions.IDNotFoundException;
import com.ganesh.productservice.models.Category;
import com.ganesh.productservice.models.Price;
import com.ganesh.productservice.models.Product;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConverters implements Converters{
    ModelMapper mapper;
    IDNotFoundException exception;
    ModelMapperConverters(ModelMapper mapperr, IDNotFoundException exception){
        this.mapper=mapperr;
        this.exception=exception;
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.getConfiguration().setSkipNullEnabled(true);
        Condition<Object,Object> isNotNull=context -> context.getSource()!=null;
        TypeMap<Product, ProductResponseDTO> productProductResponseDTOTypeMap = mapper.createTypeMap(Product.class, ProductResponseDTO.class);
        productProductResponseDTOTypeMap.addMappings(mapper -> {
            mapper.map(Product::getName, ProductResponseDTO::setTitle);
            mapper.map(Product::getUuid,ProductResponseDTO::setId);
            mapper.when(isNotNull).map(product->product.getPrice().getCurrency(),ProductResponseDTO::setCurrency);
            mapper.when(isNotNull).map(product->product.getCategory().getName() ,ProductResponseDTO::setCategory);
            mapper.when(isNotNull).map(product->product.getPrice().getPrice() ,ProductResponseDTO::setPrice);
        });

//        TypeMap<ApiProduct,ProductResponseDTO> apiProductProductResponseDTOTypeMap = this.mapper.createTypeMap(ApiProduct.class, ProductResponseDTO.class);
//        apiProductProductResponseDTOTypeMap.addMappings(mapping -> {
//            mapping.skip(ProductResponseDTO::setCurrency);
//            mapping.skip(ProductResponseDTO::setRating);
//        });

//        TypeMap<FindAllProductsDA0,ProductResponseDTO> findAllProductsDA0ProductResponseDTOTypeMap=mapper.createTypeMap(FindAllProductsDA0.class,ProductResponseDTO.class);
//        findAllProductsDA0ProductResponseDTOTypeMap.addMappings(mapper -> {
//            mapper.map(FindAllProductsDA0::getUuid, ProductResponseDTO::setId);
//            mapper.map(FindAllProductsDA0::getName,ProductResponseDTO::setTitle);
//        });

        TypeMap<ApiProduct,Product> apiProductProductTypeMap=mapper.createTypeMap(ApiProduct.class,Product.class);
        apiProductProductTypeMap.addMappings(mapping -> {
            mapping.map(ApiProduct::getTitle,Product::setName);
            //mapping.map(ApiProduct::getCategory,dst-> dst.getCategory().setName());
        });

//        TypeMap<ValidateProductDTO,Price> validateProductDTOPriceTypeMap= mapper.createTypeMap(ValidateProductDTO.class, Price.class);
//        validateProductDTOPriceTypeMap.addMappings(mapping -> {
//            mapping.map(ValidateProductDTO::getPrice,Price::setPrice);
//            mapping.map(ValidateProductDTO::getCurrency,Price::setCurrency);
//            mapping.skip(Price::setProduct);
//        });

        TypeMap<ValidateProductDTO,Product> validateProductDTOProductTypeMap=mapper.createTypeMap(ValidateProductDTO.class, Product.class);
        validateProductDTOProductTypeMap.addMappings(mapping -> {
            mapping.map(ValidateProductDTO::getTitle,Product::setName);
        });

        TypeMap<Category,CategoryResponseDTO> categoryCategoryResponseDTOTypeMap=mapper.createTypeMap(Category.class, CategoryResponseDTO.class);
        categoryCategoryResponseDTOTypeMap.addMappings(mapping -> {
           mapping.map(Category::getName,CategoryResponseDTO::setCategory);
        });

    }

    public ProductResponseDTO findAllProductDaoToProductResponseDTO(FindAllProductsDA0 da0){
//        TypeMap<FindAllProductsDA0,ProductResponseDTO> typeMap=mapper.createTypeMap(FindAllProductsDA0.class,ProductResponseDTO.class);
//        typeMap.addMappings(mapper -> {
//            mapper.map(FindAllProductsDA0::getUuid, ProductResponseDTO::setId);
//            mapper.map(FindAllProductsDA0::getName,ProductResponseDTO::setTitle);
//        });
        if(da0==null)
            return null;
        return mapper.map(da0,ProductResponseDTO.class);
    }

    public ProductResponseDTO productToProductResponseDTO(Product product) {
        if(product==null)
            return null;
//        ProductResponseDTO dto= ProductResponseDTO.builder().id(product.getUuid().toString()).title(product.getName())
//                .description(product.getDescription()).rating(product.getRating()).price(product.getPrice().getPrice())
//                .currency(product.getPrice().getCurrency()).category(product.getCategory()!=null?product.getCategory().getName():null).build();
//        return dto;
//        TypeMap<Product, ProductResponseDTO> typeMap = mapper.createTypeMap(Product.class, ProductResponseDTO.class);
//        typeMap.addMappings(mapper -> {
//            mapper.map(Product::getName, ProductResponseDTO::setTitle);
//            mapper.skip(ProductResponseDTO::setCategory);
//            mapper.skip(ProductResponseDTO::setPrice);
//        });
        return mapper.map(product,ProductResponseDTO.class);
    }

    public ProductResponseDTO apiProductToProductResponseDTO(ApiProduct product) {
        if(product==null)
            return null;
//        TypeMap<ApiProduct,ProductResponseDTO> propertyMapper = this.mapper.createTypeMap(ApiProduct.class, ProductResponseDTO.class);
//        propertyMapper.addMappings(new PropertyMap<ApiProduct,ProductResponseDTO>() {
//            @Override
//            protected void configure() {
//                skip().setCurrency(null);
//                skip().setRating(null);
//            }
//        });
        return mapper.map(product,ProductResponseDTO.class);
    }

    public ApiProduct fakeStoreApiProductToApiProduct(FakeStoreApiProduct product){
        if(product==null)
            return null;
//        ApiProduct product1=new ApiProduct();
//        product1.setCategory(product.getCategory());product1.setId(product.getId());product1.setTitle(product.getTitle());
//        product1.setPrice(product.getPrice());product1.setDescription(product.getDescription());product1.setImage(product.getImage());
        return mapper.map(product,ApiProduct.class);
    }

    @Override
    public Product apiProductToProduct(ApiProduct product) {
        if(product==null)
            return null;
        return mapper.map(product,Product.class);
    }

    @Override
    public CategoryResponseDTO categoryToCategoryResponseDTO(Category c) {
        if(c==null)
            return null;
        return mapper.map(c, CategoryResponseDTO.class);
    }

    @Override
    public Price validateProductDTOToPrice(ValidateProductDTO dto) {
        if(dto==null)
            return null;
        return mapper.map(dto, Price.class);
    }

    @Override
    public Product validateProductDTOToProduct(ValidateProductDTO dto) {
        if(dto==null)
            return null;
        return mapper.map(dto, Product.class);
    }


}
