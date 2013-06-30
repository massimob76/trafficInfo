package traffic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Reporter {
	
	private Reporter() { }
	
	private static Reporter reporter = new Reporter();
	
	public static Reporter getInstance() {
		return reporter;
	}
	
	private Map<String, TrafficInfo> trafficInfos = new ConcurrentHashMap<String, TrafficInfo>();
	
	public void report(TrafficInfo trafficInfo) {
		trafficInfos.put(trafficInfo.station, trafficInfo);
	}
	
	public Map<String, TrafficInfo> getReport() {
		return trafficInfos;
	}

}
