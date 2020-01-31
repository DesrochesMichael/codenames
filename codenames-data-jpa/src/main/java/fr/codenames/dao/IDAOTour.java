package fr.codenames.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import fr.codenames.model.Tour;


public interface IDAOTour extends JpaRepository<Tour, Integer> {

	public Tour findTopOneByPartieIdOrderByIdDesc(int id);
	
	
	
}
