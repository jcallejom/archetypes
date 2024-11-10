package com.archetype.app.cmd.api.controllers;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archetype.app.cmd.api.command.ChangePrototypeDayCommand;
import com.archetype.app.cmd.domain.mappers.PrototypeMapperDomain;
import com.archetype.cqrsev.core.infrastructure.CommandDispatcher;
import com.archetype.cqrsev.core.vo.BaseResponse;

import jakarta.validation.Valid;

// TODO: Auto-generated Javadoc
/**
 * The Class ChangePrototypeDayController.
 */
@RestController
@RequestMapping(path="/v1/prototype")
public class ChangePrototypeDayController implements IChangePrototypeDayController{
	
	/** The logger. */
	private final Logger logger=Logger.getLogger(ChangePrototypeDayController.class.getName());

/** The command dispatcher. */
//	@Qualifier("PrototypeCommandDispatcher")
	@Autowired 
	private CommandDispatcher commandDispatcher;
	
	@Autowired
	private PrototypeMapperDomain mapper;
	/**
	 * Change prototype day.
	 *
	 * @param id the id
	 * @param command the command
	 * @return the response entity
	 */
	@PatchMapping(path="/changeprototypeday/{id}")
	public ResponseEntity<BaseResponse> changePrototypeDay(@Valid @PathVariable(value="id") String id,@RequestBody ChangePrototypeDayCommand command){
		try {	
//			command.setId(id);
			commandDispatcher.send(mapper.clone(command,id));
			return new ResponseEntity<BaseResponse>(new BaseResponse("The prototype´s day has been change successfully"),HttpStatus.OK);
		}catch (IllegalStateException e) {
			logger.log(Level.WARNING,MessageFormat.format("Could not change prototype´s day  - {0} ", e.toString()));
			return new ResponseEntity<BaseResponse>(new BaseResponse(e.toString()),HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			var safeErrorMessage=MessageFormat.format("Errors while processing the request - {0} ", id);
			logger.log(Level.SEVERE,safeErrorMessage,e);
			return new ResponseEntity<BaseResponse>(new BaseResponse(safeErrorMessage),HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
}
