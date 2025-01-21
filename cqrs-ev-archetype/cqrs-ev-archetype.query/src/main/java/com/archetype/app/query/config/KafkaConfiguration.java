package com.archetype.app.query.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class KafkaConfiguration {

/*Consumer*/
	 //ajustar properties no aautocommit, esta manual
	 private Map<String, Object> consumerProps() {
		 Map<String, Object>props=new HashMap<>();
		 props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		 props.put(ConsumerConfig.GROUP_ID_CONFIG, "prototypeConsumer");
//		 props.put("kafka.listener.ack-mode", "MANUAL_IMMEDIATE"); 
//		 props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true); 
//		 props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
//		 props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
		 props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		 props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		 props.put("spring.json.trusted.packages", "*");
//		 KafkaConfig.
		 return props;
	  }

	  @Bean
	  public ConsumerFactory<String, Object> consumerFactory() {

	    return new DefaultKafkaConsumerFactory<>(consumerProps());
		//  return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Event.class));
	
	  }
	
	  @Bean
	  public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
	    ConcurrentKafkaListenerContainerFactory<String, Object> listenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
	    listenerContainerFactory.setConsumerFactory(consumerFactory());
	    listenerContainerFactory.setBatchListener(true);//añade batch al consumer
	    listenerContainerFactory.setConcurrency(5);//añade hilos
	    listenerContainerFactory.getContainerProperties().setAckMode(AckMode.MANUAL_IMMEDIATE);//conf respuesta manual
	//    factory.setCommonErrorHandler(new KafkaCommonErrorHandler());
	    /*NOTE ErrorHandler @deprecated in favor of {@link #setCommonErrorHandler(CommonErrorHandler) */
	//    factory.setErrorHandler(new KafkaErrorHandler());
	    
	    return listenerContainerFactory;
	  }

	
}