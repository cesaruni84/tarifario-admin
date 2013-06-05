package org.srluren.web.services.impl;

import java.util.List;
import org.srluren.web.beans.hb.Parametro;
import org.srluren.web.dao.impl.HibernateParametroDAO;
import org.srluren.web.services.ParametroService;

public class ParametroServiceImpl implements ParametroService{
	
	
	private HibernateParametroDAO hibernateParametroDAO = new HibernateParametroDAO();

	@Override
	public List<Parametro> listarParametros() throws Exception {
		// TODO Auto-generated method stub
		return hibernateParametroDAO.listar();
	}

	@Override
	public Parametro buscarParametro(String idParametro) throws Exception {
		// TODO Auto-generated method stub
		return hibernateParametroDAO.buscar(idParametro);
	}

	@Override
	public Integer actualizarParametro(Parametro parametroBean)
			throws Exception {
		// TODO Auto-generated method stub
		return hibernateParametroDAO.actualizar(parametroBean);
	}

	@Override
	public Integer insertarParametro(Parametro parametroBean) throws Exception {
		// TODO Auto-generated method stub
		return hibernateParametroDAO.insertar(parametroBean);
	}

}
