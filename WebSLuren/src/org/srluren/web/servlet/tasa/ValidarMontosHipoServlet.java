package org.srluren.web.servlet.tasa;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.sim.credito.resources.Constantes;
import org.srluren.web.beans.hb.Tasa;
import org.srluren.web.resources.Application;
import org.srluren.web.resources.Parameters;
import org.srluren.web.services.impl.TasaServicesImpl;

/**
 * Servlet implementation class ValidarMontosHipoServlet
 */
public class ValidarMontosHipoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarMontosHipoServlet() {
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
			HttpServletResponse response) {

		/*SERVICIOS PARA OBTENER INFORMACION DE TASAS*/
		TasaServicesImpl tasaServicesImpl = new TasaServicesImpl();
		Tasa beanTasa					  = null;
		
		/*VARIABLES*/
		PrintWriter out 		= null;
		Parameters parameters 	= Parameters.getInstance();
		
		/*MONTOS A OBTENER*/
		long MONTO_MIN	=  0;
		long MONTO_MAX	=  0;
		
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
			String plazo			= json.get("numCuotas").toString();
			Long montoCredito		= obtenerMontoFinanciar(Long.parseLong(json.get("montoCredito").toString()));
			
			/*OBTIENE OBJETO CON LOS MONTOS*/
			beanTasa				= tasaServicesImpl.buscarRangoxMonto(idSubProducto, idTipoPersona, idMoneda, plazo);
			
			/*SIMBOLO DE MONEDA*/
			String SIM_MONEDA="";
			if(idMoneda.equals(String.valueOf(Constantes.MONEDA_SOLES))){
				SIM_MONEDA = "S/.";
			}else{
				SIM_MONEDA = "$";
			}
			
			
			if(beanTasa != null){
				MONTO_MIN		= beanTasa.getMtoInicial();
				MONTO_MAX		= beanTasa.getMtoFinal();
				
				/*VALIDA SI EL MONTO INGRESADO SE ENCUENTRA EN LOS RANGOS ESTABLECIDOS*/
				if ((montoCredito >= MONTO_MIN) && (montoCredito <= MONTO_MAX)){
					responseJSON.put("cod_retorno", parameters.getKey("COD_VAL_MONTOS_OK"));
					responseJSON.put("msj_retorno", parameters.getKey("MSJ_VAL_MONTOS_OK"));
					
				}else{
					responseJSON.put("cod_retorno", parameters.getKey("COD_VAL_MONTOS_NOK"));
					responseJSON.put("msj_retorno", parameters.getKey("MSJ_VAL_MONTOS_NOK") +  ": "+ SIM_MONEDA+" "+MONTO_MIN + " - " + SIM_MONEDA+" "+MONTO_MAX );
				}
			}else{
				responseJSON.put("cod_retorno", parameters.getKey("COD_VAL_MONTOS_OK"));
				responseJSON.put("msj_retorno", parameters.getKey("MSJ_VAL_MONTOS_OK"));
			}
			
			//Devuelve valor
			out.println(responseJSON.toString());

		} catch (Exception e) {
			e.printStackTrace();
			responseJSON.put("cod_retorno", parameters.getKey("COD_VAL_MONTOS_ERR"));
			responseJSON.put("msj_retorno", parameters.getKey("MSJ_VAL_MONTOS_ERR"));
			out.println(responseJSON.toString());
		}
		
		
	}
	
	private Long obtenerMontoFinanciar(Long MONTO_PRESTAMO ){
		
		//Variables
		Long montoFinanciar = (long) 0;
		Application application = Application.getInstance();
		Double bonoBuenPagador = (Double)0.00;
		
		//Lee parametros
		Double BONO_BUEN_PAGADOR_MIVV_1  = Double.valueOf(application.getKey("BONO_BUEN_PAGADOR_MIVV_1"));
		Double MTO_MIN_PREST_BNPG_MIVV_1 = Double.valueOf(application.getKey("MTO_MIN_PREST_BNPG_MIVV_1"));
		Double MTO_MAX_PREST_BNPG_MIVV_1 = Double.valueOf(application.getKey("MTO_MAX_PREST_BNPG_MIVV_1"));
		Double BONO_BUEN_PAGADOR_MIVV_2  = Double.valueOf(application.getKey("BONO_BUEN_PAGADOR_MIVV_2"));
		Double MTO_MIN_PREST_BNPG_MIVV_2 = Double.valueOf(application.getKey("MTO_MIN_PREST_BNPG_MIVV_2"));
		Double MTO_MAX_PREST_BNPG_MIVV_2 = Double.valueOf(application.getKey("MTO_MAX_PREST_BNPG_MIVV_2"));
		
		//Realiza validación
		if ((MONTO_PRESTAMO >= MTO_MIN_PREST_BNPG_MIVV_1) && (MONTO_PRESTAMO <= MTO_MAX_PREST_BNPG_MIVV_1)){
			bonoBuenPagador = BONO_BUEN_PAGADOR_MIVV_1;
		}else{
			if ((MONTO_PRESTAMO >= MTO_MIN_PREST_BNPG_MIVV_2) && (MONTO_PRESTAMO <= MTO_MAX_PREST_BNPG_MIVV_2)){
				bonoBuenPagador = BONO_BUEN_PAGADOR_MIVV_2;
			}
		}
		
		//Calcula valor
		montoFinanciar = (long) (MONTO_PRESTAMO - bonoBuenPagador);
		
		//Retorna valor
		return montoFinanciar;
		
	}


}
