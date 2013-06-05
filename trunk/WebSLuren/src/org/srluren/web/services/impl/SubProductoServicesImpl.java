/**
 * 
 */
package org.srluren.web.services.impl;

import java.util.List;

import org.srluren.web.beans.hb.SubProducto;
import org.srluren.web.dao.impl.HibernateSubProductoDAO;
import org.srluren.web.services.SubProductoServices;

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
