package com.archetype.app.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.archetype.app.domain.Prototype;
import com.archetype.base.core.exception.FunctionalException;

public interface IPrototypeRepository {
	
	public Prototype save(Prototype data);

	public List<Prototype> findAll();

	public Prototype findById(Long id);
	
	public Prototype update(Long id, Prototype data) throws FunctionalException;
	
	public Page<Prototype> findAllPage(Pageable page);
}
