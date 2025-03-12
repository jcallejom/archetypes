package com.archetype.app.domain.dto;

import com.archetype.base.core.model.response.BaseResponseVo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;


@AllArgsConstructor
@Jacksonized
@Builder
@Getter
@EqualsAndHashCode(callSuper = true)
@Schema(name = "PrototypeResponse", description = "Model represent a Prototype on database")
public class PrototypeResponse extends BaseResponseVo<PrototypeResponse> {
	@Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED,example = "1", defaultValue = "1", description = "Unique Id of example  on database")
	private final String id;
	@Schema(name = "column", requiredMode = Schema.RequiredMode.REQUIRED,example = "column", defaultValue = "column", description = "Unique column of example on external api")
	private final String column;
}
