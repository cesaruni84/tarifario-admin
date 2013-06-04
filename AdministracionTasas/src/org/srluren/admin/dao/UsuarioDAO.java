/**
 * 
 */
package org.srluren.admin.dao;

import org.srluren.admin.beans.hb.Usuario;

/**
 * @author AETOS SAC
 *
 */
public interface UsuarioDAO {
	
	public Usuario autenticar(String codigo, String clave) throws Exception;

}
