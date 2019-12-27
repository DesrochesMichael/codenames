package codenames.dao.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public abstract class DAOHibernate<T> {

	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("NomPersistenceUnit");
	protected EntityManager em = emf.createEntityManager();
	protected Class<T> cls;

	public static void close() {
		if (emf != null) {
			emf.close();
			emf = null;
		}
	}

	public T finByID(Integer id) {

		return em.find(cls, id);
		
	}


}
