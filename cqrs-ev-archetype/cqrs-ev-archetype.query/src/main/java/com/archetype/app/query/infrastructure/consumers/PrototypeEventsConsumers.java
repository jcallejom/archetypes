package com.archetype.app.query.infrastructure.consumers;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.archetype.app.common.events.PrototypeClosedEvent;
import com.archetype.app.common.events.PrototypeDayChangedEvent;
import com.archetype.app.common.events.PrototypeOpenedEvent;
import com.archetype.app.query.infrastructure.handlers.EventHandler;

import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/**
 * The Class PrototypeEventsConsumers.
 */
@Slf4j
@Service
public class PrototypeEventsConsumers implements EventsConsumers {
	
	/** The event handler. */
	@Autowired
	private EventHandler eventHandler;
	
	private static long numRep=0;
	private static long start=0;
	private static long finish=0;
	
//	@KafkaListener(topics="PrototypeOpenedEvent",groupId = "${spring.kafka.consumer.group-id}")
//	@Override
//	public void consume( ConsumerRecord<String,PrototypeOpenedEvent> messageEvent, Acknowledgment ack) {
//		numRep++;
//		if(numRep==1) {
//			start=System.currentTimeMillis();
//		}
//		finish=System.currentTimeMillis();
//		if(Integer.parseInt(messageEvent.value().getPrototypeCode())%500==0)
//			log.info("Recived Message :  topic {}, offset {}, partition {}, key: {}, value: {}",
//					messageEvent.topic(),
//					messageEvent.offset(),
//					messageEvent.partition(),
//					messageEvent.key(),
//					messageEvent.value()
//					);
//		
//		eventHandler.on(messageEvent.value());
//		ack.acknowledge();//una vez consuma el mensaje tengo que indicaler PrototypeEntity PrototypeEntity kafka que ya fue consumido
//		if(numRep%500==0)
//			log.info("num repeticiones {} ,finish time: {} ms", numRep, finish-start);
//	}
	//@KafkaListener(topics="${spring.kafka.template.default-topic}",groupId = "${spring.kafka.consumer.group-id}")
//	@KafkaListener(topics="PrototypeOpenedEvent",groupId = "${spring.kafka.consumer.group-id}")
//	@Override
//	public void consume(PrototypeOpenedEvent event, Acknowledgment ack) {
//		eventHandler.on(event);
//		ack.acknowledge();//una vez consuma el mensaje tengo que indicaler PrototypeEntity PrototypeEntity kafka que ya fue consumido
//	}
	@KafkaListener(topics="PrototypeOpenedEvent",
					groupId = "${spring.kafka.consumer.group-id}",
			  containerFactory  ="kafkaListenerContainerFactory",
			  properties = {"max.poll.interval.ms:10000",//4 sec
		  		"max.poll.records:5"})//10 rgis 
	@Override
	public void consume(List<ConsumerRecord<String,PrototypeOpenedEvent>> events, Acknowledgment ack) {
		numRep++;
		if(numRep==1) {
			start=System.currentTimeMillis();
		}
		finish=System.currentTimeMillis();
		log.info("num repeticiones {} ,start batch time: {} ms", numRep, start);
		for (ConsumerRecord<String,PrototypeOpenedEvent> messageEvent : events) {
			log.info("Recived Message :  topic {}, offset {}, partition {}, key: {}, value: {}",
					messageEvent.topic(),
					messageEvent.offset(),
					messageEvent.partition(),
					messageEvent.key(),
					messageEvent.value()
					);
			eventHandler.on(messageEvent.value());
		}
		ack.acknowledge();//una vez consuma el mensaje tengo que indicaler PrototypeEntity PrototypeEntity kafka que ya fue consumido
//		if(numRep%50==0)
			log.info("num repeticiones {} ,finish batch time: {} ms", numRep, finish-start);	
	}
	/**
	 * Consume.
	 *
	 * @param event the event
	 * @param ack the ack
	 */
//	@KafkaListener(topics="PrototypeDayChangedEvent",
//			  groupId = "${spring.kafka.consumer.group-id}",
//			  containerFactory  ="kafkaListenerContainerFactory",
//			  properties = {"max.poll.interval.ms:10000",//4 sec
//		  		"max.poll.records:50"})//10 rgis 
	@KafkaListener(topics="PrototypeDayChangedEvent",groupId = "${spring.kafka.consumer.group-id}")
	@Override
	public void consume(PrototypeDayChangedEvent event, Acknowledgment ack) {
		numRep++;
		if(numRep==1) {
			start=System.currentTimeMillis();
		}
		finish=System.currentTimeMillis();
		eventHandler.on(event);
		ack.acknowledge();
		if(numRep%100==0)
			log.info("num repeticiones {} ,finish time: {} ms", numRep, finish-start);

	}
	
	/**
	 * Consume.
	 *
	 * @param event the event
	 * @param ack the ack
	 */
//	@KafkaListener(topics="PrototypeClosedEvent",
//			  groupId = "${spring.kafka.consumer.group-id}",
//			  containerFactory  ="kafkaListenerContainerFactory",
//			  properties = {"max.poll.interval.ms:10000",//4 sec
//		  		"max.poll.records:50"})//10 rgis
	@KafkaListener(topics="PrototypeClosedEvent",groupId = "${spring.kafka.consumer.group-id}")
	@Override
	public void consume(PrototypeClosedEvent event, Acknowledgment ack) {
		eventHandler.on(event);
		ack.acknowledge();
	}

}
