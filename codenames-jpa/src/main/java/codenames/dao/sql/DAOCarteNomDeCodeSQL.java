package codenames.dao.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import codenames.dao.IDAOCarteNomDeCode;
import fr.codenames.model.CartesNomDeCode;

public class DAOCarteNomDeCodeSQL extends DAOConnexionSQL implements IDAOCarteNomDeCode {
	
	
	public List<CartesNomDeCode> findAll() {

		List<CartesNomDeCode> liste = new ArrayList<CartesNomDeCode>();

		try {
			Statement myStatement = connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT * from CarteNomDeCode");

			while (myResult.next()) {
				CartesNomDeCode carte = new CartesNomDeCode();
				carte.setId(myResult.getInt("id"));
				carte.setNom(myResult.getString("nom"));
				liste.add(carte);

			}
		} catch (SQLException e) {
			System.out.println("loupe bro !");
			e.printStackTrace();

		}
		return liste;
	}

	@Override
	public CartesNomDeCode finByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartesNomDeCode save(CartesNomDeCode entity) {
		try {
			PreparedStatement myStatement = connection.prepareStatement(
					"INSERT INTO cartenomdecode (nom) VALUES (?)");

			myStatement.setString(1, entity.getNom());
			
			myStatement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("nul germaine");
		}
		return null;
	}

	@Override
	public void delete(CartesNomDeCode entity) {
		PreparedStatement myStatement = null;
		try {
			myStatement =
			connection.prepareStatement("DELETE FROM cartenomdecode WHERE nom = ?");
			myStatement.setString(1,entity.getNom());
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
			myStatement =
			connection.prepareStatement("DELETE FROM cartenomdecode WHERE id = ?");
			myStatement.setInt(1,id);
			myStatement.execute();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(CartesNomDeCode entity) {
		// TODO Auto-generated method stub
		
	}
}
