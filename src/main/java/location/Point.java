package location;

public class Point {
	
	private final float latitude, longitude;
	
	public Point(float latitude, float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Point(String latitude, String longitude) {
		this(Float.parseFloat(latitude), Float.parseFloat(longitude));
	}
	
	public float getLat() {
		return this.latitude;
	}
	
	public float getLng() {
		return this.longitude;
	}
	
	@Override
	public String toString() {
		return "latitude: " + latitude + " longitude: " + longitude;
	}

}
