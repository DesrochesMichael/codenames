package fr.codenames;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import codenames.dao.IDAOCarteNomDeCode;
import codenames.dao.IDAOJoueur;
import codenames.dao.hibernate.DAOCarteNomDeCodeHibernate;
import codenames.dao.hibernate.DAOJoueurHibernate;
import fr.codenames.model.CartesNomDeCode;
import fr.codenames.model.Joueur;


public class Application {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Partie maPartie = new Partie();
//		List<CartesNomDeCode> mots = new ArrayList<CartesNomDeCode>();
//		mots = maPartie.ChoixMots();
//
//		for (CartesNomDeCode c : mots) {
//			System.out.println(c.getNom());
//		}
//		
//		Joueur test = new Joueur();
//		test.créerJoueur();
		int carte;
		int joueur;
		int menu;
		int histo;

		Joueur test = new Joueur();
		
		
		DAOJoueurHibernate joueurs = new DAOJoueurHibernate();
		DAOCarteNomDeCodeHibernate cartes = new DAOCarteNomDeCodeHibernate();

		List<CartesNomDeCode> listeCarte = new ArrayList<CartesNomDeCode>();
		List<Joueur> listeJoueur = new ArrayList<Joueur>();
		
		
		
		// RÉPÉTITION DU MENU (0 pour en sortir)
		do {

			menu = menu();
			switch (menu) {
			case 1:
				// NOUVELLE PARTIE
				Partie partie = new Partie();
				partie.choixMots();
				partie.affecterEquipe(listeJoueur);
				
				break;
			case 2:
				do {
					joueur = menuJoueurs();
					switch (joueur) {

					case 1:// conenxion
						
						break;

					case 2:// creer joueur
						Joueur joueur1 = new Joueur();
						Scanner scanPseudo = new Scanner(System.in);
						System.out.println("nom du nouveau joueur ?");
						joueur1.setPseudo(scanPseudo.nextLine());
						Scanner scanMDP = new Scanner(System.in);
						System.out.println("mot de passe du nouveau joueur ?");
						joueur1.setMdp(scanPseudo.nextLine());
						joueurs.save(joueur1);
						
						break;

					case 3:
						// modifier joueur
						Joueur joueur2 = new Joueur();
						Scanner scanPseudo2 = new Scanner(System.in);
						System.out.println("Nouveau nom du joueur ?");
						joueur2.setPseudo(scanPseudo2.nextLine());
						Scanner scanPseudo3 = new Scanner(System.in);
						System.out.println("Nom du joueur à modifier ?");
						joueur2.setId(scanPseudo3.nextInt());
						joueurs.save(joueur2);
						
						break;
					case 4:// supprimer joueur
						Joueur joueursup = new Joueur();
						Scanner scansup = new Scanner(System.in);
						System.out.println("nom du nouveau joueur a supprimer ?");
						joueursup.setPseudo(scansup.nextLine());
						joueurs.delete(joueursup);
						
						
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
						listeCarte = cartes.findAll();
						for (CartesNomDeCode c : listeCarte) {
							System.out.println(c.getNom());
						}
						break;

					case 2:// creer carte
						CartesNomDeCode cartecreer = new CartesNomDeCode();
						Scanner scanCreer = new Scanner(System.in);
						System.out.println("Nom de la nouvelle carte nom de code ? ");
						cartecreer.setNom(scanCreer.nextLine());
						cartes.save(cartecreer);
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

					case 2:// statitique joueur
						Joueur joueur1 = new Joueur();
						System.out.println("Saisir le nom du joueur :");
						Scanner scannom = new Scanner(System.in);
						String nom = scannom.nextLine();
						joueur1 = joueurs.findByNom(nom);
						System.out.println("Le jouer " + joueur1.getPseudo() + " a joué " + joueur1.getNbrPartie()
								+ " parties et en a gagné " + joueur1.getNbrVictoire() + ". Il a joué MaitreEspion "
								+ joueur1.getNbrMaitreEspion() + " fois.");
						break;

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
		System.out.println("2- Créer joueur");
		System.out.println("3- Modifier joueur");
		System.out.println("4- Supprimer joueur");
		System.out.println("0- Retour ");
		System.out.println("----------------------");
		System.out.println("Votre choix : ");
		return lireEntier();
	}

	public static int menuCartes() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("1- Liste des Cartes");
		System.out.println("2- Créer Carte");
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

	// Demander nombre de joueur
	// affecter dans equipe au hasard avec le meme nombre

	// vote dans les equipes pour maitre espion

	// affecter carte cles a mots

	// tour de partie : indice du maitre espion + nombre de reponse
	// reponse de l'equipe
	// condition de victoire a verifier pour chaque mot donne
}
