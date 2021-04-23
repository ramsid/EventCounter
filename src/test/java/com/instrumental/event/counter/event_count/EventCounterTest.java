package com.instrumental.event.counter.event_count;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.instrumental.Enum.Frequency;
import com.instrumental.even.counter.impl.EventCounterImpl;
import com.instrumental.event.counter.EventCounter;

/**
 * Unit test for {@ EventCounterImpl}.
 */
public class EventCounterTest {

	@Test
	public void testEventCounterOneSecond() throws InterruptedException {

		EventCounter eventCount = new EventCounterImpl();
		for (int i = 0; i < 30; i++) {
			eventCount.hitEvent();

		}
		TimeUnit.SECONDS.sleep(1);
		for (int i = 0; i < 30; i++) {
			eventCount.hitEvent();
		}
		assertTrue(30 == eventCount.getEventCount(Frequency.SECONDS, 1));

	}

	@Test
	public void testEventCounterTwoSecond() throws InterruptedException {

		EventCounter eventCount = new EventCounterImpl();
		for (int i = 0; i < 30; i++) {
			eventCount.hitEvent();

		}
		TimeUnit.SECONDS.sleep(1);
		for (int i = 0; i < 30; i++) {
			eventCount.hitEvent();
		}
		assertTrue(60 == eventCount.getEventCount(Frequency.SECONDS, 2));

	}

	@Test
	public void testEventCounterMinute() throws InterruptedException {

		EventCounter eventCount = new EventCounterImpl();
		int count = 0;
		while (count < 5) {
			TimeUnit.SECONDS.sleep(1);
			for (int i = 0; i < 30; i++) {
				eventCount.hitEvent();
			}
			count++;
		}
		TimeUnit.MINUTES.sleep(1);
		for (int i = 0; i < 30; i++) {
			eventCount.hitEvent();
		}

		assertTrue(180 == eventCount.getEventCount(Frequency.MINUTES, 2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEventCounterInvalidFrequency() throws InterruptedException {

		EventCounter eventCount = new EventCounterImpl();
		for (int i = 0; i < 30; i++) {
			eventCount.hitEvent();
		}
		eventCount.getEventCount(null, 2);

	}

	@Test
	public void testEventCounterMinuteOutsideBoundary() throws InterruptedException {

		EventCounter eventCount = new EventCounterImpl();
		int count = 0;
		while (count < 8) {
			TimeUnit.MINUTES.sleep(1);
			for (int i = 0; i < 30; i++) {
				eventCount.hitEvent();
			}
			count++;
		}

		assertTrue(150 == eventCount.getEventCount(Frequency.MINUTES, 8));
	}

}
