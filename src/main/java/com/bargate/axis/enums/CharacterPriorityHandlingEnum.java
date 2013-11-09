/*
 * User: eldad.Dor
 * Date: 07/10/13 10:42
*/
package com.bargate.axis.enums;

/**
 *
 */
public enum CharacterPriorityHandlingEnum {

	THE_GOOD(1), THE_BAD(0), THE_UGLY(2);
	private int value;

	CharacterPriorityHandlingEnum(int priority) {
		this.value = priority;
	}

	public static CharacterPriorityHandlingEnum getPriorityHandlingEnum(int priority) {
		switch (priority) {
			case 1:
				return THE_GOOD;
			case 0:
				return THE_BAD;
			case 2:
				return THE_UGLY;
		}
		return null;
	}

	public int getValue() {
		return value;
	}


}