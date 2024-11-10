package com.archetype.app.cmd.api.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archetype.app.cmd.domain.PrototypeAggregate;
import com.archetype.cqrsev.core.handlers.EventSourcingHandler;


// TODO: Auto-generated Javadoc
/**
 * The Class PrototypeCommandHandler.
 */
@Service
public class PrototypeCommandHandler implements ICommandHandler{
	
/** The event sourcing handler. */
//	@Qualifier("PrototypeEventSourcingHandler")
	@Autowired
	private EventSourcingHandler<PrototypeAggregate> eventSourcingHandler;
	
	/**
	 * Handle.
	 *
	 * @param command the command
	 */
	@Override
	public void handle(OpenPrototypeCommand command) {
//	public BaseResponse handle(OpenPrototypeCommand command){
		var aggregate= new PrototypeAggregate(command);
		eventSourcingHandler.save(aggregate);
		
	}

	/**
	 * Handle.
	 *
	 * @param command the command
	 */
	@Override
	public void handle(ChangePrototypeDayCommand command) {
//	public BaseResponse handle(ChangePrototypeDayCommand command){
		var aggregate=eventSourcingHandler.getById(command.getId());
		aggregate.changePrototypeDay(command);
		eventSourcingHandler.save(aggregate);
	}

	/**
	 * Handle.
	 *
	 * @param command the command
	 */
	@Override
	public void handle(ClosePrototypeCommand command) {
//	public BaseResponse handle(ClosePrototypeCommand command){
		var aggregate=eventSourcingHandler.getById(command.getId());
		aggregate.closePrototype();
		eventSourcingHandler.save(aggregate);
	}
	
}
