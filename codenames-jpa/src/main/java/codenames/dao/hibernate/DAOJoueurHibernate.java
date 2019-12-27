package codenames.dao.hibernate;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import codenames.dao.IDAO;
import fr.codenames.model.Joueur;

public class DAOJoueurHibernate extends DAOHibernate<Joueur> implements IDAO<Joueur, Integer> {

	public DAOJoueurHibernate() {
		this.cls = Joueur.class;
	}

	public Joueur connect(Joueur j) {

		try {
			TypedQuery<Joueur> myQuery = em.createQuery("select j from Joueur j where j.pseudo = :nom and j.mdp=:mdp",
					Joueur.class);
			myQuery.setParameter("nom", j.getPseudo());
			myQuery.setParameter("mdp", j.getMdp());
			return myQuery.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public Joueur findByNom(String nom) {
		try {
			TypedQuery<Joueur> myQuery = em.createQuery("select j from Joueur j where j.pseudo = :nom", Joueur.class);
			myQuery.setParameter("nom", nom);
			return myQuery.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Pas de joueur de ce nom");
			;
		}
		return null;
	}

	@Override
	public List<Joueur> findAll() {

		return em.createQuery("select j from Joueur j", Joueur.class).getResultList();
	}

	@Override
	public Joueur save(Joueur entity) {
		if (this.findByNom(entity.getPseudo()) != null) {
			System.out.println("Ce nom de joueur existe deja");
			return null;
		}

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
		em.getTransaction().begin();
		Query query = em.createQuery("delete from Joueur j where j.pseudo = :pseudo");
		query.setParameter("pseudo",entity.getPseudo()).executeUpdate();
		em.getTransaction().commit();
	}
	
	@Override
	public void deleteByID(Integer id) {
		Joueur sup = new Joueur();
		sup.setId(id);
		delete(sup);

	}

}
