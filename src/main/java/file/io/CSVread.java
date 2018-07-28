package file.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

import com.github.javafaker.Faker;

public class CSVread {
	public List<Map<String, String>> loadData(String fileName) {
		List<Map<String, String>> data = new ArrayList<>();
		File f = new File(fileName);
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			String[] attributes = br.readLine().split(",");
			String line = null;
			
			while( (line = br.readLine()) != null ) {
				String[] values = line.split(",");
				if (values.length > 0 && values[0].length() > 0) {
					Map<String, String> row = new HashMap<>();
					for (int i = 0; i < attributes.length; i++)
						row.put(attributes[i], values[i]);
					data.add(row);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
