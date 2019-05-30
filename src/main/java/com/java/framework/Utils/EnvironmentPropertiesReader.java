package com.java.framework.Utils;

import java.io.*;
import java.util.Properties;


/**
 * EnvironmentPropertiesReader is to set the environment variable declaration
 * mapping for config properties in the UI test
 */
public class EnvironmentPropertiesReader {

	public static EnvironmentPropertiesReader envProperties=null;
	private Properties properties;

	public EnvironmentPropertiesReader() {
		properties = PropertiesFile();		
	}
	public Properties PropertiesFile() {
		File file = new File("C:\\Users\\dheeraj.singh\\eclipse-workspace\\JavaFrameWork\\src\\main\\java\\com\\java\\framework\\Config\\Config.properties");
		FileInputStream fileInput = null;
		Properties props = new Properties();
		try {
			fileInput = new FileInputStream(file);
			props.load(fileInput);
			//fileInput.close();
		} 
		catch (FileNotFoundException e) {			
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();			
		}
		return props;
	}
		
	public static EnvironmentPropertiesReader getInstance() {
		if (envProperties == null) {
			envProperties = new EnvironmentPropertiesReader();
		}
		return envProperties;
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

}
