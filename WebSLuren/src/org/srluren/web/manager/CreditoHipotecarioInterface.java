package org.srluren.web.manager;

import org.sim.credito.bo.CreditoCabeceraHipotecarioBO;
import org.sim.credito.bo.CreditoCronogramaHipotecarioBO;

public interface CreditoHipotecarioInterface {
	
	public CreditoCronogramaHipotecarioBO generarCronograma(String jsonRequest, String idSubProducto);
	public CreditoCabeceraHipotecarioBO getDatosCabeceraCredito ();

}
