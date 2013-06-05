/**
 * 
 */
package org.srluren.web.services;

import java.util.List;

import org.srluren.web.beans.hb.Plazo;
import org.srluren.web.beans.hb.Tasa;

/**
 * @author AETOS SAC.
 *
 */
public interface TasaServices {
	
	
	public List<Tasa> listarTasas(String idSubProducto, String idTipoPersona,String idMoneda) throws Exception;
	public Tasa buscarTasa(String idTasa) throws Exception;
	public Integer actualizarTasa(Tasa tasaBean) throws Exception;
	public Integer insertarTasa(Tasa tasaBean) throws Exception;
	public Integer eliminarTasa(Integer idTasa) throws Exception;
	public Tasa buscarRangoTasa(String idSubProducto, String idTipoPersona,String idMoneda,String monto) throws Exception;
	public Tasa buscarRangoxMontoPlazo(String idSubProducto, String idTipoPersona,String idMoneda, String monto, String plazo) throws Exception;
	public Tasa buscarRangoxMonto(String idSubProducto, String idTipoPersona,String idMoneda, String monto) throws Exception;
	public List<Plazo> listarPlazosxSubProducto(String idSubProducto, String idTipoPersona,String idMoneda) throws Exception;

}
