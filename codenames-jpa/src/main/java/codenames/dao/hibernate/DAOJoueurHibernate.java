package codenames.dao.hibernate;

import java.util.List;

import codenames.dao.IDAO;
import fr.codenames.model.Joueur;

public class DAOJoueurHibernate extends DAOHibernate implements IDAO<Joueur, Integer> {

	@Override
	public List<Joueur> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("select p from Produit p", Joueur.class).getResultList();
	}

	@Override
	public Joueur finByID(Integer id) {

		return em.find(Joueur.class, id);
	}

	@Override
	public Joueur save(Joueur entity) {
		try {

			em.getTransaction().begin();

			if (entity.getId() == 0) {
				em.persist(entity);
			}

			else {
				em.merge(entity);
			}

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		return entity;
	}

	@Override
	public void delete(Joueur entity) {
		try {
			em.getTransaction().begin();
			em.remove(em.merge(entity));
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}

	}

	@Override
	public void deleteByID(Integer id) {
		// TODO Auto-generated method stub

	}

}
