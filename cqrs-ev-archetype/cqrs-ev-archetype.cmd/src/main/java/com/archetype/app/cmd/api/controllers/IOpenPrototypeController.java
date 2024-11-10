package com.archetype.app.cmd.api.controllers;

import org.springframework.http.ResponseEntity;

import com.archetype.app.cmd.api.command.OpenPrototypeCommand;
import com.archetype.cqrsev.core.exceptions.model.StandarizedApiExceptionResponse;
import com.archetype.cqrsev.core.vo.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Open Prototype Controller", description = "Prototype open endpoint to try the API")
public interface IOpenPrototypeController {
	

	
	@Operation(description = "Executes a new OpenPrototypeCommand",summary ="Return 400 if Could not generate prototype")
	@ApiResponse(responseCode = "200", description = "Exito")
	@ApiResponse(responseCode = "500", description = "Error conecting with the DB"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "400", description = "Could not generate prototype"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "401", description = "Unauthorized,security error"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "403", description = "Forbidden,security error"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))	
	public ResponseEntity<BaseResponse> openPrototype( OpenPrototypeCommand command);
}
