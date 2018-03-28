package prop_file;

import java.io.*;
import java.util.Properties;

import javax.swing.JOptionPane;

public class DriverTester {
	static Properties prop = new Properties();
	static File fileTest = new File("C:\\Users\\vv3383my\\Documents\\GitHub\\Resources\\Java\\Project Files\\src\\prop_file\\prop_test.ini");
	public static void main(String[] args) throws IOException {
		
		
		fileLoadProperties(prop, fileTest);
		
		prop.list(System.out);
		
		fileLoadProperties(prop, new File("C:\\Users\\vv3383my\\Documents\\GitHub\\Resources\\Java\\Project Files\\src\\prop_file\\prop_test_2.ini"));
		
		prop.list(System.out);
		
		//fileSaveProperties(prop, new File("C:\\Users\\vv3383my\\Documents\\GitHub\\Resources\\Java\\Project Files\\src\\prop_file\\prop_out.ini"));
	}
	public static void fileSaveProperties(Properties p, File f) throws IOException{
		try {
			FileOutputStream out = new FileOutputStream(f);
			p.store(out, "--No Comment--");
			out.close();
		} catch (FileNotFoundException e) {
			showError(e.getMessage());
		}
	}
	public static void fileLoadProperties(Properties p, File f) throws IOException {
		try {
			FileInputStream in = new FileInputStream(f);
			p.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			showError(e.getMessage());
		}
	}
	public static void showError(String str) {JOptionPane.showMessageDialog(null, str);}
}
