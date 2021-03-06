package fr.codenames.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cartes_nom_de_code")
public class CartesNomDeCode {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NOM_DE_CODE_ID")
	private int id;
	
	@Column(name = "NOM_DE_CODE_NOM", nullable = false)
	@NotNull
	private String nom;
	
	@OneToOne
	@JoinColumn(name="cartenomdecode")
	private Cases code_cases;
	
	@ManyToOne
	@JoinColumn(name="partie")
	private Partie partie;
	
	
	
	

	public Cases getCode_cases() {
		return code_cases;
	}

	public void setCode_cases(Cases code_cases) {
		this.code_cases = code_cases;
	}

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

}
