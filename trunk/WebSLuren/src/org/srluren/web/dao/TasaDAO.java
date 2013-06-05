/**
 * 
 */
package org.srluren.web.dao;

import java.util.List;

import org.srluren.web.beans.hb.Plazo;
import org.srluren.web.beans.hb.Tasa;

/**
 * @author AETOS SAC.
 *
 */
public interface TasaDAO {

	public List<Tasa> listar(String idSubProducto, String idTipoPersona,String idMoneda) throws Exception;
	public Tasa buscar(String idTasa) throws Exception;
	public Integer actualizar(Tasa tasaBean) throws Exception;
	public Integer insertar(Tasa tasaBean) throws Exception;
	public Integer eliminar(Integer idTasa) throws Exception;
	public Tasa buscarRango(String idSubProducto, String idTipoPersona,String idMoneda, String monto ) throws Exception;
	public Tasa buscarRangoxMontoPlazo(String idSubProducto, String idTipoPersona,String idMoneda, String monto, String plazo) throws Exception;
	public Tasa buscarRangoxMonto(String idSubProducto, String idTipoPersona,String idMoneda, String plazo) throws Exception;
	public List<Plazo> listarPlazos(String idSubProducto, String idTipoPersona,String idMoneda) throws Exception;

}
