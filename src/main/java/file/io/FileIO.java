package file.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileIO {
	public static void main(String[] args) throws IOException {
		//File f = new File("test.txt");
		File d = new File("./output");
		d.mkdirs();
		File f = new File(d, "test.txt");
		System.out.println(f.exists());
		System.out.println(f.isFile());
		System.out.println(f.isDirectory());
		// mkdir/mkdirs, canRead, canWrite etc.
		
		FileWriter fw = null;
		try {
			//FileWriter fw = new FileWriter("test.txt", true);	//appends
			//fw = new FileWriter("test.txt");
			fw = new FileWriter(f);
			fw.write("test string 1\n");
			fw.write("test string 2" + System.lineSeparator());	// windows: \r\n
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		FileReader fr = new FileReader(f);
		int c = 0;
		while ( (c = fr.read()) != -1 )
			System.out.print((char) c);
		fr.close();
		
		Scanner sc = new Scanner(f);
//		while (sc.hasNext())
//			System.out.println(sc.nextLine());
//		sc.close();		

		PrintWriter pw = new PrintWriter(new File(d, "test_pw.txt"));
		while (sc.hasNext())
			pw.println(sc.nextLine());
		sc.close();
		pw.close();
		
		InputStream is = new ByteArrayInputStream(new byte[] {1, 2, 3} );
		int b = 0;
		while( (b = is.read() )> -1 )
			System.out.println(b);
		
		PrintStream os = new PrintStream(new FileOutputStream(f));
		os.println("Hello World");
		os.close();
		
	}
}
