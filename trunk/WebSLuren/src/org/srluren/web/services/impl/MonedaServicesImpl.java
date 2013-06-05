/**
 * 
 */
package org.srluren.web.services.impl;

import java.util.List;

import org.srluren.web.beans.hb.Moneda;
import org.srluren.web.dao.impl.HibernateMonedaDAO;
import org.srluren.web.services.MonedaServices;

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
