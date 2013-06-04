package org.srluren.admin.servlet.usuario;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.srluren.admin.beans.hb.Usuario;
import org.srluren.admin.resources.Parameters;
import org.srluren.admin.services.impl.UsuarioServicesImpl;
import org.srluren.admin.util.Utilitario;

/**
 * Servlet implementation class ValidarUsuario
 */
public class ValidarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		procesar(request,response);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		procesar(request,response);
	}
	
	private void procesar(HttpServletRequest request,HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		//Obteniendo sesión
		HttpSession sesion_actual;
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Parameters parameters = Parameters.getInstance();
		
		//Obtiene usuario y password
		String usuario = request.getParameter("username");
		String clave = request.getParameter("password");
				
		//Obtiene usuario de base de datos y lo valida
		UsuarioServicesImpl usuarioServicesImpl = new UsuarioServicesImpl();
		Usuario beanUsuario = new Usuario();
		
		try { 
		
		//Valida contra base de datos
		beanUsuario  = usuarioServicesImpl.autenticarUsuario(usuario, Utilitario.encriptarMD5(clave));
		
			if (beanUsuario.getCodigo()!=null){
				
				//Si el usuario es válido, guarda datos en la sesion				
				sesion_actual = request.getSession(true);
				sesion_actual.setAttribute("usuario", beanUsuario.getCodigo());
				sesion_actual.setAttribute("nombre", beanUsuario.getNombre());
				sesion_actual.setAttribute("apellido", beanUsuario.getApellido());
								
				//Retorna mensaje
				out.println("1");
				
				
			} else{ //Error datos incorrectos
				out.println(parameters.getKey("MSJ_LOGIN_ERROR_ING"));

			}
		} catch (Exception e){ //Problema de conexión
			out.println(parameters.getKey("MSJ_LOGIN_ERROR_BD"));
		}
		
	}

}
