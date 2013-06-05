/**
 * 
 */
package org.srluren.web.dao;

import java.util.List;
import org.srluren.web.beans.hb.Parametro;

/**
 * @author AETOS SAC.
 *
 */
public interface ParametroDAO {

	public List<Parametro> listar() throws Exception;
	public Parametro buscar(String idParametro) throws Exception;
	public Integer actualizar(Parametro parametroBean) throws Exception;
	public Integer insertar(Parametro parametroBean) throws Exception; 

}
