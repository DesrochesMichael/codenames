package fr.codenames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.codenames.dao.IDAOCartesNomDeCode;
import fr.codenames.model.CartesNomDeCode;

@Controller
public class CarteController {

	@Autowired
	private IDAOCartesNomDeCode daocarte;

	// creer carte
	@PostMapping("/creercartebouton")
	public String savepost(@ModelAttribute CartesNomDeCode carte) {
		daocarte.save(carte);
		return "redirect:Lobby";
	}

	@GetMapping("creercartebouton")
	public String saveget() {
		return "redirect:Lobby";
	}

	// modifier carte
	@PostMapping("/modifiercartebouton")
	public String modifpost(@ModelAttribute CartesNomDeCode carte, @RequestParam("nouveaunom") String nom) {
		carte = daocarte.findByNom(carte.getNom());
		carte.setNom(nom);
		daocarte.save(carte);
		return "redirect:Lobby";
	}

	@GetMapping("/modifiercartebouton")
	public String getpost() {
		return "redirect:Lobby";
	}

	// supprimer carte
	@PostMapping("/supcartebouton")
	public String suppost(@ModelAttribute CartesNomDeCode carte) {
		carte = daocarte.findByNom(carte.getNom());
		daocarte.delete(carte);
		return "redirect:Lobby";
	}

	@GetMapping("/supcartebouton")
	public String supget() {
		return "redirect:Lobby";
	}

}
