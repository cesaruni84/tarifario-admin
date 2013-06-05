package org.srluren.web.hibernate.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {

		try {

			//Crea el SessionFactory en base al archivo hibernate.cfg.xml
			SessionFactory sessionFactory = new Configuration().configure("/org/srluren/web/hibernate/config/hibernate.cfg.xml").buildSessionFactory();
			return sessionFactory;
		}

		catch (Throwable ex) {
			System.err.println("Fallo De Inicializacion. " + ex);
			throw new ExceptionInInitializerError(ex);

		}

	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

}