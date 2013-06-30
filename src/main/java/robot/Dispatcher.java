package robot;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import location.Point;

import csv.CSVReader;

import traffic.Reporter;
import traffic.TrafficInfo;
import util.TimeConverter;

public class Dispatcher {
	
	private static final int NO_OF_THREADS = 2;
	private static final String ROBOT_5937_FILENAME = "/5937.csv";
	private static final String ROBOT_6043_FILENAME = "/6043.csv";
	private static final String SIMULATION_END = "2011-03-22 08:10:00";
	
	private class RobotMovement extends CSVReader {
		
		private Robot robot;
		
		public RobotMovement(Robot robot) {
			this.robot = robot;
		}
		
		@Override
		public void addCSVLine(String[] values) {
			Point point = new Point(values[1], values[2]);
			Date time = null;
			try {
				time = TimeConverter.parse(values[3]);
				if (time.compareTo(TimeConverter.parse(SIMULATION_END)) <= 0) {
					robot.queueMovement(point, time);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	public void simulation() throws ExecutionException, IOException, InterruptedException {
		
		// robots set up
		Robot robot5937 = new Robot(5937);
		Robot robot6043 = new Robot(6043);
		
		// setting up thread pool
		ExecutorService exec = Executors.newFixedThreadPool(NO_OF_THREADS);
		
		Future<?> firstRobot = exec.submit(robot5937);
		Future<?> secondRobot = exec.submit(robot6043);
		
		// inject robot movement
		new RobotMovement(robot5937).parse(ROBOT_5937_FILENAME);
		new RobotMovement(robot6043).parse(ROBOT_6043_FILENAME);
		
		exec.shutdownNow();
		
		// await for termination
		firstRobot.get();
		secondRobot.get();
		
		// print traffic info
		Map<String, TrafficInfo> trafficInfos = Reporter.getInstance().getReport();
		System.out.println(trafficInfos);
		
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
		new Dispatcher().simulation();
	}

}
