package com.archetype.app.domain.mapper;

import java.util.List;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

import com.archetype.app.domain.Prototype;
import com.archetype.app.domain.dto.PrototypeRequest;
import com.archetype.app.domain.dto.PrototypeResponse;
import com.archetype.base.core.mapper.BaseMapperVo;

@Mapper(componentModel = "spring" , collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public interface PrototypeMapperVo extends BaseMapperVo<PrototypeRequest,Prototype,PrototypeResponse>{

	Prototype toDomain (PrototypeRequest example);
	
	PrototypeResponse toResponse(Prototype example);
	
	List<PrototypeResponse> toResponse(List<Prototype> example);

}
