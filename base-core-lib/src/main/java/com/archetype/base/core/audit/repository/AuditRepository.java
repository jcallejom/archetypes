/**
 * Ayesa
 * @author jcallejo
 */


package com.archetype.base.core.audit.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;

import com.archetype.base.core.audit.model.Revision;
import com.archetype.base.core.audit.model.RevisionEntityInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class AuditRepository.
 *
 * @param <T> the generic type
 * @param <ID> the generic type
 */
public abstract class AuditRepository<T, ID> implements IAuditHistoryRepository<T, ID> {

	/** The domain class. */
	private Class<T> domainClass;

	/** The audit reader. */
	@Autowired
	private AuditReader auditReader;

	/**
	 * Instantiates a new audit repository.
	 *
	 * @param domainClass the domain class
	 */
	public AuditRepository(final Class<T> domainClass) {
		this.domainClass = domainClass;

	}
	
	/**
	 * Revisions.
	 *
	 * @return the list
	 */
	@Override
	public List<RevisionEntityInfo<T>> revisions() {
	    List resultList = auditReader.createQuery().forRevisionsOfEntity(domainClass, false, true)
	                                 .getResultList();

		return processList(resultList);
	  }
	
	/**
	 * Revision.
	 *
	 * @param id the id
	 * @return the list
	 */
	@Override
	public List<RevisionEntityInfo<T>> revision(final Object id) {
	    List resultList = auditReader.createQuery().forRevisionsOfEntity(domainClass, false, true)
	                                 .add(AuditEntity.id().eq(id))
	                                 .getResultList();

	    return processList(resultList);
	  }


	/**
	 * All revisions for property changed.
	 *
	 * @param id the id
	 * @param propertyName the property name
	 * @return the list
	 */
	@Override
	public List<RevisionEntityInfo<T>> allRevisionsForPropertyChanged(final ID id, final String propertyName) {
		List<Object[]> audit = this.queryForPropertyChanged(id, propertyName);
		List<RevisionEntityInfo<T>> revisions = null;
		if (audit != null && !audit.isEmpty()) {
			revisions = this.processList(audit);
		}

		return revisions;
	}

	/**
	 * All entities for property changed.
	 *
	 * @param id the id
	 * @param propertyName the property name
	 * @return the list
	 */
	@Override
//	@SuppressWarnings("unchecked")
	public List<T> allEntitiesForPropertyChanged(final ID id, final String propertyName) {
		List<Object[]> audit = this.queryForPropertyChanged(id, propertyName);
		List<T> revs = null;
		if (audit != null && !audit.isEmpty()) {
			revs = audit.stream().map(s -> ((T) s[0])).collect(Collectors.toList());
		}
		return revs;
	}

	/**
	 * Query for property changed.
	 *
	 * @param id the id
	 * @param propertyName the property name
	 * @return the list
	 */
	private List<Object[]> queryForPropertyChanged(final ID id, final String propertyName) {
		AuditQuery q = auditReader.createQuery().forRevisionsOfEntity(domainClass, false, true);
		q.add(AuditEntity.id().eq(id));
		q.add(AuditEntity.property(propertyName).hasChanged());
		return q.getResultList();
	}

/**
 * Process list.
 *
 * @param resultList the result list
 * @return the list
 */
//	@SuppressWarnings({ "unchecked" })
	private List<RevisionEntityInfo<T>> processList(final List resultList) {
		List<RevisionEntityInfo<T>> info = new ArrayList<>();

		resultList.stream().forEach(r -> {
			if (r instanceof Object[]) {
				Object[] array = (Object[]) r;
				RevisionEntityInfo<T> revEntityInfo = new RevisionEntityInfo<>();
				T entity = (T) array[0];
				Revision rev = (Revision) array[1];
				revEntityInfo.setEntity(entity);
				revEntityInfo.setRevisionInfo(rev);
				revEntityInfo.setEntityName(entity.getClass().getSimpleName());
				info.add(revEntityInfo);
			}
		});

		return info;
	}

}
