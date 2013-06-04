/**
 * 
 */
package org.srluren.admin.services;

import java.util.List;
import org.srluren.admin.beans.hb.Moneda;

/**
 * @author AETOS SAC.
 *
 */
public interface MonedaServices {
	
	
	public List<Moneda> listarMonedas() throws Exception;
	public Moneda buscarMoneda(String idMoneda) throws Exception;

}
