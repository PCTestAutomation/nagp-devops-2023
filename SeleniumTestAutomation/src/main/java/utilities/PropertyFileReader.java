package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {
	public static String getValue = "";

	public static String getData(String val) {
		/*
		 * read() takes string input as Key and returns the value of the respective key
		 */
		// Access the File
		// FileInputStream Object
		// Load the object
		File file = new File(System.getProperty("user.dir")+ "/src/main/resources/dataset.properties");

		FileInputStream fis = null;
		Properties prop = null;

		try {
			fis = new FileInputStream(file);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		getValue = prop.getProperty(val);
		return getValue;
	}
}