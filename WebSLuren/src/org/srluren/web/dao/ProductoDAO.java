/**
 * 
 */
package org.srluren.web.dao;

import java.util.List;

import org.srluren.web.beans.hb.Producto;

/**
 * @author AETOS SAC.
 *
 */
public interface ProductoDAO {
	
	
	public List<Producto> listar() throws Exception;
	public Producto buscar(String idProducto) throws Exception;
	public Producto buscarNem(String nemProducto) throws Exception;

}
