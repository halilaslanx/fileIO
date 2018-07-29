package file.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CSVreadNIO {

	public List<Map<String, String>> loadData(String filename) throws IOException {
		List<Map<String, String>> data = new ArrayList<>();
		Path dataFile = Paths.get(filename);
		
		Iterator<String> it = Files.readAllLines(dataFile).iterator();
		String[] attributes = it.next().split(",");
		
		while (it.hasNext()) {
		String[] values = it.next().split(",");
		if (values.length > 0 && values[0].length() > 0) {
			Map<String, String> row = new HashMap<>();
			for (int i = 0; i < attributes.length; i++)
				row.put(attributes[i], values[i]);
			data.add(row);
		}
	}		

//		try (Stream<String> lines = Files.lines(dataFile)) {
//			Iterator<String> it = lines.iterator();
//			String[] attributes = it.next().split(",");
//
//			while (it.hasNext()) {
//				String[] values = it.next().split(",");
//				if (values.length > 0 && values[0].length() > 0) {
//					Map<String, String> row = new HashMap<>();
//					for (int i = 0; i < attributes.length; i++)
//						row.put(attributes[i], values[i]);
//					data.add(row);
//				}
//			}

//			it.forEachRemaining(l -> {
//				String[] values = l.split(",");
//				if (values.length > 0 && values[0].length() > 0) {
//					Map<String, String> row = new HashMap<>();
//					for (int i = 0; i < attributes.length; i++)
//						row.put(attributes[i], values[i]);
//					data.add(row);
//				}
//			});
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return data;
	}

	public static void main(String[] args) {
		List<Map<String, String>> data = new CSVread().loadData("./output/data.csv");
		for (Map<String, String> row : data) {
			System.out.println(row);
		}
		System.out.println();
		System.out.println("First Name:\t" + data.get(0).get("FirstName"));
		System.out.println("Country:\t" + data.get(0).get("Country"));
		
	}

}
