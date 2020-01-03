package fr.codenames.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.codenames.config.Config;
import fr.codenames.model.Joueur;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class })
public class IDAOJoueurTest {

	@Autowired(required = false)
	private IDAOJoueur daojoueur;

	@Transactional
	@Rollback
	@Test
	public void testConnect() {
		try {
			Joueur joueur2 = new Joueur();
			joueur2.setId(18);
			joueur2.setMdp("25");
			joueur2.setPseudo("TEST");
			daojoueur.save(joueur2);
			Joueur joueurconnect = daojoueur.connect(joueur2);
			assertNotNull(joueurconnect);
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Transactional
	@Rollback(true)
	@Test
	public void deleteByPseudoTest() {
		List<Joueur> listeJoueur = new ArrayList<Joueur>();
		boolean booleen = false;
		try {

			daojoueur.deleteByPseudo("mika");
			listeJoueur = daojoueur.findAll();
			for (Joueur j : listeJoueur) {
				if (j.getPseudo().equalsIgnoreCase("mika")) {
					booleen = true;
				}
			}
			assertFalse(booleen);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

//	@Transactional
//	@Rollback
//	@Test
//	public void save() {
//
//		Joueur p = new Joueur();
//		try {
//			p.setPseudo("Flute");
//
//			assertEquals(0, p.getId()); // Vérifie
//
//			daojoueur.save(p);
//
//			assertNotEquals(0, p.getId());
//
//			assertTrue(daojoueur.findById(p.getId()).isPresent());
//		} catch (Exception e) {
//			fail(e.getMessage());
//
//		}
//
//	}
//
//	
//	
//	
//	@Test
//	public void testFindById() {
//		try {
//			Optional<Joueur> optionalProd = daojoueur.findById(4);
//			assertNotNull(optionalProd);// Savoir si optionalProd est bien un produit ?
//			assertTrue(optionalProd.isPresent()); // Est-ce que le produit est présent ?
//			assertEquals(4, optionalProd.get().getId()); // Est-ce que l'ID du produit correspond au paramètre
//		} catch (Exception e) {
//			fail(e.getMessage());
//
//		}
//	}
}
