package fr.codenames.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import fr.codenames.model.Partie;

@EnableJpaRepositories
public interface IDAOPartie extends JpaRepository<Partie, Integer> {

	public Partie findTopByOrderByIdDesc();
}
