package com.archetype.cqrsev.core.commands;

import com.archetype.cqrsev.core.vo.BaseResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface CommandHandlerMethod.
 *
 * @param <T> the generic type
 */
@FunctionalInterface
public interface CommandHandlerMethod <T extends BaseCommand> {
	
	/**
	 * Handle.
	 *
	 * @param command the command
	 * @return the base response
	 */
//	BaseResponse handle(T command);
	void handle(T command);
}
