package fr.codenames.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.codenames.dao.IDAOCartesNomDeCode;
import fr.codenames.dao.IDAOCases;
import fr.codenames.dao.IDAOEquipe;
import fr.codenames.dao.IDAOPartie;
import fr.codenames.dao.IDAOTour;
import fr.codenames.model.CartesCles;
import fr.codenames.model.CartesNomDeCode;
import fr.codenames.model.Cases;
import fr.codenames.model.Equipe;
import fr.codenames.model.Joueur;
import fr.codenames.model.Partie;
import fr.codenames.model.Passeur;
import fr.codenames.model.Tour;

@RestController
public class BoardgameRestController {

	@Autowired
	IDAOCartesNomDeCode daocarte;

	@Autowired
	IDAOPartie daopartie;

	@Autowired
	IDAOCases daocase;

	@Autowired
	IDAOTour daotour;

	@Autowired
	IDAOEquipe daoequipe;

	@PostMapping("/boardgame/initialisation")
	@Transactional
	public String initialisation(Model model, HttpSession session) {

		int id = (int) session.getAttribute("id");

		Partie p = daopartie.findById(id).get();

		// triche
		Joueur j1 = new Joueur();
		j1.setPseudo("mika");
		j1.setPartie(p);

		Joueur j2 = new Joueur();
		j2.setPseudo("pl");
		j2.setPartie(p);

		Joueur j3 = new Joueur();
		j3.setPseudo("camille");
		j3.setPartie(p);

		Joueur j4 = new Joueur();
		j4.setPseudo("jo");
		j4.setPartie(p);

		p.getJoueurspartie().add(j1);
		p.getJoueurspartie().add(j2);
		p.getJoueurspartie().add(j3);
		p.getJoueurspartie().add(j4);

		CartesCles cartescles = new CartesCles();

		List<CartesNomDeCode> cartes = daocarte.findByPartieId(id);

		p.setCasesRestantes(cartescles.attributionCases(cartes));

		// save en bdd des cases
		for (Cases c : p.getCasesRestantes()) {
//			c.setPartie(p);
//			daocase.save(c);
		}

		p.setEquipes(p.affecterEquipe(p.getJoueurspartie()));

		// montration des �quipes
		String s = "Composition des equipes : ";

		for (Equipe e : p.getEquipes()) {
			s = s + " Equipe " + e.getNom() + " ";

			for (int i = 0; i < e.getListeJoueur().size(); i++) {
				s = s + e.getListeJoueur().get(i).getPseudo() + ", ";

			}
			s = s + ".";
		}

		// montration des maitre espion

		for (Equipe e : p.getEquipes()) {
			for (int i = 0; i < e.getListeJoueur().size(); i++) {
				if (e.getListeJoueur().get(i).getRole() == "MaitreEspion") {
					s = s + e.getListeJoueur().get(i).getPseudo() + " est maitre espion de l'equipe " + e.getNom()
							+ ". ";
				}
			}
		}

		// qui commence
		int index = p.quiCommence(p.getCasesRestantes());
		s = s + "L'equipe " + p.getEquipes().get(index).getNom() + " commence.";

		// save en bdd
//		p.getEquipes().get(0).setPartie(p);
//		p.getEquipes().get(1).setPartie(p);
//		daoequipe.save(p.getEquipes().get(0));
//		daoequipe.save(p.getEquipes().get(1));

		Tour tour = new Tour();

		tour.setEquipe(p.getEquipes().get(index));
		tour.setPartie(p);
//		daotour.save(tour);
		return s;
	}

	@PostMapping("/boardgame/indice")
	@Transactional
	public String indice(@RequestBody Passeur passeur, HttpSession session) {
		Tour t = daotour.findTopOneByPartieIdOrderByIdDesc((int) session.getAttribute("id"));
		t.setNbrReponse(passeur.getNbr());
		t.setIndice(passeur.getIndice());
		daotour.save(t);
		return "Le maitre espion a donne le mot " + passeur.getIndice() + " qui correspond a " + passeur.getNbr()
				+ " mots.";
	}

	@PostMapping("/boardgame/reponse")
	@Transactional
	public int reponse(@RequestBody Passeur passeur, HttpSession session) {
		System.out.println(passeur.getCarte());
		List<Cases> cases = daocase.findByPartieId((int) session.getAttribute("id"));
		String couleur = null;
		for (Cases c : cases) {
			if (c.getCartenomdecode().getNom().equalsIgnoreCase(passeur.getCarte()) == true) {
				couleur = c.getCouleur();
			}
		}

		System.out.println("Cases : " + couleur);

		if (couleur.equalsIgnoreCase("noir")) {
			return 0;
		}

		else if (couleur.equalsIgnoreCase("gris")) {
			return 1;
		}

		else if (couleur.equalsIgnoreCase("bleu")) {

			if (daotour.findTopOneByPartieIdOrderByIdDesc((int) session.getAttribute("id")).getEquipe().getNom()
					.equalsIgnoreCase("bleu") == true) {
				return 2;
			} else {
				return 3;
			}
		}

		else if (couleur.equalsIgnoreCase("rouge")) {
			if (daotour.findTopOneByPartieIdOrderByIdDesc((int) session.getAttribute("id")).getEquipe().getNom()
					.equalsIgnoreCase("rouge") == true) {
				return 2;
			} else {
				return 3;
			}
		}
		return 0;

	}

}
