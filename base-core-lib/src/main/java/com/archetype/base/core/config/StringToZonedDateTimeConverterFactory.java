/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base.core.config;

import java.time.ZonedDateTime;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating StringToZonedDateTimeConverter objects.
 */
@Component
public class StringToZonedDateTimeConverterFactory implements ConverterFactory<String, ZonedDateTime> {

	/**
	 * Gets the converter.
	 *
	 * @param <T> the generic type
	 * @param targetType the target type
	 * @return the converter
	 */
	@Override
	public <T extends ZonedDateTime> Converter<String, T> getConverter(final Class<T> targetType) {
		// TODO Auto-generated method stub
		return new StringToZonedDateTimeConverter();
	}

}
