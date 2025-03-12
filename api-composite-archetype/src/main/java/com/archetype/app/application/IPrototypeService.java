package com.archetype.app.application;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.archetype.app.domain.Prototype;
import com.archetype.base.core.exception.FunctionalException;

public interface IPrototypeService {
	
	public Prototype get(String id);

	public List<Prototype> get();

	public Prototype save(Prototype data);
	
	public Prototype update(String id,Prototype data);
	
	public Page<Prototype>findAllPage(Pageable pageable);
}