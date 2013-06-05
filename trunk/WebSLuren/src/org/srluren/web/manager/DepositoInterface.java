/**
 * 
 */
package org.srluren.web.manager;

import org.sim.credito.bean.DepositoSimulacion;

/**
 * @author AETOS PERU S.A.C
 *
 */
public interface DepositoInterface {
	
	public DepositoSimulacion generarSimulacion(String jsonRequest, String idSubProducto);


}
