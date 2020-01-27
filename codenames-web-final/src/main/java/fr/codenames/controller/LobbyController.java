package fr.codenames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.codenames.dao.IDAOCartesNomDeCode;
import fr.codenames.dao.IDAOJoueur;

@Controller
public class LobbyController {

	@Autowired
	IDAOCartesNomDeCode daocarte;

	@Autowired
	IDAOJoueur daojoueur;

	@GetMapping("/Lobby")
	public String affichage(Model model) {
		model.addAttribute("cartes", daocarte.findAll());
		model.addAttribute("joueurs", daojoueur.findAll());
		return "Lobby";
	}
}
