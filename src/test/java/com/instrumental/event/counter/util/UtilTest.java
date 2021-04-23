package com.instrumental.event.counter.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.instrumental.Enum.Frequency;

/**
 * Unit test for {@ Util}.
 */
public class UtilTest {
	
	@Test
	public void testCalulateDurationValid() {
			assertTrue(150==Util.calulateDuration(Frequency.MINUTES, 3));
	}
	@Test
	public void testCalulateDurationInvalid() {
			assertTrue(150==Util.calulateDuration(null, 3));
	}

}
