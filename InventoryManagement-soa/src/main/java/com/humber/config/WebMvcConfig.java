package com.humber.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@Import(SwaggerConfig.class)
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/",
			"classpath:/resources/", "classpath:/static/", "classpath:/public/" };

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

		if (!registry.hasMappingForPattern("/**")) {
			registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
		}

	}

	@Override
	public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
		// Turn off suffix-based content negotiation
		configurer.favorPathExtension(false);
	}

}
