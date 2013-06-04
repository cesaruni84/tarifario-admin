package org.srluren.admin.servlet.usuario;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CerrarSesion
 */
public class CerrarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CerrarSesion() {
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

	private void procesar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(true);
		
		//Removiendo datos de sesion
		httpSession.removeAttribute("usuario");
		httpSession.removeAttribute("nombre");
		httpSession.removeAttribute("apellido");
		httpSession.removeAttribute("idProducto");
		httpSession.removeAttribute("idSubProducto");
		httpSession.removeAttribute("idTipoPersona");
		httpSession.removeAttribute("idMoneda");
		httpSession.removeAttribute("filtroProducto");
		httpSession.removeAttribute("filtroTipoPersona");
		httpSession.removeAttribute("filtroMoneda");
		httpSession.removeAttribute("filtroSubProducto");
		httpSession.removeAttribute("mtoAnterior");
		httpSession.removeAttribute("valTasaAnterior");
		httpSession.removeAttribute("tipoProducto");
		
		httpSession.invalidate();
		
		//Redirecciona a Servlet principal
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/main.do");
		dispatcher.forward(request, response);
		
		
	}

}
