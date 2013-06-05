package org.srluren.web.services;

import java.util.List;

import org.srluren.web.beans.hb.Parametro;

public interface ParametroService {

	public List<Parametro> listarParametros() throws Exception;
	public Parametro buscarParametro(String idParametro) throws Exception;
	public Integer actualizarParametro(Parametro parametroBean) throws Exception;
	public Integer insertarParametro(Parametro parametroBean) throws Exception; 
	
}
