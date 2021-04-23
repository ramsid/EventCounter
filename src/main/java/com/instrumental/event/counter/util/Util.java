package com.instrumental.event.counter.util;

import com.instrumental.Enum.Frequency;

public class Util {

	public static int calulateDuration(Frequency frequency) {
		int interval = 1;
		if (frequency == null)
			frequency = Frequency.INVALIDFREQUENCY;

		switch (frequency) {
		case MINUTES:
			interval = 60;
			return interval;
		case SECONDS:
			return interval;
		default:
			throw new IllegalArgumentException("Not a valid Frequency");
		}

	}
}
