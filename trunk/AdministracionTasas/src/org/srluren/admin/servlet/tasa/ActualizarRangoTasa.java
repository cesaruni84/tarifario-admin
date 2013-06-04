package org.srluren.admin.servlet.tasa;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.srluren.admin.beans.hb.Tasa;
import org.srluren.admin.resources.Parameters;
import org.srluren.admin.services.impl.TasaServicesImpl;

/**
 * Servlet implementation class ActualizarRangoTasa
 */
public class ActualizarRangoTasa extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public String CODIGO_RETORNO;
	public String MENSAJE_RETORNO;
	public Parameters parameters;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActualizarRangoTasa() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		procesar(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		procesar(request, response);
	}

	@SuppressWarnings({ "unused" })
	private void procesar(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

		// Obtiene session
		HttpSession sesion_actual = request.getSession(true);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		parameters = Parameters.getInstance();

		//Respuesta
		JSONObject responseJSON = new JSONObject();
		
		// Define objetos a utilizar
		TasaServicesImpl tasaServicesImpl = new TasaServicesImpl();
		Tasa beanRangoTasaActual = new Tasa();
		

		try {
			
			// Obtiene datos de sesión
			String idSubProducto = (String)sesion_actual.getAttribute("idSubProducto");
			String idTipoPersona = (String)sesion_actual.getAttribute("idTipoPersona");
			String idMoneda = (String)sesion_actual.getAttribute("idMoneda");
			
			// Obtiene valor de tasa y rango del formulario
			BigDecimal valorTasaMin = new BigDecimal(request.getParameter("valorTasaMin"));
			BigDecimal valorTasaMax = new BigDecimal(request.getParameter("valorTasaMax"));
			int plazoInicialAct = Integer.parseInt(request.getParameter("plazoInicial"));
			int plazoFinalAct = Integer.parseInt(request.getParameter("plazoFinal"));
			int unidadPlazoAct = Integer.parseInt(request.getParameter("unidadPlazo"));
			int rango_actual = Integer.parseInt(request.getParameter("idRango"));

			// Obtiene lista de tasas de la sesion
			List<Tasa> listaRangoTasasPlazo = tasaServicesImpl.listarTasasxPlazos((String)sesion_actual.getAttribute("idSubProducto"), 
					(String)sesion_actual.getAttribute("idTipoPersona"), 
					(String)sesion_actual.getAttribute("idMoneda"), 
					String.valueOf(plazoInicialAct), 
					String.valueOf(plazoFinalAct), 
					String.valueOf(unidadPlazoAct));
			
			//Obtiene cantidad de registros
			int cantidadRegistros = listaRangoTasasPlazo.size();

			// Recupera datos para validacion de rangos
			String tipoProducto = (String) sesion_actual.getAttribute("tipoProducto");
			
			//Validando tasas
			if (validarTasas(listaRangoTasasPlazo,valorTasaMin, valorTasaMax,rango_actual,tipoProducto) || cantidadRegistros == 1) {

				// Obtiene objeto a actualizar
				beanRangoTasaActual = listaRangoTasasPlazo.get(rango_actual - 1);

				// Actualiza valor de tasa actual
				beanRangoTasaActual.setValTasaMin(valorTasaMin);
				beanRangoTasaActual.setValTasaMax(valorTasaMax);
				beanRangoTasaActual.setPlazoInicial(plazoInicialAct);
				beanRangoTasaActual.setPlazoFinal(plazoFinalAct);
				Integer idbeanRangoTasaActual = tasaServicesImpl.actualizarTasa(beanRangoTasaActual);

				// Devuelve mensaje
				responseJSON.put("cod_retorno", parameters.getKey("COD_ACT_RANGO_OK"));
				responseJSON.put("msj_retorno", parameters.getKey("MSJ_ACT_RANGO_OK"));
				out.println(responseJSON.toString());
				
			} else {
				// Devuelve mensaje: error en datos ingresados
				responseJSON.put("cod_retorno", CODIGO_RETORNO);
				responseJSON.put("msj_retorno", MENSAJE_RETORNO);
				out.println(responseJSON.toString());

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Devuelve mensaje: error
			responseJSON.put("cod_retorno", parameters.getKey("COD_ACT_RANGO_NOK"));
			responseJSON.put("msj_retorno", parameters.getKey("MSJ_ACT_RANGO_NOK"));
			out.println(responseJSON);
		}

	}

	/**
	 * @param listaRangoTasas
	 * @param valorTasa
	 * @param rango_actual
	 * @param tipoProducto
	 * @return
	 */
	private boolean validarTasas(
			List<Tasa> listaRangoTasas, 
			BigDecimal valorTasaMin,
			BigDecimal valorTasaMax,
			int rango_actual, 
			String tipoProducto) 
	{
		// TODO Auto-generated method stub
		return true;
		
		//Define variables
		/*boolean validacion = false;
		Tasa beanRangoTasaAnterior = new Tasa();
		Tasa beanRangoTasaSiguiente = new Tasa();
		BigDecimal valTasaAnteriorMin;
		BigDecimal valTasaAnteriorMax;
		BigDecimal valTasaSiguienteMin;
		BigDecimal valTasaSiguienteMax;
		
		//Obtiene cantidad de registros
		int cantidadRegistros = listaRangoTasas.size();
		
		//Validando por rango anterior
		if (rango_actual >= 2){
			//Obtiene rango anterior
			beanRangoTasaAnterior = listaRangoTasas.get(rango_actual - 2);
			valTasaAnteriorMin = beanRangoTasaAnterior.getValTasaMin();
			valTasaAnteriorMax = beanRangoTasaAnterior.getValTasaMax();

		} else {
			 //Valor por defecto
			if (tipoProducto.equals("A")){//Para activos
				valTasaAnteriorMin = new BigDecimal(9999);
				valTasaAnteriorMax = new BigDecimal(9999);
			} else{ // Para producto pasivo
				valTasaAnteriorMin = new BigDecimal(0);
				valTasaAnteriorMax = new BigDecimal(0);

			}
			
		}
		
		//Validando por rango siguiente
		if (rango_actual < cantidadRegistros){
			//Obtiene rango siguiente
			beanRangoTasaSiguiente = listaRangoTasas.get(rango_actual);
			valTasaSiguienteMin = beanRangoTasaSiguiente.getValTasaMin();
			valTasaSiguienteMax = beanRangoTasaSiguiente.getValTasaMax();
			
		} else{
			 //Valor por defecto
			if (tipoProducto.equals("A")){
				valTasaSiguienteMin = new BigDecimal(0);
				valTasaSiguienteMax = new BigDecimal(0);
			} else {
				valTasaSiguienteMin = new BigDecimal(9999);
				valTasaSiguienteMax = new BigDecimal(9999);
			}
			
		}
		
		
		//*******COMPARANDO TASA MINIMA *************
		
		//Si producto es Activo: tasas descendentes
		if (tipoProducto.equals("A")){
			if (( valorTasaMin.compareTo(valTasaAnteriorMin) <0 )){ 
				if ((valorTasaMin.compareTo(valTasaSiguienteMin)>0)){
					// Datos corrrectos, procede a grabar
					validacion = true;
				} else{
					//Devuelve mensaje
					CODIGO_RETORNO  = parameters.getKey("COD_ACT_RANGO_NOK_2");
					MENSAJE_RETORNO = parameters.getKey("MSJ_ACT_RANGO_NOK_MAYOR")+ " " + valTasaSiguienteMin.toString() + "%";;
				}
				
			} else{
				//Devuelve mensaje
				CODIGO_RETORNO  = parameters.getKey("COD_ACT_RANGO_NOK_1");
				MENSAJE_RETORNO = parameters.getKey("MSJ_ACT_RANGO_NOK_MENOR") + " " + valTasaAnteriorMin.toString() + "%";
			}
			
		}else{	//Si producto es Pasivo: tasas ascendentes

			if (( valorTasaMin.compareTo(valTasaAnteriorMin)>0 )){
					if ((valorTasaMin.compareTo(valTasaSiguienteMin)<0)){
						// Datos corrrectos, procede a grabar
						validacion = true;
					}else{
						//Devuelve mensaje
						CODIGO_RETORNO  = parameters.getKey("COD_ACT_RANGO_NOK_4");
						MENSAJE_RETORNO = parameters.getKey("MSJ_ACT_RANGO_NOK_MENOR")+ " " + valTasaSiguienteMin.toString() + "%";;
					}
			} else{
				//Devuelve mensaje
				CODIGO_RETORNO  = parameters.getKey("COD_ACT_RANGO_NOK_3");
				MENSAJE_RETORNO = parameters.getKey("MSJ_ACT_RANGO_NOK_MAYOR") + " " + valTasaAnteriorMin.toString() + "%";;
			}
			
			
		}
		
		//*******COMPARANDO TASA MAXIMA *************
		
		//Si producto es Activo: tasas descendentes
		if (tipoProducto.equals("A")){
			if (( valorTasaMax.compareTo(valTasaAnteriorMax) <0 )){ 
				if ((valorTasaMax.compareTo(valTasaSiguienteMax)>0)){
					// Datos corrrectos, procede a grabar
					validacion = true;
				} else{
					//Devuelve mensaje
					CODIGO_RETORNO  = parameters.getKey("COD_ACT_RANGO_NOK_2");
					MENSAJE_RETORNO = parameters.getKey("MSJ_ACT_RANGO_NOK_MAYOR")+ " " + valTasaSiguienteMax.toString() + "%";;
				}
				
			} else{
				//Devuelve mensaje
				CODIGO_RETORNO  = parameters.getKey("COD_ACT_RANGO_NOK_1");
				MENSAJE_RETORNO = parameters.getKey("MSJ_ACT_RANGO_NOK_MENOR") + " " + valTasaAnteriorMax.toString() + "%";
			}
			
		}else{	//Si producto es Pasivo: tasas ascendentes
			if (( valorTasaMax.compareTo(valTasaAnteriorMax)>0 )){
					if ((valorTasaMax.compareTo(valTasaSiguienteMax)<0)){
						// Datos corrrectos, procede a grabar
						validacion = true;
					}else{
						//Devuelve mensaje
						CODIGO_RETORNO  = parameters.getKey("COD_ACT_RANGO_NOK_4");
						MENSAJE_RETORNO = parameters.getKey("MSJ_ACT_RANGO_NOK_MENOR")+ " " + valTasaSiguienteMax.toString() + "%";;
					}
			} else{
				//Devuelve mensaje
				CODIGO_RETORNO  = parameters.getKey("COD_ACT_RANGO_NOK_3");
				MENSAJE_RETORNO = parameters.getKey("MSJ_ACT_RANGO_NOK_MAYOR") + " " + valTasaAnteriorMax.toString() + "%";;
			}
		}	
		//Retorna código
		return validacion;*/
		
	}

}
