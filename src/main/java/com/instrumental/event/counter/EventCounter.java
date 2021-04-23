package com.instrumental.event.counter;

import com.instrumental.Enum.Frequency;

/**
 * Interface to interact with EventCounter.
 */
public interface EventCounter {

	/**
	 * 
	 * Keeps track of hit events based on system timeStamp.
	 *
	 */
	public void hitEvent();

	/**
	 * 
	 * Returns the event count for user specified {@link Frequency} and time.
	 * 
	 * Note: Supports currently for a maximum of last 5 minutes period.If the supplied
	 * time is greater than 5 minutes, event counter returns the event count for last
	 * 5 minutes.
	 *
	 *@throws {@link IllegalArgumentException} if invalid {@link Frequency} is passed
	 */
	public Long getEventCount(Frequency frequency, int time);

}
