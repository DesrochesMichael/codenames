package fr.codenames.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import fr.codenames.model.CartesNomDeCode;
import fr.codenames.model.Partie;


public interface IDAOCartesNomDeCode extends JpaRepository<CartesNomDeCode, Integer> {

	public List<CartesNomDeCode> findAll();

	public CartesNomDeCode findByNom(String nom);

	public CartesNomDeCode save(CartesNomDeCode c);

	public void deleteByNom(String nom);

	public List<CartesNomDeCode> findByPartieId (int id);
	
}
