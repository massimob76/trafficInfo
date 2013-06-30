package csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class CSVReader {
	
	private static final String COMMA = ",";
	
	public void parse(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(fileName)));
		String line = "";
		try {
			while ((line = br.readLine()) != null) {
				addCSVLine(trimDoubleQuotes(line.split(COMMA)));
			}
		} finally {
			if (br!=null) br.close();
		}
	}
	
	public abstract void addCSVLine(String[] values);
	
	private static String[] trimDoubleQuotes(String[] values) {
		for (int i = 0; i < values.length; i++) {
			values[i] = values[i].replaceAll("^\"|\"$", "");
		}
		return values;
	}
	
	

}
