package com.ganesh.productservice.configurations;

import com.ganesh.productservice.Exceptions.IDNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfigurations {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean("idNotFoundException")
    IDNotFoundException getIDNotFoundException(){
        return new IDNotFoundException("ID/IDs not found");
    }

    @Bean("modelMapper")
    ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
