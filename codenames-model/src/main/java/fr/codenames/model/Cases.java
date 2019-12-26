package fr.codenames.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cases")
public class Cases {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CASE_ID")
	private int id;
	
	
	private CartesNomDeCode cartenomdecode;
	
	@Column(name = "CASE_COULEUR", nullable = false)
	@NotNull
	private String couleur;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CartesNomDeCode getCartenomdecode() {
		return cartenomdecode;
	}

	public void setCartenomdecode(CartesNomDeCode cartenomdecode) {
		this.cartenomdecode = cartenomdecode;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

}
