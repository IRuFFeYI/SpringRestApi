package com.spring.restApi.SpringRestApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class SpringRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApiApplication.class, args);
	}

	@Bean
	public Docket swaggerApi(){
		return new Docket(DocumentationType.SWAGGER_2).groupName("restApi").apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.spring.restApi.SpringRestApi")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Spring Rest Api")
				.description("REST Service")
				.build();
	}
}
