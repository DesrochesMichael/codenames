package fr.codenames.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.codenames.model.CartesNomDeCode;

public interface IDAOCartesNomDeCode extends JpaRepository<CartesNomDeCode, Integer> {

}
