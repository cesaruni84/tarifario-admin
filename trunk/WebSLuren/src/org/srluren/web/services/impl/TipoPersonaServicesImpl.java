/**
 * 
 */
package org.srluren.web.services.impl;

import java.util.List;

import org.srluren.web.beans.hb.TipoPersona;
import org.srluren.web.dao.impl.HibernateTipoPersonaDAO;
import org.srluren.web.services.TipoPersonaServices;

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
