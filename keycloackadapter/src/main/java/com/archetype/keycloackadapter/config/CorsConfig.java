package com.archetype.keycloackadapter.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//montado sin spring security
@Configuration
public class CorsConfig {
//public class CorsConfig implements WebMvcConfigurer{    
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/greeting-javaconfig")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders(
                        "Authorization",
                        "Accept",
                        "X-Requested-With",
                        "Content-Type",
                        "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders(
                        "Access-Control-Allow-Origin",
                        "Access-Control-Allow-Credentials")
                .maxAge(3600L)
                .allowCredentials(false)
                ;
            }
        };
    }
	
//    @Bean
//    public CorsFilter corsFilter() {
//
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(false);
//        config.setAllowedOrigins(List.of("*"));
//        config.setAllowedMethods(List.of("POST", "PUT", "PATCH", "GET", "OPTIONS", "DELETE"));
//
//        config.setAllowedHeaders(List.of(
//                "Authorization",
//                "Accept",
//                "X-Requested-With",
//                "Content-Type",
//                "Access-Control-Request-Method",
//                "Access-Control-Request-Headers"));
//
//        config.setExposedHeaders(List.of(
//                "Access-Control-Allow-Origin",
//                "Access-Control-Allow-Credentials"));
//
//        config.setMaxAge(3600L);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//
//        return new CorsFilter(source);
//    }
    /*otra forma*/
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration cc = new CorsConfiguration();
//       
//        cc.setAllowedHeaders(Arrays.asList("Origin,Accept", "X-Requested-With", "Content-Type", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Authorization"));
//        cc.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
//       
//        cc.setAllowedOrigins(Arrays.asList("/*"));
//        
//        cc.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "PATCH"));
//        
//        cc.addAllowedOriginPattern("*");       
//
//        
//        cc.setMaxAge(Duration.ZERO);
//        cc.setAllowCredentials(Boolean.TRUE);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", cc);
//        return source;
//    }
//    
}
