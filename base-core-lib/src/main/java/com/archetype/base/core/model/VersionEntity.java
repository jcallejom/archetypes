/**
 * Ayesa
 * @author jcallejo
 */


package com.archetype.base.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

// TODO: Auto-generated Javadoc
/**
 * The Class VersionEntity.
 */
@MappedSuperclass
public abstract class VersionEntity implements BaseEntity {
	
	/** The version. */
	@Version
	@Column(name = "version")
	protected long version;

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public long getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version the new version
	 */
	public void setVersion(final long version) {
		this.version = version;
	}



}
