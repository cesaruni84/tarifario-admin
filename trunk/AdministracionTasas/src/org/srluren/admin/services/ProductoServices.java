/**
 * 
 */
package org.srluren.admin.services;

import java.util.List;

import org.srluren.admin.beans.hb.Producto;

/**
 * @author AETOS SAC.
 *
 */
public interface ProductoServices {

	
	public List<Producto> listarProductos() throws Exception;
	public Producto buscarProducto(String idProducto) throws Exception;
	public Producto buscarProductoNemonico(String nemProducto) throws Exception;
}
