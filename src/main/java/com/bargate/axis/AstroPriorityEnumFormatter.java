/*
 * User: eldad.Dor
 * Date: 09/10/13 17:02
 */

package com.bargate.axis;

import com.bargate.axis.enums.AstroPriorityHandlingEnum;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;


public class AstroPriorityEnumFormatter implements Formatter<AstroPriorityHandlingEnum> {

	@Override
	public AstroPriorityHandlingEnum parse(String text, Locale locale) throws ParseException {
		final Number numberType = Integer.valueOf(text);
		switch (numberType.intValue()) {
			case 1:
				return AstroPriorityHandlingEnum.HIGH_ONLY;
			case 0:
				return AstroPriorityHandlingEnum.LOW_ONLY;
			case 2:
				return AstroPriorityHandlingEnum.HIGH_LOW;
		}
		return AstroPriorityHandlingEnum.HIGH_LOW;
	}

	@Override
	public String print(AstroPriorityHandlingEnum object, Locale locale) {
		return String.valueOf(object.getValue());
	}
}