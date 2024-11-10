package com.archetype.app.cmd.api.command;

import com.archetype.cqrsev.core.vo.BaseResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface ICommandHandler.
 */
public interface ICommandHandler {
	
	/**
	 * Handle.
	 *
	 * @param command the command
	 */
	void handle (OpenPrototypeCommand command);
	
	/**
	 * Handle.
	 *
	 * @param command the command
	 */
	void handle (ChangePrototypeDayCommand command);
	
	/**
	 * Handle.
	 *
	 * @param command the command
	 */
	void handle (ClosePrototypeCommand command);
//	BaseResponse handle (OpenPrototypeCommand command);
//	BaseResponse handle (ChangePrototypeDayCommand command);
//	BaseResponse handle (ClosePrototypeCommand command);

}
