/**
 * 
 */
package org.srluren.web.dao;

import java.util.List;

import org.srluren.web.beans.hb.Moneda;

/**
 * @author AETOS SAC.
 *
 */
public interface MonedaDAO {
	
	public List<Moneda> listar() throws Exception;
	public Moneda buscar(String idMoneda) throws Exception;

}
