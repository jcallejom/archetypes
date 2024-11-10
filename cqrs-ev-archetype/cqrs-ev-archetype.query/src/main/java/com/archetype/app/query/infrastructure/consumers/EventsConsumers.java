package com.archetype.app.query.infrastructure.consumers;

import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

import com.archetype.app.common.events.PrototypeClosedEvent;
import com.archetype.app.common.events.PrototypeDayChangedEvent;
import com.archetype.app.common.events.PrototypeOpenedEvent;

// TODO: Auto-generated Javadoc
/**
 * The Interface EventsConsumers.
 */
public interface EventsConsumers {
	//una vez consuma el mensaje tengo que indicale PrototypeEntity kafka que ya fue consumido
	/**
	 * Consume.
	 *
	 * @param event the event
	 * @param ack the ack
	 */
	//de eso se encarga Acknowledgment
	void consume(@Payload PrototypeOpenedEvent event,Acknowledgment ack);
	
	/**
	 * Consume.
	 *
	 * @param event the event
	 * @param ack the ack
	 */
	void consume(@Payload PrototypeDayChangedEvent event,Acknowledgment ack);
	
	/**
	 * Consume.
	 *
	 * @param event the event
	 * @param ack the ack
	 */
	void consume(@Payload PrototypeClosedEvent event,Acknowledgment ack);
	
}
