/*
 * User: eldad.Dor
 * Date: 10/10/13 09:39
 */
package com.bargate.axis.conversion;

import com.bargate.axis.enums.CharacterPriorityHandlingEnum;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

public class AstroFormattingConvertersFactory extends FormattingConversionServiceFactoryBean {

	@Override
	public void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		registry.addFormatterForFieldType(CharacterPriorityHandlingEnum.class, new AstroPriorityEnumFormatter());
	}
}