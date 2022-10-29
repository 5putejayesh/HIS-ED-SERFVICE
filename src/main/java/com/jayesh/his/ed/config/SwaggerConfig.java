package com.jayesh.his.ed.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private ApiInfo apiInfo() {
		return new ApiInfo("Dc module of HIS application",
				"DC Module to collect citizens data", "1", "Term of Service",
				new Contact("Jayesh", "", "5putejayesh@gmail.com"), "License-1.0", "License URL",
				Collections.emptyList());
	}
	
	@Bean
	public Docket apiDoc() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.jayesh.his.ed")).paths(PathSelectors.any()).build();
	}
	
	
}
