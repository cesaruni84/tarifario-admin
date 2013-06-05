/**
 * 
 */
package org.srluren.web.manager.impl;

import org.srluren.web.manager.CreditoInterface;
import org.srluren.web.resources.Application;
import org.srluren.web.resources.Parameters;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.sim.credito.bean.Credito;
import org.sim.credito.bean.CreditoCronograma;
import org.sim.credito.bo.CreditoCabeceraBO;
import org.sim.credito.bo.CreditoCronogramaBO;
import org.sim.credito.service.impl.CreditoCronogramaServiceImpl;
import org.sim.credito.util.Utilitario;

/**
 * @author AETOS PERU S.A.C
 * 
 */
public class CreditoConsumoManager implements CreditoInterface {


	public Credito credito=new Credito();
	
	@Override
	public CreditoCronogramaBO generarCronograma(String jsonRequest, String idSubProducto) {

		// Servicio de generacion de cronograma
		CreditoCronogramaServiceImpl cronogramaServiceImpl = new CreditoCronogramaServiceImpl();
		CreditoCronograma creditoCronograma = new CreditoCronograma();
		Parameters parameters = Parameters.getInstance();
		Application application = Application.getInstance();

		// La respuesta la parseamos en formato JSON
		JSONParser jsonParser = new JSONParser();
		JSONObject json;

		try {
			
			//Realizar parseo
			json = (JSONObject) jsonParser.parse(jsonRequest);

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
			
			// Genera Cronograma con los datos enviados
			creditoCronograma = cronogramaServiceImpl.getCronogramaConsumo(credito);
			
			//Periodo de gracia
			if (credito.getPeriodoGracia() > 0){
				creditoCronograma.setPeriodoGracia(true);
			}else{
				creditoCronograma.setPeriodoGracia(false);
			}
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Devuleve Cronograma
		return new CreditoCronogramaBO(creditoCronograma);

	}

	@Override
	public CreditoCabeceraBO getDatosCabeceraCredito() {
		// TODO Auto-generated method stub
		return new CreditoCabeceraBO(credito);
	}
	
	
	
}
