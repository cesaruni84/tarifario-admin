/**
 * 
 */
package org.srluren.admin.resources;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



/**
 * @author cesar
 *
 */
public class LogParameters {
	
	
	private static LogParameters logParameters = null;
	private static Properties config;
	
	public static LogParameters getInstance (){

		if (logParameters == null) {
			logParameters = new LogParameters();
			config = new Properties();
		}
		
		return logParameters;

	}
	
	public void load (String urlFile){
		
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(urlFile);
			config.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getKey(String key){
		
		if (config != null){
			return config.getProperty(key);
			
		}
		return null;
	}

}
