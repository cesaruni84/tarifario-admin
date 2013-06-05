/**
 * 
 */
package org.srluren.web.services;

import java.util.List;

import org.srluren.web.beans.hb.Producto;

/**
 * @author AETOS SAC.
 *
 */
public interface ProductoServices {

	
	public List<Producto> listarProductos() throws Exception;
	public Producto buscarProducto(String idProducto) throws Exception;
	public Producto buscarProductoNemonico(String nemProducto) throws Exception;
}
