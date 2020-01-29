package fr.codenames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.codenames.dao.IDAOJoueur;
import fr.codenames.model.Joueur;
import fr.codenames.model.Passeur;

@RestController
public class JoueurController {

	@Autowired
	private IDAOJoueur daojoueur;

	@PostMapping("/Lobby/connect")
	public int connect(@RequestBody Joueur joueur) {

		Joueur j = daojoueur.findByPseudo(joueur.getPseudo());

		if (j == null) {
			return 0;
		}

		else if (j.getMdp().equalsIgnoreCase(joueur.getMdp()) == false) {
			return 1;
		}

		else {
			return 2;
		}
	}

	@PostMapping("/Lobby/creerjoueur")
	public int creerjoueur(@RequestBody Joueur joueur) {
		daojoueur.save(joueur);
		return 0;
	}

	@PostMapping("/Lobby/modifpseudo")
	public int modifpseudo(@RequestBody Passeur passeur) {
		Joueur j = daojoueur.findByPseudo(passeur.getPseudo1());

		if (j == null) {
			return -1;
		}

		else if (daojoueur.findByPseudo(passeur.getPseudo2()) != null) {
			return 0;
		}
		
		else if (j.getMdp().equalsIgnoreCase(passeur.getMdp1())==false) {
			return 1;
		}

		else {
			j.setPseudo(passeur.getPseudo2());
			daojoueur.save(j);
			return 2;
		}
	}

	@PostMapping("/Lobby/modifmdp")
	public int modifmdp(@RequestBody Passeur passeur) {
		Joueur j = daojoueur.findByPseudo(passeur.getPseudo1());

		if (j == null) {
			return 0;
		}
		else if (j.getMdp().equalsIgnoreCase(passeur.getMdp1())==false) {
			return 1;
		}

		else {
			j.setMdp(passeur.getMdp2());
			daojoueur.save(j);
			return 2;
		}
	}

}

//	@GetMapping("/listeJoueurs")
//	public String findAll(Model model) {
//		model.addAttribute("joueurs", daoJoueur.findAll());
//		return "Lobby";
//	}
//
//	@PostMapping("/connecter")
//	public String connect(@Valid @ModelAttribute Joueur joueur, BindingResult result, Model model) {
//		
//		//S'IL Y A DES ERREURS, ON REAFFICHE LE FORMULAIRE
//		if(result.hasErrors()) {
//			return "Lobby";
//		}
//		daoJoueur.connect(joueur);
//		return "Lobby";
//	}
//	
//	
//	
//	@GetMapping("/creerjoueurbouton")
//	public String add(Model model) {
//		model.addAttribute("joueur", new Joueur());
//		return "redirect:Lobby";
//	}
//
//	@PostMapping("/creerjoueurbouton")
//	public String add(@Valid @ModelAttribute Joueur joueur, BindingResult result, Model model, @RequestParam("confmdp")String mdp) {
//		
//		//S'IL Y A DES ERREURS, ON REAFFICHE LE FORMULAIRE
//		if(result.hasErrors() || joueur.getMdp().equalsIgnoreCase(mdp) != true) {
//			System.out.println("Err");
//			return "redirect:Lobby";
//		}
//		daoJoueur.save(joueur);
//		return "redirect:Lobby";
//	}
//
//	@GetMapping("/supprimerJoueur")
//	public String delete(Model model) {
//		return "redirect:/Lobby";
//	}
//	
//	@PostMapping("/supprimerJoueur")
//	public String delete(@ModelAttribute Joueur joueur, @RequestParam String mdp) {
//		joueur = daoJoueur.findByPseudo(joueur.getPseudo());
//		if(joueur.getMdp().equalsIgnoreCase(mdp) != true) {
//			System.out.println("FAUX");
//		}else {
//			daoJoueur.delete(joueur);	
//		}
//		return "redirect:/Lobby";
//	}
//	
//	
//
//	
//	@GetMapping("/modifPseudo")
//	public String editPseudo(Model model, @RequestParam int id) {
//			return "redirect:/Lobby";
//	}
//	 
//	
//	@PostMapping("/modifPseudo")
//	public String editPseudo(@Valid @ModelAttribute Joueur joueur, BindingResult result, Model model, @RequestParam String mdp, @RequestParam String newpseudo) {
//		joueur = daoJoueur.findByPseudo(joueur.getPseudo());
//		if(result.hasErrors() || joueur == null) {
//			System.out.println("Joueur inconnu");
//		}else if(joueur.getMdp().equalsIgnoreCase(mdp) != true){
//				System.out.println("Mdp incorrect");
//			}else {
//				joueur.setPseudo(newpseudo);
//				daoJoueur.save(joueur);
//			}
//		return "redirect:/Lobby";
//	}
//	
//	
//	
//	@GetMapping("/modifMdp")
//	public String editMdp(Model model, @RequestParam int id) {
//			return "redirect:/Lobby";
//	}
//	 
//	
//	@PostMapping("/modifMdp")
//	public String editMdp(@Valid @ModelAttribute Joueur joueur, BindingResult result, Model model, @RequestParam String mdp,@RequestParam String newmdp, @RequestParam String confnewmdp) {
//		joueur = daoJoueur.findByPseudo(joueur.getPseudo());
//		if(result.hasErrors() || joueur == null) {
//			System.out.println("Joueur inconnu");
//		}else if(joueur.getMdp().equalsIgnoreCase(mdp) != true){
//				System.out.println("Mdp incorrect");
//			}else {
//				joueur.setMdp(newmdp);
//				if (joueur.getMdp().equalsIgnoreCase(confnewmdp)== true) {
//				daoJoueur.save(joueur);
//			}else {
//				System.out.println("Les mdp ne correspondent pas");
//			}
//			}
//	return "redirect:/Lobby";
//	}
//
//		
//
//	// histo joueur
//	@PostMapping("/statjoueurbouton")
//	@ResponseBody
//	public String statpost(@ModelAttribute Joueur joueur, Model model) {
//		joueur = daoJoueur.findByPseudo(joueur.getPseudo());
//		model.addAttribute("joueur", joueur);
//		return joueur.getPseudo();
//	}
//
//
//}
