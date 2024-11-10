package com.archetype.app.query.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.archetype.app.common.events.PrototypeOpenedEvent;
import com.archetype.app.query.api.vo.PrototypeResponse;
import com.archetype.app.query.domain.PrototypeEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface PrototypeMapperVo.
 */
@Mapper(componentModel = "spring" , collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE
,nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PrototypeMapperVo {
	
	/**
	 * To domain.
	 *
	 * @param prototype the prototype
	 * @return the prototype entity
	 */
	@BeanMapping(builder = @Builder(disableBuilder = true))
	 PrototypeEntity toDomain (PrototypeOpenedEvent prototype);

/**
 * To response.
 *
 * @param prototype the prototype
 * @return the prototype response
 */
//	@BeanMapping(builder = @Builder(disableBuilder = true))
	  PrototypeResponse toResponse(PrototypeEntity prototype);

/**
 * To response.
 *
 * @param prototype the prototype
 * @return the list
 */
//	@BeanMapping(builder = @Builder(disableBuilder = true))
	  List<PrototypeResponse> toResponse(List<PrototypeEntity> prototype);

	/**
	 * Flushto childs.
	 *
	 * @param prototype the prototype
	 * @param prototypeEntity the prototype entity
	 */
	@AfterMapping
	public default void flushtoChilds(PrototypeOpenedEvent prototype, @MappingTarget PrototypeEntity prototypeEntity) {
		 prototypeEntity.getPrototypeNumbers().forEach(pn -> pn.setPrototypeEntity(prototypeEntity));
//		 return prototypeEntity;
	}
}
