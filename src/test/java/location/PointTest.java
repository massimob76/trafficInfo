package location;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {
	
	@Test
	public void toStringReturnsANicelyFormattedString() {
		String expected = "latitude: 1.0 longitude: 2.0";
		String actual = new Point(1f, 2f).toString();
		assertEquals(expected, actual);
	}

}
