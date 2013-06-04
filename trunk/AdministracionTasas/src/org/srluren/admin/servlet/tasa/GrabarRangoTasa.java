package org.srluren.admin.servlet.tasa;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.srluren.admin.beans.hb.Tasa;
import org.srluren.admin.resources.Parameters;
import org.srluren.admin.services.impl.MonedaServicesImpl;
import org.srluren.admin.services.impl.SubProductoServicesImpl;
import org.srluren.admin.services.impl.TasaServicesImpl;
import org.srluren.admin.services.impl.TipoPersonaServicesImpl;

/**
 * Servlet implementation class GrabarRangoTasaServlet
 */
public class GrabarRangoTasa extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public String CODIGO_RETORNO;
	public String MENSAJE_RETORNO;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GrabarRangoTasa() {
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
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		//Obtiene session 
		HttpSession sesion_actual = request.getSession(true);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Parameters parameters = Parameters.getInstance();
		
		//Define objetos a utilizar
		TasaServicesImpl tasaServicesImpl = new TasaServicesImpl();
		SubProductoServicesImpl subProductoServicesImpl = new SubProductoServicesImpl();
		TipoPersonaServicesImpl tipoPersonaServicesImpl = new TipoPersonaServicesImpl();
		MonedaServicesImpl monedaServicesImpl = new MonedaServicesImpl();
		Tasa nuevoRangoTasa = new Tasa();
		Tasa anteriorRangoTasa= new Tasa();
		Tasa anteriorRangoTasaPlazo= new Tasa();
		long montoAnterior = 0;
		BigDecimal valorTasaAnteriorMin;
		BigDecimal valorTasaAnteriorMax;
		
		//Respuesta
		JSONObject responseJSON = new JSONObject();
		
		try{
		
			//Obtiene valores de formulario
			BigDecimal valorTasaMin= new BigDecimal(request.getParameter("valorTasaMin"));
			BigDecimal valorTasaMax= new BigDecimal(request.getParameter("valorTasaMax"));
			long montoMinimo = Long.parseLong(request.getParameter("mtoMinimo"));
			long montoMaximo = Long.parseLong(request.getParameter("mtoMaximo"));
			int plazoInicial = Integer.parseInt(request.getParameter("plazoInicial"));
			int plazoFinal = Integer.parseInt(request.getParameter("plazoFinal"));
			int unidadPlazo = Integer.parseInt(request.getParameter("unidadPlazo"));
			String descripcionPlazo = null;
			
			//Obtiene tipo de producto
			String tipoProducto = (String)sesion_actual.getAttribute("tipoProducto");
			
			//Obtiene plazo máximo
			int PLAZO_MAXIMO =  Integer.parseInt(parameters.getKey("PLZO_MAXIMO").toString());
			
			//Obtiene valores de sesión
			String idTipoPersona =  sesion_actual.getAttribute("idTipoPersona").toString();
			String idMoneda =  sesion_actual.getAttribute("idMoneda").toString();
			
			
			//Descripcion
			if (tipoProducto.equals("A")){ // Productos Activos
				if (unidadPlazo==0){ // dias
					descripcionPlazo = "De " + plazoInicial + " a " + plazoFinal + " dias";
				}else{ //meses
					descripcionPlazo = "De " + plazoInicial + " a " + plazoFinal + " meses";
				}
			}else{ // Productos Pasivos
				if (unidadPlazo==0){ // dias
					if (idMoneda.equals("2") && idTipoPersona.equals("1")){ // caso particular persona natural - dolares
						if (plazoFinal == PLAZO_MAXIMO){
							descripcionPlazo = "De " +  plazoInicial + " dias a mas";
						}else{
							descripcionPlazo = "De " +  plazoInicial +  " a "  + plazoFinal + " dias";
						}
					}else{
						if (plazoFinal == PLAZO_MAXIMO){
							descripcionPlazo = "De " +  plazoInicial + " dias a mas";
						}else{
							descripcionPlazo = "A " +  plazoFinal + " dias";
						}
					}
				}else{ //meses
					
					if (idMoneda.equals("2") && idTipoPersona.equals("1")){ 
						if (plazoFinal == PLAZO_MAXIMO){
							descripcionPlazo = "De " +  plazoInicial + " meses a mas";
						}else{
							descripcionPlazo = "De " +  plazoInicial +  " a "  + plazoFinal + " meses";
						}
						
					}else{
						if (plazoFinal == PLAZO_MAXIMO){
							descripcionPlazo = "De " +  plazoInicial + " meses a mas";
						}else{
							descripcionPlazo = "A " + plazoFinal + " meses";
						}
					}

					
				}
				
			}
			
			//Monto máximo por defecto
			//long montoMaximo = Long.parseLong(parameters.getKey("MTO_MAXIMO_PRODUCTOS"));
					
			//Recupera lista de tasas actuales de sesion
			//List<Tasa> listaRangoTasas = ((List<Tasa>) sesion_actual.getAttribute("listaRangoTasas"));	
			List<Tasa> listaRangoTasasPlazo = tasaServicesImpl.listarTasasxPlazos((String)sesion_actual.getAttribute("idSubProducto"), 
															(String)sesion_actual.getAttribute("idTipoPersona"), 
															(String)sesion_actual.getAttribute("idMoneda"), 
															String.valueOf(plazoInicial), 
															String.valueOf(plazoFinal), 
															String.valueOf(unidadPlazo));
			
			//Obtiene cantidad de registros
			int cantidadRegistros = listaRangoTasasPlazo.size();
			
			//Recupera datos para validacion de rangos
			if (cantidadRegistros > 0){
				anteriorRangoTasaPlazo = listaRangoTasasPlazo.get(cantidadRegistros-1);
				valorTasaAnteriorMin = anteriorRangoTasaPlazo.getValTasaMin();
				valorTasaAnteriorMax = anteriorRangoTasaPlazo.getValTasaMax();
				montoAnterior = anteriorRangoTasaPlazo.getMtoInicial();
			}else{
				valorTasaAnteriorMin = new BigDecimal(0);
				valorTasaAnteriorMax = new BigDecimal(0);
			}
			

			
			//Valida montos y tasas ingresados
			if (validarMontosTasas(montoMinimo,valorTasaMin,valorTasaMax,montoAnterior,valorTasaAnteriorMin,valorTasaAnteriorMax,tipoProducto)|| cantidadRegistros==0){
				
				//Si los datos son correctos se procede a grabar
				
				//Obtiene secuencial de nuevo rango
				int rango = cantidadRegistros + 1;
				
				//Setea valores de rango tasa a grabar
				nuevoRangoTasa.setSubProductos(subProductoServicesImpl.buscarSubProducto((String)sesion_actual.getAttribute("idSubProducto")));
				nuevoRangoTasa.setTipoPersona(tipoPersonaServicesImpl.buscarTipoPersona((String)sesion_actual.getAttribute("idTipoPersona")));
				nuevoRangoTasa.setMoneda(monedaServicesImpl.buscarMoneda((String)sesion_actual.getAttribute("idMoneda")));
				nuevoRangoTasa.setMtoInicial(montoMinimo);
				nuevoRangoTasa.setMtoFinal(montoMaximo);
				nuevoRangoTasa.setValTasaMin(valorTasaMin);
				nuevoRangoTasa.setValTasaMax(valorTasaMax);
				nuevoRangoTasa.setPlazoInicial(plazoInicial);
				nuevoRangoTasa.setPlazoFinal(plazoFinal);
				nuevoRangoTasa.setUnidadPlazo(unidadPlazo);
				nuevoRangoTasa.setDescripcionPlazo(descripcionPlazo);
				nuevoRangoTasa.setRango(rango);
				nuevoRangoTasa.setFechaReg(new Date());
				
				//Añade rango en base de datos
				tasaServicesImpl.insertarTasa(nuevoRangoTasa);
				
				//Valida si es primer rango o no
				if (rango > 1){
					
					//Actualiza el valor del moto maximo para el rango anterior
					anteriorRangoTasa = listaRangoTasasPlazo.get(rango-2);
					anteriorRangoTasa.setMtoFinal(montoMinimo-1);
					
					//Actualiza rango anterior
					tasaServicesImpl.actualizarTasa(anteriorRangoTasa);
					
				}
				
				// Devuelve mensaje
				responseJSON.put("cod_retorno", parameters.getKey("COD_ING_RANGO_OK"));
				responseJSON.put("msj_retorno", parameters.getKey("MSJ_ING_RANGO_OK"));
				out.println(responseJSON.toString());
					
				
			} else {
				// Devuelve mensaje: error en datos ingresados
				responseJSON.put("cod_retorno", CODIGO_RETORNO);
				responseJSON.put("msj_retorno", MENSAJE_RETORNO);
				out.println(responseJSON.toString());
			}

			
		}catch(Exception e){
			System.out.println(e.getMessage());
			// Devuelve mensaje
			responseJSON.put("cod_retorno", "6");
			responseJSON.put("msj_retorno", parameters.getKey("MSJ_ING_RANGO_NOK"));
			out.println(responseJSON.toString());
			
		}
		
		
		
	}

	/**
	 * @param montoIngresado
	 * @param valorTasaIngresado
	 * @param montoAnterior
	 * @param valorTasaAnteriorMin
	 * @param valorTasaAnteriorMax
	 * @param tipoProducto
	 * @return
	 */
	private boolean validarMontosTasas(
			long montoIngresado, 
			BigDecimal valorTasaIngresadoMin,
			BigDecimal valorTasaIngresadoMax,
			long montoAnterior, 
			BigDecimal valTasaAnteriorMin,
			BigDecimal valTasaAnteriorMax,
			String tipoProducto) 
	{
		
		boolean validacion = false;
		Parameters parameters = Parameters.getInstance();
		
		//Valida montos
		if (montoIngresado > montoAnterior){
			validacion = true;
			CODIGO_RETORNO ="0";
			
			//Validando tasas en función al tipo de producto
			//Si el producto es un activo: mayor monto , menor tasa
			/*if (tipoProducto.equals("A")){
				
				if (valorTasaIngresadoMin.compareTo(valTasaAnteriorMin) < 0){
					
					//Datos correctos, procede a grabar
					if  (valorTasaIngresadoMax.compareTo(valTasaAnteriorMax) < 0){
						validacion = true;
						CODIGO_RETORNO ="0";
					}else{
						CODIGO_RETORNO = "4";
						MENSAJE_RETORNO = parameters.getKey("MSJ_VAL_TMX_LW") + valTasaAnteriorMax + '%';
					}
				}else{
					CODIGO_RETORNO = "2";
					MENSAJE_RETORNO = parameters.getKey("MSJ_VAL_TMN_LW") + valTasaAnteriorMin + '%';
				}
				
			}else{
				// El producto es pasivo: mayor tasa, mayor tasa
				if (valorTasaIngresadoMin.compareTo(valTasaAnteriorMin) > 0){
					
					if (valorTasaIngresadoMax.compareTo(valTasaAnteriorMax) > 0){
					//Datos cor,rectos, procede a grabar
						validacion = true;
						CODIGO_RETORNO ="0";
					}else{
						CODIGO_RETORNO = "5";
						MENSAJE_RETORNO = parameters.getKey("MSJ_VAL_TMX_LW") + valTasaAnteriorMax + '%';
					}
				}else{
					CODIGO_RETORNO = "3";
					MENSAJE_RETORNO = parameters.getKey("MSJ_VAL_TMN_HG") + valTasaAnteriorMin + '%';
				}
			}*/
			
		}else{
			CODIGO_RETORNO = "1";
			MENSAJE_RETORNO = parameters.getKey("MSJ_VAL_MTO") + montoAnterior;	
		}
		
		//Retorna codigo de validación
		return validacion;

		
	}



}
