package fr.codenames.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.codenames.dao.IDAOCartesNomDeCode;
import fr.codenames.dao.IDAOPartie;
import fr.codenames.model.CartesNomDeCode;
import fr.codenames.model.Partie;

@Controller
@Transactional
public class BoardgameController {

	@Autowired
	IDAOCartesNomDeCode daocarte;

	@Autowired
	IDAOPartie daopartie;

	@GetMapping("/boardgame")
	@Transactional
	public String affichage(Model model, HttpSession session) {

		// triche a supprimer
		session.setAttribute("id", 15);

		Partie p = daopartie.findById((int) session.getAttribute("id")).get();
//		List<CartesNomDeCode> cartes = p.choixMots(daocarte.findAll());
		
		List<CartesNomDeCode> cartes = daocarte.findByPartieId((int) session.getAttribute("id"));
		model.addAttribute("cartes", cartes);
		
		for (int i=0;i<25;i++) {
//			cartes.get(i).setPartie(p);
//			daocarte.save(cartes.get(i));
		}
		System.out.println("fini l'affichage");

		return "boardgame";
	}
}