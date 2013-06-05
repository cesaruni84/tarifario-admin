/**
 * 
 */
package org.srluren.web.manager.impl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.sim.credito.bean.Deposito;
import org.sim.credito.bean.DepositoSimulacion;
import org.sim.credito.service.impl.DepositoServiceImpl;
import org.srluren.web.beans.hb.Tasa;
import org.srluren.web.manager.DepositoInterface;
import org.srluren.web.resources.Parameters;
import org.srluren.web.services.impl.TasaServicesImpl;

/**
 * @author AETOS PERU S.A.C
 *
 */
public class DPlazoSimuladorManager implements DepositoInterface{
	
	public Deposito datosDeposito = new Deposito();

	@Override
	public DepositoSimulacion generarSimulacion(String jsonRequest,
			String idSubProducto) {
		
		//Parametros
		Parameters parameters = Parameters.getInstance();

		// Servicio de generacion de cronograma
		DepositoServiceImpl depositoServiceImpl = new DepositoServiceImpl();
		DepositoSimulacion depositoSimulacion = new DepositoSimulacion();
		
		//Servicio de obtencion de TREA's
		TasaServicesImpl tasaServicesImpl = new TasaServicesImpl();
		Tasa beanTasa;

		//Obtenemos los valores enviados
		JSONParser jsonParser = new JSONParser();
		JSONObject json;

		try {
			
			//Realizar parseo
			json = (JSONObject) jsonParser.parse(jsonRequest);
			
			//Genera objeto con los datos del depósito
			datosDeposito.setSubProducto(json.get("subProducto").toString());
			datosDeposito.setMontoDeposito(Double.valueOf(json.get("importeDeposito").toString()));
			datosDeposito.setMoneda(json.get("moneda").toString());
			
			datosDeposito.setTipoPersona(json.get("tipoPersona").toString());
			datosDeposito.setFechaInicio(json.get("fechaInicio").toString()) ;
			datosDeposito.setFormaPagoInteres(Integer.parseInt(json.get("formaPagoIntereses").toString()));
			
			//Obtiene TEA de base de datos: toma el valor promedio
			beanTasa = tasaServicesImpl.buscarRangoxMontoPlazo(idSubProducto, json.get("tipoPersona").toString(), json.get("moneda").toString(), json.get("importeDeposito").toString(), json.get("plazoDeposito").toString());
			//Double tasaMin = beanTasa.getValTasaMin().doubleValue();
			Double tasaMax = beanTasa.getValTasaMax().doubleValue();
			datosDeposito.setValorTEA((tasaMax));
			System.out.println("TEA: "+ datosDeposito.getValorTEA());
			
			//Plazo Final,Inicial
			Integer plazoFinal   = beanTasa.getPlazoFinal();
			Integer plazoInicial = beanTasa.getPlazoInicial();
			
			//Plazo Máximo
			Integer PLAZO_MAXIMO = Integer.parseInt(parameters.getKey("PLZO_MAXIMO"));
			
			if (plazoFinal >= PLAZO_MAXIMO){
				datosDeposito.setPlazoDeposito(plazoInicial);
			}else{
				datosDeposito.setPlazoDeposito(plazoFinal);
			}
			
			// Genera Cronograma-Deposito con los datos enviados
			depositoSimulacion = depositoServiceImpl.doSimulacionDepPlazo(datosDeposito);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Devuleve Cronograma-Deposito
		return depositoSimulacion;
		
	}

	/**
	 * @return the datosDeposito
	 */
	public Deposito getDatosDeposito() {
		return datosDeposito;
	}

	/**
	 * @param datosDeposito the datosDeposito to set
	 */
	public void setDatosDeposito(Deposito datosDeposito) {
		this.datosDeposito = datosDeposito;
	}
	
	

}
