package fr.codenames.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.codenames.dao.IDAOCartesNomDeCode;
import fr.codenames.model.CartesNomDeCode;

@Controller
public class BoardgameController {

	@Autowired
	IDAOCartesNomDeCode daocarte;
	
	
	@GetMapping("/boardgame")
	public String affichage(Model model) {
		List<CartesNomDeCode> cartes = daocarte.findAll();
		Collections.shuffle(cartes);
		model.addAttribute("cartes",cartes);
		return "boardgame";
	}
	
	//Jusqu'au choix du premier mot
	
}
