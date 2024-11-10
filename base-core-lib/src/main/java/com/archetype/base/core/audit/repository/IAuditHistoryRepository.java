/**
 * Ayesa
 * @author jcallejo
 */


package com.archetype.base.core.audit.repository;

import java.util.List;

import com.archetype.base.core.audit.model.RevisionEntityInfo;

// TODO: Auto-generated Javadoc
/**
 * The Interface IAuditHistoryRepository.
 *
 * @param <T> the generic type
 * @param <ID> the generic type
 */
public interface IAuditHistoryRepository<T, ID> {

	/**
	 * All revisions for property changed.
	 *
	 * @param id the id
	 * @param propertyName the property name
	 * @return the list
	 */
	List<RevisionEntityInfo<T>> allRevisionsForPropertyChanged(ID id, String propertyName);

	/**
	 * All entities for property changed.
	 *
	 * @param id the id
	 * @param propertyName the property name
	 * @return the list
	 */
	List<T> allEntitiesForPropertyChanged(ID id, String propertyName);

	/**
	 * Revision.
	 *
	 * @param id the id
	 * @return the list
	 */
	List<RevisionEntityInfo<T>> revision(Object id);

	/**
	 * Revisions.
	 *
	 * @return the list
	 */
	List<RevisionEntityInfo<T>> revisions();

}
