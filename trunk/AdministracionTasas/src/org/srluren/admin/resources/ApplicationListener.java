/**
 * 
 */
package org.srluren.admin.resources;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



/**
 * @author cesar
 *
 */
public class ApplicationListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
		//Carga archivo de configuracion
		loadFileParameters();
		loadFileLog4j();
		
	}
	
	private void loadFileParameters(){
		System.out.println("Cargando archivo : application.properties");
		Parameters parameters = Parameters.getInstance();
		parameters.load(ApplicationProperties.getString("PATH_FILE_APP"));
		
	}
	
	private void loadFileLog4j(){
		System.out.println("Cargando archivo : log4j.properties");
		LogParameters logParameters = LogParameters.getInstance();
		logParameters.load(ApplicationProperties.getString("PATH_FILE_LOG"));
	}

}
