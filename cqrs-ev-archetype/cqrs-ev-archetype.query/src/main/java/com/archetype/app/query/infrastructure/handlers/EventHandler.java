package com.archetype.app.query.infrastructure.handlers;

import com.archetype.app.common.events.PrototypeClosedEvent;
import com.archetype.app.common.events.PrototypeDayChangedEvent;
import com.archetype.app.common.events.PrototypeOpenedEvent;

// TODO: Auto-generated Javadoc
/**
 * The Interface EventHandler.
 */
public interface EventHandler {
	
	/**
	 * On.
	 *
	 * @param event the event
	 */
	void on(PrototypeOpenedEvent event);

	/**
	 * On.
	 *
	 * @param event the event
	 */
	void on(PrototypeDayChangedEvent event);

	/**
	 * On.
	 *
	 * @param event the event
	 */
	void on(PrototypeClosedEvent event);
}
