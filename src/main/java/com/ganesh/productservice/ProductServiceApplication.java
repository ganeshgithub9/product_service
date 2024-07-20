package com.ganesh.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.core.SpringVersion;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        //System.out.println(SpringVersion.getVersion());
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
