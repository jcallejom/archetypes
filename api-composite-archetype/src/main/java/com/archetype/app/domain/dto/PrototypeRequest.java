package com.archetype.app.domain.dto;

import com.archetype.app.domain.Prototype;
import com.archetype.base.core.model.request.BaseRequestVo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;


@AllArgsConstructor
@Jacksonized
@Builder
@Getter
@EqualsAndHashCode(callSuper = true)
@Schema(name = "PrototypeRequest", description = "Model represent a Prototype on database")
public final class PrototypeRequest extends BaseRequestVo<PrototypeRequest>{
	
//	@JsonProperty(required = true)
	@NotNull@NotBlank
	@Schema(name = "campo", requiredMode = Schema.RequiredMode.REQUIRED,example = "column", defaultValue = "column", description = "Unique column of example on external api")
	private final String campo;
}
