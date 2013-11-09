/*
 * User: eldad.Dor
 * Date: 10/10/13 12:20
 */
package com.bargate.axis.conversion;

import com.bargate.axis.enums.CharacterPriorityHandlingEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.support.ConversionServiceFactory;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;


@Component
public class PriorityEnumPropertyTypeEditor extends PropertyEditorSupport implements ApplicationContextAware {
	private static final Log log = LogFactory.getLog(PriorityEnumPropertyTypeEditor.class);

	private BeanFactory beanFactory;

	private CharacterPriorityHandlingEnum convertViaCustomConverter(Number value) {
//		final DefaultConversionService defaultConversionService = new DefaultConversionService();
//		defaultConversionService.addConverter(new NumberToEnumConverterFactory().getConverter(CharacterPriorityHandlingEnum.class));
		final GenericConversionService conversionService = ConversionServiceFactory.createDefaultConversionService();
		conversionService.addConverterFactory(new NumberToEnumConverterFactory());

		final CharacterPriorityHandlingEnum characterPriorityHandlingEnum = conversionService.convert(value, CharacterPriorityHandlingEnum.class);
		log.info("convertViaCustomConverter(): value=" + value.intValue() + " converted=" + characterPriorityHandlingEnum);
		return characterPriorityHandlingEnum;
	}

	@Override
	public String getAsText() {
		return super.getValue().toString();
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		final Number numberType = Integer.valueOf(text);
		super.setValue(convertViaCustomConverter(numberType));
	}

	@Override
	public Object getValue() {
		return super.getValue();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.beanFactory = applicationContext;
	}
}
