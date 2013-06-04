/**
 * 
 */
package org.srluren.admin.services.impl;

import java.util.List;

import org.srluren.admin.beans.hb.Tasa;
import org.srluren.admin.dao.impl.HibernateTasaDAO;
import org.srluren.admin.services.TasaServices;

/**
 * @author AETOS PERU S.A.C
 *
 */
public class TasaServicesImpl implements TasaServices {
	
	
	private HibernateTasaDAO hibernateTasaDAO = new HibernateTasaDAO();
	

	/* (non-Javadoc)
	 * @see org.srluren.admin.services.TasaServices#listarTasas(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Tasa> listarTasas(String idSubProducto, String idTipoPersona,
			String idMoneda) throws Exception {
		// TODO Auto-generated method stub
		return hibernateTasaDAO.listar(idSubProducto, idTipoPersona, idMoneda);
	}

	/* (non-Javadoc)
	 * @see org.srluren.admin.services.TasaServices#buscarTasa(java.lang.String)
	 */
	@Override
	public Tasa buscarTasa(String idTasa) throws Exception {
		// TODO Auto-generated method stub
		return hibernateTasaDAO.buscar(idTasa);
	}

	/* (non-Javadoc)
	 * @see org.srluren.admin.services.TasaServices#actualizarTasa(org.srluren.admin.beans.hb.Tasa)
	 */
	@Override
	public Integer actualizarTasa(Tasa tasaBean) throws Exception {
		// TODO Auto-generated method stub
		return hibernateTasaDAO.actualizar(tasaBean);
	}

	/* (non-Javadoc)
	 * @see org.srluren.admin.services.TasaServices#insertarTasa(org.srluren.admin.beans.hb.Tasa)
	 */
	@Override
	public Integer insertarTasa(Tasa tasaBean) throws Exception {
		// TODO Auto-generated method stub
		return hibernateTasaDAO.insertar(tasaBean);
	}

	/* (non-Javadoc)
	 * @see org.srluren.admin.services.TasaServices#eliminarTasa(java.lang.Integer)
	 */
	@Override
	public Integer eliminarTasa(Integer idTasa) throws Exception {
		// TODO Auto-generated method stub
		return hibernateTasaDAO.eliminar(idTasa);
	}

	@Override
	public List<Tasa> listarTasasxPlazos(String idSubProducto,
			String idTipoPersona, String idMoneda, String plzoIni,
			String plzoFinal, String plzoUnidad) throws Exception {
		// TODO Auto-generated method stub
		return hibernateTasaDAO.listarxPlazos(idSubProducto, idTipoPersona, idMoneda, plzoIni, plzoFinal, plzoUnidad);
	}

}
