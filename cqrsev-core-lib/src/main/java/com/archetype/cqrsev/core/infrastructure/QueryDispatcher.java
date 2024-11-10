package com.archetype.cqrsev.core.infrastructure;

import java.util.List;

import com.archetype.cqrsev.core.domain.BaseEntity;
import com.archetype.cqrsev.core.queries.BaseQuery;
import com.archetype.cqrsev.core.queries.QueryHandlerMethod;

// TODO: Auto-generated Javadoc
/**
 * The Interface QueryDispatcher.
 */
public interface QueryDispatcher {
	
	/**
	 * Register handler.
	 *
	 * @param <T> the generic type
	 * @param type the type
	 * @param handler the handler
	 */
	<T extends BaseQuery > void registerHandler(Class<T> type, QueryHandlerMethod<T> handler );
	
	/**
	 * Send.
	 *
	 * @param <U> the generic type
	 * @param query the query
	 * @return the list
	 */
	<U extends BaseEntity > List <U> send (BaseQuery query);

}
