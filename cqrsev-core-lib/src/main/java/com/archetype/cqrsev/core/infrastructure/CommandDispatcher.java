package com.archetype.cqrsev.core.infrastructure;

import com.archetype.cqrsev.core.commands.BaseCommand;
import com.archetype.cqrsev.core.commands.CommandHandlerMethod;
import com.archetype.cqrsev.core.vo.BaseResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface CommandDispatcher.
 */				 
public interface CommandDispatcher {
	
	/**
	 * Register handler.
	 *
	 * @param <T> the generic type
	 * @param type the type
	 * @param handler the handler
	 */
	public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler );

	public void send (BaseCommand command);
	
//	public <R extends BaseResponse> R send (BaseCommand command);

}
