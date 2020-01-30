package fr.codenames.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "joueur")
public class Joueur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "JOUEUR_ID")
	private int id;
	
	@Column(name = "JOUEUR_PSEUDO", length = 25, nullable = false)
//	@Size(max=25)
	@NotEmpty(message="Veuillez insérer un pseudo")
	private String pseudo;
	
	@Column(name = "JOUEUR_MDP",length = 25, nullable = false)
//	@Size(max=25)
	@NotEmpty(message="Veuillez insérer un mot de passe")
	private String mdp;
	
	@Column(name = "JOUEUR_NB_PARTIE")
	private int nbrPartie;
	
	@Column(name = "JOUEUR_NB_VICTOIRE")
	private int nbrVictoire;
	
	@Column(name = "JOUEUR_NB_MAITRE_ESPION")
	private int nbrMaitreEspion;
	
	@Column(name = "JOUEUR_ROLE")
	private String role = "Agent";
	
	@ManyToOne
	@JoinColumn(name = "JOUEUR_EQUIPE_ID")
	private Equipe equipe;
	
	@ManyToOne
	@JoinColumn(name = "JOUEUR_CARTE_CLES_ID")
	private CartesCles cartecles;
	
	@ManyToOne
	@JoinColumn(name = "partie")
	private Partie partie;
	
	
	
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

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public CartesCles getCartecles() {
		return cartecles;
	}

	public void setCartecles(CartesCles cartecles) {
		this.cartecles = cartecles;
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}
	
}
