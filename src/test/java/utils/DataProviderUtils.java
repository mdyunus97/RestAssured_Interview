package utils;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

public class DataProviderUtils {

	@DataProvider(name = "bookingdata")
	public Object[][] getData() throws IOException, ParseException {

		// Step 1: Parse JSON file
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("src/test/resources/testData.json"));

		Object[][] data = new Object[jsonArray.size()][1];
		for (int i = 0; i < jsonArray.size(); i++) {
			data[i][0] = jsonArray.get(i);
		}
		return data;

	}
}
