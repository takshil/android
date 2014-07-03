package fr.formation.articlefragment;

public class Article {

	private int id;
	private String libelle;
	
	Article (int pId, String pLibelle){
		id = pId;
		libelle = pLibelle;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
