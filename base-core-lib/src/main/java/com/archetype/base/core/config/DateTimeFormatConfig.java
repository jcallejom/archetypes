/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.config;

import java.time.ZonedDateTime;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// TODO: Auto-generated Javadoc
/**
 * Configure the converters to use the ISO format for dates by default.
 */
@Configuration
public class DateTimeFormatConfig implements WebMvcConfigurer {

	/**
	 * Adds the formatters.
	 *
	 * @param registry the registry
	 */
	@Override
	public void addFormatters(final FormatterRegistry registry) {
		registry.addConverter(new StringToZonedDateTimeConverter<ZonedDateTime>());
		registry.addConverterFactory(new StringToZonedDateTimeConverterFactory());
	}


}
