/**
 * 
 */
package org.srluren.web.services.impl;

import java.util.List;

import org.srluren.web.beans.hb.Producto;
import org.srluren.web.dao.impl.HibernateProductoDAO;
import org.srluren.web.services.ProductoServices;

/**
 * @author cesar
 *
 */
public class ProductoServicesImpl implements ProductoServices {

	/* (non-Javadoc)
	 * @see org.srluren.admin.services.ProductoService#listarProductos()
	 */
	
	private HibernateProductoDAO hibernateProductoDAO = new HibernateProductoDAO();
	
	@Override
	public List<Producto> listarProductos() throws Exception {
		// TODO Auto-generated method stub
		return hibernateProductoDAO.listar();
	}

	/* (non-Javadoc)
	 * @see org.srluren.admin.services.ProductoService#buscarProducto(java.lang.String)
	 */
	@Override
	public Producto buscarProducto(String idProducto) throws Exception {
		// TODO Auto-generated method stub
		return hibernateProductoDAO.buscar(idProducto);
	}

	@Override
	public Producto buscarProductoNemonico(String nemProducto) throws Exception {
		// TODO Auto-generated method stub
		return hibernateProductoDAO.buscarNem(nemProducto);
	}

}
