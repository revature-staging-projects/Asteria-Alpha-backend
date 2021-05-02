package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@SpringBootApplication
public class AsteriaAlpha {
    public static void main(String[] args) {
        SpringApplication.run(AsteriaAlpha.class, args);
    }


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.revature.controllers"))
                // Ignore the root controller
                .apis(RequestHandlerSelectors.withClassAnnotation(ApiIgnore.class).negate())
                .paths(PathSelectors.any())
                .build();
    }

}