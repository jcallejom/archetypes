/**
 * Self.
 *
 * @return the b
 */
package com.archetype.cqrsev.core.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class Message. is the parent class of all Vo elements of EDA Architecture
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

/**
 * The Class MessageBuilder.
 *
 * @param <C> the generic type
 * @param <B> the generic type
 */
@SuperBuilder
public abstract class Message {
	
	/** The id. of message */
	private String id;
}
