/**
 * 
 */
package org.srluren.web.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.srluren.web.beans.hb.Plazo;
import org.srluren.web.beans.hb.Tasa;
import org.srluren.web.dao.TasaDAO;
import org.srluren.web.hibernate.config.HibernateUtil;

/**
 * @author AETOS PERU S.A.C.
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
	public Tasa buscarRango(String idSubProducto, String idTipoPersona,
			String idMoneda, String monto) throws Exception {

		// Lista de Tasas a recuperar
		List<Tasa> listaTasas = new ArrayList<Tasa>();
		Tasa beanTasa = new Tasa();
		
		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {

			// Código SQL a ejecutar
			String hsql = "SELECT * FROM [dbo].[Tasa] WHERE [idSubProducto] = "
					+ idSubProducto + " AND  [idTipoPersona]=" + idTipoPersona
					+ " AND [idMoneda] =" + idMoneda + " AND [MtoInicial]<=" + monto  + " AND [MtoFinal] >="+ monto;

			// Ejecutando query para traer Lista
			listaTasas = sesion.createSQLQuery(hsql).addEntity(Tasa.class).setMaxResults(1).list();
			
			//Obtiene tasa
			beanTasa = listaTasas.get(0);
			
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

		// Devuelve bean
		return beanTasa;		
		

	}

	@SuppressWarnings("unchecked")
	@Override
	public Tasa buscarRangoxMontoPlazo(String idSubProducto,
			String idTipoPersona, String idMoneda, String monto, String plazo)
			throws Exception {
		
		// Lista de Tasas a recuperar
		List<Tasa> listaTasas = new ArrayList<Tasa>();
		Tasa beanTasa = null;
		
		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {

			// Código SQL a ejecutar
			String hsql = "SELECT * FROM [dbo].[Tasa] WHERE [idSubProducto] = "
					+ idSubProducto + " AND  [idTipoPersona]=" + idTipoPersona
					+ " AND [idMoneda] =" + idMoneda + " AND ([MtoInicial]<=" + monto  + " AND [MtoFinal] >="+ monto + ")"
					+ " AND ([PlazoInicial]<=" + plazo + " AND [PlazoFinal]>=" + plazo + ")";
					

			// Ejecutando query para traer Lista
			listaTasas = sesion.createSQLQuery(hsql).addEntity(Tasa.class).setMaxResults(1).list();
			
			//Obtiene tasa
			if (listaTasas.size()>0){
				beanTasa = listaTasas.get(0);
			}
			
			// Liberando la conexion
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				System.out.println(e.getMessage());
				throw new Exception(e.getMessage(), e.getCause());
			}

		} finally {
			if (sesion.isOpen()){
				sesion.close();
			}
		}

		// Devuelve bean
		return beanTasa;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Plazo> listarPlazos(String idSubProducto, String idTipoPersona, String idMoneda) throws Exception {
		// TODO Auto-generated method stub
		
		// Lista de Plazos a recuperar
		List<Plazo> listaPlazos = new ArrayList<Plazo>();

		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {

			// Código SQL a ejecutar
			String hsql = "SELECT  [DescripcionPlazo], [PlazoFinal], [PlazoInicial] FROM [dbo].[Tasa] WHERE [idSubProducto] = "
					+ idSubProducto + " AND  [idTipoPersona]=" + idTipoPersona
					+ " AND [idMoneda] =" + idMoneda
					+ " GROUP BY [DescripcionPlazo] , [PlazoFinal] , [PlazoInicial] ORDER BY [PlazoFinal] ASC";
	
			// Ejecutando query para traer Lista
			SQLQuery sqlQuery = sesion.createSQLQuery(hsql);

			//Obtiene lista
			List<Object[]> listaObjetos = sqlQuery.list();
			
			//
			int n = listaObjetos.size();
			int i = 1;
			
			//Recorre lista
			for (Object[] objects: listaObjetos){
				
				//Objeto Plazo
				Plazo plazo = null;
				
				//Valida si es ultimo rango
				if (i >=n){
					 plazo = new Plazo((String)objects[0], (Integer)objects[2]);
				}else{
					 plazo = new Plazo((String)objects[0], (Integer)objects[1]);

				}
				listaPlazos.add(plazo);
				
				//Contador
				i++;
			}
			
			// Liberando la conexion
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				throw new Exception(e.getMessage(), e.getCause());
			}

		} finally {
			if (sesion.isOpen()){
				sesion.close();
			}
		}

		// Devuelve bean
		return listaPlazos;	
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Tasa buscarRangoxMonto(String idSubProducto, String idTipoPersona,
			String idMoneda, String plazo) throws Exception {
		
		// Lista de Tasas a recuperar
		Tasa beanTasa = null;
		
		// Obteniendo una instancia de la sesion del HB
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

		// Iniciamos la transaccion
		Transaction tx = sesion.beginTransaction();

		try {

			// Código SQL a ejecutar
			String hsql = "SELECT MIN(MtoInicial) AS MtoInicial, MAX(MtoFinal) AS MtoFinal FROM [dbo].[Tasa] WHERE [idSubProducto] = "
					+ idSubProducto + " AND  [idTipoPersona]=" + idTipoPersona
					+ " AND [idMoneda] =" + idMoneda + " AND ([PlazoInicial]<=" + plazo  + " AND [PlazoFinal] >="+ plazo + ")";
						
			// Ejecutando query para traer Lista
			SQLQuery sqlQuery = sesion.createSQLQuery(hsql);

			//Obtiene lista
			List<Object[]> listaObjetos = sqlQuery.list();
			
			if (listaObjetos.size() > 0){
				
				//Recorre lista
				for (Object[] objects: listaObjetos){
					if (objects[0] !=null && objects[1] !=null){
						beanTasa = new Tasa((BigDecimal)objects[0], (BigDecimal)objects[1]);
					}
				}	
			}

			// Liberando la conexion
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				System.out.println(e.getMessage());
				throw new Exception(e.getMessage(), e.getCause());
			}

		} finally {
			if (sesion.isOpen()){
				sesion.close();
			}
		}

		// Devuelve bean
		return beanTasa;	
	}

}
