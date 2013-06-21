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
 * Servlet implementation class ValidacionAmortizaHipoTeppServlet
 */
public class ValidacionAmortizaHipoTeppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidacionAmortizaHipoTeppServlet() {
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
			credito.setMoneda(json.get("moneda").toString());
			credito.setMontoPrestamo(Double.parseDouble(json.get("montoCredito").toString()));
			credito.setMontoInmueble(Double.parseDouble(json.get("valorInmueble").toString()));
			credito.setNumCuotas(Integer.parseInt(json.get("plazoPrestamo").toString()));
			credito.setPeriodoPago(30); // Por defecto asumimos mensual cada 30 dias
			credito.setPeriodoGracia(Integer.parseInt(json.get("periodoGracia").toString()));
			credito.setDiaPago(Integer.parseInt(json.get("diaPago").toString()));
			credito.setTipoInteresPerGracia(Integer.parseInt(json.get("tipoInteresPerGracia").toString()));
			credito.setSubProducto(json.get("subProducto").toString());
			credito.setValorTasa(Double.valueOf(json.get("valorTasa").toString()));

			//Lee parametros
			Double BONO_BUEN_PAGADOR_TEPP_1  = Double.valueOf(application.getKey("BONO_BUEN_PAGADOR_TEPP_1"));
			Double MTO_MIN_PREST_BNPG_TEPP_1 = Double.valueOf(application.getKey("MTO_MIN_PREST_BNPG_TEPP_1"));
			Double MTO_MAX_PREST_BNPG_TEPP_1 = Double.valueOf(application.getKey("MTO_MAX_PREST_BNPG_TEPP_1"));
			Double BONO_BUEN_PAGADOR_TEPP_2  = Double.valueOf(application.getKey("BONO_BUEN_PAGADOR_TEPP_2"));
			Double MTO_MIN_PREST_BNPG_TEPP_2 = Double.valueOf(application.getKey("MTO_MIN_PREST_BNPG_TEPP_2"));
			Double MTO_MAX_PREST_BNPG_TEPP_2 = Double.valueOf(application.getKey("MTO_MAX_PREST_BNPG_TEPP_2"));
			
			//Validación de montos para acceder al bono del buen pagador
			Double MONTO_PRESTAMO = Double.parseDouble(json.get("montoCredito").toString());
			if ((MONTO_PRESTAMO >= MTO_MIN_PREST_BNPG_TEPP_1) && (MONTO_PRESTAMO <= MTO_MAX_PREST_BNPG_TEPP_1)){
				credito.setValorBonoBuenPagador(BONO_BUEN_PAGADOR_TEPP_1);
			}else{
				if ((MONTO_PRESTAMO >= MTO_MIN_PREST_BNPG_TEPP_2) && (MONTO_PRESTAMO <= MTO_MAX_PREST_BNPG_TEPP_2)){
					credito.setValorBonoBuenPagador(BONO_BUEN_PAGADOR_TEPP_2);
				}else{
					credito.setValorBonoBuenPagador(0.00);
				}
			}
			
			//Seguro inmueble
			if (json.get("seguroRiesgo").toString().equalsIgnoreCase("1")) {
				Double SEGURO_RIESGO = Double.valueOf(application.getKey("PORCENTAJE_SEG_RIESGO"));
				credito.setSeguroRiesgo(true);
				credito.setValorSeguroTodoRiesgo(SEGURO_RIESGO);
			} else {
				credito.setSeguroRiesgo(false);
				credito.setValorSeguroTodoRiesgo(0.00);
			}

			//Valor por defecto
			credito.setSeguroDesgravamen(true);
			
			// Parametros constantes
			Double ITF = (Double) 0.00 ; // CC - ITF no aplica para hipotecarios
			Double SEGURO_DESGRAVAMEN = Double.valueOf(application.getKey("PORCENTAJE_SEG_DESGRAV"));
			credito.setValorITF(ITF);
			credito.setValorSeguroDesgravamen(SEGURO_DESGRAVAMEN);
			
			boolean validacionOK = validacionService.validarAmortizacionCreditoHipo(credito);

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
