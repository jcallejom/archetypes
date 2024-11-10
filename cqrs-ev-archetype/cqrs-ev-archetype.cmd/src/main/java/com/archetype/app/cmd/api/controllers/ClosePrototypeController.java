package com.archetype.app.cmd.api.controllers;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archetype.app.cmd.api.command.ClosePrototypeCommand;
import com.archetype.cqrsev.core.infrastructure.CommandDispatcher;
import com.archetype.cqrsev.core.vo.BaseResponse;

import jakarta.validation.Valid;

// TODO: Auto-generated Javadoc
/**
 * The Class ClosePrototypeController.
 */
@RestController
@RequestMapping(path="/v1/prototype")
public class ClosePrototypeController implements IClosePrototypeController{
	
	/** The logger. */
	private final Logger logger=Logger.getLogger(ClosePrototypeController.class.getName());

/** The command dispatcher. */
//	@Qualifier("PrototypeCommandDispatcher")
	@Autowired 
	private CommandDispatcher commandDispatcher;
	
	/**
	 * Close prototype.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping(path="/closeprototype/{id}")
	public ResponseEntity<BaseResponse> closePrototype(@Valid @PathVariable(value="id") String id){
	
		
		try {	
			commandDispatcher.send(new ClosePrototypeCommand(id));
			return new ResponseEntity<BaseResponse>(new BaseResponse("The prototype has been removed successfully"),HttpStatus.OK);
		}catch (IllegalStateException e) {
			logger.log(Level.WARNING,MessageFormat.format("Could not removed prototype - {0} ", e.toString()));
			return new ResponseEntity<BaseResponse>(new BaseResponse(e.toString()),HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			var safeErrorMessage=MessageFormat.format("Errors while processing the request - {0} ", id);
			logger.log(Level.SEVERE,safeErrorMessage,e);
			return new ResponseEntity<BaseResponse>(new BaseResponse(safeErrorMessage),HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
}
