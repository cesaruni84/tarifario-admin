/**
 * 
 */
package org.srluren.admin.services.impl;

import java.util.List;

import org.srluren.admin.beans.hb.TipoPersona;
import org.srluren.admin.dao.impl.HibernateTipoPersonaDAO;
import org.srluren.admin.services.TipoPersonaServices;

/**
 * @author cesar
 *
 */
public class TipoPersonaServicesImpl implements TipoPersonaServices {
	
	private HibernateTipoPersonaDAO hibernateTipoPersonaDAO = new HibernateTipoPersonaDAO();
	

	/* (non-Javadoc)
	 * @see org.srluren.admin.services.TipoPersonaService#listarTiposPersona()
	 */
	@Override
	public List<TipoPersona> listarTiposPersona() throws Exception {
		// TODO Auto-generated method stub
		return hibernateTipoPersonaDAO.listar();
	}

	/* (non-Javadoc)
	 * @see org.srluren.admin.services.TipoPersonaService#buscarTipoPersona(java.lang.String)
	 */
	@Override
	public TipoPersona buscarTipoPersona(String idTipoPersona) throws Exception {
		// TODO Auto-generated method stub
		return hibernateTipoPersonaDAO.buscar(idTipoPersona);
	}

}
