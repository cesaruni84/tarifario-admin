package org.srluren.admin.servlet.tasa;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.srluren.admin.beans.hb.Tasa;
import org.srluren.admin.services.impl.MonedaServicesImpl;
import org.srluren.admin.services.impl.ProductoServicesImpl;
import org.srluren.admin.services.impl.SubProductoServicesImpl;
import org.srluren.admin.services.impl.TasaServicesImpl;
import org.srluren.admin.services.impl.TipoPersonaServicesImpl;

/**
 * Servlet implementation class ConsultarRangoTasas
 */
public class ConsultarRangoTasas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarRangoTasas() {
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
		
		
		//Obtiene session 
		HttpSession sesion_actual = request.getSession(true);
		TipoPersonaServicesImpl personaServicesImpl = new TipoPersonaServicesImpl();
		MonedaServicesImpl monedaServicesImpl = new MonedaServicesImpl();
		SubProductoServicesImpl subProductoServicesImpl = new SubProductoServicesImpl();
		ProductoServicesImpl productoServicesImpl = new ProductoServicesImpl();
		
		//respuesta
		PrintWriter out = response.getWriter();
		 response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Cache-Control","no-cache"); 
		response.setHeader("Pragma","no-cache"); 
		response.setDateHeader ("Expires", -1);

		// La respuesta la devolvemos en formato JSON
		net.sf.json.JSONArray cellarray = new net.sf.json.JSONArray();
		net.sf.json.JSONObject cellobj = new net.sf.json.JSONObject();
		
		//Obtiene valores 
		String idProducto = (String) sesion_actual.getAttribute("idProducto");
		String idSubProducto = (String) sesion_actual.getAttribute("idSubProducto");
		String idTipoPersona = request.getParameter("idTipoPersona");
		String idMoneda = request.getParameter("idMoneda");
		Tasa bean = new Tasa();

		
		try {
			
			//Guarda datos en Session
			sesion_actual.setAttribute("idTipoPersona", idTipoPersona);
			sesion_actual.setAttribute("idMoneda", idMoneda);
			sesion_actual.setAttribute("filtroProducto", (String)productoServicesImpl.buscarProducto(idProducto).getNombre());
			sesion_actual.setAttribute("filtroTipoPersona", (String)personaServicesImpl.buscarTipoPersona(idTipoPersona).getDescriptiva());
			sesion_actual.setAttribute("filtroMoneda", (String)monedaServicesImpl.buscarMoneda(idMoneda).getDescriptiva());
			sesion_actual.setAttribute("filtroSubProducto",  (String)subProductoServicesImpl.buscarSubProducto(idSubProducto).getNombre());
				
			//Instancia servicio
			TasaServicesImpl tasaServicesImpl = new TasaServicesImpl();
			
			//Obtiene rango de tasas en base a filtros
			List<Tasa> listaRangoTasas = tasaServicesImpl.listarTasas(idSubProducto, idTipoPersona, idMoneda);
			
			//Guarda lista en Sesion 
			sesion_actual.setAttribute("listaRangoTasas", (List<Tasa>) listaRangoTasas);
			
			//Barre lista
			Iterator<Tasa> iterator = listaRangoTasas.iterator();
			
			while(iterator.hasNext()){
				
				//Obtiene bean
				 bean = iterator.next();
				
				cellobj.put("seleccion", false);
				cellobj.put("rango", bean.getRango());
				cellobj.put("desde", bean.getMtoInicial());
				cellobj.put("hasta", bean.getMtoFinal());
				cellobj.put("plazoMin", bean.getPlazoInicial());
				cellobj.put("plazoMax", bean.getPlazoFinal());
				cellobj.put("descripcion", bean.getDescripcionPlazo());
				cellobj.put("tasaMin", bean.getValTasaMin());
				cellobj.put("tasaMax", bean.getValTasaMax());
				cellobj.put("unidadPlazo", bean.getUnidadPlazo());
				
				// Guarda en Array
				cellarray.add(cellobj);
				
				// Instancia objeto
				cellobj = new net.sf.json.JSONObject();
				
			}
			
			//Guarda ultimo rango tasa en sesion
			sesion_actual.setAttribute("mtoAnterior", (long)bean.getMtoInicial());
			sesion_actual.setAttribute("valTasaAnteriorMin",(BigDecimal) bean.getValTasaMin());
			sesion_actual.setAttribute("valTasaAnteriorMax",(BigDecimal) bean.getValTasaMax());
			
			// Devuelve respuesta al cliente
			System.out.println("nueva lista json : " + cellarray);
			out.println(cellarray);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
