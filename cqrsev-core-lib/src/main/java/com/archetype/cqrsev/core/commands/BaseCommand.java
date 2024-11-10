/*
 * 
 */
package com.archetype.cqrsev.core.commands;

import com.archetype.cqrsev.core.messages.Message;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseCommand. is the parent class of all Commands 
 */
@JsonIgnoreProperties("id")
@Data
@NoArgsConstructor
@SuperBuilder
public abstract class BaseCommand extends Message{

	/**
	 * Instantiates a new base command.
	 *
	 * @param id the id
	 */
	public BaseCommand(String id) {
			super(id);
		}


	
	

}
