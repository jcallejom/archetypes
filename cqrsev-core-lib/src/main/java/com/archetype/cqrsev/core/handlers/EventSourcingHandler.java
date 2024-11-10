package com.archetype.cqrsev.core.handlers;

import com.archetype.cqrsev.core.domain.AggregateRoot;

// TODO: Auto-generated Javadoc
/**
 * The Interface EventSourcingHandler.
 *
 * @param <T> the generic type
 */
public interface EventSourcingHandler <T>{
	
	/**
	 * Save.
	 *
	 * @param aggregate the aggregate
	 */
	public void save(AggregateRoot aggregate);
	
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	public T getById(String id);
}
