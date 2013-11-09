/*
 * User: eldad.Dor
 * Date: 09/10/13 17:02
 */

package com.bargate.axis.conversion;

import com.bargate.axis.enums.CharacterPriorityHandlingEnum;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;


public class AstroPriorityEnumFormatter implements Formatter<CharacterPriorityHandlingEnum> {

	@Override
	public CharacterPriorityHandlingEnum parse(String text, Locale locale) throws ParseException {
		final Number numberType = Integer.valueOf(text);
		switch (numberType.intValue()) {
			case 1:
				return CharacterPriorityHandlingEnum.THE_GOOD;
			case 0:
				return CharacterPriorityHandlingEnum.THE_BAD;
			case 2:
				return CharacterPriorityHandlingEnum.THE_UGLY;
		}
		return CharacterPriorityHandlingEnum.THE_UGLY;
	}

	@Override
	public String print(CharacterPriorityHandlingEnum object, Locale locale) {
		return String.valueOf(object.getValue());
	}
}