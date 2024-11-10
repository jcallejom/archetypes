/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.config;

import java.io.File;
import java.net.URL;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;


// TODO: Auto-generated Javadoc
/**
 * The Class MessageSourceConfiguration.
 */
@Configuration
public class MessageSourceConfiguration {
	
	/** The logger. */
	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	/** The app name. */
	@Value("${spring.application.name}")
	private String appName;
	
	/** The cachetime. */
	private final int cachetime = 30;
	
	/**
	 * Message source.
	 *
	 * @param env the env
	 * @return the message source
	 */
	@Bean
	public MessageSource messageSource(final Environment env) {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		Profiles profiles = Profiles.of("local","docker");
		if (env.acceptsProfiles(profiles)) {
			messageSource.setBasenames("classpath:messages", "classpath:messages-global",
					"classpath:i18n"+ File.separator + "messages-"+ appName );
		}
		else { // parte dinamicos
			messageSource.setBasenames("classpath:messages", "classpath:messages-global",
					"classpath:i18n"+ File.separator + "messages-"+ appName ,
					"classpath:i18n"+ File.separator + "messages-commons");

		}

		messageSource.setCacheSeconds(cachetime); // recarga de los ficheros en segundos
		return messageSource;
	}
}
