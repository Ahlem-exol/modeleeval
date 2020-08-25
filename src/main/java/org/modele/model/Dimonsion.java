package org.modele.model;

public class Dimonsion {
private String iddim;
private String nom;
private String descreption;
private String type;
private String rdftype;
private String nommere;
private String referncencdim;


public String getReferncencdim() {
	return referncencdim;
}
public void setReferncencdim(String referncencdim) {
	this.referncencdim = referncencdim;
}
public String getNommere() {
	return nommere;
}
public void setNommere(String nommere) {
	this.nommere = nommere;
}
public String getIddim() {
	return iddim;
}

public void setIddim(String iddim) {
	this.iddim = iddim;
}

public String getRdftype() {
	return rdftype;
}

public void setRdftype(String rdftype) {
	this.rdftype = rdftype;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}


public String getNom() {
	return nom;
}

public Dimonsion(){
	
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getDescreption() {
	return descreption;
}
public void setDescreption(String descreption ) {
	this.descreption = descreption;
}

}
