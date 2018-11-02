package CommonFunctions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
public class PropertyManager {
	
	private static Properties prop =  new Properties();
	static{
		FileInputStream fin =  null;
		// Path where Config.Property file is located.
		File file= new File("/home/arun/eclipse1/workspace/DropBox_Test/src/test/resources/Config.properties");
		try {
			fin = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
		  prop.load(fin);
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static String getProp(String property){
		if(property != null){
			return prop.getProperty(property.trim());
		}
		return null;
	}
}