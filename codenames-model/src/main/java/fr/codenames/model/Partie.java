package fr.codenames.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "partie")
public class Partie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy = "partie")
	private List<CartesNomDeCode> liste25;

	@OneToMany(mappedBy = "partie")
	private List<Joueur> joueurspartie;

	@OneToMany(mappedBy = "partie")
	private List<Equipe> equipes;

	@OneToMany(mappedBy = "partie")
	private List<Cases> casesRestantes;

	@OneToMany(mappedBy = "partie")
	private List<Tour> tours;

	public boolean conditionDefaite(List<Cases> list) {
		boolean defaite = true;

		for (Cases c : list) {
			if (c.getCouleur().equalsIgnoreCase("noir")) {
				defaite = false;
			}

		}

		return defaite;
	}

	public boolean conditionVictoire(List<Cases> list) {
		boolean victoire = false;
		boolean testRouge = true;
		boolean testBleu = true;

		for (Cases c : list) {
			if (c.getCouleur().equalsIgnoreCase("rouge")) {
				testRouge = false;
			}
			if (c.getCouleur().equalsIgnoreCase("bleu")) {
				testBleu = false;
			}
		}

		if (testRouge == true || testBleu == true) {
			victoire = true;
		}

		return victoire;
	}

	public List<Cases> eneleverRep(List<Cases> list, String s) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getCartenomdecode().getNom().equalsIgnoreCase(s)) {
				list.remove(i);
			}
		}

		return list;

	}

	public boolean reussiteReponse(Equipe e, String s) {
		if (e.getNom().equalsIgnoreCase(s)) {
			System.out.println("Le mot est bien de la couleur " + s + ".");
			return true;
		} else {
			System.out.println("Le mot ne correspond pas � votre couleur. Le mot est " + s + ".");
			return false;
		}
	}

	public int quiCommence(List<Cases> list) {
		int a = 0;
		int b = 0;

		for (Cases c : list) {
			if (c.getCouleur().equalsIgnoreCase("rouge")) {
				a++;
			}
			if (c.getCouleur().equalsIgnoreCase("bleu")) {
				b++;
			}

		}
		if (a > b)
			return 1;
		else
			return 0;
	}

	public String couleurReponse(List<Cases> liste, String mot) {

		String couleur = null;
		for (Cases c : liste) {
			if (c.getCartenomdecode().getNom().equalsIgnoreCase(mot)) {
				couleur = c.getCouleur();
			}
		}
		return couleur;
	}

	public void affichageAgent(List<Cases> list) {
		int min = 0;
		int max = 5;
		for (int i = 0; i < 5; i++) {
			for (int j = min; j < max; j++) {
				System.out.print(list.get(j).getCartenomdecode().getNom() + "   ");
			}
			min = min + 5;
			max = max + 5;
			System.out.println();
		}
	}

	public void affichageMaitreEspion(List<Cases> list) {
		int min = 0;
		int max = 5;
		for (int i = 0; i < 5; i++) {
			for (int j = min; j < max; j++) {
				System.out.print(list.get(j).getCartenomdecode().getNom() + "   ");
				System.out.print(list.get(j).getCouleur() + "   ");
			}
			min = min + 5;
			max = max + 5;
			System.out.println();
		}

	}

	public List<CartesNomDeCode> choixMots(List<CartesNomDeCode> liste) {
		List<CartesNomDeCode> liste25 = new ArrayList<CartesNomDeCode>();
		Collections.shuffle(liste);

		for (int i = 0; i < 25; i++) {
			liste25.add(liste.get(i));
		}

		return liste25;

	}

	public List<Equipe> affecterEquipe(List<Joueur> joueurs) {

		List<Equipe> equipes = new ArrayList<Equipe>();

		Collections.shuffle(joueurs);

		Equipe rouge = new Equipe();
		Equipe bleu = new Equipe();

		rouge.setNom("rouge");
		bleu.setNom("bleu");

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
		for (int i = 0; i < joueurs.size() / 2; i++) {

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

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

	public List<Cases> getCasesRestantes() {
		return casesRestantes;
	}

	public void setCasesRestantes(List<Cases> casesRestantes) {
		this.casesRestantes = casesRestantes;
	}

	public void setJoueurspartie(List<Joueur> joueurspartie) {
		this.joueurspartie = joueurspartie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Tour> getTours() {
		return tours;
	}

	public void setTours(List<Tour> tours) {
		this.tours = tours;
	}

}
