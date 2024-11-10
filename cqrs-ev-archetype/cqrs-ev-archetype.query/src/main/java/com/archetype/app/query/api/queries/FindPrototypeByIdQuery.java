package com.archetype.app.query.api.queries;

import org.springframework.validation.annotation.Validated;

import com.archetype.cqrsev.core.queries.BaseQuery;

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
 * The Class FindPrototypeByIdQuery.
 */
@Validated
@EqualsAndHashCode(callSuper=false)
@Getter
@AllArgsConstructor
@Jacksonized//permite la serializacion/des correcta de clases inmutables

/**
 * The Class FindPrototypeByIdQueryBuilder.
 */
@Builder
@Schema(name = "FindPrototypeByIdQuery", description = "query search a prototype on database")
public final class FindPrototypeByIdQuery extends BaseQuery{
    
	/** The id. */
	@NotBlank
	@NotNull(message = "id cannot be null")
    @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED,example = "01abct", defaultValue = "01abct", description = "Unique id of prototype that represent the owner of prototype")
	private final String id;
}
