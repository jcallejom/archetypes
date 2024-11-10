package com.archetype.app.query.api.queries;

import org.springframework.validation.annotation.Validated;

import com.archetype.cqrsev.core.queries.BaseQuery;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

// TODO: Auto-generated Javadoc
/**
 * The Class FindPrototypeByPrototypeCodeQuery.
 */
@Validated
@EqualsAndHashCode(callSuper=false)
@Getter
@AllArgsConstructor
@Jacksonized//permite la serializacion/des correcta de clases inmutables

/**
 * The Class FindPrototypeByPrototypeCodeQueryBuilder.
 */
@Builder
@Schema(name = "FindPrototypeByPrototypeCodeQuery", description = "query search a prototype on database")
public final class FindPrototypeByPrototypeCodeQuery extends BaseQuery{
	
	/** The prototype code. */
	@JsonProperty("prototypecode")
	@NotBlank
	@NotNull(message = "prototypecode cannot be null")
    @Schema(name = "prototypecode", requiredMode = Schema.RequiredMode.REQUIRED,example = "01abct", defaultValue = "01abct", description = "Unique id of prototype that represent the owner of prototype")
	private final String prototypeCode;
}
