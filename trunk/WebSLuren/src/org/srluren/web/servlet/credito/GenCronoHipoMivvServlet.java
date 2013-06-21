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
import org.srluren.web.manager.impl.CreditoHipoMiVVManager;
import org.srluren.web.resources.ResponsePage;

/**
 * Servlet implementation class GenCronoHipoMivvServlet
 */
public class GenCronoHipoMivvServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenCronoHipoMivvServlet() {
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
		CreditoHipoMiVVManager creditoHipotecarioManager = new CreditoHipoMiVVManager();

		//Obtiene cronograma de credito
		CreditoCronogramaHipotecarioBO creditoCronogramaHipotecarioBO = creditoHipotecarioManager.generarCronograma(request.getParameter("jsonRequest"),sesion_actual.getAttribute("idSubProducto").toString());
		List<Cuota> listaCuotasCredito =  creditoCronogramaHipotecarioBO.getListaCuotasCreditoBO();
		
		//Devuelve datos de cabecera a mostrar en el formulario de resultados
		CreditoCabeceraHipotecarioBO creditoCabeceraHipotecarioBO = creditoHipotecarioManager.getDatosCabeceraCredito();
		creditoCabeceraHipotecarioBO.setProducto((String) request.getSession().getAttribute("nombreProducto"));
		creditoCabeceraHipotecarioBO.setSubProducto((String) request.getSession().getAttribute("nombreSubProducto"));
		
		//Setea datos a mostrar: Cabecera
		request.setAttribute("subProducto", creditoCabeceraHipotecarioBO.getSubProducto());
		request.setAttribute("valorInmueble", creditoCabeceraHipotecarioBO.getMontoInmueble());
		request.setAttribute("montoPrestamo",creditoCabeceraHipotecarioBO.getMontoPrestamo());
		request.setAttribute("bonoHipotecario",creditoCabeceraHipotecarioBO.getBonoBuenPagador());
		request.setAttribute("totalRecibir",creditoCabeceraHipotecarioBO.getTotalRecibido());
		request.setAttribute("moneda", creditoCabeceraHipotecarioBO.getMoneda());
		request.setAttribute("fechaDesembolso", creditoCabeceraHipotecarioBO.getFechaDesembolso());
		request.setAttribute("valorTEA", creditoCronogramaHipotecarioBO.getValorTEA());
		request.setAttribute("seguroDesgravamen", creditoCabeceraHipotecarioBO.getSeguroDesgravamen());
		request.setAttribute("numCuotas", creditoCabeceraHipotecarioBO.getNumCuotas());
		request.setAttribute("tipoPeriodoGracia", creditoCabeceraHipotecarioBO.getTipoInteresPerGracia());
		request.setAttribute("seguroTodoRiesgo", creditoCabeceraHipotecarioBO.getSeguroTodoRiesgo());
		request.setAttribute("valorTCEA", creditoCronogramaHipotecarioBO.getValorTCEA());

		if (creditoCabeceraHipotecarioBO.equals("0.00")){
			request.setAttribute("valorCuota", "----");
			request.setAttribute("valorCuotaSinBono", creditoCronogramaHipotecarioBO.getValorCuotaBonoPagador());
		}else{
			request.setAttribute("valorCuota", creditoCronogramaHipotecarioBO.getValorCuotaBonoPagador());
			request.setAttribute("valorCuotaSinBono", creditoCronogramaHipotecarioBO.getValorCuotaSinBonoPagador());
		}
		
		//Totales
		request.setAttribute("totalIntereses", creditoCronogramaHipotecarioBO.getSumaInteres());
		request.setAttribute("totalAmortizaciones", creditoCronogramaHipotecarioBO.getSumaAmortizaciones() );
		request.setAttribute("totalCuotas",creditoCronogramaHipotecarioBO.getSumaCuotas());
		request.setAttribute("totalSegDesgravamen",creditoCronogramaHipotecarioBO.getSumaSegDesgravamen());
		request.setAttribute("totalSegRiesgo", creditoCronogramaHipotecarioBO.getSumaSegTodoRiesgo());
		request.setAttribute("totalITF", creditoCronogramaHipotecarioBO.getSumalITF());
		request.setAttribute("totalCuotaFinal", creditoCronogramaHipotecarioBO.getSumaCuotaFinal());

		//Lista de cuota
		request.setAttribute("listaCuotasCredito", listaCuotasCredito);
		
		//Cuota cuota_eval0 =listaCuotasCredito.get(0);
		
		//if(cuota_eval0.getMontoCapital()<0){}
		
		//Redirecciona a pagina
		RequestDispatcher dispatcher;
		if (creditoCronogramaHipotecarioBO.isPeriodoGracia()){
			dispatcher = getServletContext().getRequestDispatcher(ResponsePage.PAGE_CRED_HIPO_MIVV_GRACIA);
		}else{
			dispatcher = getServletContext().getRequestDispatcher(ResponsePage.PAGE_CRED_HIPO_MIVV);
		}
		dispatcher.forward(request, response);
		
		//Guarda resultados en sesion
		sesion_actual.setAttribute("creditoCabeceraHipotecarioBO", (CreditoCabeceraHipotecarioBO) creditoCabeceraHipotecarioBO);
		sesion_actual.setAttribute("creditoCronogramaHipotecarioBO", (CreditoCronogramaHipotecarioBO) creditoCronogramaHipotecarioBO);
		sesion_actual.setAttribute("listaCuotasCredito", (List<Cuota>) listaCuotasCredito);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
