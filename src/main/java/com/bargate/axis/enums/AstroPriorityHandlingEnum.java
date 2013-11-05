/*
 * User: eldad.Dor
 * Date: 07/10/13 10:42
 
 *
 * Copyright (2005) IDI. All rights reserved.
 * This software is a proprietary information of Israeli Direct Insurance.
 * Created by IntelliJ IDEA. 
 */
package com.bargate.axis.enums;

/**
 *
 */
public enum AstroPriorityHandlingEnum {

	HIGH_ONLY(1), LOW_ONLY(0), HIGH_LOW(2);
	private int value;

	AstroPriorityHandlingEnum(int priority) {
		this.value = priority;
	}

	public static AstroPriorityHandlingEnum getPriorityHandlingEnum(int priority) {
		switch (priority) {
			case 1:
				return HIGH_ONLY;
			case 0:
				return LOW_ONLY;
			case 2:
				return HIGH_LOW;
		}
		return null;
	}

	public int getValue() {
		return value;
	}


}