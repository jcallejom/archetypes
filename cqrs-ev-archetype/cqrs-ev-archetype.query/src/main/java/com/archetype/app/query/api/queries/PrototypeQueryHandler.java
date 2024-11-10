package com.archetype.app.query.api.queries;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archetype.app.query.domain.PrototypeRepository;
import com.archetype.cqrsev.core.domain.BaseEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class PrototypeQueryHandler.
 */
@Service
public class PrototypeQueryHandler implements QueryHandler {
	
	/** The repository. */
	@Autowired
	private PrototypeRepository repository;
	
	/**
	 * Handle.
	 *
	 * @param query the query
	 * @return the list
	 */
	@Override
	public List<BaseEntity> handle(FindPrototypeByIdQuery query) {
		var prototype=repository.findById(query.getId());
		if(prototype.isEmpty())
			return null;
		List<BaseEntity> listado= new ArrayList<BaseEntity>();
		listado.add(prototype.get());
		return listado;
	}

	/**
	 * Handle.
	 *
	 * @param query the query
	 * @return the list
	 */
	@Override
	public List<BaseEntity> handle(FindPrototypeByPrototypeCodeQuery query) {
		var prototype=repository.findByPrototypeCode(query.getPrototypeCode());
		if(prototype.isEmpty())
			return null;
	
		List<BaseEntity> listado= new ArrayList<BaseEntity>();
		listado.add(prototype.get());
		return listado;
	}

}
