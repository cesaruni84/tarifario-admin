/**
 * 
 */
package org.srluren.web.manager;

import org.sim.credito.bo.CreditoCabeceraBO;
import org.sim.credito.bo.CreditoCronogramaBO;

/**
 * @author AETOS PERU S.A.C
 *
 */
public interface CreditoInterface {
	
	public CreditoCronogramaBO generarCronograma(String jsonRequest, String idSubProducto);
	
	public CreditoCabeceraBO getDatosCabeceraCredito ();
	
}
