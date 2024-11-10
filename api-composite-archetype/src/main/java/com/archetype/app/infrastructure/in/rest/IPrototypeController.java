package com.archetype.app.infrastructure.in.rest;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import com.archetype.app.domain.dto.PrototypeRequest;
import com.archetype.app.domain.dto.PrototypeResponse;
import com.archetype.base.core.exception.FunctionalException;
import com.archetype.base.core.exception.model.StandarizedApiExceptionResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@Tag(name = "Prototype Controller", description = "Prototype endpoints to try the API")
public interface IPrototypeController {

	@Operation(summary = "Endpoint to check if the API is accesible")
	@ApiResponse(responseCode = "200")
	@ApiResponse(responseCode = "500", description = "Error conecting with the DB"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "401", description = "Unauthorized,security error"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "403", description = "Forbidden,security error"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	public ResponseEntity<String> test();

	@Operation(summary = "Get all rows from prototype table at the DB")
	@ApiResponse(responseCode = "200")
	@ApiResponse(responseCode = "502", description = "Error conecting with the DB"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "500", description = "Error conecting with the DB"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "401", description = "Unauthorized,security error"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "403", description = "Forbidden,security error"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	public ResponseEntity<List<PrototypeResponse>> getAll();

	@Operation(summary = "Get the row for the given ID from prototype table at the DB")
	@ApiResponse(responseCode = "200")
	@ApiResponse(responseCode = "502", description = "Error conecting with the DB"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "500", description = "Error conecting with the DB"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "401", description = "Unauthorized,security error"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "403", description = "Forbidden,security error"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	public ResponseEntity<PrototypeResponse> findById(Long id);

	@Operation(description = "Create a new row in prototype table at the DB",summary ="Return 400 if Could not generate prototype")
	@ApiResponse(responseCode = "200")
	@ApiResponse(responseCode = "502", description = "Error conecting with the DB"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "500", description = "Error conecting with the DB"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "401", description = "Unauthorized,security error"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "403", description = "Forbidden,security error"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	public ResponseEntity<PrototypeResponse> post(@Valid PrototypeRequest data);
	
	@Operation(summary = "Update row by id in prototype table at the DB")
	@ApiResponse(responseCode = "200")
	@ApiResponse(responseCode = "502", description = "Error conecting with the DB"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "500", description = "Error conecting with the DB"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "404", description = "Error element not found"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "401", description = "Unauthorized,security error"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "403", description = "Forbidden,security error"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	public ResponseEntity<PrototypeResponse> put(@NotNull Long id, @Valid PrototypeRequest data) throws FunctionalException;
	
	@Operation(summary = "Get all rows paged from prototype table at the DB")
	@ApiResponse(responseCode = "200")
	@ApiResponse(responseCode = "502", description = "Error conecting with the DB"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "500", description = "Error conecting with the DB"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "401", description = "Unauthorized,security error"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	@ApiResponse(responseCode = "403", description = "Forbidden,security error"
	,content = @Content(schema = @Schema( implementation = StandarizedApiExceptionResponse.class)))
	public ResponseEntity<List<PrototypeResponse>> findAllPage(Pageable pageable);
	
	
}
