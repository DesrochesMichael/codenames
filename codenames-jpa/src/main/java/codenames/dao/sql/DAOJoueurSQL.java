package codenames.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import codenames.dao.IDAOJoueur;
import fr.codenames.model.Joueur;

public class DAOJoueurSQL extends DAOConnexionSQL implements IDAOJoueur {

	@Override
	public Joueur save(Joueur entity) {
		try {
			PreparedStatement myStatement = connection
					.prepareStatement("INSERT INTO historique (pseudo, mdp) VALUES (?, ?)");

			myStatement.setString(1, entity.getPseudo());
			myStatement.setString(2, entity.getMdp());

			myStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("nul germaine");
		}
		return null;
	}

	

	public void seConnecter() {

		Scanner nom = new Scanner(System.in);

		System.out.println("Saisir le nom d'utilisateur :");

		String pseudo = nom.nextLine();

		DAOConnexionSQL connect = new DAOConnexionSQL();

		try {
			Statement myStatement = connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT pseudo, mdp from historique where ?=pseudo");

			while (myResult.next()) {
				if (pseudo == myResult.getString("pseudo")) {
					String mdp = myResult.getString("mdp");

					Scanner password = new Scanner(System.in);
					String motdepasse = password.nextLine();

					if (motdepasse == mdp) {
						// réussir a se conecter
					}
				}

			}
		} catch (SQLException e) {
			System.out.println("loupe bro !");
			e.printStackTrace();

		}

	}

	@Override
	public Joueur finByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Joueur entity) {
		PreparedStatement myStatement = null;
		try {
			myStatement =
			connection.prepareStatement("DELETE FROM historique WHERE pseudo = ?");
			myStatement.setString(1,entity.getPseudo());
			myStatement.execute();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteByID(Integer id) {
		PreparedStatement myStatement = null;
		try {
			myStatement = connection.prepareStatement("DELETE FROM historique WHERE id = ?");
			myStatement.setInt(1, id);
			myStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Joueur findByNom(String nom) {
		Joueur joueur = new Joueur();
		PreparedStatement myStatement = null;
		try {
			myStatement = connection.prepareStatement("select * from historique WHERE pseudo = ?");
			myStatement.setString(1, nom);

			ResultSet myResult = myStatement.executeQuery();

			while (myResult.next()) {

				joueur.setPseudo(myResult.getString("pseudo"));
				joueur.setNbrMaitreEspion(myResult.getInt("nbrMaitreespion"));
				joueur.setNbrVictoire(myResult.getInt("nbrvictoire"));
				joueur.setNbrPartie(myResult.getInt("nbrPartie"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return joueur;

	}

	@Override
	public List<Joueur> findAll() {

		List<Joueur> liste = new ArrayList<Joueur>();

		try {
			Statement myStatement = connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT * from historique");

			while (myResult.next()) {
				Joueur joueurs = new Joueur();
				joueurs.setPseudo(myResult.getString("pseudo"));
				liste.add(joueurs);

			}
		} catch (SQLException e) {
			System.out.println("loupe bro !");
			e.printStackTrace();

		}
		return liste;
	}



	@Override
	public void update(Joueur entity) {
		// TODO Auto-generated method stub
		
	}

}
