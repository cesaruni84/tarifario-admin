/**
 * 
 */
package org.srluren.web.services;

import java.util.List;

import org.srluren.web.beans.hb.SubProducto;

/**
 * @author cesar
 *
 */
public interface SubProductoServices {
	
	public List<SubProducto> listarSubProductos(String idProducto) throws Exception;
	public SubProducto buscarSubProducto(String idSubProducto) throws Exception;
	public SubProducto buscarSubProductoNemonico(String nemSubProducto) throws Exception;

}
