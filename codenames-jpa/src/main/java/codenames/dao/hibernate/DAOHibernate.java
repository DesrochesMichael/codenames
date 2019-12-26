package codenames.dao.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAOHibernate {
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("NomPersistenceUnit");
	protected EntityManager em = emf.createEntityManager();

	public static void close() {
		if (emf != null) {
			emf.close();
			emf = null;
		}
	}

}
