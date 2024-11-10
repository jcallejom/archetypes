/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.audit.model;

// TODO: Auto-generated Javadoc
/**
 * The Class RevisionEntityInfo.
 *
 * @param <T> the generic type
 */
public class RevisionEntityInfo<T> {

	/** The entity name. */
	private String entityName;

	/** The entity. */
	private T entity;

	/** The revision info. */
	private Revision revisionInfo;

	/**
	 * Gets the entity.
	 *
	 * @return the entity
	 */
	public T getEntity() {
		return entity;
	}

	/**
	 * Sets the entity.
	 *
	 * @param entity the new entity
	 */
	public void setEntity(final T entity) {
		this.entity = entity;
	}

	/**
	 * Gets the revision info.
	 *
	 * @return the revision info
	 */
	public Revision getRevisionInfo() {
		return revisionInfo;
	}

	/**
	 * Sets the revision info.
	 *
	 * @param revisionInfo the new revision info
	 */
	public void setRevisionInfo(final Revision revisionInfo) {
		this.revisionInfo = revisionInfo;
	}

	/**
	 * Gets the entity name.
	 *
	 * @return the entity name
	 */
	public String getEntityName() {
		return entityName;
	}

	/**
	 * Sets the entity name.
	 *
	 * @param entityName the new entity name
	 */
	public void setEntityName(final String entityName) {
		this.entityName = entityName;
	}

}
