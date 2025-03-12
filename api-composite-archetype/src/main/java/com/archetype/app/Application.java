	package com.archetype.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication(scanBasePackages = {"com.archetype"} )
@OpenAPIDefinition(info = 
	@Info(title = "Prototype API", description = "A REST microservice with direct access to SQL databases using JPA")
)
@EnableMethodSecurity
//@EntityScan(basePackages = {"com.archetype.base.core.audit.model","com.archetype.app.infrastructure.out.db.jpa.entity"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
