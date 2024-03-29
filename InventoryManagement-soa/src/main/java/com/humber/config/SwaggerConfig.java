package com.humber.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource("classpath:application.properties")
public class SwaggerConfig {

	@Value("${SWAGGER_TOKEN_URL}")
	private String tokenURL;

	@Value("${API_VERSION}")
	private String apiVersion;

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.humber"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Assignment 2 APIs", "API for Assignment 2", apiVersion, "1", null, "", "",
				Collections.emptyList());
	}

}
