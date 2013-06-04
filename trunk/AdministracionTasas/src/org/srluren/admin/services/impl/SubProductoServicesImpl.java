/**
 * 
 */
package org.srluren.admin.services.impl;

import java.util.List;

import org.srluren.admin.beans.hb.SubProducto;
import org.srluren.admin.dao.impl.HibernateSubProductoDAO;
import org.srluren.admin.services.SubProductoServices;

/**
 * @author cesar
 *
 */
public class SubProductoServicesImpl implements SubProductoServices {
	
	
	private HibernateSubProductoDAO hibernateSubProductoDAO = new HibernateSubProductoDAO();

	/* (non-Javadoc)
	 * @see org.srluren.admin.services.SubProductoServices#listarSubProductos()
	 */
	@Override
	public List<SubProducto> listarSubProductos(String idProducto) throws Exception {
		// TODO Auto-generated method stub
		return hibernateSubProductoDAO.listar(idProducto);
	}

	/* (non-Javadoc)
	 * @see org.srluren.admin.services.SubProductoServices#buscarSubProducto(java.lang.String)
	 */
	@Override
	public SubProducto buscarSubProducto(String idSubProducto) throws Exception {
		// TODO Auto-generated method stub
		return hibernateSubProductoDAO.buscar(idSubProducto);
	}

	@Override
	public SubProducto buscarSubProductoNemonico(String nemSubProducto)
			throws Exception {
		// TODO Auto-generated method stub
		return hibernateSubProductoDAO.buscarNem(nemSubProducto);
	}

}
