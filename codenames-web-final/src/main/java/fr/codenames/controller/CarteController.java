package fr.codenames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import fr.codenames.dao.IDAOCartesNomDeCode;

@Controller
public class CarteController {

	@Autowired
	private IDAOCartesNomDeCode daocarte;
	
}
