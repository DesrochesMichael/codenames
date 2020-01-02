package fr.codenames.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.codenames.model.Joueur;

public interface IDAOJoueur extends JpaRepository<Joueur, Integer> {

	public Joueur findByNom(String nom);
	public Joueur deleteByNom(String Nom);
	
	
}
