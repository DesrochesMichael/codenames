package fr.codenames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import fr.codenames.dao.IDAOPartie;

@RestController
public class PartieController {

	@Autowired
	IDAOPartie daopartie;
	
	
}
