/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

// TODO: Auto-generated Javadoc
/**
 * Configuration class for Internationalization
 *  ISO639
 *   text_es.properties
 *   text_en.properties
 *  ISO3166
 *   text_es_es.properties
 *   text_es_mx.properties
 * 
 */
@Configuration
public class LocaleConfig implements WebMvcConfigurer {

	/**
	 * Locale resolver.
	 *
	 * @return the locale resolver
	 */
	@Bean(name = "localeResolver")
	public LocaleResolver localeResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.forLanguageTag("es-ES"));
//		sessionLocaleResolver.setDefaultLocale(Locale.forLanguageTag("es_ES"));
		return sessionLocaleResolver;
	}
	
	/**
	 * an interceptor bean that will switch to a new locale based on the value of
	 * the language parameter appended to a request:.
	 *
	 * @param registry the registry
	 * language should be the name of the request param i.e
	 *           localhost:8081/api/get-greeting?language=fr
	 *           <p>
	 *           Note: All requests to the backend needing Internationalization
	 *           should have the "language" request param
	 */
	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		registry.addInterceptor(localeChangeInterceptor);
	}

}
