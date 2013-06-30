package robot;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import traffic.TrafficInfo;

import traffic.Reporter;
import traffic.TrafficInfo.Condition;

import location.EarthDistance;
import location.Point;
import location.TubeMap;

public class Robot implements Runnable {

	private static TubeMap tubeMap = TubeMap.getInstance();
	private static Reporter reporter = Reporter.getInstance();

	private static Random random = new Random();

	private static final int MAX_NO_OF_POINTS = 10;

	private final int id;

	private Point previousPoint;
	private Date previousTime;

	public Robot(int id) {
		this.id = id;
	}

	private BlockingQueue<Movement> queue = new LinkedBlockingQueue<Movement>(MAX_NO_OF_POINTS);

	private class Movement {
		Point point;
		Date time;
	}

	public void queueMovement(Point point, Date time) {
		Movement movement = new Movement();
		movement.point = point;
		movement.time = time;
		try {
			queue.put(movement);
			System.out.println("robot: " + id + " received movement to point: " + point);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			while (true) {
				Movement movement = queue.take();
				processMovement(movement.point, movement.time);
			}
		} catch (InterruptedException e) {
			System.out.println("robot: " + id + " shut down");
		}
	}

	public void processMovement(Point point, Date time) {
		String nearByStation = tubeMap.findNearbyTube(point);
		if (nearByStation != null) {
			reporter.report(new TrafficInfo(nearByStation, id, time, calculateSpeed(point, time), getTrafficCondition()));
		}
		previousPoint = point;
		previousTime = time;
	}

	private float calculateSpeed(Point point, Date time) {
		if (previousPoint == null) {
			return 0f;
		} else {
			float distance = EarthDistance.calculateDistance(point, previousPoint);
			long elapsed = time.getTime() - previousTime.getTime();
			return distance / elapsed * 1000;
		}

	}

	private Condition getTrafficCondition() {
		Condition[] cond = Condition.values();
		return cond[random.nextInt(cond.length)];
	}

}
