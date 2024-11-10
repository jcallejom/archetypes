package com.archetype.app.query.infrastructure.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archetype.app.common.events.PrototypeClosedEvent;
import com.archetype.app.common.events.PrototypeDayChangedEvent;
import com.archetype.app.common.events.PrototypeOpenedEvent;
import com.archetype.app.query.domain.PrototypeEntity;
import com.archetype.app.query.domain.PrototypeRepository;
import com.archetype.app.query.mapper.PrototypeMapperVo;

// TODO: Auto-generated Javadoc
/**
 * The Class PrototypeEventHandler.
 */
@Service
public class PrototypeEventHandler implements EventHandler {
	
	/** The repository. */
	@Autowired
	private PrototypeRepository repository;
	
	/** The mapper. */
	@Autowired
	private PrototypeMapperVo mapper;
	
	/**
	 * On.
	 *
	 * @param event the event
	 */
	@Override
	public void on(PrototypeOpenedEvent event) {
//		var prototype= mapper.toDomain(event);
//		prototype.getPrototypeNumbers().forEach(number -> number.setPrototypeEntity(prototype));
//		repository.save(prototype);
		repository.save(mapper.toDomain(event));
	}

	/**
	 * On.
	 *
	 * @param event the event
	 */
	@Override
	public void on(PrototypeDayChangedEvent event) {
		var prototype=repository.findById(event.getId());
		if(prototype.isEmpty()) {
			return;
		}
		prototype.get().setPrototypeDay(event.getPrototypeDay());
		repository.save(prototype.get());
	}

	/**
	 * On.
	 *
	 * @param event the event
	 */
	@Override
	public void on(PrototypeClosedEvent event) {
		repository.deleteById(event.getId());
	}

}
