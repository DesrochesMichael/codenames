package codenames.dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAOConnexionSQL {
	protected static Connection connection = null;
	
	
	public DAOConnexionSQL() {
		this.setup();
	}

	public Connection setup() {
		try {
			if (connection == null) {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/codenames?serverTimezone=UTC","root", "24021994Mi");
			}
		}

		catch (Exception e) {
			System.out.println("Pb de connexion");
		}
		return connection;
	}
}