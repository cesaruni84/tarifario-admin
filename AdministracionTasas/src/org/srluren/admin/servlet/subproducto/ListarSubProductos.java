package org.srluren.admin.servlet.subproducto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.srluren.admin.beans.hb.Producto;
import org.srluren.admin.beans.hb.SubProducto;
import org.srluren.admin.resources.Parameters;
import org.srluren.admin.services.impl.ProductoServicesImpl;
import org.srluren.admin.services.impl.SubProductoServicesImpl;

/**
 * Servlet implementation class ListarSubProductos
 */
public class ListarSubProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarSubProductos() {
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
		
		
		//Respuesta
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Parameters parameters = Parameters.getInstance();
		HttpSession sesion_actual = request.getSession(true);
		
		//Define variable: valor por defecto
		String htmlSubProductos = "";

		try {
			
			// Obtiene id de producto
			String idProducto = request.getParameter("idProducto");

			// Instancia objeto service
			SubProductoServicesImpl subProductoServicesImpl = new SubProductoServicesImpl();
			ProductoServicesImpl productoServicesImpl = new ProductoServicesImpl();

			//Guarda dato en sesion: Tipo de Producto
			Producto beanProducto = productoServicesImpl.buscarProducto(idProducto);
			sesion_actual.setAttribute("tipoProducto", (String)beanProducto.getTipo());
			
			// Obtiene lista de subproductos por producto seleccionado
			List<SubProducto> listaSubProductos = subProductoServicesImpl
					.listarSubProductos(idProducto);
			Iterator<SubProducto> iter = listaSubProductos.iterator();

			// Recorre lista de subproductos
			while (iter.hasNext()) {

				// Obtiene bean
				SubProducto bean = (SubProducto) iter.next();

				// Obtiene valor
				String valor = "<option value='" + bean.getIdSubProducto()
						+ "'>" + bean.getNombre() + "</option>";

				// Actualiza html dinamico
				htmlSubProductos = htmlSubProductos + valor;

			}
			
			//Retorna html
			out.println(htmlSubProductos);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println(parameters.getKey("MSJ_LIST_SUBPROD_NOK"));
		}

	}

}
