package com.archetype.cqrsev.core.queries;

import java.util.List;

import com.archetype.cqrsev.core.domain.BaseEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface QueryHandlerMethod.
 *
 * @param <T> the generic type
 */
@FunctionalInterface
public interface QueryHandlerMethod <T extends BaseQuery> {
	
	/**
	 * Handle.
	 *
	 * @param query the query
	 * @return the list
	 */
	List<BaseEntity>handle(T query);
}
