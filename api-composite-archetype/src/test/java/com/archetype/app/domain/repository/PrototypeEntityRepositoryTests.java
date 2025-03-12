package com.archetype.app.domain.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.archetype.app.domain.Prototype;
import com.archetype.app.domain.mapper.PrototypeMapperDomain;
import com.archetype.app.infrastructure.out.db.jpa.entity.PrototypeEntity;
import com.archetype.app.infrastructure.out.db.jpa.repository.IPrototypeEntityRepository;
import com.archetype.app.shared.PrototypeErrors;
import com.archetype.base.core.exception.NotFoundException;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PrototypeEntityRepositoryTests {
//	private final List<PrototypeEntity> savings = Arrays.asList(2, 2);
	private PrototypeEntity domainObj;
	@Spy
	private PrototypeMapperDomain mapperDomain;	
	
//	@Mock
//	@Autowired//nos interesa probarolo de verdad
	@Spy
	private IPrototypeEntityRepository repositoryjpa;
	
//	@InjectMocks
	@SpyBean
	private PrototypeEntityRepository repository;
	
//	@InjectMocks
//	ExampleMapper mapper;
	
//	@InjectMocks
//	@Mock
//	PrototypeMapperDomain mapper;
	
	@BeforeEach
	void init() {
//		domainObj = mapper.fromEntity(MockTable.dummyExampleTable());
		mapperDomain=Mappers.getMapper(PrototypeMapperDomain.class);
	
		domainObj = PrototypeEntity.builder().id("1L").campo("c2").build();
	}
	
//	@Test
//	public void findAll() {
//		List<PrototypeEntity> expected = List.of(domainObj);
//		when(repositoryjpa.findAll()).thenReturn(expected);
//	
//		List<PrototypeEntity> result = repositoryjpa.findAll();
//		
//		assertEquals(expected.size(), result.size());
//		assertEquals(expected.get(0).getColumn(), result.get(0).getColumn());
//	}
//	
//	@Test
//	public void findById() {
////		Optional<PrototypeEntity> expected = Optional.of(domainObj);
//		PrototypeEntity expected = domainObj;
//		when(repositoryjpa.findById("1L")).thenReturn(Optional.of(domainObj));
//		
//		Optional<PrototypeEntity> result = repositoryjpa.findById("1L");
//
//		assertEquals(expected.getId(), result.get().getId());
//		assertEquals(expected.getColumn(), result.get().getColumn());
//	}
//	  @Test
	  void givenSavingsWhenUpdatedUserThenVersionIsIncremented() {
//		when(repositoryjpa.findById("1L")).thenReturn(Optional.of(domainObj));
//		when(repositoryjpa.save(Mockito.any())).thenReturn(domainObj);
		
	    final PrototypeEntity expected = repositoryjpa.save(new PrototypeEntity());
	    assertEquals(0, expected.getVersion());
	    
	    var domainObjmock=PrototypeEntity.builder().id("2L").campo("c3").build();
	    List<Prototype> savings=List.of(
	    		mapperDomain.toResponse(expected),
	    		mapperDomain.toResponse(domainObjmock));
	    savings.forEach(saving -> {
	    	repository.update(saving.getId(), saving);
	    });
	    final PrototypeEntity prototypeUpdated = repositoryjpa.findById(expected.getId())
	        .orElseThrow(() ->  new NotFoundException(PrototypeErrors.EXCEPTION_PROTOTYPE_ELEMENT_NOT_FOUND.getCode(),
					   String.format(PrototypeErrors.EXCEPTION_PROTOTYPE_ELEMENT_NOT_FOUND.getMessage(), expected.getId())
					   ));
	    assertAll(
	        () -> assertEquals(2, prototypeUpdated.getVersion()),
	        () -> assertEquals(domainObj.getCampo(), prototypeUpdated.getCampo())
	    );
	  }
}
