package robot;

import static org.mockito.Mockito.*;
import static org.powermock.reflect.Whitebox.*;

import java.text.ParseException;
import java.util.Date;
import java.util.Random;

import location.TubeMap;
import location.Point;

import org.junit.Before;
import org.junit.Test;

import traffic.Reporter;
import traffic.TrafficInfo;
import traffic.TrafficInfo.Condition;
import util.TimeConverter;

public class RobotTest {
	
	private Robot iut;
	
	@Before
	public void setUp() {
		iut = new Robot(1);
	}

	@Test
	public void processMovementDoesNotReportTrafficInfoIfFarFromTubeStation() {
		
		TubeMap tubeMapMock = mock(TubeMap.class);
		setInternalState(Robot.class, "tubeMap", tubeMapMock);
		Reporter reporterMock = mock(Reporter.class);
		setInternalState(Robot.class, "reporter", reporterMock);
		
		Point point = new Point(0f,0f);
		Date time = null;
		
		when(tubeMapMock.findNearbyTube(point)).thenReturn(null);
		
		iut.processMovement(point, time);
		
		verify(reporterMock, never()).report((TrafficInfo)anyObject());
	}
	
	@Test
	public void processMovementReportTrafficInfoIfNearByTubeStation() {
		
		TubeMap tubeMapMock = mock(TubeMap.class);
		setInternalState(Robot.class, "tubeMap", tubeMapMock);
		Reporter reporterMock = mock(Reporter.class);
		setInternalState(Robot.class, "reporter", reporterMock);
		Random randomMock = mock(Random.class);
		setInternalState(Robot.class, "random", randomMock);
		
		Point point = new Point(0f,0f);
		Date time = null;
		
		when(tubeMapMock.findNearbyTube(point)).thenReturn("Mudchute (DLR)");
		when(randomMock.nextInt(3)).thenReturn(1);

		
		iut.processMovement(point, time);
		
		verify(reporterMock).report(new TrafficInfo("Mudchute (DLR)", 1, null, 0f, Condition.LIGHT));
	}
	
	@Test
	public void processMovementReportTrafficInfoWithCorrectSpeedIfNearByTubeStation() throws ParseException {
		
		TubeMap tubeMapMock = mock(TubeMap.class);
		setInternalState(Robot.class, "tubeMap", tubeMapMock);
		Reporter reporterMock = mock(Reporter.class);
		setInternalState(Robot.class, "reporter", reporterMock);
		Random randomMock = mock(Random.class);
		setInternalState(Robot.class, "random", randomMock);
		
		setInternalState(iut, "previousPoint", new Point(51.492192f, -0.015624f)); // Mudchute station
		setInternalState(iut, "previousTime", TimeConverter.parse("2011-03-22 11:00:00"));
		
		Point point = new Point("51.513347", "-0.089"); // Bank station
		Date time = TimeConverter.parse("2011-03-22 11:20:00");
		
		when(tubeMapMock.findNearbyTube(point)).thenReturn("Bank");
		when(randomMock.nextInt(3)).thenReturn(1);

		
		iut.processMovement(point, time);
		
		verify(reporterMock).report(new TrafficInfo("Bank", 1, time, 4.66432f, Condition.LIGHT));
	}
}
