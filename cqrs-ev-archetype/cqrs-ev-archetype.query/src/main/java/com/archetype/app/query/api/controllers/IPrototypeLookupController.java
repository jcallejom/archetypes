package com.archetype.app.query.api.controllers;

import org.springframework.http.ResponseEntity;

import com.archetype.app.query.api.vo.PrototypeResponse;
import com.archetype.cqrsev.core.exceptions.model.StandarizedApiExceptionResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// TODO: Auto-generated Javadoc
/**
 * The Interface IPrototypeLookupController.
 */
@Tag(name = "Open Prototype Controller", description = "Prototype open endpoint to try the API")
public interface IPrototypeLookupController {



	/**
	 * Find prototype by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@Operation(description = "Executes find PrototypeById query",summary ="Return 404 if element not found")
	@ApiResponse(responseCode = "200")
	@ApiResponse(responseCode = "404", description = "Error element not found"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "401", description = "Unauthorized,security error"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "403", description = "Forbidden,security error"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "500", description = "Error conecting with the DB"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	public ResponseEntity<PrototypeResponse> findPrototypeById(
			@Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED,example = "acdef", defaultValue = "acdef", description = "id ")
			@NotNull@NotBlank String id) ;

	
	
	/**
	 * Find prototype by prototype code.
	 *
	 * @param code the code
	 * @return the response entity
	 */
	@Operation(description = "Executes find PrototypeByPrototypeCode query",summary ="Return 404 if element not found")
	@ApiResponse(responseCode = "200")
	@ApiResponse(responseCode = "404", description = "Error element not found"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "401", description = "Unauthorized,security error"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "403", description = "Forbidden,security error"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "500", description = "Error conecting with the DB"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	public ResponseEntity<PrototypeResponse> findPrototypeByPrototypeCode(
	@Schema(name = "code", requiredMode = Schema.RequiredMode.REQUIRED,example = "LT2", defaultValue = "LT2", description = "code ")
	@NotNull@NotBlank String code);
	
}
