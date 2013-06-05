/**
 * 
 */
package org.srluren.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.srluren.web.beans.hb.TipoPersona;
import org.srluren.web.dao.TipoPersonaDAO;
import org.srluren.web.hibernate.config.HibernateUtil;

/**
 * @author cesar
 * 
 */
public class HibernateTipoPersonaDAO implements TipoPersonaDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.srluren.admin.dao.TipoPersonaDAO#listar()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoPersona> listar() throws Exception {
		// TODO Auto-generated method stub

		// Lista de TipoPersonas a recuperar
		List<TipoPersona> listaTipoPersonas = new ArrayList<TipoPersona>();

		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {

			// Código SQL a ejecutar
			String hsql = "SELECT * FROM [dbo].[TipoPersona] ORDER BY [idTipoPersona] ASC";

			// Ejecutando query para traer Lista
			listaTipoPersonas = sesion.createSQLQuery(hsql)
					.addEntity(TipoPersona.class).list();

			// Liberando la conexion
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				throw new Exception(e.getMessage(), e.getCause());
			}

		} finally {
			if (sesion.isOpen())
				sesion.close();
		}

		// Devuelve Lista
		return listaTipoPersonas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.srluren.admin.dao.TipoPersonaDAO#buscar(java.lang.String)
	 */
	@Override
	public TipoPersona buscar(String idTipoPersona) throws Exception {
		// TODO Auto-generated method stub

		// Objeto bean
		TipoPersona tipoPersonaBean = null;

		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {

			// Obtiene bean de base de datos
			tipoPersonaBean = (TipoPersona) sesion.get(TipoPersona.class,
					Integer.parseInt(idTipoPersona));

			// Liberando la conexion
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				throw new Exception(e.getMessage(), e.getCause());
			}
		} finally {
			if (sesion.isOpen())
				sesion.close();
		}

		// Retorna bean
		return tipoPersonaBean;

	}

}
