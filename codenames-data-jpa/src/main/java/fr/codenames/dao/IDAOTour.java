package fr.codenames.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import fr.codenames.model.Tour;

@EnableJpaRepositories
public interface IDAOTour extends JpaRepository<Tour, Integer> {


}
