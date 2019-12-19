package fr.codenames.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Joueur {
	private int id;	
	private String pseudo;
	private String mdp;
	private int nbrPartie;
	private int nbrVictoire;
	private int nbrMaitreEspion;
	private String role = "Agent";
	
	
	
	public int getNbrPartie() {
		return nbrPartie;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setNbrPartie(int nbrPartie) {
		this.nbrPartie = nbrPartie;
	}

	public int getNbrVictoire() {
		return nbrVictoire;
	}

	public void setNbrVictoire(int nbrVictoire) {
		this.nbrVictoire = nbrVictoire;
	}

	public int getNbrMaitreEspion() {
		return nbrMaitreEspion;
	}

	public void setNbrMaitreEspion(int nbrMaitreEspion) {
		this.nbrMaitreEspion = nbrMaitreEspion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
}
