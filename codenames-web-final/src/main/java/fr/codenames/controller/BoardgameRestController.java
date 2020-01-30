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
import fr.codenames.dao.IDAOPartie;
import fr.codenames.model.CartesCles;
import fr.codenames.model.CartesNomDeCode;
import fr.codenames.model.Equipe;
import fr.codenames.model.Joueur;
import fr.codenames.model.Partie;
import fr.codenames.model.Passeur;

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

		p.setEquipes(p.affecterEquipe(p.getJoueurspartie()));

		//montration des équipes
		String s = "Composition des equipes : \r\n ";

		for (Equipe e : p.getEquipes()) {
			s = s + " Equipe " + e.getNom() + " ";

			for (int i = 0; i < e.getListeJoueur().size(); i++) {
				s = s + e.getListeJoueur().get(i).getPseudo() + ", ";

			}
			s = s + ".";
		}
		
		//montration des maitre espion
		
		for (Equipe e : p.getEquipes()) {
			for (int i = 0; i < e.getListeJoueur().size(); i++) {
				if (e.getListeJoueur().get(i).getRole() == "MaitreEspion") {
					s=s+ e.getListeJoueur().get(i).getPseudo()
							+ " est maitre espion de l'equipe " + e.getNom()+".";
				}
			}
		}

		return s;
	}
	
	
	@PostMapping("/boardgame/indice")
	@Transactional
	public String indice(@RequestBody Passeur passeur) {
	
		return "Le maitre espion a donne le mot "+ passeur.getIndice()+" qui correspond a "+passeur.getNbr()+" mots.";
	}
}
