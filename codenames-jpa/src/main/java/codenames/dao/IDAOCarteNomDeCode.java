package codenames.dao;

import fr.codenames.model.CartesNomDeCode;
import fr.codenames.model.Joueur;

public interface IDAOCarteNomDeCode extends IDAO<CartesNomDeCode, Integer>{
	public Joueur findByNom(String nom);
}
