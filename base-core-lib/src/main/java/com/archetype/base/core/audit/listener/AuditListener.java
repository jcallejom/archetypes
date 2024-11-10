/**
 * Ayesa
 * @author jcallejo
 */


package com.archetype.base.core.audit.listener;

import org.hibernate.envers.RevisionListener;

import com.archetype.base.core.audit.model.Revision;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving audit events.
 * The class that is interested in processing a audit
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addAuditListener</code> method. When
 * the audit event occurs, that object's appropriate
 * method is invoked.
 *
 * @see  com.archetype.base.core.audit.model.Revision
 */
public class AuditListener implements RevisionListener {

	/**
	 * New revision.
	 *
	 * @param revison the revison
	 */
	@Override
	public void newRevision(final Object revison) {
		Revision rev = (Revision) revison;
		rev.setUserName("SIN_IDENTIFICAR");
//		
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		
//		if (authentication != null) {
//			rev.setUserName(authentication.getName());
//		} else {
//			rev.setUserName("SIN_IDENTIFICAR");
//		}
//		
		
	}

}
