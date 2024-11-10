package com.archetype.app.query.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// TODO: Auto-generated Javadoc
/**
 * The Interface PrototypeRepository.
 */
public interface PrototypeRepository extends JpaRepository<PrototypeEntity, String> {
	
	/**
	 * Find by prototype code.
	 *
	 * @param code the code
	 * @return the optional
	 */
	Optional<PrototypeEntity> findByPrototypeCode(String code);
}
