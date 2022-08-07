package com.humber.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
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
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().securitySchemes(Collections.singletonList(securitySchema()))
				.securityContexts(java.util.Arrays.asList(securityContext()));
	}

	private SecurityContext securityContext() {

		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
	}

	private List<SecurityReference> defaultAuth() {
		final AuthorizationScope[] authorizationScopes = new AuthorizationScope[0];
		return java.util.Arrays.asList(new SecurityReference("oauth2", authorizationScopes));
	}

	private OAuth securitySchema() {
		List<AuthorizationScope> scopeList = new ArrayList<AuthorizationScope>();
		ResourceOwnerPasswordCredentialsGrant grant = new ResourceOwnerPasswordCredentialsGrant(tokenURL);
		List<GrantType> grantList = Collections.singletonList(grant);

		return new OAuth("oauth2", scopeList, grantList);
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Assignment 2 APIs", "API for Assignment 2", apiVersion, "1", null, "", "",
				Collections.emptyList());
	}

}
