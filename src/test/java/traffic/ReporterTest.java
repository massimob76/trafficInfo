package traffic;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import traffic.TrafficInfo.Condition;
import util.TimeConverter;

public class ReporterTest {
	
	private Reporter iut;

	@Test
	public void reporterIsAbleToReportAndRetrieveTrafficInfo() throws ParseException {
		final TrafficInfo trafficInfo1 = new TrafficInfo("station1", 1, TimeConverter.parse("2011-03-22 07:57:18"), 10f, Condition.LIGHT);
		final TrafficInfo trafficInfo2 = new TrafficInfo("station2", 2, TimeConverter.parse("2011-03-23 08:57:18"), 12.2f, Condition.MODERATE);
		iut = Reporter.getInstance();

		iut.report(trafficInfo1);
		iut.report(trafficInfo2);
		
        @SuppressWarnings("serial")
		Map<String, TrafficInfo> expected = new HashMap<String, TrafficInfo>() {{
        	put("station1", trafficInfo1);
        	put("station2", trafficInfo2);
        }};
		Map<String, TrafficInfo> actual = iut.getReport();
		assertEquals(expected, actual);
	}
}
