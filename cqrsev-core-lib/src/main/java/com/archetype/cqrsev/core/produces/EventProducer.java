package com.archetype.cqrsev.core.produces;

import com.archetype.cqrsev.core.events.BaseEvent;

public interface EventProducer {
	
	 void produceASyn(String topic, BaseEvent event);
	
	 void produceASyn(String topic, String key, BaseEvent event);
	
	 void produceSyn(String topic, BaseEvent event);
	
	 void produceSyn(String topic, String key, BaseEvent event);
}
