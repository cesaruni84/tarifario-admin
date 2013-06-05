package org.srluren.web.servlet.tasa;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.sim.credito.resources.Constantes;
import org.sim.credito.util.Utilitario;
import org.srluren.web.resources.Parameters;

/**
 * Servlet implementation class ValidarFechasPagoServlet
 */
public class ValidarFechasPagoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarFechasPagoServlet() {
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
	private void procesar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		/*VARIABLES*/
		PrintWriter out 		= null;
		Parameters parameters 	= Parameters.getInstance();
		int numDiasFechas		= 0;
		
		/*DIAS MIN-MAX ENTRE LA FECHA DE DESEMBOLSO Y LA FECHA DE PAGO DE LA PRIMERA CUOTA*/
		int DIAS_MIN	=  30;
		int DIAS_MAX	=  40;
		
		/*RESPUESTA*/
		JSONObject responseJSON = new JSONObject();
		
		try {
			/*FLUJO DE SALIDA*/
			out = response.getWriter();
			
			/*OBJETOS PARA RECIBIR LA TRAMA EN FORMATO JSON*/
			JSONParser jsonParser = new JSONParser();
			JSONObject json 	  =(JSONObject) jsonParser.parse(request.getParameter("jsonRequest"));
			
			/*OBTIENE CAMPOS DE FORMULARIO*/
			Date fechaDesembolso	= Utilitario.convertStringToDate(json.get("fechaDesembolso").toString(), Constantes.FORMATO_FECHAS);
			int diaPago				= Integer.parseInt(json.get("diaPago").toString());

			/*1 ERA VALIDACION : MISMA FECHA MES*/
			//Date fechaMismoMes 		= Utilitario.obtenerFechaMes(1, diaPago, fechaDesembolso);
			
			/* CALCULA CANTIDAD DE DIAS*/
			//numDiasFechas = Utilitario.obtenerNumDias(fechaDesembolso, fechaMismoMes);
			
			//if ((numDiasFechas >= DIAS_MIN) && (numDiasFechas <= DIAS_MAX)){
				//responseJSON.put("cod_retorno", parameters.getKey("COD_VAL_FECHAS_OK"));
				//responseJSON.put("msj_retorno", parameters.getKey("MSJ_VAL_FECHAS_OK"));
			//}else{
				/*2DA VALIDACION : FECHA PROX MES*/
				Date fechaProxMes	= Utilitario.obtenerNuevaFecha(1, diaPago, fechaDesembolso);
				
				/* CALCULA CANTIDAD DE DIAS*/
				numDiasFechas = Utilitario.obtenerNumDias(fechaDesembolso, fechaProxMes);
				
				if ((numDiasFechas >= DIAS_MIN) && (numDiasFechas <= DIAS_MAX)){
					responseJSON.put("cod_retorno", parameters.getKey("COD_VAL_FECHAS_OK"));
					responseJSON.put("msj_retorno", parameters.getKey("MSJ_VAL_FECHAS_OK"));
				}else{
					responseJSON.put("cod_retorno", parameters.getKey("COD_VAL_FECHAS_NOK"));
					responseJSON.put("msj_retorno", parameters.getKey("MSJ_VAL_FECHAS_NOK"));
				}

			//}
	
			//Devuelve valor
			out.println(responseJSON.toString());

		} catch (Exception e) {
			e.printStackTrace();
			responseJSON.put("cod_retorno", parameters.getKey("COD_VAL_FECHAS_ERR"));
			responseJSON.put("msj_retorno", parameters.getKey("MSJ_VAL_FECHAS_ERR"));
			out.println(responseJSON.toString());
		}
		
		
	}

}
