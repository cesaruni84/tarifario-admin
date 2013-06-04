/**
 * 
 */
package org.srluren.admin.dao;

import java.util.List;
import org.srluren.admin.beans.hb.TipoPersona;

/**
 * @author AETOS SAC
 *
 */
public interface TipoPersonaDAO {

	public List<TipoPersona> listar() throws Exception;
	public TipoPersona buscar(String idTipoPersona) throws Exception;
	
}
