package org.modele.evl.dao;

import java.util.ArrayList;

import org.apache.jena.rdf.model.Model;
import org.modele.model.Article;
import org.modele.model.Auteur;
import org.modele.model.Cretaire;
import org.modele.model.Dimonsion;
import org.modele.model.Form;
import org.modele.model.Hypothse;
import org.modele.model.Moderateur;
import org.modele.model.Qstionne;
import org.modele.model.choix;

public interface IModeleDao {
	
	public Model openFile();
	public ArrayList<Form>  getNomModel();
	//search
	public ArrayList<Form> SearchReseult(String search);
	public Form getNomModeleByID(String idmodel);
	public Article getArticle(String idmodel);
	public ArrayList<Auteur> getAuteur(String idmodel);
	//dimension	
	public ArrayList<Dimonsion> getAllDimonsion(String idmodel);
	public ArrayList<Hypothse> GetHypothses(String idmodel);
	public ArrayList<choix> GetChoix(String qstion,String id);
	public ArrayList<Qstionne> GetQestByCre(String nomCre,String id);
    public ArrayList<Moderateur> getModerateur(String Qeustion,String id);
// ajouter modle
	public ArrayList<Cretaire> GetCreatereSansMere();
	public ArrayList<Dimonsion> GetDimHSD();
	public ArrayList<Qstionne> GetQestByCre(String nomCre);
	public ArrayList<Dimonsion> GetSubDim(String mere);
	
		public void AddModel(String nomModel,String descreptionModel,String nomModelid);
		public void addArticle(String Title,String resume,String mcle,String url, String ISSn,String doi,String model,String id);
		public void addAuteru(String nom,String prenom,String universite,String info,String modelM,String id);
		public void addDimonsion(String nomDim,String Descreptiondimonsion,String mereDimonsion,String type,String typrdf,String modelM,String id);
		public void addQeustion(String enonceqestion,String cretaire,String modele,String id);
		public void addHypothses(String hypothses,String dimind,String dimdep,String modelnam,String id);
		public void addChoix(String choix,String qeustion ,String model,String id);
		public void addModerateur(String moderateur,String hypothses,String modelname,String id);
		public void addref(String ref,String id);
	public void UpdateModel(String nom,String descreption,String oldNom,String odldescreption);
	public void UpdateArticle(String Title,String resume,String mcle,String url, String ISSn,String doi,String Titleold,String resumeold,String mcleold,String urlOld, String ISSnold,String doiold);
	public void UpdateAuteru(String nom,String prenom,String universite,String info,String dan, String datnol,String nomold,String prenomold,String universiteold,String infoold);
	public void UpdateDimonsion(String nomDim,String Descreptiondimonsion,String mereDimonsion,String type,String nomDimold,String Descreptiondimonsionold,String mereDimonsionold,String typeold);
	public void UpdateQeustion(String qeustion,String cretaire,String qeustionold,String cretaireold);
	public void UpdateHypothses(String hypothese,String idep,String dep,String hypotheseold,String idepold,String depold);
	public void UpdateChoix(String choix,String qeustion,String choixold,String qeustionold);
	public void UpdateModerateur(String moderateur,String hypothes,String moderateurold,String HypothesOld);
	public void UpdateRefernce(String Refernce,String refernceOld);
	
//dalete
    public void delet(String id);
//nbr
  /// count nbr of model
  	public int nbrmodel();
  	public int nbrarticle();
  	public int nbrauteru();
  	public int nbrdimonsion();
  	public int nbrCretaire();
  	public int nbrhypothses();
  	public int nbrqestion();
  	public int nbrchoix();
  	public int nbrmoderateur();
  	public int nbrh();
  	
}
