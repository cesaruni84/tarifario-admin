package org.srluren.web.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * @author AETOS PERU S.A.C
 *
 */

public class Application {
	
	
	private static Application application = null;
	private static Properties config;
	
	public static Application getInstance (){

		if (application == null) {
			application = new Application();
			config = new Properties();
		}
		
		return application;

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
