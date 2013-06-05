package org.srluren.web.servlet.credito;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.sim.credito.bean.Cuota;
import org.sim.credito.bo.CreditoCabeceraHipotecarioBO;
import org.sim.credito.bo.CreditoCronogramaHipotecarioBO;
import org.srluren.web.manager.impl.CreditoHipoTePPManager;
import org.srluren.web.resources.ResponsePage;

/**
 * Servlet implementation class GenCronoHipoMivvServlet
 */
public class GenCronoHipoTeppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenCronoHipoTeppServlet() {
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
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub

		//Obteniendo parámetros
		HttpSession sesion_actual = request.getSession(true);

		try{
		//Servicio de Generacion de Credito
		CreditoHipoTePPManager creditoHipotecarioManager = new CreditoHipoTePPManager();

		//Obtiene cronograma de credito
		CreditoCronogramaHipotecarioBO creditoCronogramaBO  = creditoHipotecarioManager.generarCronograma(request.getParameter("jsonRequest"),sesion_actual.getAttribute("idSubProducto").toString());
		List<Cuota> listaCuotasCredito =  creditoCronogramaBO.getListaCuotasCreditoBO();
		
		//Devuelve datos de cabecera a mostrar en el formulario de resultados
		CreditoCabeceraHipotecarioBO creditoCabeceraBO = creditoHipotecarioManager.getDatosCabeceraCredito();
		creditoCabeceraBO.setProducto((String) request.getSession().getAttribute("nombreProducto"));
		creditoCabeceraBO.setSubProducto((String) request.getSession().getAttribute("nombreSubProducto"));
		
		//Setea datos a mostrar: Cabecera
		request.setAttribute("subProducto", creditoCabeceraBO.getSubProducto());
		request.setAttribute("valorInmueble", creditoCabeceraBO.getMontoInmueble());
		request.setAttribute("montoPrestamo",creditoCabeceraBO.getMontoPrestamo());
		request.setAttribute("bonoHipotecario",creditoCabeceraBO.getBonoBuenPagador());
		request.setAttribute("totalRecibir",creditoCabeceraBO.getTotalRecibido());
		request.setAttribute("moneda", creditoCabeceraBO.getMoneda());
		request.setAttribute("fechaDesembolso", creditoCabeceraBO.getFechaDesembolso());
		request.setAttribute("valorTEA", creditoCronogramaBO.getValorTEA());
		request.setAttribute("seguroDesgravamen", creditoCabeceraBO.getSeguroDesgravamen());
		request.setAttribute("numCuotas", creditoCabeceraBO.getNumCuotas());
		request.setAttribute("tipoPeriodoGracia", creditoCabeceraBO.getTipoInteresPerGracia());
		request.setAttribute("seguroTodoRiesgo", creditoCabeceraBO.getSeguroTodoRiesgo());
		request.setAttribute("valorTCEA", creditoCronogramaBO.getValorTCEA());

		if (creditoCabeceraBO.equals("0.00")){
			request.setAttribute("valorCuota", "----");
			request.setAttribute("valorCuotaSinBono", creditoCronogramaBO.getValorCuotaBonoPagador());
		}else{
			request.setAttribute("valorCuota", creditoCronogramaBO.getValorCuotaBonoPagador());
			request.setAttribute("valorCuotaSinBono", creditoCronogramaBO.getValorCuotaSinBonoPagador());
		}
		
		//Totales
		request.setAttribute("totalIntereses", creditoCronogramaBO.getSumaInteres());
		request.setAttribute("totalAmortizaciones", creditoCronogramaBO.getSumaAmortizaciones() );
		request.setAttribute("totalCuotas",creditoCronogramaBO.getSumaCuotas());
		request.setAttribute("totalSegDesgravamen",creditoCronogramaBO.getSumaSegDesgravamen());
		request.setAttribute("totalSegRiesgo", creditoCronogramaBO.getSumaSegTodoRiesgo());
		request.setAttribute("totalITF", creditoCronogramaBO.getSumalITF());
		request.setAttribute("totalCuotaFinal", creditoCronogramaBO.getSumaCuotaFinal());

		//Lista de cuota
		request.setAttribute("listaCuotasCredito", listaCuotasCredito);

		//Redirecciona a pagina
		RequestDispatcher dispatcher;
		if (creditoCronogramaBO.isPeriodoGracia()){
			dispatcher = getServletContext().getRequestDispatcher(ResponsePage.PAGE_CRED_HIPO_TEPP_GRACIA);
		}else{
			dispatcher = getServletContext().getRequestDispatcher(ResponsePage.PAGE_CRED_HIPO_TEPP);
		}
		dispatcher.forward(request, response);
		
		//Guarda resultados en sesion
		sesion_actual.setAttribute("creditoCabeceraHipotecarioBO", (CreditoCabeceraHipotecarioBO) creditoCabeceraBO);
		sesion_actual.setAttribute("creditoCronogramaHipotecarioBO", (CreditoCronogramaHipotecarioBO) creditoCronogramaBO);
		sesion_actual.setAttribute("listaCuotasCredito", (List<Cuota>) listaCuotasCredito);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
