package codenames.dao.hibernate;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import codenames.dao.IDAO;
import fr.codenames.model.CartesNomDeCode;
import fr.codenames.model.Joueur;

public class DAOCarteNomDeCodeHibernate extends DAOHibernate<CartesNomDeCode> implements IDAO<CartesNomDeCode, Integer>  {

	
	public CartesNomDeCode findByNom(String nom) {
		try {
			TypedQuery<CartesNomDeCode> myQuery = em.createQuery("select c from CartesNomDeCode c where c.nom = :nom", CartesNomDeCode.class);
			myQuery.setParameter("nom", nom);
			return myQuery.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Pas de carte de ce nom");
		}
		return null;
	}
	
	
	@Override
	public List<CartesNomDeCode> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("select c from CartesNomDeCode c", CartesNomDeCode.class).getResultList();
	}

	@Override
	public CartesNomDeCode save(CartesNomDeCode entity) {
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
	public void deleteByID(Integer id) {
		CartesNomDeCode sup = new CartesNomDeCode();
		sup.setId(id);
		delete(sup);
		
	}

	@Override
	public void delete(CartesNomDeCode entity) {
		em.getTransaction().begin();
		Query query = em.createQuery("delete from CartesNomDeCode c where c.nom = :nom");
		query.setParameter("nom",entity.getNom()).executeUpdate();
		em.getTransaction().commit();
	}




}
