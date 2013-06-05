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
import org.sim.credito.bo.CreditoCabeceraBO;
import org.sim.credito.bo.CreditoCronogramaBO;
import org.srluren.web.manager.impl.CreditoConsumoManager;
import org.srluren.web.resources.ResponsePage;

/**
 * Servlet implementation class GenCronoKivaServlet
 */
public class GenCronoKivaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenCronoKivaServlet() {
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

		HttpSession session = request.getSession(true); 

		try{
			
		//Servicio de Generacion de Credito
		CreditoConsumoManager consumoManagerImpl = new CreditoConsumoManager();
		
		//Obtiene cronograma de credito
		CreditoCronogramaBO creditoCronogramaBO = consumoManagerImpl.generarCronograma(request.getParameter("jsonRequest"),null);
		List<Cuota> listaCuotasCredito =  creditoCronogramaBO.getListaCuotasCreditoBO();
		
		//Devuelve datos de cabecera a mostrar en el formulario de resultados
		CreditoCabeceraBO creditoCabeceraBO = consumoManagerImpl.getDatosCabeceraCredito();
		creditoCabeceraBO.setProducto((String) request.getSession().getAttribute("nombreProducto"));
		creditoCabeceraBO.setSubProducto((String) request.getSession().getAttribute("nombreSubProducto"));

		//Setea datos a mostrar: Cabecera
		request.setAttribute("subProducto", creditoCabeceraBO.getSubProducto());
		request.setAttribute("montoPrestamo",creditoCabeceraBO.getMontoPrestamo());
		request.setAttribute("tipoPersona", creditoCabeceraBO.getTipoPersona());
		request.setAttribute("moneda", creditoCabeceraBO.getMoneda());
		request.setAttribute("numCuotas", creditoCabeceraBO.getNumCuotas());
		request.setAttribute("fechaDesembolso", creditoCabeceraBO.getFechaDesembolso());
		request.setAttribute("formaPago", creditoCabeceraBO.getFormaPago());
		request.setAttribute("seguroDesgravamen", creditoCabeceraBO.getSeguroDesgravamen());
		request.setAttribute("valorTEA", creditoCronogramaBO.getValorTEA());
		request.setAttribute("valorTCEA", creditoCronogramaBO.getValorTCEA());
		
		//Setea datos a mostrar: Totales
		request.setAttribute("totalIntereses", creditoCronogramaBO.getSumaInteres());
		request.setAttribute("totalAmortizaciones", creditoCronogramaBO.getSumaAmortizaciones() );
		request.setAttribute("totalCuotas",creditoCronogramaBO.getSumaCuotas());
		request.setAttribute("totalSegDesgravamen",creditoCronogramaBO.getSumaSegDesgravamen());
		request.setAttribute("totalSegDivino", creditoCronogramaBO.getSumaSegDivino());
		request.setAttribute("totalITF", creditoCronogramaBO.getSumalITF());
		request.setAttribute("totalCuotaFinal", creditoCronogramaBO.getSumaCuotaFinal());

		//Lista de cuotas
		request.setAttribute("listaCuotasCredito", listaCuotasCredito);

		//Redirecciona a pagina
		RequestDispatcher dispatcher;
		if (creditoCronogramaBO.isPeriodoGracia()){
			dispatcher = getServletContext().getRequestDispatcher(ResponsePage.PAGE_CRED_KIVA_GRACIA);
		}else{
			dispatcher = getServletContext().getRequestDispatcher(ResponsePage.PAGE_CRED_KIVA);
		}
		dispatcher.forward(request, response);
		
		
		//Guarda resultados en sesion
		session.setAttribute("creditoCabeceraBO", (CreditoCabeceraBO) creditoCabeceraBO);
		session.setAttribute("creditoCronogramaBO", (CreditoCronogramaBO) creditoCronogramaBO);
		session.setAttribute("listaCuotasCredito", (List<Cuota>) listaCuotasCredito);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


}
