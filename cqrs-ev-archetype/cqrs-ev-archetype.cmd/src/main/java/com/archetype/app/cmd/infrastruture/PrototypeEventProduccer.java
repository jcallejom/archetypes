package com.archetype.app.cmd.infrastruture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.archetype.cqrsev.core.events.BaseEvent;
import com.archetype.cqrsev.core.produces.EventProducer;

import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/**
 * The Class PrototypeEventProduccer.
 */
@Slf4j
@Service
public class PrototypeEventProduccer implements EventProducer {
	
	/** The kafka template. */
	@Autowired
//	@Qualifier("kafkaTemplatenuevo")
	private KafkaTemplate<String,Object> kafkaTemplate;
	
	/**
	 * Produce.
	 *
	 * @param topic the topic
	 * @param event the event
	 */
	@Override
	public void produceASyn(String topic, BaseEvent event) {
		
		CompletableFuture <SendResult<String, Object>>future = kafkaTemplate.send(topic,event);
		   future.thenAccept(result -> {
				log.debug("Message sent: time {}, topic {}, offset {}, partition {}, key: {}, data: {}"
						,result.getRecordMetadata().timestamp()
						,result.getRecordMetadata().topic()
						,result.getRecordMetadata().offset()
						,result.getRecordMetadata().partition()
						,result.getProducerRecord().key()
						,result.getProducerRecord().value());
//						,event);
			}).exceptionally(ex -> {
				log.error("Error sending message {}",ex.getMessage() );
					return null;
				});
	}
	
	/**
	 * Produce.
	 *
	 * @param topic the topic
	 * @param key the key
	 * @param event the event
	 */
	@Override
	public void produceASyn(String topic, String key, BaseEvent event) {
		
		CompletableFuture <SendResult<String, Object>>future = kafkaTemplate.send(topic,key,event);
		   future.thenAccept(result -> {
				log.debug("Message sent: time {}, topic {}, offset {}, partition {}, key: {}, data: {}"
						,result.getRecordMetadata().timestamp()
						,result.getRecordMetadata().topic()
						,result.getRecordMetadata().offset()
						,result.getRecordMetadata().partition()
						,result.getProducerRecord().key()
						,result.getProducerRecord().value());
//						,event);
			}).exceptionally(ex -> {
				log.error("Error sending message {}",ex.getMessage() );
					return null;
				});
		
	}
	@Override
	public void produceSyn(String topic, BaseEvent event) {
		try {
			kafkaTemplate.send(topic,event).get();
			
		} catch (InterruptedException | ExecutionException e) {
			log.error("Error sending message {}",e.getMessage() );
		}		
	}
	@Override
	public void produceSyn(String topic, String key, BaseEvent event) {
		try {
			kafkaTemplate.send(topic,key,event).get(10,TimeUnit.SECONDS);
//			kafkaTemplate.send(topic,key,event).get();
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			log.error("Error sending message {}",e.getMessage() );
		}
		
	}
	
}
