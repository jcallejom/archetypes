package com.archetype.app.application;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.archetype.app.domain.Prototype;
import com.archetype.app.domain.mapper.PrototypeMapperDomain;
import com.archetype.app.domain.repository.IPrototypeRepository;
import com.archetype.app.infrastructure.out.db.jpa.entity.PrototypeEntity;
import com.archetype.app.infrastructure.out.db.jpa.repository.IPrototypeEntityRepository;
import com.archetype.app.shared.PrototypeErrors;
import com.archetype.base.core.exception.NotFoundException;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PrototypeServiceConcurrencyTests {

	private Prototype domainObj;
	@Autowired
	private PrototypeMapperDomain mapperDomain;	
	
	@Autowired//nos interesa probarolo de verdad
	private IPrototypeEntityRepository repositoryjpa;
	
//	@Mock
	@SpyBean
//	@Autowired
	private IPrototypeRepository repository;
	
	@Autowired
	private PrototypeService service;
	
//	@InjectMocks
//	ExampleMapper mapper;
	
//	@InjectMocks
//	@Mock
//	PrototypeMapperDomain mapper;
	
	@BeforeEach
	void init() {
//		domainObj = mapper.fromEntity(MockTable.dummyExampleTable());
		mapperDomain=Mappers.getMapper(PrototypeMapperDomain.class);
		domainObj = Prototype.builder().campo("c1").build();
	}
	
	
//	 @Test
	  void givenSavingsWhenUpdatedPrototypeThenVersionIsIncremented() {

	    final PrototypeEntity expected = repositoryjpa.save(mapperDomain.toDomain(domainObj));
	    assertEquals(0, expected.getVersion());
	    
	    var domainObjmock=Prototype.builder().campo("c2").build();
	    var domainObjmock2=Prototype.builder().campo("c3").build();
	    List<Prototype> updates=List.of(domainObjmock,domainObjmock2);
	    updates.forEach(saving -> {
	    	repository.update(expected.getId(), saving);
	    });
	    final PrototypeEntity prototypeUpdated = repositoryjpa.findById(expected.getId())
	        .orElseThrow(() ->  new NotFoundException(PrototypeErrors.EXCEPTION_PROTOTYPE_ELEMENT_NOT_FOUND.getCode(),
					   String.format(PrototypeErrors.EXCEPTION_PROTOTYPE_ELEMENT_NOT_FOUND.getMessage(), expected.getId())
					   ));
	    assertAll(
	        () -> assertEquals(2, prototypeUpdated.getVersion()),
	        () -> assertEquals(domainObjmock2.getCampo(), prototypeUpdated.getCampo())
	    );
	  }
//	  @Test
	  void givenSavingsWhenUpdatedPrototypeInAConcurrentWayVersionIsIncremented() throws InterruptedException {

	    final PrototypeEntity expected = repositoryjpa.save(mapperDomain.toDomain(domainObj));
	    assertEquals(0, expected.getVersion());
	    
	    
	    var domainObjmock=Prototype.builder().campo("c2").build();
	    var domainObjmock2=Prototype.builder().campo("c3").build();
	    List<Prototype> updates=List.of(domainObjmock,domainObjmock2);
	    final ExecutorService executor = Executors.newFixedThreadPool(updates.size());//size two
	    
	    updates.forEach(saving -> {
	    	executor.execute(()->repository.update(expected.getId(), saving));
	    });
	    executor.shutdown();
	    assertTrue(executor.awaitTermination(1, TimeUnit.MINUTES));
	    
	    final PrototypeEntity prototypeUpdated = repositoryjpa.findById(expected.getId())
	        .orElseThrow(() ->  new NotFoundException(PrototypeErrors.EXCEPTION_PROTOTYPE_ELEMENT_NOT_FOUND.getCode(),
					   String.format(PrototypeErrors.EXCEPTION_PROTOTYPE_ELEMENT_NOT_FOUND.getMessage(), expected.getId())
					   ));
	    assertAll(
	        () -> assertEquals(2, prototypeUpdated.getVersion()),
	        () -> assertEquals(domainObjmock2.getCampo(), prototypeUpdated.getCampo())
	    );
	  }
}
