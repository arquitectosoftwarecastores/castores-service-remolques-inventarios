package com.grupocastores.inventarios.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	
	@Value("${globales.version}")
	private String version;

    @Bean
    public Docket infoApiAvailabilityHub() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("inventarios")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/inventarios.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("inventarios")
                .description("Documentation and client for inventarios")
                .termsOfServiceUrl("Castores-TI")
                .license("com.grupocastores")
                .version(version)
                .build();
    }
}
