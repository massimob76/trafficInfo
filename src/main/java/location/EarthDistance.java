package location;

import static java.lang.Math.*;

public class EarthDistance {
	
	private static final int MEAN_HEARTH_RADIUS = 6371000;
	
	public static float calculateDistance(Point point1, Point point2) {
		double dLng = Math.toRadians(point1.getLng() - point2.getLng());
		double lat1 = toRadians(point1.getLat());
		double lat2 = toRadians(point2.getLat()); 
		
		double distance = 
				MEAN_HEARTH_RADIUS
				* acos(sin(lat1) * sin(lat2) + cos(lat1) * cos(lat2) * cos(dLng));
				
		return (float)distance;		
	}

}
