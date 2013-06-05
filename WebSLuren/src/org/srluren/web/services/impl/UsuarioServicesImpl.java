/**
 * 
 */
package org.srluren.web.services.impl;

import org.srluren.web.beans.hb.Usuario;
import org.srluren.web.dao.impl.HibernateUsuarioDAO;
import org.srluren.web.services.UsuarioServices;

/**
 * @author cesar
 *
 */
public class UsuarioServicesImpl implements UsuarioServices {
	
	
	private HibernateUsuarioDAO hibernateUsuarioDAO = new HibernateUsuarioDAO();

	/* (non-Javadoc)
	 * @see org.srluren.admin.services.UsuarioServices#autenticarUsuario(java.lang.String, java.lang.String)
	 */
	@Override
	public Usuario autenticarUsuario(String codigo, String clave)
			throws Exception {
		// TODO Auto-generated method stub
		return hibernateUsuarioDAO.autenticar(codigo, clave);
	}

}
