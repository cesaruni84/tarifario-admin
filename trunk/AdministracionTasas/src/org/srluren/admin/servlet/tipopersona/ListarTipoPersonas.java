package org.srluren.admin.servlet.tipopersona;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.srluren.admin.beans.hb.TipoPersona;
import org.srluren.admin.resources.Parameters;
import org.srluren.admin.services.impl.TipoPersonaServicesImpl;

/**
 * Servlet implementation class ListarTipoPersonas
 */
public class ListarTipoPersonas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarTipoPersonas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		procesar(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		procesar(request, response);
	}

	
	
	private void procesar(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		//Respuesta
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Parameters parameters = Parameters.getInstance();
		
		//Define variable
		String htmlTipoPersonas = "";
		
		//Servicio de consulta
		TipoPersonaServicesImpl tipoPersonaServicesImpl = new TipoPersonaServicesImpl();
		
		try {
			
			//Obtiene descriptivas de lista de personas
			List<TipoPersona> listaTipoPersonas = tipoPersonaServicesImpl.listarTiposPersona();
			Iterator<TipoPersona> iterator = listaTipoPersonas.iterator();
			
			//Barre lista
			while(iterator.hasNext()){
				
				//Obtiene bean
				TipoPersona bean = (TipoPersona) iterator.next();

				// Obtiene valor
				String valor = "<option value='" + bean.getIdTipoPersona()
						+ "'>" + bean.getDescriptiva() + "</option>";

				// Actualiza html dinamico
				htmlTipoPersonas = htmlTipoPersonas + valor;
							
			}
			
			//Retorna html
			out.print(htmlTipoPersonas);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println(parameters.getKey("MSJ_LIST_TIPOPER_NOK"));
		}
		
		
	}
	
	
	

}
