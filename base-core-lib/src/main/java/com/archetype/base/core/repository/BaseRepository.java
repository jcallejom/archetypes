/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * The Interface BaseRepository.
 *
 * @param <T> the generic type
 * @param <ID> the generic type
 */
public interface BaseRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}
