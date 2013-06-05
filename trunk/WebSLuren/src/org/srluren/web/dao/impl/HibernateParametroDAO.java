/**
 * 
 */
package org.srluren.web.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.srluren.web.beans.hb.Parametro;
import org.srluren.web.dao.ParametroDAO;
import org.srluren.web.hibernate.config.HibernateUtil;

/**
 * @author AETOS SAC
 *
 */
public class HibernateParametroDAO implements ParametroDAO{

	@Override
	public List<Parametro> listar() throws Exception {
		return null;
	}

	@Override
	public Parametro buscar(String idParametro) throws Exception {
		
		// TODO Auto-generated method stub
		Parametro parametroBean = null;

		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {
			// Obtiene bean de base de datos
			parametroBean = (Parametro) sesion.get(Parametro.class,Integer.parseInt(idParametro));

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
		return parametroBean;
	}

	@Override
	public Integer actualizar(Parametro parametroBean) throws Exception {
		
		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {
			parametroBean = (Parametro) sesion.merge(parametroBean);
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
		return parametroBean.getIdParametro();
	}

	@Override
	public Integer insertar(Parametro parametroBean) throws Exception {
		
		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {
			Integer id = (Integer) sesion.save(parametroBean);
			parametroBean.setIdParametro(id);
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
		return parametroBean.getIdParametro();	
	}

}
