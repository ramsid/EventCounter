package com.instrumental.even.counter.impl;

import java.time.Instant;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.instrumental.Enum.Frequency;
import com.instrumental.event.counter.EventCounter;
import com.instrumental.event.counter.util.Util;

public class EventCounterImpl implements EventCounter {

	private TreeMap<Long, Long> counterMap = new TreeMap<>();
    private static final int MAX_TIME=5;
	
    @Override
	public void hitEvent() {
		Instant instant = Instant.now();
		long timeStampSeconds = instant.getEpochSecond();
		counterMap.put(timeStampSeconds, counterMap.getOrDefault(timeStampSeconds, 0L) + 1);
	}

	@Override
	public Long getEventCount(Frequency frequency, int time) {
		Instant instant = Instant.now();
		long timeStampSeconds = instant.getEpochSecond();
		Long intervalEnd = timeStampSeconds;
		time = frequency.equals(Frequency.MINUTES)&&time > MAX_TIME ? MAX_TIME : time; 
		int interval = Util.calulateDuration(frequency, time);
		Long intervalStart = intervalEnd - time * interval + 1;
		Long start = intervalStart - 1, count = 0L;
		while (start <= intervalEnd) {
			// Getting the next closest value to start 
			Entry<Long, Long> currentTime = counterMap.ceilingEntry(start + 1);
			if (currentTime == null || currentTime.getKey() > intervalEnd) {
				break;
			}
			count += currentTime.getValue();
			start = currentTime.getKey();
		}
		return count;

	}

}
