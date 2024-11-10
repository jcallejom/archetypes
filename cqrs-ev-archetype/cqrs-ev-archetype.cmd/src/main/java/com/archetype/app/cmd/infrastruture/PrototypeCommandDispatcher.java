package com.archetype.app.cmd.infrastruture;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.archetype.cqrsev.core.commands.BaseCommand;
import com.archetype.cqrsev.core.commands.CommandHandlerMethod;
import com.archetype.cqrsev.core.infrastructure.CommandDispatcher;
import com.archetype.cqrsev.core.vo.BaseResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class PrototypeCommandDispatcher.
 */
//@Qualifier("PrototypeCommandDispatcher")
@Service
public class PrototypeCommandDispatcher implements CommandDispatcher{
	
	/** The routes. */
	private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>>routes= new HashMap<>();
	
	/**
	 * Agrega los handlers.
	 *
	 * @param <T> the generic type
	 * @param type the type
	 * @param handler the handler
	 */
	@Override
	public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
		
		var handlers=routes.computeIfAbsent(type, c-> new LinkedList<>());
		handlers.add(handler);
	}

	/**
	 * Send.
	 *
	 * @param command the command
	 */
	@Override
	public void send(BaseCommand command) {
//	public <R extends BaseResponse> R send(BaseCommand command) {
		var handlers=routes.get(command.getClass());
		if(handlers==null || handlers.size()==0)
			throw new RuntimeException("The command handler was not registered");
		if (handlers.size()>1)
			throw new RuntimeException("You cannot send a command that has more handler");
//		return handlers.get(0).handle(command);
		
		handlers.get(0).handle(command);
		
	}
}