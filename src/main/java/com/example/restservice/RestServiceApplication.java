package com.example.restservice;

import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;


@SpringBootApplication
@EnableSwagger2
public class RestServiceApplication {
    
    public static void main(String[] args) throws SQLException { 
               
        SpringApplication.run(RestServiceApplication.class, args);
    }
    
    @Bean
   public Docket productApi() {
      return new Docket(DocumentationType.SWAGGER_2).select()
         .apis(RequestHandlerSelectors.basePackage("com.example.restservice")).build();
   }
}
