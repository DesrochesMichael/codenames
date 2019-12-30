package codenames.dao;

import fr.codenames.model.Joueur;

public interface IDAOJoueur extends IDAO<Joueur, Integer> {
	
	public Joueur findByNom(String nom);
}
