package fr.codenames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import codenames.dao.hibernate.DAOCarteNomDeCodeHibernate;
import fr.codenames.model.CartesCles;
import fr.codenames.model.CartesNomDeCode;
import fr.codenames.model.Cases;
import fr.codenames.model.Equipe;
import fr.codenames.model.Joueur;

public class Partie {
	private List<CartesNomDeCode> liste25 = new ArrayList<CartesNomDeCode>();
	private List<Joueur> joueurspartie = new ArrayList<Joueur>();
	private List<Equipe> equippartie = new ArrayList<Equipe>();

	public void affichageAgent(List<Cases> list) {
		int min =0;
		int max = 5;
		for (int i = 0; i < 5; i++) {
			for (int j = min; j < max; j++) {
				System.out.print(list.get(j).getCartenomdecode().getNom()+"   ");
			}
			min=min+5;
			max=max+5;
			System.out.println();
		}
	}

	public void affichageMaitreEspion(List<Cases> list) {

	}

	public List<CartesNomDeCode> choixMots() {
		DAOCarteNomDeCodeHibernate cartes = new DAOCarteNomDeCodeHibernate();

		List<CartesNomDeCode> liste = new ArrayList<CartesNomDeCode>();

		liste = cartes.findAll();
		Collections.shuffle(liste);

		for (int i = 0; i < 25; i++) {
			liste25.add(liste.get(i));
		}

		return liste25;

	}

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
				rouge.getListeJoueur().add(joueurs.remove(0));
			}
			if (rand == 1) {
				bleu.getListeJoueur().add(joueurs.remove(0));
			}
		}

		// on attribut une equipe aux autres joueurs.
		for (int i = 0; i < joueurs.size() / 2 + 1; i++) {

			rouge.getListeJoueur().add(joueurs.remove(0));
			bleu.getListeJoueur().add(joueurs.remove(0));

		}
		// choix des maitres espions dans chaque equipe

		int max = bleu.getListeJoueur().size() - 1;
		int min = 0;
		int range = max - min + 1;
		int rand = (int) (Math.random() * range) + min;

		bleu.getListeJoueur().get(rand).setRole("MaitreEspion");

		int max1 = rouge.getListeJoueur().size() - 1;
		int min1 = 0;
		int range1 = max1 - min1 + 1;
		int rand1 = (int) (Math.random() * range1) + min1;

		rouge.getListeJoueur().get(rand1).setRole("MaitreEspion");

		equipes.add(bleu);
		equipes.add(rouge);

		return equipes;
	}

	public List<Joueur> getJoueurspartie() {
		return joueurspartie;
	}

	public List<CartesNomDeCode> getListe25() {
		return liste25;
	}

	public void setListe25(List<CartesNomDeCode> liste25) {
		this.liste25 = liste25;
	}

	public List<Equipe> getEquippartie() {
		return equippartie;
	}

	public void setEquippartie(List<Equipe> equippartie) {
		this.equippartie = equippartie;
	}

	public void setJoueurspartie(List<Joueur> joueurspartie) {
		this.joueurspartie = joueurspartie;
	}
}
