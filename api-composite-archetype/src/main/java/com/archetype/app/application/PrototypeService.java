package com.archetype.app.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.archetype.app.domain.Prototype;
import com.archetype.app.domain.repository.IPrototypeRepository;
import com.archetype.base.core.exception.FunctionalException;
import com.archetype.base.core.exception.TechnicalRuntimeException;
import com.archetype.base.core.exception.model.GenericError;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PrototypeService implements IPrototypeService {
	
	@Autowired
	IPrototypeRepository repository;
	
	public Prototype get(String id) {
		try {
			return repository.findById(id);
		} catch (Exception e) {
//			log.error("Can't get the data from the repository");
			log.error(e.getMessage());
//			throw new ManagedException(502, "Check your database connection and if the repository have the necessary configuration: %s", e.getMessage());
			throw new TechnicalRuntimeException(GenericError.EXCEPTION_COM_DATABASE_ERROR);
		}
	}
	
	public List<Prototype> get() {
		try {
			return repository.findAll();
		} catch (Exception e) {
//			log.error("Can't get the data from the repository");
			log.error(e.getMessage());
//			throw new ManagedException(502, "Check your database connection and if the repository have the necessary configuration: %s", e.getMessage());
			throw new TechnicalRuntimeException(GenericError.EXCEPTION_COM_DATABASE_ERROR);
		}
	}

	public Prototype save(Prototype data) {
		try {
			return repository.save(data);
		} catch (Exception e) {
//			log.error("Can't save the data into the repository");
			log.error(e.getMessage());
//			throw new ManagedException(502, "Check your database connection and if the repository have the necessary configuration: %s", e.getMessage());
			throw new TechnicalRuntimeException(GenericError.EXCEPTION_COM_DATABASE_ERROR);
		}
	}

	@Override
	public Prototype update(String id, Prototype data)  {
		Prototype prototype=Prototype.builder().build();
		try {
			prototype=repository.update(id,data);
      	
		}catch (ObjectOptimisticLockingFailureException e) {
            log.warn("Savings has been updated before in concurrent transaction");
            prototype=repository.update(id,data);
//       	} catch (FunctionalException e) {
//			log.error(e.getMessage());
//			throw new TechnicalRuntimeException(e.getErrorCode(),e.getMessage(),e.getHttpCode());
//		    
		} catch (Exception e) {
			log.error("Can't update the data into the repository");
			log.error(e.getMessage());
			throw new TechnicalRuntimeException(GenericError.EXCEPTION_COM_DATABASE_ERROR,e);
		}
		return prototype;
	}

	@Override
	public Page<Prototype> findAllPage(Pageable pageable) {
		try {
			return repository.findAllPage(pageable);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new TechnicalRuntimeException(GenericError.EXCEPTION_COM_DATABASE_ERROR);
		}
	}
}