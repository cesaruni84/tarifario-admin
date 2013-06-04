/**
 * 
 */
package org.srluren.admin.dao;

import java.util.List;

import org.srluren.admin.beans.hb.SubProducto;

/**
 * @author AETOS SAC.
 *
 */
public interface SubProductoDAO {
	
	public List<SubProducto> listar(String idProducto) throws Exception;
	public SubProducto buscar(String idSubProducto) throws Exception;
	public SubProducto buscarNem(String nemSubProducto) throws Exception;
	

}
