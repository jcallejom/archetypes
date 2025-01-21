package com.archetype.app.cmd.api.controllers;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archetype.app.cmd.api.command.OpenPrototypeCommand;
import com.archetype.app.cmd.api.vo.PrototypeResponse;
import com.archetype.cqrsev.core.infrastructure.CommandDispatcher;
import com.archetype.cqrsev.core.vo.BaseResponse;

import jakarta.validation.Valid;

// TODO: Auto-generated Javadoc
/**
 * The Class OpenPrototypeController.
 */
@RestController
@RequestMapping(path="/v1/prototype")
public class OpenPrototypeController implements IOpenPrototypeController {
	
	/** The logger. */
	private final Logger logger=Logger.getLogger(OpenPrototypeController.class.getName());
	
	/** The command dispatcher. */
	//@Qualifier("PrototypeCommandDispatcher")
	@Autowired 
	private CommandDispatcher commandDispatcher;
	
	/**
	 * Open prototype.
	 *
	 * @param command the command
	 * @return the response entity
	 */
	@PostMapping("/openprototype")
	public ResponseEntity<BaseResponse> openPrototype(@Valid @RequestBody OpenPrototypeCommand command){
//		var id=UUID.randomUUID().toString();
//		command.setId(id);
		try {
//			create(command);
			commandDispatcher.send(command);
			return new ResponseEntity<BaseResponse>(new PrototypeResponse("The prototype has been saved successfully",command.getPrototypeCode()),HttpStatus.CREATED);
//			return new ResponseEntity<BaseResponse>(new PrototypeResponse("The search has been saved successfully",id),HttpStatus.CREATED);

//			BaseResponse response = commandDispatcher.send(command);
//			return new ResponseEntity<BaseResponse>(new PrototypeResponse("The prototype has been saved successfully",response.getMessage()),HttpStatus.CREATED);
//		//try catc deberia ir mas adentro
		}catch (IllegalStateException e) {
			logger.log(Level.WARNING,MessageFormat.format("Could not generate prototype - {0} ", e.toString()));
			return new ResponseEntity<BaseResponse>(new BaseResponse(e.toString()),HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
//			var safeErrorMessage=MessageFormat.format("Errors while processing the request - {0} ", id);
			var safeErrorMessage=MessageFormat.format("Errors while processing the request - {0} ", command.getClass().toString());
			logger.log(Level.SEVERE,safeErrorMessage,e);
			return new ResponseEntity<BaseResponse>(new PrototypeResponse(safeErrorMessage,command.getClass().toString()),HttpStatus.INTERNAL_SERVER_ERROR);
//			return new ResponseEntity<BaseResponse>(new PrototypeResponse(safeErrorMessage,id),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	private final long MAX=28200;
	private void create(OpenPrototypeCommand command){
		long start=System.currentTimeMillis();
		for(int i=28101;i<=MAX;i++)
			
			commandDispatcher.send(OpenPrototypeCommand.builder()
					.prototypeNumbers(command.getPrototypeNumbers())
					.prototypeDay(command.getPrototypeDay())
//					.prototypeCode(UUID.randomUUID().toString())
					.prototypeCode(String.valueOf(i))
					.build());
		
		logger.log(Level.INFO,"tiempo envio: {0}",System.currentTimeMillis()-start);
		
	}
}
