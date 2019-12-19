package fr.codenames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import codenames.dao.IDAOCarteNomDeCode;
import codenames.dao.sql.DAOCarteNomDeCodeSQL;
import fr.codenames.model.CartesNomDeCode;
import fr.codenames.model.Equipe;
import fr.codenames.model.Joueur;

public class Partie {
	private List<CartesNomDeCode> cartescodepartie = new ArrayList<CartesNomDeCode>();
	private List<Joueur> joueurspartie = new ArrayList<Joueur>();
	private List<Equipe> equippartie = new ArrayList<Equipe>();

	public List<CartesNomDeCode> choixMots() {

		IDAOCarteNomDeCode cartes = new DAOCarteNomDeCodeSQL();

		List<CartesNomDeCode> liste25 = new ArrayList<CartesNomDeCode>();
		List<CartesNomDeCode> liste = new ArrayList<CartesNomDeCode>();

		liste = cartes.findAll();
		Collections.shuffle(liste);

		for (int i = 0; i < 25; i++) {
			liste25.add(liste.remove(0));
		}

		return liste25;

	}

// a finir !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public List<Equipe> affecterEquipe(List<Joueur> joueurs) {
		List<Equipe> equipes = new ArrayList<Equipe>();

		Collections.shuffle(joueurs);

		Equipe rouge = new Equipe("rouge");
		Equipe bleu = new Equipe("bleu");

		// dans le cas de joueur en nombre impair on attribut le premier
		int reste = joueurs.size() % 2;
		if (reste != 0) {
			int max = 1;
			int min = 0;
			int range = max - min + 1;
			int rand = (int) (Math.random() * range) + min;

			if (rand == 0) {
				rouge.choixEquipe(joueurs.remove(0));
			}
			if (rand == 1) {
				bleu.choixEquipe(joueurs.remove(0));
			}
		}
		// on attribut une equipe aux autres joeurs.
		for (int i = 0; i < joueurs.size() / 2; i++) {
			rouge.choixEquipe(joueurs.remove(0));
			bleu.choixEquipe(joueurs.remove(0));
		}
		// choix des maitres espions dans chaque equipe
		for (Joueur j : bleu.getListeJoueur()) {
			int max = bleu.getListeJoueur().size() - 1;
			int min = 0;
			int range = max - min + 1;
			int rand = (int) (Math.random() * range) + min;
			bleu.getListeJoueur().get(rand).setRole("MaitreEspion");
		}

		for (Joueur j : rouge.getListeJoueur()) {
			int max = rouge.getListeJoueur().size() - 1;
			int min = 0;
			int range = max - min + 1;
			int rand = (int) (Math.random() * range) + min;
			rouge.getListeJoueur().get(rand).setRole("MaitreEspion");
		}

		equipes.add(bleu);
		equipes.add(rouge);
		return equipes;
	}

}
