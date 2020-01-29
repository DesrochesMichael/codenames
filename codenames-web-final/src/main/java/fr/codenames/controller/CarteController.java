package fr.codenames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.codenames.dao.IDAOCartesNomDeCode;
import fr.codenames.model.CartesNomDeCode;
import fr.codenames.model.Passeur;

@RestController
public class CarteController {

	@Autowired
	private IDAOCartesNomDeCode daocarte;

	@PostMapping("/Lobby/creercarte")
	public int creercarte(@RequestBody Passeur passeur) {

		if (daocarte.findByNom(passeur.getNom1()) != null) {
			return 0;
		}

		else {
			CartesNomDeCode c = new CartesNomDeCode();
			c.setNom(passeur.getNom1());
			daocarte.save(c);
			return 1;
		}
	}

	@PostMapping("/Lobby/modifcarte")
	public int modifcarte(@RequestBody Passeur passeur) {
		CartesNomDeCode c = daocarte.findByNom(passeur.getNom1());
		if (c == null) {
			return 0;
		}

		else {
			c.setNom(passeur.getNom2());
			daocarte.save(c);
			return 1;
		}
	}
	
		
	@PostMapping("/Lobby/supcarte")
	@Transactional
	public int supcarte(@RequestBody Passeur passeur) {
		
		if (daocarte.findByNom(passeur.getNom1()) == null) {
			return 0;
		}

		else {
			daocarte.deleteByNom(passeur.getNom1());
			return 1;
		}
	}

//	// creer carte
//	@PostMapping("/creercartebouton")
//	public String savepost(@ModelAttribute CartesNomDeCode carte) {
//		daocarte.save(carte);
//		return "redirect:Lobby";
//	}
//
//	@GetMapping("creercartebouton")
//	public String saveget() {
//		return "redirect:Lobby";
//	}
//
//	// modifier carte
//	@PostMapping("/modifiercartebouton")
//	public String modifpost(@ModelAttribute CartesNomDeCode carte, @RequestParam("nouveaunom") String nom) {
//		carte = daocarte.findByNom(carte.getNom());
//		carte.setNom(nom);
//		daocarte.save(carte);
//		return "redirect:Lobby";
//	}
//
//	@GetMapping("/modifiercartebouton")
//	public String getpost() {
//		return "redirect:Lobby";
//	}
//
//	// supprimer carte
//	@PostMapping("/supcartebouton")
//	public String suppost(@ModelAttribute CartesNomDeCode carte) {
//		carte = daocarte.findByNom(carte.getNom());
//		daocarte.delete(carte);
//		return "redirect:Lobby";
//	}
//
//	@GetMapping("/supcartebouton")
//	public String supget() {
//		return "redirect:Lobby";
//	}

}
