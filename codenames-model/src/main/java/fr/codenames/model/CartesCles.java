package fr.codenames.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "cartes_cles")
public class CartesCles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLES_ID")
	private int id;
	
	
	@OneToOne
	@JoinColumn(name="cartescles")
	private Cases cles_cases;
	
	@OneToMany(mappedBy = "cartecles")
	private List<Joueur> listeJoueur;
	
	protected static List<Cases> cartescles = new ArrayList<Cases>(25);

	
	
	public int bleuourouge() {
		int max = 1;
		int min = 0;
		int range = max - min + 1;
		int rand = (int) (Math.random() * range) + min;
		return rand;
	}

	public List attributionCases(List<CartesNomDeCode> cartenomdecode) {
		// 0 == bleu ==> commence
		// 1 == rouge ==> commence

		for (int i = 0; i < 8; i++) {
			Cases cases = new Cases();
			cases.setCouleur("rouge");
			cases.setCartenomdecode(cartenomdecode.remove(0));
			cartescles.add(cases);
		}

		for (int i = 0; i < 8; i++) {
			Cases cases = new Cases();
			cases.setCouleur("bleu");
			cases.setCartenomdecode(cartenomdecode.remove(0));
			cartescles.add(cases);
		}

		for (int i = 0; i < 7; i++) {
			Cases cases = new Cases();
			cases.setCouleur("gris");
			cases.setCartenomdecode(cartenomdecode.remove(0));
			cartescles.add(cases);
		}

		Cases cases = new Cases();
		cases.setCouleur("noir");
		cases.setCartenomdecode(cartenomdecode.remove(0));
		cartescles.add(cases);

		int a = bleuourouge();
		if (a == 0) {
			Cases cases1 = new Cases();
			cases1.setCouleur("bleu");
			cases1.setCartenomdecode(cartenomdecode.remove(0));
			cartescles.add(cases1);
		}

		else if (a == 1) {
			Cases cases1 = new Cases();
			cases1.setCouleur("rouge");
			cases1.setCartenomdecode(cartenomdecode.remove(0));
			cartescles.add(cases1);
		}

		Collections.shuffle(cartescles);

		return cartescles;

	}

	public static List<Cases> getCartescles() {
		return cartescles;
	}

	public static void setCartescles(List<Cases> cartescles) {
		CartesCles.cartescles = cartescles;
	}

// booelan random 8 ou 9 mpour commencer

	// generation random de 8 ou 9 positions sur la grille pour chaque equipe =>
	// random 8 bleu, random 8 rouge et 1 noir pusi gris

}
