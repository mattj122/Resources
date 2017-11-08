package prop_file;

import java.io.*;
import java.util.Properties;

public class DriverTester {
	static Properties prop = new Properties();
	static File fileTest = new File("C:\\Users\\vv3383my\\Documents\\GitHub\\Resources\\Java\\Project Files\\bin\\prop_file\\test.properties");
	public static void main(String[] args) throws IOException {
		fileLoadProperties(prop, fileTest);
		System.out.println("Property = " + prop.getProperty("key2"));
	}
	public void fileSaveProperties(Properties p, File f) throws IOException{
		try {
			FileOutputStream out = new FileOutputStream(f);
			p.store(out, "--No Comment--");
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	public static void fileLoadProperties(Properties p, File f) throws IOException {
		try {
			FileInputStream in = new FileInputStream(f);
			p.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
}
