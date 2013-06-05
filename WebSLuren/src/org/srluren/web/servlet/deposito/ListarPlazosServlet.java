package org.srluren.web.servlet.deposito;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.srluren.web.beans.hb.Plazo;
import org.srluren.web.services.impl.TasaServicesImpl;

/**
 * Servlet implementation class ListarPlazosServlet
 */
public class ListarPlazosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarPlazosServlet() {
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
		HttpSession sesion_actual = request.getSession(true);
	
		//Define variable: valor por defecto
		String htmlListaPlazos = "";
		
		try {
			
			// Obtiene datos de sesion
			String idSubProducto = String.valueOf(sesion_actual.getAttribute("idSubProducto"));
					
			//Obtiene datos de formuario		
			String idMoneda = request.getParameter("idMoneda").toString();
			String idTipoPersona =request.getParameter("idTipoPersona");
			
			// Instancia objeto service
			TasaServicesImpl tasaServicesImpl = new TasaServicesImpl();
		
			// Obtiene lista de subproductos por producto seleccionado
			List<Plazo> lista = tasaServicesImpl.listarPlazosxSubProducto(idSubProducto, idTipoPersona, idMoneda);
			Iterator<Plazo> iter = lista.iterator();
			
			// Recorre lista de subproductos
			while (iter.hasNext()) {
				
				//Obtiene bean
				Plazo bean = iter.next();
				
				// Obtiene valor
				String valor = "<option value='" + bean.getPeriodo() + "'>" + bean.getDescripcion() + "</option>";

				// Actualiza html dinamico
				htmlListaPlazos = htmlListaPlazos + valor;

			}
			
			//Retorna html
			out.print(htmlListaPlazos);
			out.flush();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("");
		}

	}


}
