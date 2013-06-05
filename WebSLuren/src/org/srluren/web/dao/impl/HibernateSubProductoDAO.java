/**
 * 
 */
package org.srluren.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.srluren.web.beans.hb.SubProducto;
import org.srluren.web.dao.SubProductoDAO;
import org.srluren.web.hibernate.config.HibernateUtil;

/**
 * @author cesar
 * 
 */
public class HibernateSubProductoDAO implements SubProductoDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.srluren.admin.dao.SubProductoDAO#listar()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SubProducto> listar(String idProducto) throws Exception {

		// Lista de SubProductos a recuperar
		List<SubProducto> listaSubProductos = new ArrayList<SubProducto>();

		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {

			// Código SQL a ejecutar
			String hsql = "SELECT * FROM [dbo].[SubProductos] WHERE [idProducto]="
					+ idProducto + " ORDER BY [idSubProducto] ASC";

			// Ejecutando query para traer Lista
			listaSubProductos = sesion.createSQLQuery(hsql)
					.addEntity(SubProducto.class).list();

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
		return listaSubProductos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.srluren.admin.dao.SubProductoDAO#buscar(java.lang.String)
	 */
	@Override
	public SubProducto buscar(String idSubProducto) throws Exception {
		// TODO Auto-generated method stub

		// Objeto bean
		SubProducto subProductoBean = null;

		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {

			// Obtiene bean de base de datos
			subProductoBean = (SubProducto) sesion.get(SubProducto.class,
					Integer.parseInt(idSubProducto));

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
		return subProductoBean;

	}

	@SuppressWarnings("unchecked")
	@Override
	public SubProducto buscarNem(String nemSubProducto) throws Exception {
		// TODO Auto-generated method stub

		// Lista de SubProductos a recuperar
		List<SubProducto> listaSubProductos = new ArrayList<SubProducto>();
		SubProducto subProducto = new SubProducto();

		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {

			// Código SQL a ejecutar
			String hsql = "SELECT * FROM [dbo].[SubProductos] WHERE [Nemonico]='" + nemSubProducto + "'";

			// Ejecutando query para traer Lista
			listaSubProductos = sesion.createSQLQuery(hsql)
					.addEntity(SubProducto.class).setMaxResults(1).list();
			
			//Obtiene bean
			subProducto= listaSubProductos.get(0);

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

		// Devuelve bean
		return subProducto;

	}

}
