/**
 * Ayesa
 * @author jcallejo
 */


package com.archetype.base.core.audit.config;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManagerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class AuditHistoryConfiguration.
 */
@Configuration
public class AuditHistoryConfiguration {

    /** The entity manager factory. */
    private final EntityManagerFactory entityManagerFactory;

	/**
	 * Instantiates a new audit history configuration.
	 *
	 * @param entityManagerFactory the entity manager factory
	 */
	AuditHistoryConfiguration(final EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Audit reader.
     *
     * @return the audit reader
     */
    @Bean
    AuditReader auditReader() {
        return AuditReaderFactory.get(entityManagerFactory.createEntityManager());
    }
}
