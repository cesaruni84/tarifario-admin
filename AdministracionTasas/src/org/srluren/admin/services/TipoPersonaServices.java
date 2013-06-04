/**
 * 
 */
package org.srluren.admin.services;

import java.util.List;
import org.srluren.admin.beans.hb.TipoPersona;

/**
 * @author AETOS SAC.
 *
 */
public interface TipoPersonaServices {
	
	public List<TipoPersona> listarTiposPersona() throws Exception;
	public TipoPersona buscarTipoPersona(String idTipoPersona) throws Exception;

}
