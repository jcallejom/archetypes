package com.archetype.app.cmd.domain;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;

import com.archetype.app.cmd.api.command.ChangePrototypeDayCommand;
import com.archetype.app.cmd.api.command.OpenPrototypeCommand;
import com.archetype.app.cmd.domain.mappers.PrototypeMapperDomain;
import com.archetype.app.common.events.PrototypeClosedEvent;
import com.archetype.app.common.events.PrototypeDayChangedEvent;
import com.archetype.app.common.events.PrototypeOpenedEvent;
import com.archetype.cqrsev.core.domain.AggregateRoot;

import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class PrototypeAggregate.
 */
@NoArgsConstructor
public class PrototypeAggregate extends AggregateRoot{
	
	/** The active. */
	private Boolean active;
	
	/** The prototype code. */
	private String prototypeCode;
	
	/** The mapper. */
	@Autowired
	private PrototypeMapperDomain mapper;
	
	/**
	 * Instantiates a new prototype aggregate.
	 *
	 * @param command the command
	 */
	public PrototypeAggregate(OpenPrototypeCommand command){
		this.mapper=PrototypeMapperDomain.mapperInstance;
		try {
			raiseEvent(mapper.toDomain(command));
		}catch(DateTimeParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Apply.
	 *
	 * @param event the event
	 */
	public void apply(PrototypeOpenedEvent event) {
		this.id=event.getId();
		this.prototypeCode=event.getPrototypeCode();
		this.active=true;
	}

/**
 * Change prototype day.
 *
 * @param command the command
 */
//	public BaseResponse changePrototypeDay(ChangePrototypeDayCommand command) {
	public void changePrototypeDay(ChangePrototypeDayCommand command) {
		
		if(!this.active)
			throw new IllegalStateException("La fecha no puede ser actualiza porque prototype esta cerrado: "+active);
		if(command.getPrototypeDay().equals(LocalDate.now().toString()))
			throw new IllegalStateException("La fecha posterior a" + LocalDate.now()+" no puede ser actualiza con : "+LocalDate.now());
		
		raiseEvent(PrototypeMapperDomain.mapperInstance.toDomain(command,this.id));
		
//		return null;
	}
	
	/**
	 * Apply.
	 *
	 * @param event the event
	 */
	public void apply( PrototypeDayChangedEvent event) {
		this.id=event.getId();
	}
	
	/**
	 * Close prototype.
	 */
	public void closePrototype(){
		if(!this.active)
			throw new IllegalStateException("El prototype esta cerrado: "+active);
		raiseEvent(PrototypeMapperDomain.mapperInstance.toDomain(this.id));
	}
	
	/**
	 * Apply.
	 *
	 * @param event the event
	 */
	public void apply(PrototypeClosedEvent event) {
		this.id=event.getId();
		this.active=false;
	}
	
}
