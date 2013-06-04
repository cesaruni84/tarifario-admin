/**
 * 
 */
package org.srluren.admin.services;

import org.srluren.admin.beans.hb.Usuario;

/**
 * @author AETOS SAC.
 *
 */
public interface UsuarioServices {
	
	public Usuario autenticarUsuario(String codigo, String clave) throws Exception;

}
