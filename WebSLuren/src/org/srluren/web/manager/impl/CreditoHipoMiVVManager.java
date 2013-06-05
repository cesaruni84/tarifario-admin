/**
 * 
 */
package org.srluren.web.manager.impl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.sim.credito.bean.Credito;
import org.sim.credito.bean.CreditoCronograma;
import org.sim.credito.bo.CreditoCabeceraHipotecarioBO;
import org.sim.credito.bo.CreditoCronogramaHipotecarioBO;
import org.sim.credito.service.impl.CreditoCronogramaServiceImpl;
import org.srluren.web.beans.hb.Tasa;
import org.srluren.web.manager.CreditoHipotecarioInterface;
import org.srluren.web.resources.Application;
import org.srluren.web.resources.Parameters;
import org.srluren.web.services.impl.TasaServicesImpl;
import org.sim.credito.util.Utilitario;

/**
 * @author AETOS PERU S.A.C
 *
 */
public class CreditoHipoMiVVManager implements CreditoHipotecarioInterface{
	
	public Credito credito=new Credito();

	public CreditoCronogramaHipotecarioBO generarCronograma(String jsonRequest,
			String idSubProducto) {
		// Servicio de generacion de cronograma
		CreditoCronogramaServiceImpl cronogramaServiceImpl = new CreditoCronogramaServiceImpl();
		TasaServicesImpl tasaServicesImpl = new TasaServicesImpl();
		Tasa beanTasa;
		
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
			credito.setMoneda(json.get("moneda").toString());
			credito.setMontoPrestamo(Double.parseDouble(json.get("montoCredito").toString()));
			credito.setMontoInmueble(Double.parseDouble(json.get("valorInmueble").toString()));
			credito.setNumCuotas(Integer.parseInt(json.get("plazoPrestamo").toString()));
			credito.setPeriodoPago(30); // Por defecto asumimos mensual cada 30 dias
			credito.setPeriodoGracia(Integer.parseInt(json.get("periodoGracia").toString()));
			//credito.setPeriodoGracia(0);
			credito.setDiaPago(Integer.parseInt(json.get("diaPago").toString()));
			credito.setTipoInteresPerGracia(Integer.parseInt(json.get("tipoInteresPerGracia").toString()));
			//credito.setTipoInteresPerGracia(0);
			credito.setSubProducto(json.get("subProducto").toString());
			credito.setValorTasa(Double.valueOf(json.get("valorTasa").toString()));
			
			//Obtiene la tasa de base de datos
			//beanTasa = tasaServicesImpl.buscarRangoTasa(idSubProducto, "1", json.get("moneda").toString(), json.get("montoCredito").toString());
			//credito.setValorTasa(beanTasa.getValTasa().doubleValue());
			
			//Lee parametros
			Double BONO_BUEN_PAGADOR_MIVV_1  = Double.valueOf(application.getKey("BONO_BUEN_PAGADOR_MIVV_1"));
			Double MTO_MIN_PREST_BNPG_MIVV_1 = Double.valueOf(application.getKey("MTO_MIN_PREST_BNPG_MIVV_1"));
			Double MTO_MAX_PREST_BNPG_MIVV_1 = Double.valueOf(application.getKey("MTO_MAX_PREST_BNPG_MIVV_1"));
			Double BONO_BUEN_PAGADOR_MIVV_2  = Double.valueOf(application.getKey("BONO_BUEN_PAGADOR_MIVV_2"));
			Double MTO_MIN_PREST_BNPG_MIVV_2 = Double.valueOf(application.getKey("MTO_MIN_PREST_BNPG_MIVV_2"));
			Double MTO_MAX_PREST_BNPG_MIVV_2 = Double.valueOf(application.getKey("MTO_MAX_PREST_BNPG_MIVV_2"));
			
			//Validación de montos para acceder al bono del buen pagador
			Double MONTO_PRESTAMO = Double.parseDouble(json.get("montoCredito").toString());
			
			if ((MONTO_PRESTAMO >= MTO_MIN_PREST_BNPG_MIVV_1) && (MONTO_PRESTAMO <= MTO_MAX_PREST_BNPG_MIVV_1)){
				credito.setValorBonoBuenPagador(BONO_BUEN_PAGADOR_MIVV_1);
			}else{
				if ((MONTO_PRESTAMO >= MTO_MIN_PREST_BNPG_MIVV_2) && (MONTO_PRESTAMO <= MTO_MAX_PREST_BNPG_MIVV_2)){
					credito.setValorBonoBuenPagador(BONO_BUEN_PAGADOR_MIVV_2);
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
			
			
			// Genera Cronograma con los datos enviados
			creditoCronograma = cronogramaServiceImpl.getCronogramaHipotecario(credito);
			
			//Periodo de gracia
			if (credito.getPeriodoGracia() > 0){
				creditoCronograma.setPeriodoGracia(true);
			}else{
				creditoCronograma.setPeriodoGracia(false);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Devuleve Cronograma
		return new CreditoCronogramaHipotecarioBO(creditoCronograma);

	}

	public CreditoCabeceraHipotecarioBO getDatosCabeceraCredito() {
		// TODO Auto-generated method stub
		return new CreditoCabeceraHipotecarioBO(credito);
	}


}
