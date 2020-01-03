package fr.codenames;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import fr.codenames.dao.IDAOCartesNomDeCode;
import fr.codenames.dao.IDAOJoueur;
import fr.codenames.model.CartesCles;
import fr.codenames.model.CartesNomDeCode;
import fr.codenames.model.Cases;
import fr.codenames.model.Equipe;
import fr.codenames.model.Joueur;
import fr.codenames.model.Partie;
import fr.codenames.model.Tour;

@Configuration
@ComponentScan("fr.codenames")
public class Application {
	
	@Autowired
	private IDAOJoueur idaojoueur;

	@Autowired
	private IDAOCartesNomDeCode idaocartes;
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext heySpring = new AnnotationConfigApplicationContext(Application.class);
		Application app = heySpring.getBean("application", Application.class);

		app.run(args);
		heySpring.close();
		
	}
	
	
	
	public  void run(String[] args) {
		
		int carte;
		int joueur;
		int menu;
		int histo;
		int modif;

		boolean booleen = true;
		boolean rep = true;

		
		Partie mapartie = new Partie();
		Tour tour = new Tour();
		CartesCles cartescles = new CartesCles();
		String couleur = null;
		String reponse = null;
		int gagnant = 0;
		int nbrReponse = 1;
		int index = 0;

		List<Equipe> equipes = new ArrayList<Equipe>();
		List<Joueur> listeJoueur = new ArrayList<Joueur>();
		List<CartesNomDeCode> listeCartes = new ArrayList<CartesNomDeCode>();
		List<Cases> listeCases = new ArrayList<Cases>();
		List<Cases> repRestante = new ArrayList<Cases>();

		// j'aime utiliser tout un bordel pour test et alors ?

//		Joueur pl = new Joueur();
//		Joueur jerem = new Joueur();
//		Joueur jojo = new Joueur();
//		Joueur tib = new Joueur();
//		pl.setPseudo("pl");
//		jerem.setPseudo("jerem");
//		jojo.setPseudo("jojo");
//		tib.setPseudo("tib");
//		mapartie.getJoueurspartie().add(pl);
//		mapartie.getJoueurspartie().add(jerem);
//		mapartie.getJoueurspartie().add(jojo);
//		mapartie.getJoueurspartie().add(tib);

		// R�P�TITION DU MENU (0 pour en sortir)
		do {

			menu = menu();
			switch (menu) {
			case 1:
				// NOUVELLE PARTIE
				if (mapartie.getJoueurspartie().size() < 4) {
					System.out.println(
							"Le nombre de joueur est insuffisant. Merci de passser par le menu joueur afin d'avoir au moins 4 joueurs.");
					menu();
				} else {
					listeCartes = mapartie.choixMots(idaocartes.findAll());
					listeCases = cartescles.attributionCases(listeCartes);
					repRestante = listeCases;

					equipes = mapartie.affecterEquipe(mapartie.getJoueurspartie());

					// montre les equipes
					System.out.println("composition des �quipes :");
					for (Equipe e : equipes) {
						System.out.println("equipe " + e.getNom());
						for (int i = 0; i < e.getListeJoueur().size(); i++) {
							System.out.println(e.getListeJoueur().get(i).getPseudo());
						}
						System.out.println();
					}
					// montre le maitre espion
					for (Equipe e : equipes) {
						for (int i = 0; i < e.getListeJoueur().size(); i++) {
							if (e.getListeJoueur().get(i).getRole() == "MaitreEspion") {
								System.out.println(e.getListeJoueur().get(i).getPseudo()
										+ " est maitre espion de l'equipe " + e.getNom());
							}
						}
					}
					System.out.println();

					System.out.println("Voici les mots utilisez pour cette partie : ");
					mapartie.affichageAgent(listeCases);
					System.out.println();
					mapartie.affichageMaitreEspion(listeCases);
					System.out.println();
					// savoir qui commence
					index = mapartie.quiCommence(listeCases);
					System.out.println("L'equipe " + equipes.get(index).getNom() + " commence");

				}
				System.out.println("Voici les mots utilisez pour cette partie : ");
				System.out.println();
				mapartie.affichageAgent(listeCases);

				System.out.println();

				mapartie.affichageMaitreEspion(listeCases);

				System.out.println();

				// savoir qui commence ?
				index = mapartie.quiCommence(listeCases);
				System.out.println("L'equipe " + equipes.get(index).getNom() + " commence");

				// boucle condition de victoire/defaite pour le tour

				while (mapartie.conditionVictoire(repRestante) != true
						&& mapartie.conditionDefaite(repRestante) != true) {

					// boucle pour une equipe
					while (mapartie.conditionVictoire(repRestante) != true
							&& mapartie.conditionDefaite(repRestante) != true && nbrReponse > 0 && rep != false) {

						System.out.println("Tour de l'équipe " + equipes.get(index).getNom() + ".");
						System.out.println(tour.motMaitreEspion() + " est le mot du maitre espion.");
						nbrReponse = tour.nbrMotMaitreEspion();
						System.out.println("Celui-ci est relié à " + nbrReponse + " mots");

						// boucle pour chaque reponse possible vis a vis du mot donné
						while (nbrReponse > 0 && rep == true) {
							System.out.println(nbrReponse + " reponse(s) restante(s)");
							while (reponse == null) {
								reponse = tour.reponseAgent(repRestante);
							}
							couleur = mapartie.couleurReponse(repRestante, reponse);
							rep = mapartie.reussiteReponse(equipes.get(index), couleur);
							mapartie.eneleverRep(repRestante, reponse);
							nbrReponse--;
							reponse = null;
						}
					}
					// changement index equipe
					if (index == 1) {
						index = 0;
					} else {
						index = 1;
					}
					// raz des conditions
					rep = true;
					reponse = null;
					nbrReponse = 1;
				}

				// affichage de l'équipe victorieuse
				if (mapartie.conditionDefaite(repRestante) == true) {
					System.out.println("L'equipe " + equipes.get(index).getNom() + " a gagne");
					gagnant = index;
				}

				else if (mapartie.conditionVictoire(repRestante) == true) {
					if (index == 1) {
						index = 0;
					} else {
						index = 1;
					}
					System.out.println("L'equipe " + equipes.get(index).getNom() + " a gagne");
					gagnant = index;
				}

				// enregistrement des résultats des joueurs

				for (int i = 0; i < 2; i++) {
					for (Joueur j : equipes.get(i).getListeJoueur()) {
						int a = j.getNbrPartie();
						a++;
						j.setNbrPartie(a);

						if (j.getRole().equalsIgnoreCase("MaitreEspion")) {
							int b = j.getNbrMaitreEspion();
							b++;
							j.setNbrMaitreEspion(b);
							j.setRole("Agent");
						}

						if (i == gagnant) {
							int c = j.getNbrVictoire();
							c++;
							j.setNbrVictoire(c);
						}

						idaojoueur.save(j);

					}

				}
				// reset et choix de conserver les joueurs
				mapartie = new Partie();
				System.out.println("Souhaitez vous conserver les joueurs actuels ? oui/non");
				Scanner choix = new Scanner(System.in);
				String choice = choix.nextLine();

				if (choice.equalsIgnoreCase("oui")) {
					for (int i = 0; i < 2; i++) {
						for (Joueur j : equipes.get(i).getListeJoueur()) {
							mapartie.getJoueurspartie().add(j);
						}
					}
				}

				System.out.println("Joueur(s) present(s) pour le moment dans la prochaine partie :");
				for (Joueur j : mapartie.getJoueurspartie()) {
					System.out.println(j.getPseudo());
				}

				break;
			case 2:
				do {
					joueur = menuJoueurs();
					switch (joueur) {

					case 1:// connexion
						Joueur joueurconnect = new Joueur();
						Scanner scanPseudo = new Scanner(System.in);
						System.out.println("nom du joueur ?");
						joueurconnect.setPseudo(scanPseudo.nextLine());

						// check si joueur dans partie
						for (int i = 0; i < mapartie.getJoueurspartie().size(); i++) {
							if (joueurconnect.getPseudo()
									.equalsIgnoreCase(mapartie.getJoueurspartie().get(i).getPseudo()) == true) {
								System.out.println("Le joueur est deja connecte a la partie");
								booleen = false;
							}
						}

						if (booleen == true) {

							Scanner scanMDP = new Scanner(System.in);
							System.out.println("mot de passe du joueur ?");
							joueurconnect.setMdp(scanMDP.nextLine());
							joueurconnect = idaojoueur.connect(joueurconnect);

							if (joueurconnect != null) {
								System.out.println("Le joueur " + idaojoueur.connect(joueurconnect).getPseudo()
										+ " rejoint la partie");

								mapartie.getJoueurspartie().add(idaojoueur.connect(joueurconnect));
							}

							else {
								System.out.println("La combinaison pseudo/mdp est incorrecte");
							}

							System.out.println("Joueur(s) present(s) pour le moment dans la prochaine partie :");
							for (Joueur j : mapartie.getJoueurspartie()) {
								System.out.println(j.getPseudo());
							}

						}
						booleen = true;
						break;

					case 2:// creer joueur
						List<Joueur> toutJoueur = new ArrayList<Joueur>();
						Joueur joueur1 = new Joueur();
						Scanner scanPseudo1 = new Scanner(System.in);
						System.out.println("nom du nouveau joueur ?");
						joueur1.setPseudo(scanPseudo1.nextLine());
						toutJoueur = idaojoueur.findAll();
						for (int i = 0; i < toutJoueur.size(); i++) {
							if (joueur1.getPseudo().equalsIgnoreCase(toutJoueur.get(i).getPseudo()) == true) {
								System.out.println("Le pseudo saisi existe deja");
								booleen = false;
							}
						}

						if (booleen == true) {
							Scanner scanMDP1 = new Scanner(System.in);
							System.out.println("mot de passe du nouveau joueur ?");
							joueur1.setMdp(scanPseudo1.nextLine());
							idaojoueur.save(joueur1);

							if (idaojoueur.save(joueur1) != null) {

								mapartie.getJoueurspartie().add(joueur1);
								System.out.println("Joueur(s) present(s) pour le moment dans la prochaine partie :");
								for (Joueur j : mapartie.getJoueurspartie()) {
									System.out.println(j.getPseudo());
								}
							}
						}
						booleen = true;
						break;

					case 3:
						do {
							modif = menuModif();
							switch (modif) {
							case 1:
//								modifier pseudo du joueur 
								Joueur joueur2 = new Joueur();
								System.out.println("Nom du joueur � modifier ?");
								Scanner scanPseudo2 = new Scanner(System.in);
								String nom = scanPseudo2.nextLine();
								joueur2 = idaojoueur.findByPseudo(nom);

								Joueur joueur3 = new Joueur();
								Scanner scanPseudo3 = new Scanner(System.in);
								System.out.println("Nouveau nom du joueur ?");
								joueur3 = joueur2;
								joueur3.setPseudo(scanPseudo3.nextLine());
								idaojoueur.save(joueur3);

								break;

							case 2:

								// modifier mdp du joueur
								Joueur joueur4 = new Joueur();
								System.out.println("Nom du joueur ?");
								Scanner scanNom = new Scanner(System.in);
								joueur4 = idaojoueur.findByPseudo(scanNom.nextLine());

								System.out.println("Nouveau mot de passe ?");
								Scanner scanMdp = new Scanner(System.in);
								joueur4.setMdp(scanMdp.nextLine());

								System.out.println("Validez le mdp : ");
								Scanner scanValid = new Scanner(System.in);
								String mdpValid = scanValid.nextLine();
								if (joueur4.getMdp().equalsIgnoreCase(mdpValid)) {
									idaojoueur.save(joueur4);
								} else {
									System.out.println("ERREUR");
								}

								break;

							case 0:
								menu();
							}
						} while (modif != 0);

						break;
					case 4:// supprimer joueur de la partie
						Scanner scanPseudo21 = new Scanner(System.in);
						System.out.println("Nom du joueur a supprimer de la partie ?");
						String nom = scanPseudo21.nextLine();

						for (int i = 0; i < mapartie.getJoueurspartie().size(); i++) {

							if (mapartie.getJoueurspartie().get(i).getPseudo().equalsIgnoreCase(nom)) {
								mapartie.getJoueurspartie().remove(i);

								System.out.println("Le joueur a ete supprime");
								booleen = false;
							}
						}
						if (booleen == true) {
							System.out.println("Le joueur n'est pas dans la partie");
						}

						System.out.println("Joueur(s) present(s) pour le moment dans la prochaine partie :");
						for (Joueur j : mapartie.getJoueurspartie()) {
							System.out.println(j.getPseudo());
						}

						booleen = true;
						break;

					case 0:
						menu();
					}

				} while (joueur != 0);

				break;
			case 3:
				do {
					carte = menuCartes();
					switch (carte) {

					case 1:// find all
						listeCartes = idaocartes.findAll();
						for (CartesNomDeCode c : listeCartes) {
							System.out.println(c.getNom());
						}
						break;

					case 2:// creer carte
						listeCartes = idaocartes.findAll();
						CartesNomDeCode cartecreer = new CartesNomDeCode();
						Scanner scanCreer = new Scanner(System.in);
						System.out.println("Nom de la nouvelle carte nom de code ? ");
						cartecreer.setNom(scanCreer.nextLine());

						for (CartesNomDeCode c : listeCartes) {
							if (c.getNom().equalsIgnoreCase(cartecreer.getNom())) {
								System.out.println("Cette carte existe deja");
								booleen = false;
							}
						}
						if (booleen == true) {
							idaocartes.save(cartecreer);
						}
						break;

					case 3:
						// Modifier carte
						CartesNomDeCode cartemod = new CartesNomDeCode();
						Scanner scanModifier = new Scanner(System.in);
						System.out.println("Nom de la carte nom de code a modifier ? ");
						String nom = scanModifier.nextLine();
						cartemod = idaocartes.findByNom(nom);

						System.out.println("Nouveau mot ? ");
						Scanner scanNew = new Scanner(System.in);
						cartemod.setNom(scanNew.nextLine());
						idaocartes.save(cartemod);

						break;

					case 4:
						// supprimer carte
						CartesNomDeCode cartesup = new CartesNomDeCode();
						Scanner scanSupprimer = new Scanner(System.in);
						System.out.println("Nom de la carte nom de code a supprimer ? ");
						cartesup.setNom(scanSupprimer.nextLine());
						idaocartes.delete(cartesup);
						break;

					case 0:
						menu();
					}

				} while (carte != 0);

				break;
			case 4:

				do {
					histo = menuHistorique();
					switch (histo) {

					case 1:// find all

						listeJoueur = idaojoueur.findAll();
						for (Joueur j : listeJoueur) {
							System.out.println(j.getPseudo());
						}
						break;

					case 2:// statistique joueur
						Joueur joueur1 = new Joueur();
						System.out.println("Saisir le nom du joueur :");
						Scanner scannom = new Scanner(System.in);
						String nom = scannom.nextLine();
						joueur1 = idaojoueur.findByPseudo(nom);
						System.out.println("Le joueur " + joueur1.getPseudo() + " a jou� " + joueur1.getNbrPartie()
								+ " parties et en a gagn� " + joueur1.getNbrVictoire() + ". Il a jou� MaitreEspion "
								+ joueur1.getNbrMaitreEspion() + " fois.");
						break;

					case 3:// suppression de la bdd d'un joueur
						Joueur joueursup = new Joueur();
						Scanner scansup = new Scanner(System.in);
						System.out.println("Nom du joueur a supprimer ?");
						joueursup.setPseudo(scansup.nextLine());
						idaojoueur.delete(joueursup);
					case 0:
						menu();
					}

				} while (histo != 0);

				break;
			case 0:
				System.out.println("A plus tard !  \n\r   ");
				System.out.println(" |\\   /|\r\n" + "  \\|_|/\r\n" + "  /. .\\\r\n" + " =\\_Y_/=\r\n" + "  {>o<}");
				break;
			}
		} while (menu != 0);

		
	}

	public static int menu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("1- Nouvelle partie");
		System.out.println("2- Joueurs");
		System.out.println("3- Cartes");
		System.out.println("4- Historique");
		System.out.println("0- Quitter ");
		System.out.println("----------------------");
		System.out.println("Votre choix : ");
		return lireEntier();
	}

	public static int menuJoueurs() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("1- Se connecter");
		System.out.println("2- Creer un joueur");
		System.out.println("3- Modifier un joueur");
		System.out.println("4- Supprimer joueur de la partie");
		System.out.println("0- Retour ");
		System.out.println("----------------------");
		System.out.println("Votre choix : ");
		return lireEntier();
	}

	public static int menuCartes() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("1- Liste des Cartes");
		System.out.println("2- Creer Carte");
		System.out.println("3- Modifier Carte");
		System.out.println("4- Supprimer Carte");
		System.out.println("0- Retour ");
		System.out.println("----------------------");
		System.out.println("Votre choix : ");
		return lireEntier();
	}

	public static int menuHistorique() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("1- Liste des joueurs");
		System.out.println("2- Statistique d'un joueur");
		System.out.println("3- Suppression d'un joueur");
		System.out.println("0- Retour ");
		System.out.println("----------------------");
		System.out.println("Votre choix : ");
		return lireEntier();
	}

	public static int menuModif() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("1- Modifier pseudo");
		System.out.println("2- Modifier mot de passe");
		System.out.println("0- Retour ");
		System.out.println("----------------------");
		System.out.println("Votre choix : ");
		return lireEntier();
	}

	static int lireEntier() {
		Scanner myScanner = new Scanner(System.in);
		try {
			return myScanner.nextInt();
		} catch (Exception e) {
			return 0;
		}

	}
}
