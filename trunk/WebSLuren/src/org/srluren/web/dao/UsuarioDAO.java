/**
 * 
 */
package org.srluren.web.dao;

import org.srluren.web.beans.hb.Usuario;

/**
 * @author AETOS SAC
 *
 */
public interface UsuarioDAO {
	
	public Usuario autenticar(String codigo, String clave) throws Exception;

}
