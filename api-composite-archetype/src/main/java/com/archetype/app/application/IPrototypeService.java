package com.archetype.app.application;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.archetype.app.domain.Prototype;
import com.archetype.base.core.exception.FunctionalException;

public interface IPrototypeService {
	
	public Prototype get(Long id);

	public List<Prototype> get();

	public Prototype save(Prototype data);
	
	public Prototype update(Long id,Prototype data) throws FunctionalException;
	
	public Page<Prototype>findAllPage(Pageable pageable);
}