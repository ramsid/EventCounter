package com.instrumental.event.counter.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.instrumental.Enum.Frequency;
import com.instrumental.event.counter.util.Util;

/**
 * Unit test for {@ Util}.
 */
public class UtilTest {

	@Test
	public void testCalulateDurationMinutes() {
		assertTrue(60 == Util.calulateDuration(Frequency.MINUTES));
	}

	@Test
	public void testCalulateDurationSeconds() {
		assertTrue(1 == Util.calulateDuration(Frequency.SECONDS));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCalulateDurationInvalid() {
		assertTrue(1 == Util.calulateDuration(null));
	}

}