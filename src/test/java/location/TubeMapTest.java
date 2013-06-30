package location;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TubeMapTest {
	
	private TubeMap tubeMap;
	
	@Before
	public void setUp() {
		tubeMap = TubeMap.getInstance();
	}
	
	@Test
	public void loadMapReturnsATubeMapInstanceWithALoadedMap() {
		assertNotNull(tubeMap.getMap().get("Mudchute (DLR)"));
	}
	
	@Test
	public void findNearTubeReturnsANearbyTubeStation() {
		Point coord = new Point(51.492192f,-0.015624f);
		String expected = "Mudchute (DLR)";
		String actual = tubeMap.findNearbyTube(coord);
		assertEquals(expected, actual);
	}
	
	@Test
	public void findNearTubeReturnsNullIfThereAreNoStationsNearby() {
		Point coord = new Point(51.492192f,-0.025624f);
		assertNull(tubeMap.findNearbyTube(coord));
	}

}
