package fr.codenames;

import java.util.ArrayList;
import java.util.List;

import fr.codenames.model.CartesCles;
import fr.codenames.model.CartesNomDeCode;
import fr.codenames.model.Cases;
import fr.codenames.model.Equipe;
import fr.codenames.model.Joueur;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CartesCles test = new CartesCles();
		Partie maPartie = new Partie();
		List<CartesNomDeCode> liste = new ArrayList<CartesNomDeCode>();
		List<Cases> cartescles = new ArrayList<Cases>();
		List<Joueur> joueurs = new ArrayList<Joueur>();
		List<Equipe> equipes = new ArrayList<Equipe>();
		
		liste = maPartie.choixMots();
		Joueur mika = new Joueur();
		Joueur elo = new Joueur();
		Joueur pl = new Joueur();
		Joueur jojo = new Joueur();
		Joueur tintin = new Joueur();
		Joueur prout = new Joueur();
		Joueur flute = new Joueur();
		
		joueurs.add(flute);
		joueurs.add(mika);
		joueurs.add(elo);
		joueurs.add(jojo);
		joueurs.add(tintin);
		joueurs.add(prout);
		joueurs.add(pl);
		
		
		equipes = maPartie.affecterEquipe(joueurs);
		
		System.out.println("composition des equipes :");
		for(Equipe e : equipes ) {
			System.out.println(e.getListeJoueur().size()+ " Joueur");
			System.out.println("equipe " + e.getNom() + " composé de " + e.getListeJoueur().get(0).getPseudo());
		}

	}

}
