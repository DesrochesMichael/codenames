package fr.codenames;

import java.util.ArrayList;
import java.util.List;

import fr.codenames.model.Equipe;
import fr.codenames.model.Joueur;

public class test {

	public static void main(String[] args) {
		Partie mapartie = new Partie();
		List<Equipe> equipes = new ArrayList<Equipe>();

		Joueur mika = new Joueur();
		Joueur pl = new Joueur();
		Joueur jerem = new Joueur();
		Joueur jojo = new Joueur();
		Joueur tib = new Joueur();

		mika.setPseudo("mika");
		pl.setPseudo("pl");
		jerem.setPseudo("jerem");
		jojo.setPseudo("jojo");
		tib.setPseudo("tib");

		
		mapartie.getJoueurspartie().add(pl);
		mapartie.getJoueurspartie().add(jerem);
		mapartie.getJoueurspartie().add(jojo);
		mapartie.getJoueurspartie().add(tib);
		mapartie.getJoueurspartie().add(mika);

		equipes=mapartie.affecterEquipe(mapartie.getJoueurspartie());

		System.out.println("composition des équipes :");
		System.out.println();
		for (Equipe e : equipes) {
			System.out.println(e.getNom());

			for (int i = 0; i < e.getListeJoueur().size(); i++) {
				System.out.println(e.getListeJoueur().get(i).getPseudo());
			}
			System.out.println();
		}
	}
}