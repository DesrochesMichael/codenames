package fr.codenames.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.codenames.model.CartesNomDeCode;
import fr.codenames.model.Cases;

public interface IDAOCases extends JpaRepository<Cases, Integer> {
	
	public Cases findByCartenomdecode(CartesNomDeCode carte);
	
	
}
