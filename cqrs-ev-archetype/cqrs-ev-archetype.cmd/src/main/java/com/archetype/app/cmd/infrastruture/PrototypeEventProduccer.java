package com.archetype.app.cmd.infrastruture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.archetype.cqrsev.core.events.BaseEvent;
import com.archetype.cqrsev.core.produces.EventProducer;

// TODO: Auto-generated Javadoc
/**
 * The Class PrototypeEventProduccer.
 */
@Service
public class PrototypeEventProduccer implements EventProducer {
	
	/** The kafka template. */
	@Autowired
	private KafkaTemplate<String,Object> kafkaTemplate;
	
	/**
	 * Produce.
	 *
	 * @param topic the topic
	 * @param event the event
	 */
	@Override
	public void produce(String topic, BaseEvent event) {
		kafkaTemplate.send(topic,event);
		
	}
	
	/**
	 * Produce.
	 *
	 * @param topic the topic
	 * @param key the key
	 * @param event the event
	 */
	@Override
	public void produce(String topic, String key, BaseEvent event) {
		kafkaTemplate.send(topic,key,event);
		
	}
}
