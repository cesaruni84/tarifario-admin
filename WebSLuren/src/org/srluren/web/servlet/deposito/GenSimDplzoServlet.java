package org.srluren.web.servlet.deposito;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.sim.credito.resources.Constantes;
import org.sim.credito.bean.Deposito;
import org.sim.credito.bean.DepositoSimulacion;
import org.srluren.web.manager.impl.DPlazoSimuladorManager;
import org.sim.credito.util.Utilitario;

/**
 * Servlet implementation class GenCronoDplzoServlet
 */
public class GenSimDplzoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenSimDplzoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesar(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesar(request,response);
	}
	
	private void procesar(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub

		//Obteniendo parámetros
		HttpSession sesion_actual = request.getSession(true);
		String URL_RESULTADOS;
		
		try{
		//Servicio de Generacion de Simulacion Ahorros
		DPlazoSimuladorManager dPlazoSimuladorManager = new DPlazoSimuladorManager();
		
		//Obtiene resultados de la simulacion
		DepositoSimulacion depositoSimulacion = dPlazoSimuladorManager.generarSimulacion(request.getParameter("jsonRequest"),sesion_actual.getAttribute("idSubProducto").toString());
		
		//Devuelve datos de entrada
		Deposito deposito = dPlazoSimuladorManager.getDatosDeposito();

		//Devuelve datos a mostrar en pantalla de resultados
		request.setAttribute("subProducto", deposito.getSubProducto());

		if (Integer.parseInt(deposito.getMoneda()) == Constantes.MONEDA_SOLES){
			request.setAttribute("moneda", Constantes.DES_MONEDA_SOLES);
		}else{
			request.setAttribute("moneda", Constantes.DES_MONEDA_DOLARES);
		}
		request.setAttribute("montoDeposito", Utilitario.formatearMontosMiles(deposito.getMontoDeposito(), Constantes.FORMATO_MONTOS_VISTA));
		request.setAttribute("plazoDeposito", deposito.getPlazoDeposito() + " días");
		request.setAttribute("valorTREA", Utilitario.formatearMontosMiles(depositoSimulacion.getTREA(), Constantes.FORMATO_TASAS_VISTA));
		request.setAttribute("intereses", Utilitario.formatearMontosMiles(depositoSimulacion.getIntereses(), Constantes.FORMATO_MONTOS_VISTA));
		request.setAttribute("montoRecibir", Utilitario.formatearMontosMiles(depositoSimulacion.getMontoRecibir(), Constantes.FORMATO_MONTOS_VISTA));
		request.setAttribute("fechaInicio", deposito.getFechaInicio());
		request.setAttribute("fechaTermino", depositoSimulacion.getFechaTermino());
		request.setAttribute("numPagos", depositoSimulacion.getNumPagos());
		
		
		if (Integer.parseInt(deposito.getTipoPersona()) == Constantes.PERSONA_NATURAL){
			request.setAttribute("tipoPersona", Constantes.DES_PERSONA_NATURAL);
		}else{
			request.setAttribute("tipoPersona", Constantes.DES_PERSONA_JURIDICA);
		}
		
		
		if (deposito.getFormaPagoInteres()== Constantes.FINAL_PLAZO){
			URL_RESULTADOS = "/WEB-INF/jsp/ahorros/crono-ahorro-plazo.jsp";
			request.setAttribute("tipoPagoInteres", Constantes.DES_FINAL_PLAZO);
	
		}else{
			request.setAttribute("tipoPagoInteres", Constantes.DES_MENSUALMENTE);
			URL_RESULTADOS = "/WEB-INF/jsp/ahorros/crono-ahorro-plazo1.jsp";
		}
		
		
		//
		
		
		//Redirecciona a pagina
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(URL_RESULTADOS);
		dispatcher.forward(request, response);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
