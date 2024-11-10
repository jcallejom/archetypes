package com.archetype.app.query.infrastructure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.archetype.cqrsev.core.domain.BaseEntity;
import com.archetype.cqrsev.core.infrastructure.QueryDispatcher;
import com.archetype.cqrsev.core.queries.BaseQuery;
import com.archetype.cqrsev.core.queries.QueryHandlerMethod;

// TODO: Auto-generated Javadoc
/**
 * The Class PrototypeQueryDispacher.
 */
@Service
public class PrototypeQueryDispacher  implements QueryDispatcher{
	
	/** The routes. */
	private final Map <Class<? extends BaseQuery >, List<QueryHandlerMethod>> routes = new HashMap<>();
	
	
	/**
	 * Register handler.
	 *
	 * @param <T> the generic type
	 * @param type the type
	 * @param handler the handler
	 */
	public <T extends BaseQuery > void registerHandler(Class<T> type, QueryHandlerMethod<T> handler ) {
		var handlers= routes.computeIfAbsent(type, c -> new LinkedList<>());
		handlers.add(handler);
	}
	
	/**
	 * Send.
	 *
	 * @param <U> the generic type
	 * @param query the query
	 * @return the list
	 */
	public <U extends BaseEntity > List <U> send (BaseQuery query){
		var handlers= routes.get(query.getClass());
		if(handlers== null || handlers.size() == 0)
			throw new RuntimeException("The queryHandler is not registred");
		if(handlers.size()>1)
			throw new RuntimeException("Can´t send query by more one handler");
		return handlers.get(0).handle(query);
		
		
	}
}
