package traffic;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

import traffic.TrafficInfo;
import traffic.TrafficInfo.Condition;
import util.TimeConverter;

public class TrafficInfoTest {
	
	@Test
	public void toStringShouldReturnANicelyFormattedString() throws ParseException {
		String expected = "Station: Mudchute (DLR) - robot id: 6403 - time: Tue Mar 22 07:57:18 GMT 2011 - speed: 14.2m/s - condition: HEAVY";
		String station = "Mudchute (DLR)";
		int robotId = 6403;
		Date time = TimeConverter.parse("2011-03-22 07:57:18");
		float speed = 14.2f;
		Condition condition = Condition.HEAVY;
		String actual = new TrafficInfo(station, robotId, time, speed, condition).toString();
		assertEquals(expected, actual);
	}

}
