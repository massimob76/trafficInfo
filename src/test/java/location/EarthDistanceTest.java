package location;

import static org.junit.Assert.*;
import location.Point;

import org.junit.Test;

public class EarthDistanceTest {
	
	private static final Point london = new Point(51.5f, 0.11667f);
	private static final Point paris = new Point("48.85", "2.35");
	
	// 335 344

	
	@Test
	public void calculateDistanceReturnsCorrectDistance() {
		float expected = 335000f;
		float actual = EarthDistance.calculateDistance(london, paris);
		assertEquals(expected, actual, 500f);
	}

}
