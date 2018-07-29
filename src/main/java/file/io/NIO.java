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
		Path file = Paths.get("output/testNIO.txt");  // root and file component
		try {
			Files.write(file, ls);	//accepts iterables
//			Files.write(file, ls, new OpenOption[] { StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Iterator it = Files.list(Paths.get("output")).iterator();
		while (it.hasNext())
			System.out.println(it.next());
		
		Files.list(Paths.get("")).forEach( p -> System.out.println(p));
		
		//exists, isDirectory, isReadable, isWritable
		
		Files.copy(Paths.get("output/test.txt"),
				   Paths.get("output/testCopy.txt"), StandardCopyOption.REPLACE_EXISTING);
		
		Files.copy(new FileInputStream("output/data.csv"), Paths.get("output/dataCopy.csv"), StandardCopyOption.REPLACE_EXISTING);
		
		System.out.println("================");
		
		it = Files.walk(Paths.get(""), 10).iterator();
		while(it.hasNext()) {
			String thisFile = it.next().toString();
			if (thisFile.endsWith(".txt")) 
				System.out.println(thisFile);
		}
		
		System.out.println("================");
		Files.lines(Paths.get("output/data.csv")).skip(1)
			.map( line -> line.split(",") ).filter( lineArr -> lineArr[0].charAt(0) > 'E' )
			.forEach( lineArr -> System.out.println(Arrays.toString(lineArr)) );
		
		System.out.println("================");
		BufferedReader br = Files.newBufferedReader(Paths.get("output/test.txt"));
		String s = null;
		while ( (s = br.readLine()) != null )
			System.out.println(s);
		br.close();
		
		System.out.println("================");
		BufferedWriter bw = Files.newBufferedWriter(Paths.get("output/fileBRex.txt"));
		bw.write("test string for Files bw");
		bw.close();
	}
}
