package file.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CSVreadNIO {

	public static List<Map<String, String>> loadData(String filename) throws IOException {
		// create a data structure to load the data into
		List<Map<String, String>> data = new ArrayList<>();
		Path dataFile = Paths.get(filename);
		
		// Files.readAllLines only accepts Path and returns a List
		// it reads the whole file in one shot
		List<String> linesList = Files.readAllLines(dataFile);
		// header row to attributes array
		String[] attributes = linesList.get(0).split(",");
		
		// process data rows
		for (int i = 1; i < linesList.size(); i++) {
			String[] values = linesList.get(i).split(",");
			if (values.length == 0 && values[0].length() == 0) break;
			// create a map for each row
			Map<String, String> row = new HashMap<>();
			//fill the row
			for (int j = 0; j < attributes.length; j++)
				row.put(attributes[j], values[j]);
			data.add(row);		// add data to row
		}	

		// do the same with a Stream, but use the iterator of the stream
		// instead of dealing with it directly
		Stream<String> linesStream = Files.lines(dataFile);
		Iterator<String> it = linesStream.iterator();
		attributes = it.next().split(",");
		while (it.hasNext()) {
			String[] values = it.next().split(",");
			if (values.length > 0 && values[0].length() > 0) {
				Map<String, String> row = new HashMap<>();
				for (int i = 0; i < attributes.length; i++)
					row.put(attributes[i], values[i]);
				// data.add(row);
			}
		}
		linesStream.close();

		return data;
	}

	public static void main(String[] args) throws IOException {
		List<Map<String, String>> data = loadData("./output/data.csv");
		for (Map<String, String> row : data) {
			System.out.println(row);
		}
		System.out.println();
		System.out.println("First Name:\t" + data.get(0).get("FirstName"));
		System.out.println("Country:\t" + data.get(0).get("Country"));
		
	}

}
