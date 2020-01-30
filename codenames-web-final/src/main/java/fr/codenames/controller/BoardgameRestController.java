package fr.codenames.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.codenames.dao.IDAOCartesNomDeCode;
import fr.codenames.dao.IDAOPartie;
import fr.codenames.model.CartesCles;
import fr.codenames.model.CartesNomDeCode;
import fr.codenames.model.Joueur;
import fr.codenames.model.Partie;

@RestController
public class BoardgameRestController {

	@Autowired
	IDAOCartesNomDeCode daocarte;

	@Autowired
	IDAOPartie daopartie;

	@PostMapping("/boardgame/initialisation")
	@Transactional
	public String initialisation(Model model, HttpSession session) {

		int id = (int) session.getAttribute("id");

		System.out.println("début du rest");

		Partie p = daopartie.findById(id).get();

		// triche
		Joueur j1 = new Joueur();
		j1.setPseudo("mika");
		j1.setPartie(p);

		Joueur j2 = new Joueur();
		j1.setPseudo("pl");
		j1.setPartie(p);

		CartesCles cartescles = new CartesCles();

		p.getJoueurspartie().add(j1);
		p.getJoueurspartie().add(j2);

		List<CartesNomDeCode> cartes = daocarte.findByPartieId(id);
		
		System.out.println("avant for");
		for (CartesNomDeCode c : cartes) {
			System.out.println(c.getNom());
		}
		
		p.setCasesRestantes(cartescles.attributionCases(cartes));

		p.setEquipes(p.affecterEquipe(p.getJoueurspartie()));
		
		return null;
	}
}
