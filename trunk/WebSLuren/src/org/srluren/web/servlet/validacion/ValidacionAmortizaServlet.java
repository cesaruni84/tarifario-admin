package org.srluren.web.servlet.validacion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.sim.credito.bean.Credito;
import org.sim.credito.util.Utilitario;
import org.sim.credito.validacion.ValidacionService;
import org.srluren.web.resources.Application;
import org.srluren.web.resources.Parameters;


/**
 * Servlet implementation class ValidacionAmortizaServlet
 */
public class ValidacionAmortizaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidacionAmortizaServlet() {
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
	
	@SuppressWarnings("unchecked")
	private void procesar(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
	
	
		// Servicio de generacion de cronograma
		ValidacionService validacionService = new ValidacionService();
		Credito credito=new Credito();
		Parameters parameters = Parameters.getInstance();
		Application application = Application.getInstance();

		// La respuesta la parseamos en formato JSON
		JSONParser jsonParser = new JSONParser();
		JSONObject responseJSON = new JSONObject();
		JSONObject json;
		PrintWriter out = response.getWriter();

		try {
			
			//Realizar parseo
			json = (JSONObject) jsonParser.parse(request.getParameter("jsonRequest"));

			// Genera objeto con los datos del credito
			credito.setFechaDesembolso(Utilitario.convertStringToDate(json.get("fechaDesembolso").toString(), "dd/MM/yyyy"));
			credito.setFormaPago(json.get("formaPago").toString());
			credito.setMoneda(json.get("moneda").toString());
			credito.setMontoPrestamo(Double.parseDouble(json.get("montoCredito").toString()));
			credito.setNumCuotas(Integer.parseInt(json.get("numCuotas").toString()));
			credito.setDiaPago(Integer.parseInt(json.get("diaPago").toString()));
			credito.setPeriodoGracia(Integer.parseInt(json.get("diasGracia").toString()));
			credito.setTipoInteresPerGracia(Integer.parseInt(json.get("tipoInteresPerGracia").toString()));
			credito.setPeriodoPago(30); // Valor por defecto
			credito.setSubProducto(json.get("subProducto").toString());
			credito.setTipoPersona(json.get("tipoPersona").toString());
			credito.setValorTasa(Double.valueOf(json.get("valorTasa").toString()));

			if (json.get("divinoSeguro").toString().equalsIgnoreCase("1")) {
				credito.setSeguroDivino(true);

			} else {
				credito.setSeguroDivino(false);
			}
			
			Double SEG_DIV_SOL = Double.valueOf(application.getKey("SEG_DIV_SOLES"));
			Double SEG_DIV_DOL = Double.valueOf(application.getKey("SEG_DIV_DOLARES"));
			credito.setValorDivSeguroSoles(SEG_DIV_SOL);
			credito.setValorDivSeguroDolares(SEG_DIV_DOL);

			if (json.get("seguroDesgravamen").toString().equalsIgnoreCase("1")) {
				credito.setSeguroDesgravamen(true);
				Double SEGURO_DESGRAVAMEN = Double.valueOf(application.getKey("PORCENTAJE_SEG_DESGRAV"));
				credito.setValorSeguroDesgravamen(SEGURO_DESGRAVAMEN);
			} else {
				credito.setSeguroDesgravamen(false);
				credito.setValorSeguroDesgravamen(0.00);
			}

			// Parametros constantes
			Double ITF = Double.valueOf(parameters.getKey("ITF"));
			credito.setValorITF(ITF);
			
			// Valida Amortizacion
			boolean validacionOK = validacionService.validarAmortizacionCredito(credito);
			
			if(validacionOK){
				responseJSON.put("cod_retorno", parameters.getKey("COD_VAL_AMORTIZA_OK"));
				responseJSON.put("msj_retorno", parameters.getKey("MSJ_VAL_AMORTIZA_OK"));
			}else{
				responseJSON.put("cod_retorno", parameters.getKey("COD_VAL_AMORTIZA_NOK"));
				responseJSON.put("msj_retorno", parameters.getKey("MSJ_VAL_AMORTIZA_NOK"));
			}

			//Respuesta
			out.println(responseJSON.toString());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseJSON.put("cod_retorno", parameters.getKey("COD_VAL_AMORTIZA_ERR"));
			responseJSON.put("msj_retorno", parameters.getKey("MSJ_VAL_AMORTIZA_ERR"));
			out.println(responseJSON.toString());
		}


	
	}

}
