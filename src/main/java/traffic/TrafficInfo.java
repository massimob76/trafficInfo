package traffic;

import java.util.Date;

public class TrafficInfo {
	
	public static enum Condition { HEAVY, LIGHT, MODERATE};
	
	final String station;
	final int robotId;
	final Date time;
	final float speed;
	final Condition condition;
	
	public TrafficInfo(String station, int robotId, Date time, float speed, Condition condition) {
		this.station = station;
		this.robotId = robotId;
		this.time = time;
		this.speed = speed;
		this.condition = condition;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Station: ");
		sb.append(station);
		sb.append(" - robot id: ");
		sb.append(robotId);
		sb.append(" - time: ");
		sb.append(time);
		sb.append(" - speed: ");
		sb.append(speed);
		sb.append("m/s - condition: ");
		sb.append(condition);
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((condition == null) ? 0 : condition.hashCode());
		result = prime * result + robotId;
		result = prime * result + Float.floatToIntBits(speed);
		result = prime * result + ((station == null) ? 0 : station.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrafficInfo other = (TrafficInfo) obj;
		if (condition != other.condition)
			return false;
		if (robotId != other.robotId)
			return false;
		if (Float.floatToIntBits(speed) != Float.floatToIntBits(other.speed))
			return false;
		if (station == null) {
			if (other.station != null)
				return false;
		} else if (!station.equals(other.station))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	
	
	
}
