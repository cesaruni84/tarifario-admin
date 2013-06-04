/**
 * 
 */
package org.srluren.admin.services;

import java.util.List;
import org.srluren.admin.beans.hb.Tasa;

/**
 * @author AETOS SAC.
 *
 */
public interface TasaServices {
	
	
	public List<Tasa> listarTasas(String idSubProducto, String idTipoPersona,String idMoneda) throws Exception;
	public List<Tasa> listarTasasxPlazos(String idSubProducto, String idTipoPersona,String idMoneda,String plzoIni, String plzoFinal, String plzoUnidad) throws Exception;
	public Tasa buscarTasa(String idTasa) throws Exception;
	public Integer actualizarTasa(Tasa tasaBean) throws Exception;
	public Integer insertarTasa(Tasa tasaBean) throws Exception;
	public Integer eliminarTasa(Integer idTasa) throws Exception;

}
