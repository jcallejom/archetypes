package com.archetype.app.cmd.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.MicrometerProducerListener;
import org.springframework.kafka.support.micrometer.KafkaRecordSenderContext;
import org.springframework.kafka.support.micrometer.KafkaTemplateObservationConvention;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import io.micrometer.common.KeyValues;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling//permite programar algo que se ejecute cada cierto tiempo
@Configuration
public class KafkaConfiguration {
//	@Autowired
//	private MeterRegistry meterRegistry;
	
	@Scheduled(fixedDelay = 100000000, initialDelay = 5000)//cada segundo se imprime el log
	public void print() {
		log.info("##metricas disponibles##");
		List<Meter> avoidMetrics=meterRegistry().getMeters();
		for(Meter meter:avoidMetrics) {
			 log.info("Metric {} ",meter.getId());
			 }	
	}
//	@Scheduled(fixedDelay = 2000, initialDelay = 500)
//	 public void messageCountMetric() {   
//		 double count = 
//		 meterRegistry().get("kafka.producer.record.send.total").functionCounter().count();
//		 log.info("Count {} ",count);
//	 }
	  /**Metricas prometheus en el produccer**/
	  @Bean
	  public MeterRegistry meterRegistry() {
		  PrometheusMeterRegistry prometheusMeterRegistry=  new PrometheusMeterRegistry (PrometheusConfig.DEFAULT);
		  
		  return prometheusMeterRegistry;
	  }
	/*Producer*/
	  
	private Map<String, Object> producerProps() {
		 Map<String, Object> props=new HashMap<>();
		 props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
		 "localhost:9092");
		 //props.put(ProducerConfig.RETRIES_CONFIG, 0);
		 //props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		 //props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		 //props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		 props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
		 props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
		 return props;
		 }
	 @WithSpan(value = "cqrs.command:publish")
	 @Bean//(name = "kafkaTemplatenuevo" )
	 public KafkaTemplate<String, Object> kafkaTemplate() {
		 Map<String, Object>senderProps= producerProps();
		 DefaultKafkaProducerFactory<String, Object> producerFactory= new  DefaultKafkaProducerFactory<String, Object>(senderProps);
		 /*Añadir listener de mertricas prometeus*/
		 producerFactory.addListener(new MicrometerProducerListener<String,Object> (meterRegistry()));
		 KafkaTemplate<String, Object> template=new KafkaTemplate<>(producerFactory);
		 /*añadir otel*/
		 template.setObservationEnabled(true);
		 template.setObservationConvention(new KafkaTemplateObservationConvention() {
	         @Override
	         public KeyValues getLowCardinalityKeyValues(KafkaRecordSenderContext context) {
	            return KeyValues.of("topic", context.getDestination(),
	                    "id", String.valueOf(context.getRecord().key()));
	         }
	      });
		 return template;
	 }


	
}