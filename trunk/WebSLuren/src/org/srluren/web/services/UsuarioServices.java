/**
 * 
 */
package org.srluren.web.services;

import org.srluren.web.beans.hb.Usuario;

/**
 * @author AETOS SAC.
 *
 */
public interface UsuarioServices {
	
	public Usuario autenticarUsuario(String codigo, String clave) throws Exception;

}
