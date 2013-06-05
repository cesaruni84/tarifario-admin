package org.srluren.web.servlet.tasa;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.srluren.web.resources.Parameters;
import org.srluren.web.beans.hb.Tasa;
import org.srluren.web.services.impl.TasaServicesImpl;

/**
 * Servlet implementation class ValidarTasasMontosServlet
 */
public class ValidarTasaCreConsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarTasaCreConsServlet() {
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
	
	@SuppressWarnings("unchecked")
	private void procesar(HttpServletRequest request,HttpServletResponse response) {
		
		/*SERVICIOS PARA OBTENER INFORMACION DE TASAS*/
		TasaServicesImpl tasaServicesImpl = new TasaServicesImpl();
		Tasa beanTasa					  = null;
		
		/*VARIABLES*/
		PrintWriter out 		= null;
		Parameters parameters 	= Parameters.getInstance();
		
		/*TASAS A OBTENER*/
		Double	TEA_MIN	= (Double) 0.00;
		Double	TEA_MAX	= (Double) 0.00;
		
		/*RESPUESTA*/
		JSONObject responseJSON = new JSONObject();
		
		try {
			/*FLUJO DE SALIDA*/
			out = response.getWriter();
			
			
			/*OBJETOS PARA RECIBIR LA TRAMA EN FORMATO JSON*/
			JSONParser jsonParser = new JSONParser();
			JSONObject json 	  =(JSONObject) jsonParser.parse(request.getParameter("jsonRequest"));
			
			/*CAMPOS A ENVIAR EN LA CONSULTA*/
			String idSubProducto = (String) request.getSession().getAttribute("idSubProducto");
			
			/*OBTIENE CAMPOS DE FORMULARIO*/
			String idTipoPersona	= json.get("tipoPersona").toString();
			String idMoneda			= json.get("moneda").toString();
			String monto			= json.get("montoCredito").toString();
			String plazo			= json.get("numCuotas").toString();
			Double TEA				= Double.parseDouble(json.get("valorTasa").toString());
			
			/*OBTIENE TASAS*/
			beanTasa				= tasaServicesImpl.buscarRangoxMontoPlazo(idSubProducto, idTipoPersona, idMoneda, monto, plazo);
			
			if(beanTasa != null){
				TEA_MIN		= beanTasa.getValTasaMin().doubleValue();
				TEA_MAX		= beanTasa.getValTasaMax().doubleValue();
				
				
				System.out.println(TEA_MIN);
				/*VALIDA SI LA TASA INGRESADA SE ENCUENTRA EN LOS RANGOS ESTABLECIDOS*/
				if ((TEA >= TEA_MIN) && (TEA <= TEA_MAX)){
					responseJSON.put("cod_retorno", parameters.getKey("COD_VAL_TASA_OK"));
					responseJSON.put("msj_retorno", parameters.getKey("MSJ_VAL_TASA_OK"));
					
				}else{
					responseJSON.put("cod_retorno", parameters.getKey("COD_VAL_TASA_NOK"));
					responseJSON.put("msj_retorno", parameters.getKey("MSJ_VAL_TASA_NOK") +  ": "+ TEA_MIN + "% - " + TEA_MAX + "%" );
				}
			}else{
				responseJSON.put("cod_retorno", parameters.getKey("COD_VAL_TASA_OK"));
				responseJSON.put("msj_retorno", parameters.getKey("MSJ_VAL_TASA_OK"));
			}
			
			//Devuelve valor
			out.println(responseJSON.toString());

		} catch (Exception e) {
			e.printStackTrace();
			responseJSON.put("cod_retorno", parameters.getKey("COD_VAL_TASA_ERR"));
			responseJSON.put("msj_retorno", parameters.getKey("MSJ_VAL_TASA_ERR"));
			out.println(responseJSON.toString());
		}
		
	}

}
