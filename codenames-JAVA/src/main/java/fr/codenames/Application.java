package fr.codenames;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import codenames.dao.hibernate.DAOCarteNomDeCodeHibernate;
import codenames.dao.hibernate.DAOJoueurHibernate;
import fr.codenames.model.CartesCles;
import fr.codenames.model.CartesNomDeCode;
import fr.codenames.model.Cases;
import fr.codenames.model.Equipe;
import fr.codenames.model.Joueur;
import fr.codenames.model.Tour;

public class Application {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		int carte;
		int joueur;
		int menu;
		int histo;

		boolean booleen = true;

		DAOJoueurHibernate joueurs = new DAOJoueurHibernate();
		DAOCarteNomDeCodeHibernate cartes = new DAOCarteNomDeCodeHibernate();
		Partie mapartie = new Partie();
		Tour tour = new Tour();
		CartesCles cartescles = new CartesCles();

		List<Equipe> equipes = new ArrayList<Equipe>();
		List<Joueur> listeJoueur = new ArrayList<Joueur>();
		List<CartesNomDeCode> listeCartes = new ArrayList<CartesNomDeCode>();
		List<Cases> listeCases = new ArrayList<Cases>();

		// RÉPÉTITION DU MENU (0 pour en sortir)
		do {

			menu = menu();
			switch (menu) {
			case 1:
				// NOUVELLE PARTIE

				listeCartes = mapartie.choixMots();

				listeCases = cartescles.attributionCases(listeCartes);

				mapartie.affichageAgent(listeCases);

				equipes = mapartie.affecterEquipe(mapartie.getJoueurspartie());

				System.out.println("composition des équipes :");
				// montre les equipes
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
							System.out.println(e.getListeJoueur().get(i).getPseudo() + " est maitre espion de l'equipe "
									+ e.getNom());
						}

					}

				}
				System.out.println("Voici les mots utilisez pour cette partie : ");
				mapartie.affichageAgent(listeCases);
				tour.motMaitreEspion();
				tour.nbrMotMaitreEspion();

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

						for (int i = 0; i < mapartie.getJoueurspartie().size(); i++) {
							if (joueurconnect.getPseudo()
									.equalsIgnoreCase(mapartie.getJoueurspartie().get(i).getPseudo()) == true) {
								System.out.println("Le joueur est déjà connecte a la partie");
								booleen = false;
							}
						}

						if (booleen == true) {

							Scanner scanMDP = new Scanner(System.in);
							System.out.println("mot de passe du joueur ?");
							joueurconnect.setMdp(scanMDP.nextLine());
							joueurconnect = joueurs.connect(joueurconnect);

							if (joueurconnect != null) {
								System.out.println("Le joueur " + joueurs.connect(joueurconnect).getPseudo()
										+ " rejoint la partie");

								mapartie.getJoueurspartie().add(joueurs.connect(joueurconnect));
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
						toutJoueur = joueurs.findAll();
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
							joueurs.save(joueur1);

							if (joueurs.save(joueur1) != null) {

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
						// modifier joueur
						Joueur joueur2 = new Joueur();
						Scanner scanPseudo2 = new Scanner(System.in);
						System.out.println("Nouveau nom du joueur ?");
						joueur2.setPseudo(scanPseudo2.nextLine());
						Scanner scanPseudo3 = new Scanner(System.in);
						System.out.println("ID du joueur � modifier ?");
						joueur2.setId(scanPseudo3.nextInt());
						joueurs.save(joueur2);

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
						listeCartes = cartes.findAll();
						for (CartesNomDeCode c : listeCartes) {
							System.out.println(c.getNom());
						}
						break;

					case 2:// creer carte
						listeCartes = cartes.findAll();
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
							cartes.save(cartecreer);
						}
						break;

					case 3:
						// supprimer carte
						CartesNomDeCode cartesup = new CartesNomDeCode();
						Scanner scanSupprimer = new Scanner(System.in);
						System.out.println("Nom de la carte nom de code a supprimer ? ");
						cartesup.setNom(scanSupprimer.nextLine());
						cartes.delete(cartesup);
						break;

					case 0:
						menu();
					}

				} while (carte != 0);

				break;
			case 4:
				histo = menuHistorique();
				do {
					histo = menuHistorique();
					switch (histo) {

					case 1:// find all

						listeJoueur = joueurs.findAll();
						for (Joueur j : listeJoueur) {
							System.out.println(j.getPseudo());
						}
						break;

					case 2:// statistique joueur
						Joueur joueur1 = new Joueur();
						System.out.println("Saisir le nom du joueur :");
						Scanner scannom = new Scanner(System.in);
						String nom = scannom.nextLine();
						joueur1 = joueurs.findByNom(nom);
						System.out.println("Le joueur " + joueur1.getPseudo() + " a jou� " + joueur1.getNbrPartie()
								+ " parties et en a gagn� " + joueur1.getNbrVictoire() + ". Il a jou� MaitreEspion "
								+ joueur1.getNbrMaitreEspion() + " fois.");
						break;

					case 3:// suppression de la bdd d'un joueur
						Joueur joueursup = new Joueur();
						Scanner scansup = new Scanner(System.in);
						System.out.println("Nom du joueur a supprimer ?");
						joueursup.setPseudo(scansup.nextLine());
						joueurs.delete(joueursup);
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

		// FIN DU PROGRAMME PRINCIPAL
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
		System.out.println("3- Supprimer Carte");
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

	static int lireEntier() {
		Scanner myScanner = new Scanner(System.in);
		try {
			return myScanner.nextInt();
		} catch (Exception e) {
			return 0;
		}

	}
}
