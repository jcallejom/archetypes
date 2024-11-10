package com.archetype.app.cmd.domain;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.archetype.cqrsev.core.events.EventModel;


// TODO: Auto-generated Javadoc
/**
 * The Interface EventStoreRepository.
 */
public interface EventStoreRepository extends MongoRepository<EventModel, String>{
	

	/**
	 * Find by aggregate identifier.
	 *
	 * @param aggregateIdentifier the aggregate identifier
	 * @return the list of EventModel
	 */
	List<EventModel> findByAggregateIdentifier(String aggregateIdentifier);

	
	List<EventModel> findByEventType(String eventType);
}
