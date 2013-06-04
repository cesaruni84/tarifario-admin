/**
 * 
 */
package org.srluren.admin.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.srluren.admin.beans.hb.Usuario;
import org.srluren.admin.dao.UsuarioDAO;
import org.srluren.admin.util.HibernateUtil;

/**
 * @author cesar
 * 
 */
public class HibernateUsuarioDAO implements UsuarioDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.srluren.admin.dao.UsuarioDAO#autenticar(java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Usuario autenticar(String codigo, String clave) throws Exception {

		// TODO Auto-generated method stub

		// Lista de Usuarios a recuperar
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();

		// Usuario a devolver
		Usuario beanUsuario = new Usuario();

		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		
		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {
			// Código SQL a ejecutar
			String hsql = "SELECT * FROM [dbo].[Usuario] WHERE [Codigo] ='"
					+ codigo + "' AND [Clave]='" + clave + "'";

			// Ejecutando query para traer Lista
			listaUsuarios = sesion.createSQLQuery(hsql)
					.addEntity(Usuario.class).setMaxResults(1).list();
			Iterator<Usuario> iter = listaUsuarios.iterator();

			while (iter.hasNext()) {
				beanUsuario = (Usuario) iter.next();
			}
			

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

		// Retornando bean
		return beanUsuario;

	}
}
