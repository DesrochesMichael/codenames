package codenames.dao.hibernate;

import java.util.List;

import codenames.dao.IDAO;
import fr.codenames.model.CartesNomDeCode;
import fr.codenames.model.Joueur;

public class DAOCarteNomDeCodeHibernate extends DAOHibernate<CartesNomDeCode> implements IDAO<CartesNomDeCode, Integer>  {

	
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




}
