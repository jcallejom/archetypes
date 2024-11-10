package com.archetype.app.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.archetype.app.query.api.queries.FindPrototypeByIdQuery;
import com.archetype.app.query.api.queries.FindPrototypeByPrototypeCodeQuery;
import com.archetype.app.query.api.queries.QueryHandler;
import com.archetype.cqrsev.core.infrastructure.QueryDispatcher;

import jakarta.annotation.PostConstruct;

// TODO: Auto-generated Javadoc
/**
 * The Class QueryApplication.
 */
@SpringBootApplication
public class QueryApplication {
	
	/** The query dispacher. */
	@Autowired
	private QueryDispatcher queryDispacher;
	
	/** The query handler. */
	@Autowired
	private QueryHandler queryHandler;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(QueryApplication.class, args);
	}
	
	/**
	 * Register.
	 */
	@PostConstruct
	public void register() {
		queryDispacher.registerHandler(FindPrototypeByIdQuery.class, queryHandler::handle);
		queryDispacher.registerHandler(FindPrototypeByPrototypeCodeQuery.class, queryHandler::handle);
	}
}
