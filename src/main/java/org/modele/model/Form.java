package org.modele.model;

import java.io.Serializable;

public class Form implements Serializable{
private  String idmodel;
private String nomModel;
private String explain;
private int nbr;




public Form() {

}


public Form(String nomModel, String explain, int nbr) {
	super();
	this.nomModel = nomModel;
	this.explain = explain;
	this.nbr = nbr;
}


public int getNbr() {
	return nbr;
}


public void setNbr(int nbr) {
	this.nbr = nbr;
}


public String getIdmodel() {
	return idmodel;
}


public void setIdmodel(String idmodel) {
	this.idmodel = idmodel;
}


public String getExplain() {
	return explain;
}


public void setExplain(String explain) {
	this.explain = explain;
}


public String getnomModel() {
	return nomModel;
}


public void setnomModel(String nomModel) {
	this.nomModel = nomModel;
}








}
