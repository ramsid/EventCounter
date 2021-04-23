package com.instrumental.Enum;

/**
 * Frequency of duration supported.
 */
public enum Frequency {

	SECONDS("seconds"), MINUTES("MINUTES"), INVALIDFREQUENCY("INVALIDFREQUENCY");

	private String frequency;

	Frequency(String frequency) {
		this.frequency = frequency;
	}

	public String getFrequency() {
		return frequency;
	}

	@Override
	public String toString() {
		return String.valueOf(frequency);
	}
}
