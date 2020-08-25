package org.modele.model;

import java.util.ArrayList;

public class Hypothse {
private String idhypo;
String enonceHypothse;
String dimonsiondep;
String dimonsionindp;
ArrayList<Moderateur> m ;

public String getIdhypo() {
	return idhypo;
}
public void setIdhypo(String idhypo) {
	this.idhypo = idhypo;
}
public ArrayList<Moderateur> getM() {
	return m;
}
public void setM(ArrayList<Moderateur> m) {
	this.m = m;
}
public String getEnonceHypothse() {
	return enonceHypothse;
}
public void setEnonceHypothse(String enonceHypothse) {
	this.enonceHypothse = enonceHypothse;
}
public String getDimonsiondep() {
	return dimonsiondep;
}
public void setDimonsiondep(String dimonsiondep) {
	this.dimonsiondep = dimonsiondep;
}
public String getDimonsionindp() {
	return dimonsionindp;
}
public void setDimonsionindp(String dimonsionindp) {
	this.dimonsionindp = dimonsionindp;
}

}
