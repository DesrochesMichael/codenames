package fr.codenames.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "equipe")
public class Equipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EQUIPE_ID")
	private int id;

	@Column(name = "EQUIPE_NOM", nullable = false)
	@NotNull
	private String nom;

	@OneToMany(mappedBy = "equipe")
	private List<Joueur> listeJoueur = new ArrayList<Joueur>();

	@OneToMany(mappedBy = "equipe")
	private List<Tour> listeTour;

	@ManyToOne
	@JoinColumn(name = "partie")
	private Partie partie;

	

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Joueur> getListeJoueur() {
		return listeJoueur;
	}

	public List<Tour> getListeTour() {
		return listeTour;
	}

	public void setListeTour(List<Tour> listeTour) {
		this.listeTour = listeTour;
	}

	public void setListeJoueur(List<Joueur> listeJoueur) {
		this.listeJoueur = listeJoueur;
	}

}
