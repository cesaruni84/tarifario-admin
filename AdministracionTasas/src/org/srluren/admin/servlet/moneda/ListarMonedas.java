package org.srluren.admin.servlet.moneda;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.srluren.admin.beans.hb.Moneda;
import org.srluren.admin.resources.Parameters;
import org.srluren.admin.services.impl.MonedaServicesImpl;


/**
 * Servlet implementation class ListarMonedas
 */
public class ListarMonedas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarMonedas() {
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

		// Respuesta
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Parameters parameters = Parameters.getInstance();

		// Define variable
		String htmlMonedas = "";

		// Servicio de consulta
		MonedaServicesImpl monedaServicesImpl = new MonedaServicesImpl();

		try {

			// Obtiene descriptivas de lista de monedas
			List<Moneda> listaMonedas = monedaServicesImpl
					.listarMonedas();
			Iterator<Moneda> iterator = listaMonedas.iterator();

			// Barre lista
			while (iterator.hasNext()) {

				// Obtiene bean
				Moneda bean = (Moneda) iterator.next();

				// Obtiene valor
				String valor = "<option value='" + bean.getIdMoneda()
						+ "'>" + bean.getDescriptiva() + "</option>";

				// Actualiza html dinamico
				htmlMonedas = htmlMonedas + valor;

			}

			// Retorna html
			out.print(htmlMonedas);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println(parameters.getKey("MSJ_LIST_MONEDAS_NOK"));
			
		}

	}

}
