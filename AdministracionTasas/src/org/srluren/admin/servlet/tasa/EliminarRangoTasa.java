package org.srluren.admin.servlet.tasa;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.srluren.admin.beans.hb.Tasa;
import org.srluren.admin.resources.Parameters;
import org.srluren.admin.services.impl.TasaServicesImpl;

/**
 * Servlet implementation class EliminarTasa
 */
public class EliminarRangoTasa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminarRangoTasa() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		procesar(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		procesar(request, response);

	}


	private void procesar(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

		// Obtiene session
		HttpSession sesion_actual = request.getSession(true);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Parameters parameters = Parameters.getInstance();

		// Define objetos a utilizar
		TasaServicesImpl tasaServicesImpl = new TasaServicesImpl();

		// Monto máximo por defecto
		//long montoMaximo = Long.parseLong(parameters.getKey("MTO_MAXIMO_PRODUCTOS"));

		try {
			// Obtiene datos de formulario
			int rango_actual = Integer.parseInt(request.getParameter("idRango"));
			int plazoInicialAct = Integer.parseInt(request.getParameter("plazoInicial"));
			int plazoFinalAct = Integer.parseInt(request.getParameter("plazoFinal"));
			int unidadPlazoAct = Integer.parseInt(request.getParameter("unidadPlazo"));
			
			// Obtiene datos de sesión
			String idSubProducto = (String)sesion_actual.getAttribute("idSubProducto");
			String idTipoPersona = (String)sesion_actual.getAttribute("idTipoPersona");
			String idMoneda = (String)sesion_actual.getAttribute("idMoneda");
			
			int i = 1;

			//Obtiene lista de rango-tasa x plazo de BD
			List<Tasa> listaRangoTasasPlazo = tasaServicesImpl.listarTasasxPlazos(
													idSubProducto, 
													idTipoPersona, 
													idMoneda, 
													String.valueOf(plazoInicialAct), 
													String.valueOf(plazoFinalAct), 
													String.valueOf(unidadPlazoAct));
			
			//Obtiene iterador
			Iterator<Tasa> iterator = listaRangoTasasPlazo.iterator();

			// Recorre lista
			while (iterator.hasNext()) {
				
				// Obtiene objeto
				Tasa rangoTasaActual = iterator.next();

				// Actualiza montos de ultimo rango
				/*if (i == rango_actual - 1) {

					// Actualiza monto máximo
					rangoTasaActual.setMtoFinal(montoMaximo);

					// Actualiza en base de datos
					tasaServicesImpl.actualizarTasa(rangoTasaActual);
				}*/

				// Elimina desde el rango Actual en adelante
				if (i >= rango_actual) {

					// Elimina rango de base de datos
					tasaServicesImpl.eliminarTasa(rangoTasaActual.getIdTasa());

				}

				// Aumenta contador
				i++;
			}
			
			//Devuelve mensaje
			out.println(parameters.getKey("MSJ_ELI_RANGO_OK"));

		} catch (Exception e) {
			e.printStackTrace();
			//Devuelve mensaje
			out.println(parameters.getKey("MSJ_ELI_RANGO_NOK"));
		}

	}

}
