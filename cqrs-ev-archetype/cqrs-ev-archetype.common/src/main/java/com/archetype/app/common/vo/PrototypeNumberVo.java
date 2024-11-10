package com.archetype.app.common.vo;

import org.springframework.validation.annotation.Validated;

import com.archetype.cqrsev.core.vo.BaseVo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

// TODO: Auto-generated Javadoc
/**
 * The Class PrototypeNumberVo.
 */
@Validated
@EqualsAndHashCode(callSuper=false)
@Getter
@AllArgsConstructor
@Jacksonized//permite la serializacion/des correcta de clases inmutables
@Builder
@Schema(name = "PrototypeNumberVo", description = "Vo represent a PrototypeNumber")
public final class PrototypeNumberVo implements BaseVo{
	
	/** The number. */
	@NotNull(message = "value cannot be null")
	@Schema(name = "value", requiredMode = Schema.RequiredMode.REQUIRED, description = "number of prototypeNumbers")
	private  final Integer value;
	
	/** The type. */
	@NotNull(message = "type cannot be null")
	@Schema(name = "type", requiredMode = Schema.RequiredMode.REQUIRED, description = "type of prototypeNumbers")
	private  final PrototypeType type;
	
}
