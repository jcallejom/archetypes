/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.audit.model;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import com.archetype.base.core.audit.listener.AuditListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;


// TODO: Auto-generated Javadoc
/**
 * The Class Revision.
 */
@Entity
@Table(name = "REVINFO")
@RevisionEntity(AuditListener.class)
public class Revision extends DefaultRevisionEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The user name. */
	@Size(max = 100)
    @Column(name = "USER_NAME", length = 100)
	private String userName;

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(final String userName) {
		this.userName = userName;
	}
}
