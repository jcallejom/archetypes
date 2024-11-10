package com.archetype.app.domain;

import com.archetype.base.core.model.dto.BaseVo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Prototype extends BaseVo{

	Long id;
	
	String column;
}
