package com.up.platform.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerConfig {

    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("platform接口文档")
                        .description("描述内容")
//                        .contact("who")
                        .version("2.0.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.up.platform.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
