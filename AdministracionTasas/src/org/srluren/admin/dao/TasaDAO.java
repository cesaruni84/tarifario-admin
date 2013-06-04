/**
 * 
 */
package org.srluren.admin.dao;

import java.util.List;
import org.srluren.admin.beans.hb.Tasa;

/**
 * @author AETOS SAC.
 *
 */
public interface TasaDAO {

	public List<Tasa> listar(String idSubProducto, String idTipoPersona,String idMoneda) throws Exception;
	public List<Tasa> listarxPlazos(String idSubProducto, String idTipoPersona,String idMoneda,String plzoIni, String plzoFinal, String plzoUnidad) throws Exception;
	public Tasa buscar(String idTasa) throws Exception;
	public Integer actualizar(Tasa tasaBean) throws Exception;
	public Integer insertar(Tasa tasaBean) throws Exception;
	public Integer eliminar(Integer idTasa) throws Exception;
	
}
