package com.archetype.app.domain.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.archetype.app.domain.Prototype;
import com.archetype.app.domain.mapper.PrototypeMapperDomain;
import com.archetype.app.infrastructure.out.db.jpa.entity.PrototypeEntity;
import com.archetype.app.infrastructure.out.db.jpa.repository.IPrototypeEntityRepository;
import com.archetype.app.shared.PrototypeErrors;
import com.archetype.base.core.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class PrototypeEntityRepository implements IPrototypeRepository{
	
	@Autowired
	private IPrototypeEntityRepository jpaRepository;

	@Autowired
	private PrototypeMapperDomain mapperDomain;

	@Override
	public Prototype save(Prototype data) {

		PrototypeEntity entity = mapperDomain.toDomain(data);
		entity = jpaRepository.save(entity);
		

		return mapperDomain.toResponse(entity);
	}

	@Override
	public List<Prototype> findAll() {
		List<Prototype> response = jpaRepository
			.findAll()
			.stream()
			.map(mapperDomain::toResponse)
			.collect(Collectors.toList());
		
		return response;
	}

	@Override
	public Prototype findById(String id) {
		return mapperDomain.toResponse(jpaRepository.findById(id).orElse(null));
//		return mapperDomain.toResponse(jpaRepository.findById(id).orElseThrow(
//				   ()-> new NotFoundException(PrototypeErrors.EXCEPTION_PROTOTYPE_ELEMENT_NOT_FOUND.getCode(),
//						   String.format(PrototypeErrors.EXCEPTION_PROTOTYPE_ELEMENT_NOT_FOUND.getMessage(), id)
//						   )));
	
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Prototype update(String id, Prototype data) {
		PrototypeEntity updateEntity = mapperDomain.toDomain(data);
		PrototypeEntity findEntity=jpaRepository.findById(id).orElseThrow(
		   ()-> new NotFoundException(PrototypeErrors.EXCEPTION_PROTOTYPE_ELEMENT_NOT_FOUND.getCode(),
				   String.format(PrototypeErrors.EXCEPTION_PROTOTYPE_ELEMENT_NOT_FOUND.getMessage(), id)
				   ));
		findEntity= mapperDomain.clon(updateEntity, findEntity);
		
		return mapperDomain.toResponse(jpaRepository.save(findEntity));
	}

	@Override
	public Page<Prototype> findAllPage(Pageable pageable) {
	
		return jpaRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()))
			   .map(mapperDomain::toResponse);
	}
	
	

}
