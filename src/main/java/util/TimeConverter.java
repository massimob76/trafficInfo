package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConverter {
	
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static Date parse(String time) throws ParseException {
		return new SimpleDateFormat(DATE_FORMAT).parse(time);
	}

}
