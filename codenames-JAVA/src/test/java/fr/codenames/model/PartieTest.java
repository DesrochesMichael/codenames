package fr.codenames.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PartieTest {

	@Test
	public void testConditionDefaite() {
		List<Cases> cases = new ArrayList<Cases>();
		Cases c = new Cases();
		Partie mapartie = new Partie();
		c.setCouleur("nimporte");
		cases.add(c);
		cases.add(c);
		cases.add(c);
		assertTrue(mapartie.conditionDefaite(cases));
		cases.get(0).setCouleur("noir");
		assertFalse(mapartie.conditionDefaite(cases));

	}

	@Test
	public void testConditionVictoire() {
		List<Cases> cases = new ArrayList<Cases>();
		Cases c1 = new Cases();
		Cases c2 = new Cases();
		Partie mapartie = new Partie();
		c1.setCouleur("test");
		cases.add(c1);

		assertTrue(mapartie.conditionVictoire(cases));

		c1.setCouleur("rouge");
		cases.add(c1);
		assertTrue(mapartie.conditionVictoire(cases));

		c2.setCouleur("bleu");
		cases.add(c2);
		assertFalse(mapartie.conditionVictoire(cases));

	}

	@Test
	public void eneleverRepTest() {
		List<Cases> cases = new ArrayList<Cases>();
		Cases c1 = new Cases();
		Cases c2 = new Cases();
		Partie mapartie = new Partie();
		CartesNomDeCode carte = new CartesNomDeCode();
		CartesNomDeCode carte2 = new CartesNomDeCode();
		String s1 = "test";

		carte.setNom(s1);
		carte2.setNom("blabla");
		c1.setCartenomdecode(carte);
		c2.setCartenomdecode(carte2);
		cases.add(c1);
		cases.add(c2);

		mapartie.eneleverRep(cases, "rien");

		boolean b = false;

		for (int i = 0; i < cases.size(); i++) {
			if (cases.get(i).getCartenomdecode().getNom().equalsIgnoreCase(s1)) {
				b = true;
			}
		}

		assertTrue(b);

		mapartie.eneleverRep(cases, s1);

		b = false;

		for (int i = 0; i < cases.size(); i++) {
			if (cases.get(i).getCartenomdecode().getNom().equalsIgnoreCase(s1)) {
				b = true;
			}
		}

		assertFalse(b);
	}

	@Test
	public void reussiteReponseTest() {
		Equipe rouge = new Equipe("rouge");
		Partie mapartie = new Partie();

		List<Equipe> equipes = new ArrayList<Equipe>();

		assertFalse(mapartie.reussiteReponse(rouge, "bleu"));
		assertTrue(mapartie.reussiteReponse(rouge, "rouge"));
	}

	@Test
	public void quiCommenceTest() {
		List<Cases> cases = new ArrayList<Cases>();
		Cases c1 = new Cases();
		Cases c2 = new Cases();
		Partie mapartie = new Partie();
		CartesNomDeCode carte1 = new CartesNomDeCode();
		CartesNomDeCode carte2 = new CartesNomDeCode();

		c1.setCouleur("rouge");
		c2.setCouleur("gris");
		cases.add(c1);
		cases.add(c2);

		int test = mapartie.quiCommence(cases);
		assertEquals(test, 1);

		c2.setCouleur("bleu");
		c1.setCouleur("rouge");
		cases.add(c2);
		cases.add(c1);
		test = mapartie.quiCommence(cases);
		assertEquals(test, 0);
	}

	@Test
	public void couleurReponseTest() {
		List<Cases> cases = new ArrayList<Cases>();
		String s = "test";
		Cases c1 = new Cases();
		Cases c2 = new Cases();
		Partie mapartie = new Partie();
		CartesNomDeCode carte1 = new CartesNomDeCode();
		CartesNomDeCode carte2 = new CartesNomDeCode();

		carte1.setNom(s);
		c1.setCartenomdecode(carte1);
		c1.setCouleur(s);
		cases.add(c1);

		assertNull(mapartie.couleurReponse(cases, "bla"));
		assertNotNull(mapartie.couleurReponse(cases, s));
		assertNotEquals(mapartie.couleurReponse(cases, "bla"), s);
		assertEquals(mapartie.couleurReponse(cases, s), s);
	}

	@Test
	public void affecterEquipeTest() {
		List<Joueur> joueurs = new ArrayList<Joueur>();
		List<Equipe> equipes = new ArrayList<Equipe>();
		Joueur j1 = new Joueur();
		Joueur j2 = new Joueur();
		Joueur j3 = new Joueur();
		Joueur j4 = new Joueur();
		Joueur j5 = new Joueur();

		joueurs.add(j1);
		joueurs.add(j2);
		joueurs.add(j3);
		joueurs.add(j4);

		Partie mapartie = new Partie();

		equipes = mapartie.affecterEquipe(joueurs);

		assertEquals(equipes.get(0).getListeJoueur().size(), equipes.get(1).getListeJoueur().size());

		joueurs.add(j1);
		joueurs.add(j2);
		joueurs.add(j3);
		joueurs.add(j4);
		joueurs.add(j5);
		equipes = mapartie.affecterEquipe(joueurs);
		assertNotEquals(equipes.get(0).getListeJoueur().size(), equipes.get(1).getListeJoueur().size());

		boolean test = false;

		for (Joueur j : equipes.get(0).getListeJoueur()) {
			if (j.getRole().equalsIgnoreCase("MaitreEspion")) {
				test = true;
			}
		}
		assertTrue(test);
		test = false;
		
		for (Joueur j : equipes.get(1).getListeJoueur()) {
			if (j.getRole().equalsIgnoreCase("MaitreEspion")) {
				test = true;
			}

		}
		assertTrue(test);
	}

}
