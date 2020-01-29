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
@Table(name = "cases")
public class Cases {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CASE_ID")
	private int id;
	
	@OneToOne
	@JoinColumn(name="code_cases")
	private CartesNomDeCode cartenomdecode;
	
	@OneToOne
	@JoinColumn(name="cle_cases")
	private CartesCles cartescles;
	
	@Column(name = "CASE_COULEUR", nullable = false)
	@NotNull
	private String couleur;
	
	@ManyToOne
	@JoinColumn(name="partie")
	private Partie partie;

	
	
	
	
	
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
