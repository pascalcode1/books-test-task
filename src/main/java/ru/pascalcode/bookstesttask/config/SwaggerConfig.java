package ru.pascalcode.bookstesttask.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.pascalcode.bookstesttask.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo metaInfo() {
        return new ApiInfo(
                "Books Test Task",
                "REST API",
                "1.0",
                "",
                new Contact("Vadim Timofeev", "pascalcode.ru", "pascalcode1@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licese.html",
                new ArrayList<>()
        );
    }

}
