package com.example.httptest;

public class Personne {
	String nom;
	String prenom;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String toString(){
		return getPrenom() + " " + getNom();
	}
}
