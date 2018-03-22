package prop_file;

import java.io.*;
import java.util.Properties;

public class DriverTester {
	static Properties prop = new Properties();
	static File fileTest = new File("C:\\Users\\vv3383my\\Documents\\GitHub\\Resources\\Java\\Project Files\\src\\prop_file\\prop_test.ini");
	public static void main(String[] args) throws IOException {
		
		
		fileLoadProperties(prop, fileTest);
		
		String propName = "version";
		System.out.println(propName + "=" + prop.getProperty(propName, "unknown version"));
		propName = "appName";
		System.out.println(propName + "=" + prop.getProperty(propName, "unknown name"));
		propName = "datePublished";
		System.out.println(propName + "=" + prop.getProperty(propName, "unknown date"));
		
		prop.list(System.out);
		
		//fileSaveProperties(prop, fileTest);
	}
	public static void fileSaveProperties(Properties p, File f) throws IOException{
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
