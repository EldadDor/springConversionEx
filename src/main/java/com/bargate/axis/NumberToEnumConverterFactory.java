/*
 * User: eldad.Dor
 * Date: 07/10/13 12:20
 */
package com.idi.astro.server.spring;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;


@Component
public class NumberToEnumConverterFactory implements ConverterFactory<Number, Enum<?>> {

	@Override
	public <T extends Enum<?>> Converter<Number, T> getConverter(Class<T> targetType) {
		return new NumberToEnum<T>(targetType);
	}

	private class NumberToEnum<T extends Enum> implements Converter<Number, T> {

		private final Class<T> numberType;

		private NumberToEnum(Class<T> enumType) {
			this.numberType = enumType;
		}

		@Override
		public T convert(Number source) {
			if (NumberUtils.isNumber(String.valueOf(source))) {
				try {
					final String enumFieldFromInstance = findEnumFieldFromInstance(source);
					return ((T) Enum.valueOf(this.numberType,enumFieldFromInstance));
				} catch (Throwable throwable) {
					return null;
				}
			} else {
				return null;
			}
		}


		private String findEnumFieldFromInstance(Number source) {
			for (final Field field : numberType.getFields()) {
				try {
					final Object number = ReflectionUtils.invokeMethod(numberType.getDeclaredMethods()[0],getInstance(field.getName(), numberType));
					if (number.toString().equals(String.valueOf(source).trim())) {
						return field.getName();
					}
				} catch (Throwable throwable) {
				}
			}
			return "";
		}

		public <T extends Enum<T>> T getInstance(final String value, final Class<T> enumClass) {
			return Enum.valueOf(enumClass, value);
		}

	}

}