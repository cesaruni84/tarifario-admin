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
import org.srluren.web.services.impl.TasaServicesImpl;

/**
 * @author AETOS PERU S.A.C
 *
 */
public class CTSSimuladorManager implements DepositoInterface{
	
	public Deposito datosDeposito = new Deposito();

	@Override
	public DepositoSimulacion generarSimulacion(String jsonRequest, String idSubProducto) {

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
			datosDeposito.setPlazoDeposito(Integer.parseInt(json.get("plazoDeposito").toString()));
			
			//Obtiene TEA de base de datos: toma el valor promedio
			beanTasa = tasaServicesImpl.buscarRangoxMontoPlazo(idSubProducto, "1", json.get("moneda").toString(), json.get("importeDeposito").toString(), json.get("plazoDeposito").toString());
			Double tasaMin = beanTasa.getValTasaMin().doubleValue();
			Double tasaMax = beanTasa.getValTasaMax().doubleValue();
			datosDeposito.setValorTEA((tasaMin+tasaMax)/2.00);
			
			// Genera Cronograma-Deposito con los datos enviados
			depositoSimulacion = depositoServiceImpl.doSimulacionCTS(datosDeposito);

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
