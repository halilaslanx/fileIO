package file.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
				// TODO Auto-generated catch block
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
//
//		sc = new Scanner(f);
		PrintWriter pw = new PrintWriter(new File(d, "test_pw.txt"));
		while (sc.hasNext())
			pw.println(sc.nextLine());
		sc.close();
		pw.close();
		
		
		
	}
}
