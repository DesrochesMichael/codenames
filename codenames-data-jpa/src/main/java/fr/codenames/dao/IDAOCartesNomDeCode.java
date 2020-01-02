package fr.codenames.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.codenames.model.CartesNomDeCode;

public interface IDAOCartesNomDeCode extends JpaRepository<CartesNomDeCode, Integer> {
	
	public List<CartesNomDeCode> findAll();
	public CartesNomDeCode findByNom(String nom);
	public CartesNomDeCode save(CartesNomDeCode c);
	public CartesNomDeCode deleteByNom(String nom);
	
}
