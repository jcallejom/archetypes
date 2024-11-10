/**
 * Self.
 *
 * @return the prototype saved event. prototype saved event builder impl
 */
package com.archetype.app.common.events;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import com.archetype.app.common.vo.PrototypeNumberVo;
import com.archetype.cqrsev.core.events.BaseEvent;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
 
@Validated
@EqualsAndHashCode(callSuper=false)
@Getter
//@AllArgsConstructor()
@Jacksonized//permite la serializacion/des correcta de clases inmutables en builder
@SuperBuilder
//@Document
@CompoundIndex(def = "{'id':1, 'prototypeCode':1}",unique = true)
public final class PrototypeOpenedEvent extends BaseEvent{
 
	/** The prototype code. */
	@Indexed(unique = true)
	private final String prototypeCode;
  	
	  /** The prototype day. */
	private final LocalDate prototypeDay;
	
	/** The prototype number vo. */
	private final List<PrototypeNumberVo> prototypeNumbers;
	/**
	 * Gets the prototype numbers.
	 *
	 * @return the prototype numbers
	 */
	public final List<PrototypeNumberVo> getPrototypeNumbers() {
		//return immutable list
		return Collections.unmodifiableList(this.prototypeNumbers);
	}
	@PersistenceCreator//necesario por objetos imutables
	public PrototypeOpenedEvent(String prototypeCode, LocalDate prototypeDay,
			List<PrototypeNumberVo> prototypeNumbers) {
		super();
		this.prototypeCode = prototypeCode;
		this.prototypeDay = prototypeDay;
		this.prototypeNumbers = prototypeNumbers;
	}
}
