/**
 * 
 */
package org.srluren.admin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.srluren.admin.beans.hb.Producto;
import org.srluren.admin.dao.ProductoDAO;
import org.srluren.admin.util.HibernateUtil;

/**
 * @author cesar
 * 
 */
public class HibernateProductoDAO implements ProductoDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.srluren.admin.dao.ProductoDAO#listar()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> listar() throws Exception {
		// TODO Auto-generated method stub

		// Lista de productos a recuperar
		List<Producto> listaProductos = new ArrayList<Producto>();

		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {

			// Código SQL a ejecutar
			String hsql = "SELECT * FROM [dbo].[Productos] ORDER BY [idProductos] ASC";

			// Ejecutando query para traer Lista
			listaProductos = sesion.createSQLQuery(hsql)
					.addEntity(Producto.class).list();

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
		return listaProductos;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.srluren.admin.dao.ProductoDAO#buscar(java.lang.String)
	 */
	@Override
	public Producto buscar(String idProducto) throws Exception {
		// TODO Auto-generated method stub

		// Objeto bean
		Producto productoBean = null;

		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {

			// Obtiene bean de base de datos
			productoBean = (Producto) sesion.get(Producto.class,
					Integer.parseInt(idProducto));

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
		return productoBean;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Producto buscarNem(String nemProducto) throws Exception {

		// Lista de productos a recuperar
		List<Producto> listaProductos = new ArrayList<Producto>();
		Producto bean = new Producto();
		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {

			// Código SQL a ejecutar
			String hsql = "SELECT * FROM [dbo].[Productos] WHERE [Nemonico] ='" +  nemProducto+"'";

			// Ejecutando query para traer Lista
			listaProductos = sesion.createSQLQuery(hsql)
					.addEntity(Producto.class).setMaxResults(1).list();
			
			//Obtiene producto
			bean = listaProductos.get(0);
			
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
		return bean;
	}

}
