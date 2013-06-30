package location;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import csv.CSVReader;


public class TubeMap extends CSVReader {
	
	private static final int DEFAULT_NEARBY_STATION_MAX_DISTANCE = 350;

	private static final String DEFAULT_TUBE_MAP = "/tube.csv";
	
	private Map<String, Point> map = new HashMap<String, Point>();
	
	private TubeMap() {}
	
	private static TubeMap instance;
	
	static {
		instance = new TubeMap();
		try {
			instance.parse(DEFAULT_TUBE_MAP);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static TubeMap getInstance() {
		return instance;
	}
	
	public Map<String, Point> getMap() {
		return map;
	}

	@Override
	public void addCSVLine(String[] values) {
		String name = values[0];
		Point point = new Point(values[1], values[2]);
		map.put(name, point);
	}
	
	public String findNearbyTube(Point coord) {
		for (String station: map.keySet()) {
			if (EarthDistance.calculateDistance(map.get(station), coord) <= DEFAULT_NEARBY_STATION_MAX_DISTANCE) {
				return station;
			}
		}
		return null;
	}

}
