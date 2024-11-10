package com.archetype.app.query.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Builder;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.archetype.app.common.events.PrototypeOpenedEvent;
import com.archetype.app.common.vo.PrototypeNumberVo;
import com.archetype.app.query.domain.PrototypeEntity;
import com.archetype.app.query.domain.PrototypeNumberEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class PrototypeMapperVoold.
 */
@Mapper(componentModel = "spring" , collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE
,nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class PrototypeMapperVoold {
	
/**
 * Flusto before.
 *
 * @param prototypeEntity the prototype entity
 * @param prototypeNumberEntity the prototype number entity
 */
//	@BeanMapping(builder = @Builder(disableBuilder = true))
	@BeforeMapping
	public void flustoBefore(PrototypeEntity prototypeEntity , @MappingTarget PrototypeNumberEntity prototypeNumberEntity ) {
		prototypeEntity.getPrototypeNumbers().forEach(pn -> pn.setPrototypeEntity(prototypeEntity));
	}

/**
 * To domain.
 *
 * @param prototype the prototype
 * @return the prototype entity
 */
//	@BeanMapping(builder = @Builder(disableBuilder = true))
	public abstract PrototypeEntity toDomain (PrototypeOpenedEvent prototype);
////	@BeanMapping(builder = @Builder(disableBuilder = true))
//	public abstract PrototypeNumberEntity toDomain (PrototypeNumberVo prototype);
	
/**
 * Flusto entity.
 *
 * @param prototypeEntity the prototype entity
 * @param prototypeNumberEntity the prototype number entity
 */
//	@BeanMapping(builder = @Builder(disableBuilder = true))
	@AfterMapping
	public void flustoEntity(PrototypeEntity prototypeEntity , PrototypeNumberEntity prototypeNumberEntity ) {
		prototypeEntity.getPrototypeNumbers().forEach(pn -> pn.setPrototypeEntity(prototypeEntity));

	}
}
