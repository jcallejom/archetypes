package com.archetype.cqrsev.core.events;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class EventModel.
 */
@Data

/**
 * The Class EventModelBuilder.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "eventstore")
public class EventModel {

	/** The id. */
	@Id
	private String id;
	/** The time stamp. */
	private LocalDateTime timeStamp;
	
	/** The aggregate identifier. */
	private String aggregateIdentifier;
	
	/** The aggregate type. */
	private String aggregateType;
	
	/** The version. */
	private int version;
	
	/** The event type. */
	private String eventType;
	
	/** The event data. */
	private BaseEvent eventData;
}
