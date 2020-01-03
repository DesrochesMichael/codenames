package fr.codenames;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

	public void run(String[] args) {
		Joueur j1 = new Joueur();
		Joueur j2 = new Joueur();
		CartesNomDeCode cartecode = new CartesNomDeCode();

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

		// repetition du menu (0 pour en sortir)
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
					mapartie.setListe25(mapartie.choixMots(idaocartes.findAll())); 
					listeCases = cartescles.attributionCases(mapartie.getListe25());
					repRestante = listeCases;

					mapartie.setEquippartie(mapartie.affecterEquipe(mapartie.getJoueurspartie())); 

					// montre les equipes
					System.out.println("composition des equipes :");
					for (Equipe e : mapartie.getEquippartie()) {
						System.out.println("equipe " + e.getNom());
						for (int i = 0; i < e.getListeJoueur().size(); i++) {
							System.out.println(e.getListeJoueur().get(i).getPseudo());
						}
						System.out.println();
					}
					// montre le maitre espion
					for (Equipe e : mapartie.getEquippartie()) {
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
					System.out.println("L'equipe " + mapartie.getEquippartie().get(index).getNom() + " commence");

					System.out.println("Voici les mots utilisez pour cette partie : ");
					System.out.println();
					mapartie.affichageAgent(listeCases);

					System.out.println();

					mapartie.affichageMaitreEspion(listeCases);

					System.out.println();

					// savoir qui commence ?
					index = mapartie.quiCommence(listeCases);
					System.out.println("L'equipe " + mapartie.getEquippartie().get(index).getNom() + " commence");

					// boucle condition de victoire/defaite pour le tour

					while (mapartie.conditionVictoire(repRestante) != true
							&& mapartie.conditionDefaite(repRestante) != true) {

						// boucle pour une equipe
						while (mapartie.conditionVictoire(repRestante) != true
								&& mapartie.conditionDefaite(repRestante) != true && nbrReponse > 0 && rep != false) {

							System.out.println("Tour de l'equipe " + mapartie.getEquippartie().get(index).getNom() + ".");
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
								rep = mapartie.reussiteReponse(mapartie.getEquippartie().get(index), couleur);
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

					// affichage de l'equipe victorieuse
					if (mapartie.conditionDefaite(repRestante) == true) {
						System.out.println("L'equipe " + mapartie.getEquippartie().get(index).getNom() + " a gagne");
						gagnant = index;
					}

					else if (mapartie.conditionVictoire(repRestante) == true) {
						if (index == 1) {
							index = 0;
						} else {
							index = 1;
						}
						System.out.println("L'equipe " + mapartie.getEquippartie().get(index).getNom() + " a gagne");
						gagnant = index;
					}

					// enregistrement des resultats des joueurs

					for (int i = 0; i < 2; i++) {
						for (Joueur j : mapartie.getEquippartie().get(i).getListeJoueur()) {
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
					String choice = sc.nextLine();

					if (choice.equalsIgnoreCase("oui")) {
						for (int i = 0; i < 2; i++) {
							for (Joueur j : mapartie.getEquippartie().get(i).getListeJoueur()) {
								mapartie.getJoueurspartie().add(j);
							}
						}
					}

					System.out.println("Joueur(s) present(s) pour le moment dans la prochaine partie :");
					for (Joueur j : mapartie.getJoueurspartie()) {
						System.out.println(j.getPseudo());
					}
				}

				break;
			case 2:
				do {
					joueur = menuJoueurs();
					switch (joueur) {

					case 1:// connexion

						System.out.println("nom du joueur ?");
						j1.setPseudo(sc.nextLine());

						// check si joueur dans partie
						for (int i = 0; i < mapartie.getJoueurspartie().size(); i++) {
							if (j1.getPseudo()
									.equalsIgnoreCase(mapartie.getJoueurspartie().get(i).getPseudo()) == true) {
								System.out.println("Le joueur est deja connecte a la partie");
								booleen = false;
							}
						}

						if (booleen == true) {

							System.out.println("mot de passe du joueur ?");
							j1.setMdp(sc.nextLine());
							j1 = idaojoueur.connect(j1);

							if (j1 != null) {
								System.out.println(
										"Le joueur " + idaojoueur.connect(j1).getPseudo() + " rejoint la partie");

								mapartie.getJoueurspartie().add(idaojoueur.connect(j1));
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
						j1 = new Joueur();
						break;

					case 2:// creer joueur
						List<Joueur> toutJoueur = new ArrayList<Joueur>();

						System.out.println("nom du nouveau joueur ?");
						j1.setPseudo(sc.nextLine());
						toutJoueur = idaojoueur.findAll();
						for (int i = 0; i < toutJoueur.size(); i++) {
							if (j1.getPseudo().equalsIgnoreCase(toutJoueur.get(i).getPseudo()) == true) {
								System.out.println("Le pseudo saisi existe deja");
								booleen = false;
							}
						}

						if (booleen == true) {

							System.out.println("mot de passe du nouveau joueur ?");
							j1.setMdp(sc.nextLine());
							idaojoueur.save(j1);

							if (idaojoueur.save(j1) != null) {

								mapartie.getJoueurspartie().add(j1);
								System.out.println("Joueur(s) present(s) pour le moment dans la prochaine partie :");
								for (Joueur j : mapartie.getJoueurspartie()) {
									System.out.println(j.getPseudo());
								}
							}
						}
						booleen = true;
						j1 = new Joueur();
						break;

					case 3:
						do {
							modif = menuModif();
							switch (modif) {
							case 1:
//								 modifier pseudo du joueur 

								System.out.println("Nom du joueur a modifier ?");

								String nom = sc.nextLine();
								j1 = idaojoueur.findByPseudo(nom);

								if (j1 == null) {
									System.out.println("Ce joueur n existe pas");
								} else {

									System.out.println("Nouveau nom du joueur ?");
									j2 = j1;
									j2.setPseudo(sc.nextLine());
									idaojoueur.save(j2);
									System.out.println("Le joueur " + nom + " est devenu " + j2.getPseudo() + ".");
								}
								j1 = new Joueur();
								j2 = new Joueur();
								break;

							case 2:

								// modifier mdp du joueur
								System.out.println("Nom du joueur ?");
								j1 = idaojoueur.findByPseudo(sc.nextLine());
								if (j1 == null) {
									System.out.println("Ce joueur n existe pas");
								} else {

									System.out.println("Ancien mot de passe ?");

									if (j1.getMdp().equalsIgnoreCase(sc.nextLine()) == false) {
										System.out.println("Le mdp est incorrect");
									} else {

										System.out.println("Nouveau mot de passe ?");
										j1.setMdp(sc.nextLine());

										System.out.println("Validez le mdp : ");

										String mdpValid = sc.nextLine();
										if (j1.getMdp().equalsIgnoreCase(mdpValid)) {
											idaojoueur.save(j1);
											System.out.println("Le mdp a ete modifie");
										} else {
											System.out.println(
													"Les deux mdp sont diff�rents. Le changement n'a pas �t� effectu�");
										}
									}
								}
								j1 = new Joueur();
								break;

							case 0:
								menu();
							}
						} while (modif != 0);

						break;
					case 4:// supprimer joueur de la partie

						System.out.println("Nom du joueur a supprimer de la partie ?");
						String nom = sc.nextLine();

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
						 ;
						for (CartesNomDeCode c : idaocartes.findAll()) {
							System.out.println(c.getNom());
						}
						break;

					case 2:// creer carte
						
						CartesNomDeCode cartecreer = new CartesNomDeCode();

						System.out.println("Nom de la nouvelle carte nom de code ? ");
						cartecreer.setNom(sc.nextLine());

						for (CartesNomDeCode c : idaocartes.findAll()) {
							if (c.getNom().equalsIgnoreCase(cartecreer.getNom())) {
								System.out.println("Cette carte existe deja");
								booleen = false;
							}
						}
						if (booleen == true) {
							idaocartes.save(cartecreer);
							System.out.println("Carte cr��");
						}
						break;

					case 3:
						// Modifier carte

						System.out.println("Nom de la carte nom de code a modifier ? ");
						String nom = sc.nextLine();
						cartecode = idaocartes.findByNom(nom);
						if (cartecode == null) {
							System.out.println("Cette carte n'existe pas.");
						} else {
							System.out.println("Nouveau mot ? ");
							cartecode.setNom(sc.nextLine());

							if (idaocartes.findByNom(cartecode.getNom()) != null) {
								System.out.println("Ce nom de carte existe deja. Modification non applique.");
							} else {
								idaocartes.save(cartecode);
								System.out.println(
										"La carte " + nom + " est devenu la carte " + cartecode.getNom() + ".");
							}
						}
						break;

					case 4:
						// supprimer carte

						System.out.println("Nom de la carte nom de code a supprimer ? ");
						String cartesup = sc.nextLine();
						cartecode = idaocartes.findByNom(cartesup);

						if (cartecode == null) {
							System.out.println("Cette carte n'exsite pas.");
						} else {
							idaocartes.delete(cartecode);
							System.out.println("La carte a ete supprime.");
						}
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
						for (Joueur j : idaojoueur.findAll()) {
							System.out.println(j.getPseudo());
						}
						break;

					case 2:// statistique joueur
						System.out.println("Saisir le nom du joueur :");
						String nom = sc.nextLine();
						j1 = idaojoueur.findByPseudo(nom);
						if (j1 == null) {
							System.out.println("Ce joueur n'existe pas.");
						} else {
							System.out.println("Le joueur " + j1.getPseudo() + " a joue " + j1.getNbrPartie()
									+ " parties et en a gagne " + j1.getNbrVictoire() + ". Il a joue MaitreEspion "
									+ j1.getNbrMaitreEspion() + " fois.");
						}
						j1 = new Joueur();
						break;

					case 3:// suppression de la bdd d'un joueur
						System.out.println("Nom du joueur a supprimer ?");
						String supsup = sc.nextLine();
						j1 = idaojoueur.findByPseudo(supsup);
						if (j1 == null) {
							System.out.println("Ce joueur n'existe pas");
						} else {
							System.out.println("Mdp du joueur a supprimer ?");

							if (j1.getMdp().equalsIgnoreCase(sc.nextLine()) == false) {
								System.out.println("Mdp incorrecte. Suppression abandonn�.");
							} else {
								idaojoueur.delete(j1);
								System.out.println("Joueur supprime de la bdd.");
							}

						}

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
		Scanner scan = new Scanner(System.in);
		try {
			return scan.nextInt();
		} catch (Exception e) {
			return 0;
		}

	}
}
