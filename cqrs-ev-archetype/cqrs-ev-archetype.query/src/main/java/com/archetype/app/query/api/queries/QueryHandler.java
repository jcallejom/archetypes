package com.archetype.app.query.api.queries;

import java.util.List;

import com.archetype.cqrsev.core.domain.BaseEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface QueryHandler.
 */
public interface QueryHandler {
	
	/**
	 * Handle.
	 *
	 * @param query the query
	 * @return the list
	 */
	List<BaseEntity> handle(FindPrototypeByIdQuery query);
	
	/**
	 * Handle.
	 *
	 * @param query the query
	 * @return the list
	 */
	List<BaseEntity> handle(FindPrototypeByPrototypeCodeQuery query);
}
