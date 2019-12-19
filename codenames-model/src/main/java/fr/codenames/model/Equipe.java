package fr.codenames.model;

import java.util.ArrayList;
import java.util.List;

public class Equipe {
private String nom;
private List<Joueur> listeJoueur = new ArrayList<Joueur>();

public Equipe(String nom) {
	this.nom=nom;
}

public void choixEquipe(Joueur j) {
	this.listeJoueur.add(j);
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
public void setListeJoueur(List<Joueur> listeJoueur) {
	this.listeJoueur = listeJoueur;
}


}
