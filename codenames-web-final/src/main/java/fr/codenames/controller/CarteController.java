package fr.codenames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.codenames.dao.IDAOCartesNomDeCode;
import fr.codenames.model.CartesNomDeCode;

@Controller
public class CarteController {

	@Autowired
	private IDAOCartesNomDeCode daocarte;

	@PostMapping("/creercartebouton")
	public String savepost(@ModelAttribute CartesNomDeCode carte) {
		daocarte.save(carte);
		return "Lobby";
	}

	@GetMapping("/creercartebouton")
	public String saveget() {
		return "Lobby";
	}

}
