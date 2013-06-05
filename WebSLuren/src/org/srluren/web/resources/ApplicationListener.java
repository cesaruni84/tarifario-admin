/**
 * 
 */
package org.srluren.web.resources;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



/**
 * @author AETOS PERU S.A.C
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
		
	}
	
	private void loadFileParameters(){
		
		System.out.println("Cargando archivo : application.properties");
		Parameters parameters = Parameters.getInstance();
		parameters.load(ApplicationProperties.getString("PATH_FILE_APP"));
		
		System.out.println("Cargando archivo : parameters.properties");
		Application application = Application.getInstance();
		application.load(ApplicationProperties.getString("PATH_FILE_PAR"));
		
	}
	
}
