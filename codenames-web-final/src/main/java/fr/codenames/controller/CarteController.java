package fr.codenames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import fr.codenames.dao.IDAOCartesNomDeCode;

@Controller
public class CarteController {

	@Autowired
	private IDAOCartesNomDeCode daocarte;

	@PostMapping("/creercarte")
	public String findall(Model model) {
		model.addAttribute("cartes", daocarte.findAll());

		return "Lobby";
	}

}
