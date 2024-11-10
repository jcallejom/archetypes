package com.archetype.app.query.infrastructure.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.archetype.app.common.events.PrototypeClosedEvent;
import com.archetype.app.common.events.PrototypeDayChangedEvent;
import com.archetype.app.common.events.PrototypeOpenedEvent;
import com.archetype.app.query.infrastructure.handlers.EventHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class PrototypeEventsConsumers.
 */
@Service
public class PrototypeEventsConsumers implements EventsConsumers {
	
	/** The event handler. */
	@Autowired
	private EventHandler eventHandler;
	
	
	/**
	 * Consume.
	 *
	 * @param event the event
	 * @param ack the ack
	 */
	//@KafkaListener(topics="${spring.kafka.template.default-topic}",groupId = "${spring.kafka.consumer.group-id}")
	@KafkaListener(topics="PrototypeOpenedEvent",groupId = "${spring.kafka.consumer.group-id}")
	@Override
	public void consume(PrototypeOpenedEvent event, Acknowledgment ack) {
		eventHandler.on(event);
		ack.acknowledge();//una vez consuma el mensaje tengo que indicaler PrototypeEntity PrototypeEntity kafka que ya fue consumido
	}
	
	/**
	 * Consume.
	 *
	 * @param event the event
	 * @param ack the ack
	 */
	@KafkaListener(topics="PrototypeDayChangedEvent",groupId = "${spring.kafka.consumer.group-id}")
	@Override
	public void consume(PrototypeDayChangedEvent event, Acknowledgment ack) {
		eventHandler.on(event);
		ack.acknowledge();
		
	}
	
	/**
	 * Consume.
	 *
	 * @param event the event
	 * @param ack the ack
	 */
	@KafkaListener(topics="PrototypeClosedEvent",groupId = "${spring.kafka.consumer.group-id}")
	@Override
	public void consume(PrototypeClosedEvent event, Acknowledgment ack) {
		eventHandler.on(event);
		ack.acknowledge();
	}

}
