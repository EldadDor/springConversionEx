/*
 * User: eldad.Dor
 * Date: 07/10/13 12:20
 */
package com.bargate.axis.conversion;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


@Component(value = "NumberToEnumConverterFactory")
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
					return (T) findEnumFieldFromInstance(source);
				} catch (Throwable throwable) {
					return null;
				}
			} else {
				return null;
			}
		}


		private Enum findEnumFieldFromInstance(Number source) throws Throwable {
			for (final Field field : numberType.getFields()) {
				final Enum instance = getInstance(field.getName(), numberType);
				final Object number = ReflectionUtils.invokeMethod(numberType.getDeclaredMethods()[0], instance);
				final Method method = number.getClass().getComponentType().getMethod("getValue");
				final Object enumValue = ReflectionUtils.invokeMethod(method, instance);
				if (enumValue.toString().equals(String.valueOf(source).trim())) {
					return instance;
				}
			}
			return null;
		}

		public <E extends Enum<E>> E getInstance(final String value, final Class<T> enumClass) {
			return (E) Enum.valueOf(enumClass, value);
		}

	}

}