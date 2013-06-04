/**
 * 
 */
package org.srluren.admin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.srluren.admin.beans.hb.Tasa;
import org.srluren.admin.dao.TasaDAO;
import org.srluren.admin.util.HibernateUtil;

/**
 * @author AETOS SAC.
 * 
 */
public class HibernateTasaDAO implements TasaDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.srluren.admin.dao.TasaDAO#listar()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Tasa> listar(String idSubProducto, String idTipoPersona,
			String idMoneda) throws Exception {

		// TODO Auto-generated method stub
		// Lista de Tasas a recuperar
		List<Tasa> listaTasas = new ArrayList<Tasa>();

		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {

			// Código SQL a ejecutar
			String hsql = "SELECT * FROM [dbo].[Tasa] WHERE [idSubProducto] = "
					+ idSubProducto + " AND  [idTipoPersona]=" + idTipoPersona
					+ " AND [idMoneda] =" + idMoneda + " ORDER BY [Rango] ASC";

			// Ejecutando query para traer Lista
			listaTasas = sesion.createSQLQuery(hsql).addEntity(Tasa.class)
					.list();

			// Liberando la conexion
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				System.out.println(e.getMessage());
				throw new Exception(e.getMessage(), e.getCause());
			}

		} finally {
			if (sesion.isOpen())
				sesion.close();
		}

		// Devuelve Lista
		return listaTasas;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.srluren.admin.dao.TasaDAO#buscar(java.lang.String)
	 */
	@Override
	public Tasa buscar(String idTasa) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.srluren.admin.dao.TasaDAO#actualizar(org.srluren.admin.beans.hb.Tasa)
	 */
	@Override
	public Integer actualizar(Tasa tasaBean) throws Exception {
		// TODO Auto-generated method stub

		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {
			tasaBean = (Tasa) sesion.merge(tasaBean);
			tx.commit();

		} catch (Exception e) {
			if (tx != null){
				tx.rollback();
				throw new Exception(e.getMessage(),e.getCause());
			}
		} finally {
			if (sesion.isOpen())
				sesion.close();
		}

		// retorna id de bean actualizado
		return tasaBean.getIdTasa();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.srluren.admin.dao.TasaDAO#insertar(org.srluren.admin.beans.hb.Tasa)
	 */
	@Override
	public Integer insertar(Tasa tasaBean) throws Exception {
		// TODO Auto-generated method stub

		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {
			Integer id = (Integer) sesion.save(tasaBean);
			tasaBean.setIdTasa(id);
			tx.commit();

		} catch (Exception e) {
			if (tx != null){
				tx.rollback();
				throw new Exception(e.getMessage(),e.getCause());
			}
		} finally {
			if (sesion.isOpen())
				sesion.close();
		}

		// Retorna id de bean creado
		return tasaBean.getIdTasa();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.srluren.admin.dao.TasaDAO#eliminar(java.lang.Integer)
	 */
	@Override
	public Integer eliminar(Integer idTasa) throws Exception {
		// TODO Auto-generated method stub

		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		// Id de Bean
		Integer idBean = 0;

		try {
			// Ejecuta consulta
			Tasa Tasa = (Tasa) sesion.get(Tasa.class, idTasa);
			idBean = Tasa.getIdTasa();
			sesion.delete(Tasa);

			// Libera conexion
			tx.commit();

		} catch (Exception e) {
			if (tx != null){
				tx.rollback();
				throw new Exception(e.getMessage(),e.getCause());
			}
		} finally {
			if (sesion.isOpen())
				sesion.close();
		}
		// Retorna id de bean eliminado
		return idBean;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tasa> listarxPlazos(
			String idSubProducto, 
			String idTipoPersona,
			String idMoneda, 
			String plzoIni, 
			String plzoFinal, 
			String plzoUnidad
	) throws Exception {
		
		// Lista de Tasas a recuperar
		List<Tasa> listaTasas = new ArrayList<Tasa>();

		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {

			// Código SQL a ejecutar
			String hsql = "SELECT * FROM [dbo].[Tasa] WHERE [idSubProducto] = "
					+ idSubProducto + " AND  [idTipoPersona]=" + idTipoPersona
					+ " AND [idMoneda] =" + idMoneda +  " AND [PlazoInicial] ="+ plzoIni
					+ " AND [PlazoFinal] ="+ plzoFinal + " AND [UnidadPlazo] ="+ plzoUnidad
					+ " ORDER BY [Rango] ASC";

			// Ejecutando query para traer Lista
			listaTasas = sesion.createSQLQuery(hsql).addEntity(Tasa.class)
					.list();

			// Liberando la conexion
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				System.out.println(e.getMessage());
				throw new Exception(e.getMessage(), e.getCause());
			}

		} finally {
			if (sesion.isOpen())
				sesion.close();
		}

		// Devuelve Lista
		return listaTasas;
	}

}
