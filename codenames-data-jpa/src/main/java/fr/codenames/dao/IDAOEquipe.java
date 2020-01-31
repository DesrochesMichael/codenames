package fr.codenames.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.codenames.model.Equipe;

public interface IDAOEquipe extends JpaRepository<Equipe, Integer> {
	

}
