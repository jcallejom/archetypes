/**
 * Self.
 *
 * @return the prototype changed event. prototype changed event builder impl
 */
package com.archetype.app.common.events;

import java.time.LocalDate;

import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.validation.annotation.Validated;

import com.archetype.cqrsev.core.events.BaseEvent;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Validated
@EqualsAndHashCode(callSuper=false)
@Getter
//@AllArgsConstructor
@Jacksonized//permite la serializacion/des correcta de clases inmutables
@SuperBuilder
public final class PrototypeDayChangedEvent extends BaseEvent{
	
	/** The prototype day. */
	private final LocalDate prototypeDay;
	@PersistenceCreator
	public PrototypeDayChangedEvent(LocalDate prototypeDay) {
		super();
		this.prototypeDay = prototypeDay;
	}
	



	

}
