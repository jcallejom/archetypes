package com.archetype.app.cmd.infrastruture;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archetype.app.cmd.domain.EventStoreRepository;
import com.archetype.app.cmd.domain.PrototypeAggregate;
import com.archetype.app.common.events.PrototypeOpenedEvent;
import com.archetype.cqrsev.core.events.BaseEvent;
import com.archetype.cqrsev.core.events.EventModel;
import com.archetype.cqrsev.core.exceptions.AggregateNotFoundException;
import com.archetype.cqrsev.core.exceptions.ConcurrencyException;
import com.archetype.cqrsev.core.infrastructure.EventStore;
import com.archetype.cqrsev.core.produces.EventProducer;

/**
 * The Class PrototypeEventStore.
 */
//@Qualifier("PrototypeEventStore")
@Service
public class PrototypeEventStore implements EventStore{
	
	/** The event store repository. */
	@Autowired
	private EventStoreRepository eventStoreRepository;

	/** The event producer. */
	@Autowired
	private EventProducer eventProducer;
	
	
	/**
	 * Save events.
	 *
	 * @param aggregateId the aggregate id
	 * @param events the events
	 * @param expectedVersion the expected version
	 */
	@Override
	public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
		//buscar la cadena de  eventos ya existentes
		var eventStream =eventStoreRepository.findByAggregateIdentifier(aggregateId);
		
		if(expectedVersion!=-1 && eventStream.get(eventStream.size()-1).getVersion()!=expectedVersion)
			throw new ConcurrencyException();
	
		var version =expectedVersion;
		for(var event: events) {
		
			if(event instanceof PrototypeOpenedEvent) {
				var allEventStream =eventStoreRepository.findByEventType(event.getClass().getTypeName());		
				var result= allEventStream.stream()
				.filter( evt -> ((PrototypeOpenedEvent)  evt.getEventData()).getPrototypeCode().equals(((PrototypeOpenedEvent) event).getPrototypeCode()))
				.collect(Collectors.toList());
				if( result.size() != 0)
					throw new IllegalStateException("Ya exite un evento con el prototype Code: "+((PrototypeOpenedEvent) event).getPrototypeCode());

			}
			version++;
			event.setVersion(version);
			var eventModel=EventModel.builder()
					.timeStamp(LocalDateTime.now())
					.aggregateIdentifier(aggregateId)
					.aggregateType(PrototypeAggregate.class.getTypeName())
					.version(version)
					.eventType(event.getClass().getTypeName())
					.eventData(event)
					.build();
//			var eventModel= new EventModel(
//					LocalDateTime.now(),
//					aggregateId,
//					PrototypeAggregate.class.getTypeName(),
//					version,
//					event.getClass().getTypeName(),
//					event
//					);
			var persistEvent=eventStoreRepository.save(eventModel);
			//si la variable persistEvent es null significa que no se ha realizado la operacion
			//si no es null tengo que producir un evento para kafka
			if(!persistEvent.getId().isEmpty()) {
				//el nombre del topic deberia ser el mismo que el de la clase que genera este evento
				//indepotencia-> la key es el nombre de la clase y el id del evento
//				eventProducer.produce(event.getClass().getSimpleName(),event.getClass().getSimpleName()+ event.getId(),event);
				eventProducer.produce(event.getClass().getSimpleName(), event);
				//eventProducer.produce("${spring.kafka.template.default-topic}", event);
			}
		}
		
	}

	/**
	 * Gets the event.
	 *
	 * @param aggregateId the aggregate id
	 * @return the event
	 */
	@Override
	public List<BaseEvent> getEvent(String aggregateId) {
		var eventStream =eventStoreRepository.findByAggregateIdentifier(aggregateId);
//		List<EventModel> eventStream =eventStoreRepository.findByAggregateIdentifier(aggregateId);
		if(eventStream==null && eventStream.isEmpty())
			throw new AggregateNotFoundException("the search is wrong");

		return eventStream.stream().map(x -> x.getEventData()).collect(Collectors.toList());
	}
}
