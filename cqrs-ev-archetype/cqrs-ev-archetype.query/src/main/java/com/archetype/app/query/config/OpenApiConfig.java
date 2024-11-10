package com.archetype.app.query.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

// TODO: Auto-generated Javadoc
/**
 * The Class OpenApiConfig.
 */
@Configuration
public class OpenApiConfig {
    
    /** The application name. */
    @Value("${spring.application.name}")
    private String applicationName;
    
    /** The Constant BEARER_KEY_SECURITY_SCHEME. */
    public static final String BEARER_KEY_SECURITY_SCHEME = "bearer-key";	

    /**
     * Custom open API.
     *
     * @return the open API
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(
                        new Components().addSecuritySchemes(BEARER_KEY_SECURITY_SCHEME,
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList(BEARER_KEY_SECURITY_SCHEME))
                .info(new Info().title(applicationName).version("1.0.0"));
    }

    /**
     * Custom api.
     *
     * @return the grouped open api
     */
    @Bean
    public GroupedOpenApi customApi() {
        return GroupedOpenApi.builder().group("api").pathsToMatch("/v1/prototypeLookup/**").build();
    }

    /**
     * Actuator api.
     *
     * @return the grouped open api
     */
    @Bean
    public GroupedOpenApi actuatorApi() {
        return GroupedOpenApi.builder().group("actuator").pathsToMatch("/actuator/**").build();
    }
}
