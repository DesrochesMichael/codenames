package fr.codenames;

import java.util.ArrayList;
import java.util.List;

import fr.codenames.model.Equipe;
import fr.codenames.model.Joueur;

public class test {

	public static void main(String[] args) {

		Joueur mika = new Joueur();
		Joueur pl = new Joueur();
		Joueur jerem = new Joueur();
		Joueur jojo = new Joueur();
		
		mika.setPseudo("mika");
		pl.setPseudo("pl");
		jerem.setPseudo("jerem");
		jojo.setPseudo("jojo");
		
		List<Joueur> listeJoueur = new ArrayList<Joueur>();
		List<Equipe> equipes = new ArrayList<Equipe>();
		
		listeJoueur.add(jerem);
		listeJoueur.add(pl);
		listeJoueur.add(mika);
		listeJoueur.add(jojo);
		
		Partie test = new Partie();
		
		equipes = test.affecterEquipe(listeJoueur);
		
		for (Equipe e : equipes ) {
			System.out.println(e.getNom()+e.getListeJoueur().get(0).getPseudo());
		}
	}
}
