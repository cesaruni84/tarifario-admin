/**
 * 
 */
package org.srluren.admin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.srluren.admin.beans.hb.Moneda;
import org.srluren.admin.dao.MonedaDAO;
import org.srluren.admin.util.HibernateUtil;

/**
 * @author cesar
 * 
 */
public class HibernateMonedaDAO implements MonedaDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.srluren.admin.dao.MonedaDAO#listar()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Moneda> listar() throws Exception {
		// TODO Auto-generated method stub

		// Lista de Monedas a recuperar
		List<Moneda> listaMonedas = new ArrayList<Moneda>();

		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {

			// Código SQL a ejecutar
			String hsql = "SELECT * FROM [dbo].[Moneda] ORDER BY [idMoneda] ASC";

			// Ejecutando query para traer Lista
			listaMonedas = sesion.createSQLQuery(hsql).addEntity(Moneda.class)
					.list();

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
		return listaMonedas;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.srluren.admin.dao.MonedaDAO#buscar(java.lang.String)
	 */
	@Override
	public Moneda buscar(String idMoneda) throws Exception {
		// TODO Auto-generated method stub

		// Objeto bean
		Moneda monedaBean = null;

		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {

			// Obtiene bean de base de datos
			monedaBean = (Moneda) sesion.get(Moneda.class,
					Integer.parseInt(idMoneda));

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
		return monedaBean;

	}

}
