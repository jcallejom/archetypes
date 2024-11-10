package com.archetype.app.cmd.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
	 	@Value("${spring.application.name}")
	    private String applicationName;
	    public static final String BEARER_KEY_SECURITY_SCHEME = "bearer-key";	

	    @Bean
	    public OpenAPI customOpenAPI() {
	        return new OpenAPI()
	                .components(
	                        new Components().addSecuritySchemes(BEARER_KEY_SECURITY_SCHEME,
	                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
	                .addSecurityItem(new SecurityRequirement().addList(BEARER_KEY_SECURITY_SCHEME))
	                .info(new Info().title(applicationName).version("1.0.0"));
	    }

	    @Bean
	    public GroupedOpenApi customApi() {
	        return GroupedOpenApi.builder().group("api").pathsToMatch("/v1/prototype/**").build();
	    }

	    @Bean
	    public GroupedOpenApi actuatorApi() {
	        return GroupedOpenApi.builder().group("actuator").pathsToMatch("/actuator/**").build();
	    }	
	
}	
//	
//	@Bean
//	public OpenAPI customOpenAPI() {
//	     return new OpenAPI()
//	          .components(new Components())
//	          .info(new Info()
//	          .title("Command API")
//	          .version("1.0.0"));
//	}
	
//	@Bean
//	public OpenAPI apiInfo() {
//	
//	        return new OpenAPI()
//	                .info(new Info().title("Administraccion")
//	                .description("modelo de maec-da-api-admin")
//	                .version("v1.0.0")).components(new Components()
//	          	          .addSecuritySchemes("bearer-key", 
//	          	  	          new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))) 
//	                .addSecurityItem(new SecurityRequirement().addList("bearer-key"));
//	}
	
	

