/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.config;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class StringToZonedDateTimeConverter.
 *
 * @param <T> the generic type
 */
@Component
public class StringToZonedDateTimeConverter<T extends ZonedDateTime> implements Converter<String, T> {

	/**
	 * Convert.
	 *
	 * @param source the source
	 * @return the t
	 */
	@Override
	public T convert(final String source) {
		ZoneId timezone = ZoneId.systemDefault();
		return (T) LocalDateTime.parse(source, DateTimeFormatter.ISO_DATE_TIME).atZone(timezone);
	}


}
