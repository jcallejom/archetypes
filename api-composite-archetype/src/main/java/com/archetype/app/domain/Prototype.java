package com.archetype.app.domain;

import com.archetype.base.core.model.dto.BaseVo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode(callSuper = true)
public final class Prototype extends BaseVo{

	private final String id;
	
	
	private final String campo;
}
