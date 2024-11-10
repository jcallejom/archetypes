package com.archetype.cqrsev.core.produces;

import com.archetype.cqrsev.core.events.BaseEvent;

public interface EventProducer {
	
	void produce(String topic, BaseEvent event);
	
	/**
	 * Problemas--> El orden de los mensajes como van por varios brokers pueden llegarme desordenados(indepotencia)
	 * Solucion--> Añadir la key al productor que nos garantizara que todos los mensajes vayan por la misma particion del topic de kafka
	 * @param topic
	 * @param key
	 * @param event
	 */
	 void produce(String topic,String key, BaseEvent event);
}
