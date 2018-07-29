package file.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class NIO {
	public static void main(String[] args) throws IOException {
		List<String> ls = Arrays.asList(new String[] {"a", "b", "c"});
		Path path = Paths.get("output/testNIO.txt");  // root and file component
		try {
			Files.write(path, ls);	//accepts iterables
			// write with options: clear the contents of the file (truncate) if the file exists
			//					   create if it doesn't exist, overwrite if it exists
//			Files.write(file, ls, new OpenOption[] { StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// get all the files at the path location. this one lists the files under output folder
		Iterator it = Files.list(Paths.get("output")).iterator();
		while (it.hasNext())
			System.out.println(it.next());
		
		// get all the files in the project folder (does not include subfolders) and print them on the console 
		Files.list(Paths.get("")).forEach( p -> System.out.println(p));

		// check file status : Files.exists(path)
		// exists, isDirectory, isReadable, isWritable, getAttribute
		
		// copy file to a new location
		Files.copy(Paths.get("output/test.txt"),
				   Paths.get("output/testCopy.txt"), StandardCopyOption.REPLACE_EXISTING);
		
		// copy the data you get from a stream to a file
		Files.copy(new FileInputStream("output/data.csv"), Paths.get("output/dataCopy.csv"), StandardCopyOption.REPLACE_EXISTING);
		
		System.out.println("================");
		
		// this one is to find a file the easier way. it walks through the path (in this case project folder)
		// including the subfolders upto 10 levels down. It returns a Stream, but we can get the iterator 
		// of the Stream and not deal with the Streams. Example below finds all files ending with ".txt"
		it = Files.walk(Paths.get(""), 10).iterator();
		while(it.hasNext()) {
			String thisFile = it.next().toString();
			if (thisFile.endsWith(".txt")) 
				System.out.println(thisFile);
		}
		
		System.out.println("================");
		
		// For Streams curious people only: This example is reading lines from a file as a string
		// and prints out all data where first name is starts with a letter bigger than 'E' by
		// skipping the first line as it holds the header row
		// mapping line string to an array of string values using map
		// filtering the first column in each line of string array and selecting the ones
		// that start with a first letter bigger than 'E'
		// finally printing the line 
		Files.lines(Paths.get("output/data.csv")).skip(1)
			.map( line -> line.split(",") ).filter( lineArr -> lineArr[0].charAt(0) > 'E' )
			.forEach( lineArr -> System.out.println(Arrays.toString(lineArr)) );
		
		System.out.println("================");
		
		// easy BufferedReader
		BufferedReader br = Files.newBufferedReader(Paths.get("output/test.txt"));
		String s = null;
		while ( (s = br.readLine()) != null )
			System.out.println(s);
		br.close();
		
		System.out.println("================");
		// easy BufferedWriter
		BufferedWriter bw = Files.newBufferedWriter(Paths.get("output/fileBRex.txt"));
		bw.write("test string for Files bw");
		bw.close();
	}
}
