package com.archetype.cqrsev.core.infrastructure;

import java.util.List;

import com.archetype.cqrsev.core.events.BaseEvent;

// TODO: Auto-generated Javadoc
/**
 * The Interface EventStore.
 */
public interface EventStore {
	
	/**
	 * Save events.
	 *
	 * @param aggregateId the aggregate id
	 * @param events the events
	 * @param expectedVersion the expected version
	 */
	void  saveEvents( String aggregateId,Iterable<BaseEvent> events, int expectedVersion);
	
	/**
	 * Gets the event.
	 *
	 * @param aggregateId the aggregate id
	 * @return the event
	 */
	List<BaseEvent> getEvent(String aggregateId);
}
