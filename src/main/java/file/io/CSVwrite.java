package file.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.github.javafaker.Faker;

public class CSVwrite {
	public static void main(String[] args) throws IOException {
		Faker fake = new Faker();
		File d = new File("./output");
		d.mkdirs();
		File f = new File(d, "data.csv");

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
			bw.write("FirstName,LastName,Country" + System.lineSeparator());

			for (int i = 0; i < 10; i++) {
				String fName = fake.name().firstName();
				String lName = fake.name().lastName();
				String country = fake.address().country();

				bw.write(fName + "," + lName + "," + country + System.lineSeparator());
			}
		}

	}
}
