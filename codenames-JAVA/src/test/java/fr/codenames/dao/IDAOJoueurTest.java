package fr.codenames.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
	public void testConnectBon() {
		try {
			Joueur j = new Joueur();
			j = daojoueur.findByPseudo("mika");
			boolean b = true;
			assertNotNull(daojoueur.connect(j));
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Transactional
	@Rollback
	@Test
	public void testConnectPasBon() {
		try {
			Joueur j = new Joueur();
			boolean b = true;
			if (daojoueur.findByPseudo("toto") == null) {
				b = false;
			}
			assertFalse(b);

			j.setPseudo("toto");
			j.setMdp("test");
			assertNull(daojoueur.connect(j));

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

}
