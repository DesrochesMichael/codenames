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
	
	
	
	@GetMapping("/connecter")
	public String connect(Model model) {
		model.addAttribute("joueur", new Joueur());
		return "Lobby";
	}

	@PostMapping("/connecter")
	public String connect(@Valid @ModelAttribute Joueur joueur, BindingResult result, Model model) {
		
		//S'IL Y A DES ERREURS, ON REAFFICHE LE FORMULAIRE
		if(result.hasErrors()) {
			return "Lobby";
		}
		daoJoueur.connect(joueur);
		return "Lobby";
	}
	
	
	
	@GetMapping("/creerjoueurbouton")
	public String add(Model model) {
		model.addAttribute("joueur", new Joueur());
		return "redirect:Lobby";
	}

	@PostMapping("/creerjoueurbouton")
	public String add(@Valid @ModelAttribute Joueur joueur, BindingResult result, Model model, @RequestParam("confmdp")String mdp) {
		
		//S'IL Y A DES ERREURS, ON REAFFICHE LE FORMULAIRE
		if(result.hasErrors() || joueur.getMdp().equalsIgnoreCase(mdp) != true) {
			System.out.println("Err");
			return "redirect:Lobby";
		}
		daoJoueur.save(joueur);
		return "redirect:Lobby";
	}
	
	
	
	
	@GetMapping("/supprimerJoueur")
	public String delete(Model model) {
		return "redirect:/Lobby";
	}
	
	@PostMapping("/supprimerJoueur")
	public String delete(@ModelAttribute Joueur joueur, @RequestParam String mdp) {
		joueur = daoJoueur.findByPseudo(joueur.getPseudo());
		if(joueur.getMdp().equalsIgnoreCase(mdp) != true) {
			System.out.println("FAUX");
		}else {
			daoJoueur.delete(joueur);	
		}
		return "redirect:/Lobby";
	}
	
	

	
	@GetMapping("/modifPseudo")
	public String editPseudo(Model model, @RequestParam int id) {
			return "redirect:/Lobby";
	}
	 
	
	@PostMapping("/modifPseudo")
	public String editPseudo(@Valid @ModelAttribute Joueur joueur, BindingResult result, Model model, @RequestParam String mdp, @RequestParam String newpseudo) {
		joueur = daoJoueur.findByPseudo(joueur.getPseudo());
		if(result.hasErrors() || joueur == null) {
			System.out.println("Joueur inconnu");
		}else if(joueur.getMdp().equalsIgnoreCase(mdp) != true){
				System.out.println("Mdp incorrect");
			}else {
				joueur.setPseudo(newpseudo);
				daoJoueur.save(joueur);
			}
		return "redirect:/Lobby";
	}
	
	
	
	@GetMapping("/modifMdp")
	public String editMdp(Model model, @RequestParam int id) {
			return "redirect:/Lobby";
	}
	 
	
	@PostMapping("/modifMdp")
	public String editMdp(@Valid @ModelAttribute Joueur joueur, BindingResult result, Model model, @RequestParam String mdp,@RequestParam String newmdp, @RequestParam String confnewmdp) {
		joueur = daoJoueur.findByPseudo(joueur.getPseudo());
		if(result.hasErrors() || joueur == null) {
			System.out.println("Joueur inconnu");
		}else if(joueur.getMdp().equalsIgnoreCase(mdp) != true){
				System.out.println("Mdp incorrect");
			}else {
				joueur.setMdp(newmdp);
				if (joueur.getMdp().equalsIgnoreCase(confnewmdp)== true) {
				daoJoueur.save(joueur);
			}else {
				System.out.println("Les mdp ne correspondent pas");
			}
			}
	return "redirect:/Lobby";
	}

		
}
