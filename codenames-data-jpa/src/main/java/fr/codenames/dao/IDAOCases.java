package fr.codenames.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.codenames.model.CartesNomDeCode;
import fr.codenames.model.Cases;

public interface IDAOCases extends JpaRepository<Cases, Integer> {
	
	public Cases findByCartenomdecode(CartesNomDeCode carte);
	
	public List<Cases> findByPartieId (int id);
}
