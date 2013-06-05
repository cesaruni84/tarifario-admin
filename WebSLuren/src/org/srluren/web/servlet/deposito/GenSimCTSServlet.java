package org.srluren.web.servlet.deposito;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sim.credito.bean.Deposito;
import org.sim.credito.bean.DepositoSimulacion;
import org.sim.credito.resources.Constantes;
import org.srluren.web.manager.impl.CTSSimuladorManager;
import org.sim.credito.util.Utilitario;

/**
 * Servlet implementation class GenSimCTSServlet
 */
public class GenSimCTSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenSimCTSServlet() {
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
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub

		//Obteniendo parámetros
		HttpSession sesion_actual = request.getSession(true);
		
		try{
		//Servicio de Generacion de Simulacion Ahorros
		CTSSimuladorManager ctsSimuladorManager = new CTSSimuladorManager();
		
		//Obtiene resultados de la simulacion
		DepositoSimulacion depositoSimulacion = ctsSimuladorManager.generarSimulacion(request.getParameter("jsonRequest"),sesion_actual.getAttribute("idSubProducto").toString());
		
		//Devuelve datos de entrada
		Deposito deposito = ctsSimuladorManager.getDatosDeposito();

		//Devuelve datos a mostrar en pantalla de resultados
		request.setAttribute("subProducto", deposito.getSubProducto());

		if (Integer.parseInt(deposito.getMoneda()) == Constantes.MONEDA_SOLES){
			request.setAttribute("moneda", Constantes.DES_MONEDA_SOLES);
		}else{
			request.setAttribute("moneda", Constantes.DES_MONEDA_DOLARES);
		}
		request.setAttribute("montoDeposito", Utilitario.formatearMontosMiles(deposito.getMontoDeposito(), Constantes.FORMATO_MONTOS_VISTA));
		request.setAttribute("plazoDeposito", deposito.getPlazoDeposito());
		request.setAttribute("valorTREA", Utilitario.formatearMontosMiles(depositoSimulacion.getTREA(),Constantes.FORMATO_TASAS_VISTA));
		request.setAttribute("intereses", Utilitario.formatearMontosMiles(depositoSimulacion.getIntereses(),Constantes.FORMATO_MONTOS_VISTA));
		request.setAttribute("montoRecibir",Utilitario.formatearMontosMiles(depositoSimulacion.getMontoRecibir(), Constantes.FORMATO_MONTOS_VISTA));

		//Redirecciona a pagina
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/ahorros/crono-cts.jsp");
		dispatcher.forward(request, response);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
