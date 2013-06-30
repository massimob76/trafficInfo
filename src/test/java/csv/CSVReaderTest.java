package csv;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CSVReaderTest {
	
	private class CSVReaderImpl extends CSVReader {
		
		List<String> content = new ArrayList<String>();

		@Override
		public void addCSVLine(String[] values) {
			content.add(values[0] + values[1] + values[2]);
		}
		
	}
	
	@SuppressWarnings("serial")
	@Test
	public void parseShouldParseCSVCorrectly() throws IOException {
		
		CSVReaderImpl csvReader = new CSVReaderImpl();
		
		List<String> expected = new ArrayList<String>() {{
			add("first1I");
			add("second2II");
		}};
		
		csvReader.parse("/sample.csv");
		assertEquals(expected, csvReader.content);
	}

}
