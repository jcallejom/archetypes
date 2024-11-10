package com.archetype.app.cmd.domain.mappers;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.archetype.app.cmd.api.command.ChangePrototypeDayCommand;
import com.archetype.app.cmd.api.command.ClosePrototypeCommand;
import com.archetype.app.cmd.api.command.OpenPrototypeCommand;
import com.archetype.app.common.events.PrototypeClosedEvent;
import com.archetype.app.common.events.PrototypeDayChangedEvent;
import com.archetype.app.common.events.PrototypeOpenedEvent;

// TODO: Auto-generated Javadoc
/**
 * The Interface PrototypeMapperDomain.
 */
@Mapper(componentModel = "spring" ,
collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE
,nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PrototypeMapperDomain {
	public PrototypeMapperDomain mapperInstance=Mappers.getMapper(PrototypeMapperDomain.class);	
	/**
	 * To domain.
	 *
	 * @param command the command
	 * @return the prototype opened event
	 */

	@Mapping(source = "id", target="id", defaultExpression = "java( java.util.UUID.randomUUID().toString() )")
	@Mapping(target = "prototypeDay", dateFormat = "dd/MM/yyyy")
//	@MapMapping(valueDateFormat = "dd.MM.yyyy")
	PrototypeOpenedEvent toDomain(OpenPrototypeCommand command);
	
	/**
	 * To domain.
	 *
	 * @param command the command
	 * @param id the id
	 * @return the prototype day changed event
	 */
	@Mapping(target = "prototypeDay", dateFormat = "dd/MM/yyyy")
	@Mapping(source = "id", target = "id")
	PrototypeDayChangedEvent toDomain(ChangePrototypeDayCommand command,String id);

	
	/**
	 * To domain.
	 *
	 * @param id the id
	 * @return the prototype closed event
	 */
	PrototypeClosedEvent toDomain(String id);
	 
	@Mapping(source = "id", target="id")
	ChangePrototypeDayCommand clone(ChangePrototypeDayCommand source,String id);
	
	@Mapping(source = "id", target="id")
	ClosePrototypeCommand clone(ClosePrototypeCommand source,String id);


}
