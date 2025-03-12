package com.archetype.app.infrastructure.in.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.archetype.app.application.PrototypeService;
import com.archetype.app.domain.Prototype;
import com.archetype.app.domain.dto.PrototypeRequest;
import com.archetype.app.domain.dto.PrototypeResponse;
import com.archetype.app.domain.mapper.PrototypeMapperVo;
import com.archetype.base.core.exception.FunctionalException;
import com.archetype.base.core.utils.PaginationUtil;

@RestController
@RequestMapping(path = "/v1/prototype")
public class PrototypeController implements IPrototypeController { 
	
	@Autowired
	private PrototypeService service;
	

	@Autowired
	private PrototypeMapperVo mapperVo;
//	@PreAuthorize("hasAuthority('ROLE_BASIC_USER')")
	@GetMapping("/test")
	public ResponseEntity<String> test(){
		return ResponseEntity.ok("The application is working");
	}
	@PreAuthorize("hasAuthority('ROLE_BASIC_USER')")
	@GetMapping()
	public ResponseEntity<List<PrototypeResponse>> getAll(){
		return ResponseEntity.ok(
				service
					.get()
					.stream()
					.map(mapperVo::toResponse)
					.collect(Collectors.toList())
					);
	}
	@PreAuthorize("hasAuthority('ROLE_BASIC_USER')")
	@GetMapping("/{id}")
	public ResponseEntity<PrototypeResponse> findById(@PathVariable(value="id") String id){
		return ResponseEntity.ok(mapperVo.toResponse(service.get(id)));
	}
	@PreAuthorize("hasAuthority('ROLE_BASIC_USER')")
	@PostMapping()
	public ResponseEntity<PrototypeResponse> post(@RequestBody PrototypeRequest data){

		return ResponseEntity.ok(
				mapperVo.toResponse(
						service.save(mapperVo.toDomain(data))
						)
				);
	}
	@PreAuthorize("hasAuthority('ROLE_BASIC_USER')")
	@PutMapping("/{id}")
	public ResponseEntity<PrototypeResponse> put(@PathVariable(value="id") String id, @RequestBody PrototypeRequest data)
			throws FunctionalException {
		return ResponseEntity.ok(
				mapperVo.toResponse(
						service.update(id,mapperVo.toDomain(data))
						)
				);
	}
	@PreAuthorize("hasAuthority('ROLE_BASIC_USER')")
	@GetMapping("/findall")
	public ResponseEntity<List<PrototypeResponse>> findAllPage(Pageable pageable) {
		Page<Prototype> page = service.findAllPage(PaginationUtil.parsePageableSort(Prototype.class, pageable));
		HttpHeaders headers =PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return new ResponseEntity<>( mapperVo.toResponse(page.getContent()) ,headers, HttpStatus.OK);
	}
	
}
