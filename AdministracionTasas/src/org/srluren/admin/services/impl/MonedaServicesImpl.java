/**
 * 
 */
package org.srluren.admin.services.impl;

import java.util.List;

import org.srluren.admin.beans.hb.Moneda;
import org.srluren.admin.dao.impl.HibernateMonedaDAO;
import org.srluren.admin.services.MonedaServices;

/**
 * @author cesar
 *
 */
public class MonedaServicesImpl implements MonedaServices {
	
	private HibernateMonedaDAO hibernateMonedaDAO = new HibernateMonedaDAO();

	/* (non-Javadoc)
	 * @see org.srluren.admin.services.MonedaServices#listarMonedas()
	 */
	@Override
	public List<Moneda> listarMonedas() throws Exception {
		// TODO Auto-generated method stub
		return hibernateMonedaDAO.listar();
	}

	/* (non-Javadoc)
	 * @see org.srluren.admin.services.MonedaServices#buscarMoneda(java.lang.String)
	 */
	@Override
	public Moneda buscarMoneda(String idMoneda) throws Exception {
		// TODO Auto-generated method stub
		return hibernateMonedaDAO.buscar(idMoneda);
	}

}
