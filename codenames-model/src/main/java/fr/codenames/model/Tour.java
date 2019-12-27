package fr.codenames.model;

import java.util.List;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tour")
public class Tour {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TOUR_ID")
	private int id;

	@ManyToOne
	@JoinColumn(name = "TOUR_EQUIPE_ID")
	private Equipe equipe;

	@OneToMany(mappedBy = "tour")
	private List<CartesNomDeCode> listeCartesNomDeCode;

	
	
	
	public boolean bonnereponse(String couleur, Equipe e) {
		if (couleur.equalsIgnoreCase(e.getNom()))
			return true;

		else
			return false;
	}

	public String reponseAgent(List<Cases> list) {
		String rep = null;
		boolean test = false;
		System.out.println("Quel mot est relié au mot donné par le maitre espion ?");
		Scanner sc = new Scanner(System.in);
		rep = sc.nextLine();

		for (Cases c : list) {
			if (c.getCartenomdecode().getNom().equalsIgnoreCase(rep)) {
				test = true;
			}
		}
		if (test == true) {
			return rep;
		} else {
			System.out.println("La case ne correspond a aucun mot. Veuillez recommencer");
			return null;
		}
	}

	public String motMaitreEspion() {
		System.out.println("Quel mot donnez vous aux agent ?");
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

	public int nbrMotMaitreEspion() {
		System.out.println("Quel nombre de mots les agents doivent ils deviner ?");
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

}
