package com.archetype.app.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.archetype.app.domain.Prototype;
import com.archetype.app.domain.repository.IPrototypeRepository;

@ExtendWith(MockitoExtension.class)
public class PrototypeServiceTests {

	private Prototype domainObj;
	
	@Mock
	private IPrototypeRepository repository;
	
	@InjectMocks
	private PrototypeService service;
	
//	@InjectMocks
//	ExampleMapper mapper;
	
//	@InjectMocks
//	@Mock
//	PrototypeMapperDomain mapper;
	
	@BeforeEach
	void init() {
//		domainObj = mapper.fromEntity(MockTable.dummyExampleTable());
		domainObj = Prototype.builder().id("1L").campo("c2").build();
	}
	
	@Test
	public void findAll() {
		List<Prototype> expected = List.of(domainObj);
		when(repository.findAll()).thenReturn(expected);
	
		List<Prototype> result = repository.findAll();
		
		assertEquals(expected.size(), result.size());
		assertEquals(expected.get(0).getCampo(), result.get(0).getCampo());
	}
	
	@Test
	public void findById() {
		Prototype expected = domainObj;
		
		when(repository.findById("1L")).thenReturn(expected);
		
		Prototype result = repository.findById("1L");

		assertEquals(expected.getId(), result.getId());
		assertEquals(expected.getCampo(), result.getCampo());
	}
	
}
