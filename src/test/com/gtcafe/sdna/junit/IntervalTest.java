package com.gtcafe.sdna.junit;

import com.gtcafe.sdna.core.notation.Interval;

public class IntervalTest extends TestCase {
	
	public void testSemitone() {
		Interval interval = new Interval(0);
		assertEquals(interval.toString(), "P1");
	}

}
