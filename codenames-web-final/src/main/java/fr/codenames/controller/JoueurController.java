package fr.codenames.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.codenames.dao.IDAOJoueur;
import fr.codenames.model.Joueur;

@Controller
public class JoueurController {
@Autowired
private IDAOJoueur daoJoueur;
	
	@GetMapping("/listeJoueurs")
	public String findAll(Model model) {
		model.addAttribute("joueurs", daoJoueur.findAll());
		return "Lobby";
	}
	
	
	@GetMapping("/ajouterJoueur")
	public String add(Model model) {
		model.addAttribute("joueur", new Joueur());

		return "ajouterJoueur";
	}

	@PostMapping("/ajouterJoueur")
	public String add(@Valid @ModelAttribute Joueur joueur, BindingResult result, Model model) {
		
		//S'IL Y A DES ERREURS, ON REAFFICHE LE FORMULAIRE
		if(result.hasErrors()) {
			return "ajouterJoueur";
		}
		daoJoueur.save(joueur);
		return "redirect:/listeJoueur";
	}
	
	
	@GetMapping("/supprimerJoueur")
	public String delete(
			@RequestParam int id
			) {
		
		daoJoueur.deleteById(id);
		return "redirect:/listeJoueur";
	}
	
	
	@GetMapping("/modifierJoueur")
	public String edit(Model model, @RequestParam int id) {
		model.addAttribute("joueurs", daoJoueur.findAll());
		Joueur j = daoJoueur.findById(id).orElse(new Joueur());

		model.addAttribute("joueur", j);
		return "ajouterJoueur";
	}
	 
	
	@PostMapping("/modifierJoueur")
	public String edit(@Valid @ModelAttribute Joueur joueur, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "ajouterJoueur";
		}
		daoJoueur.save(joueur);
		return "redirect:/listeJoueur";
	}
	
	
	
	
	
}
