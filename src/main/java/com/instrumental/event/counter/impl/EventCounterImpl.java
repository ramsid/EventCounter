package com.instrumental.event.counter.impl;

import java.time.Instant;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.instrumental.Enum.Frequency;
import com.instrumental.event.counter.EventCounter;
import com.instrumental.event.counter.util.Util;

/**
 * Implementation for {@link EventCounter}
 */
public class EventCounterImpl implements EventCounter {
	private static final int MAX_TIME = 5;

	private Map<Long, Long> counterMap = Collections.synchronizedMap(new LinkedHashMap<Long, Long>() {

		private static final long serialVersionUID = 1868749192653363316L;

		@Override
		protected boolean removeEldestEntry(final Map.Entry<Long, Long> eldest) {
			Instant instant = Instant.now();
			long timeStampSeconds = instant.getEpochSecond();
			return eldest.getKey() < timeStampSeconds - MAX_TIME * 60;
		}
	});

	/**
	 * {@inheritDoc}
	 *
	 */
	public void hitEvent() {
		Instant instant = Instant.now();
		long timeStampSeconds = instant.getEpochSecond();
		counterMap.put(timeStampSeconds, counterMap.getOrDefault(timeStampSeconds, 0L) + 1);
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	public Long getEventCount(Frequency frequency, int time) {
		Instant instant = Instant.now();
		long timeStampSeconds = instant.getEpochSecond();
		Long intervalEnd = timeStampSeconds;
		int interval = Util.calulateDuration(frequency);
		time = frequency.equals(Frequency.MINUTES) && time > MAX_TIME ? MAX_TIME : time;
		Long intervalStart = intervalEnd - time * interval + 1;
		Long start = intervalStart, count = 0L;
		while (start <= intervalEnd) {
			if (counterMap.containsKey(start)) {
				Long currentTime = counterMap.get(start);
				count += currentTime;
			}
			start++;
		}
		return count;

	}
}
