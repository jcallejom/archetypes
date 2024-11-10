/**
 * Self.
 *
 * @return the b
 */
package com.archetype.cqrsev.core.events;

import com.archetype.cqrsev.core.messages.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseEvent.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

/**
 * The Class BaseEventBuilder.
 *
 * @param <C> the generic type
 * @param <B> the generic type
 */
@SuperBuilder
public abstract class BaseEvent extends Message{
	
	/** The version. */
	private int version;
}
