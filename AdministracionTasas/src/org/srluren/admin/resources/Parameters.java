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
public class Parameters {
	
	
	private static Parameters parameters = null;
	private static Properties config;
	
	public static Parameters getInstance (){

		if (parameters == null) {
			parameters = new Parameters();
			config = new Properties();
		}
		
		return parameters;

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
