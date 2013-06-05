package org.srluren.web.servlet.mail;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.srluren.web.resources.Parameters;
import org.srluren.web.util.ServerMail;

/**
 * Servlet implementation class EnviarCorreoServlet
 */
public class EnviarCorreoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EnviarCorreoServlet() {
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
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		// Variables
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		Parameters parameters = Parameters.getInstance();
		
		//Datos de entrada
		String producto;
		String subProducto;
		String nombreApellido;
		String correo;
		String telefono;
		
		
		try {
			out = response.getWriter();
			ServerMail serverMail = new ServerMail();
			JSONParser jsonParser = new JSONParser();
			JSONObject json = null;
			
			//Obtiene respuesta en formato json
			json = (JSONObject) jsonParser.parse(request.getParameter("jsonRequest"));
			
			//Obtiene campos
			producto	 	= (String) request.getSession().getAttribute("nombreProducto");
			subProducto		= (String) request.getSession().getAttribute("nombreSubProducto");
			nombreApellido 	= json.get("nombreApellido").toString();
			correo 			= json.get("correo").toString();
			telefono 		= json.get("telefono").toString();
			
			
			// Envia correo con los datos enviados.
			boolean envioCorreoOK = serverMail.enviarCorreo(producto,subProducto,nombreApellido,correo,telefono);
			
			//Si el envio de correo fue exitoso
			if (envioCorreoOK) {
				//responseJSON.put("msj_retorno", parameters.getKey("MSJ_ENVIO_OK"));
				out.println(parameters.getKey("MSJ_ENVIO_OK"));
			} else { //Sino muestra mensaje de error
				//responseJSON.put("msj_retorno", parameters.getKey("MSJ_ENVIO_NOK"));
				out.println(parameters.getKey("MSJ_ENVIO_NOK"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//responseJSON.put("cod_retorno", "1");
			//responseJSON.put("msj_retorno", parameters.getKey("MSJ_ENVIO_NOK"));
			out.println(parameters.getKey("MSJ_ENVIO_NOK"));
		} 

	}

}
