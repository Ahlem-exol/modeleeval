package org.modele.evl.dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.update.GraphStoreFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.apache.jena.util.FileManager;
import org.modele.model.Article;
import org.modele.model.Auteur;
import org.modele.model.Cretaire;
import org.modele.model.Dimonsion;
import org.modele.model.Form;
import org.modele.model.Hypothse;
import org.modele.model.Moderateur;
import org.modele.model.Qstionne;
import org.modele.model.choix;


public class ModeleDaoImpl implements IModeleDao{

	@Override
	public Model openFile() {
		Model model = ModelFactory.createDefaultModel();
		InputStream in =FileManager.get().open("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf");
		if(in == null) {
			throw new IllegalArgumentException("File : not found");
		}
		model.read(in,null);
		return model;
	}

	@Override
	public ArrayList<Form> getNomModel() {
		Model model = openFile();	
		ArrayList<Form> result = new ArrayList<Form>();
		String queryString = "PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"SELECT ?subject  ?descreption ?nom\r\n" + 
				"	WHERE {?subject fin:NomModele ?nom.\r\n" + 
				"    OPTIONAL{ ?subject fin:DescreptionDuModule ?descreption.}\r\n" + 
				" }";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
				Form tmp=new Form();
				String desc;String nom;
				QuerySolution soln = results.nextSolution();
				Literal descreption=soln.getLiteral("descreption");;

				if(descreption !=null) {
					 desc =descreption.getString();
				}else {
					 desc ="";	
				}
			
				String idM;
				String id =soln.get("subject").toString();
				if(id !=null) {
					 idM= id.substring(58);
				}else {
					 idM ="";
				}
				
				
				Literal name =soln.getLiteral("nom");
				if(name !=null) {
					 nom= name.getString();	
				}else {
					 nom ="";
				}	
					
				tmp.setExplain(desc);
				tmp.setnomModel(nom);			
				tmp.setIdmodel(idM);		
				result.add(tmp);
			}}finally {
			qexec.close();
		}
		return result;
	}

	@Override
	public ArrayList<Form> SearchReseult(String search) {
		ArrayList<Form> resultat =new ArrayList<Form>();
		
    	Model model = openFile();
		String queryString = "PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"SELECT ?model ?NomModel ?DescreptionDuModule  \r\n" + 
				"WHERE {       \r\n" + 
				"\r\n" + 
				"OPTIONAL{    ?model fin:NomModele  ?NomModel.\r\n" + 
				"OPTIONAL{  ?model fin:DescreptionDuModule  ?DescreptionDuModule.}\r\n" + 
				"FILTER regex (str(?NomModel),\""+search+"\",\"i\").}\r\n" + 
				"\r\n" + 
				"OPTIONAL{    ?model fin:DescreptionDuModule  ?DescreptionDuModule.\r\n" + 
				"                          ?model fin:NomModele  ?NomModel.\r\n" + 
				"FILTER regex (str(?DescreptionDuModule),\""+search+"\",\"i\").}\r\n" + 
				"\r\n" + 
				"\r\n" + 
				" OPTIONAL{    ?subject fin:DateDeCreation  ?DateDeCreation.\r\n" + 
				"OPTIONAL{    ?model fin:DescreptionDuModule  ?DescreptionDuModule.\r\n" + 
				"                          ?model fin:NomModele  ?NomModel.\r\n" + 
				"FILTER regex (str(?DateDeCreation),\""+search+"\",\"i\").}\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"OPTIONAL{   ?subject fin:NomDimension  ?NomDimension.\r\n" + 
				"OPTIONAL{ ?model fin:SeComposeDE ?subject .\r\n" + 
				" ?model fin:NomModele  ?NomModel.}\r\n" + 
				"OPTIONAL{  ?model fin:DescreptionDuModule  ?DescreptionDuModule.}\r\n" + 
				"FILTER regex (str(?NomDimension),\""+search+"\",\"i\").}\r\n" + 
				"\r\n" + 
				"OPTIONAL{    ?subject fin:DescreptionDimension  ?DescreptionDimension.\r\n" + 
				"OPTIONAL{ ?model fin:SeComposeDE ?subject .\r\n" + 
				"                      ?model fin:NomModele  ?NomModel.}\r\n" + 
				"OPTIONAL{  ?model fin:DescreptionDuModule  ?DescreptionDuModule.}\r\n" + 
				"FILTER regex (str(?DescreptionDimension),\""+search+"\",\"i\").}\r\n" + 
				"\r\n" + 
				"OPTIONAL{  ?subject fin:TypeDeDim  ?TypeDeDim.\r\n" + 
				"OPTIONAL{ ?model fin:SeComposeDE ?subject .\r\n" + 
				"                      ?model fin:NomModele  ?NomModel.}\r\n" + 
				"OPTIONAL{  ?model fin:DescreptionDuModule  ?DescreptionDuModule.}\r\n" + 
				"FILTER regex (str(?TypeDeDim),\""+search+"\",\"i\").} \r\n" + 
				"\r\n" + 
				"OPTIONAL{ ?subject fin:EnnonceDeQuestion  ?EnnonceDeQuestion.\r\n" + 
				"OPTIONAL{ ?dimonsion fin:SeMesurePar ?subject .\r\n" + 
				"                      ?model fin:SeComposeDE ?dimonsion .\r\n" + 
				"                      ?model fin:NomModele  ?NomModel.\r\n" + 
				"OPTIONAL{  ?model fin:DescreptionDuModule  ?DescreptionDuModule.}}\r\n" + 
				"OPTIONAL{ ?model fin:DescreptionDuModule  ?DescreptionDuModule.}\r\n" + 
				"FILTER regex (str(?EnnonceDeQuestion),\""+search+"\",\"i\").}\r\n" + 
				"\r\n" + 
				"OPTIONAL{    ?subject fin:EnonceHypothes  ?EnonceHypothes.\r\n" + 
				" OPTIONAL{  ?dimonsion fin:influenceDans ?subject.\r\n" + 
				"OPTIONAL{ ?dimonsion fin:SeMesurePar ?subject .\r\n" + 
				"                      ?model fin:SeComposeDE ?dimonsion .\r\n" + 
				"                      ?model fin:NomModele  ?NomModel.\r\n" + 
				"OPTIONAL{  ?model fin:DescreptionDuModule  ?DescreptionDuModule.}}}\r\n" + 
				" OPTIONAL{  ?dimonsion1 fin:InfluenseuseDans ?subject.\r\n" + 
				"OPTIONAL{ ?dimonsion1 fin:SeMesurePar ?subject .\r\n" + 
				"                      ?model fin:SeComposeDE ?dimonsion1 .\r\n" + 
				"                      ?model fin:NomModele  ?NomModel.\r\n" + 
				"OPTIONAL{  ?model fin:DescreptionDuModule  ?DescreptionDuModule.}}}\r\n" + 
				"FILTER regex (str(?EnonceHypothes),\""+search+"\",\"i\").}\r\n" + 
				"\r\n" + 
				" OPTIONAL{    ?subject fin:Réference  ?Réference.\r\n" + 
				"?article fin:referencePar ?Réference.\r\n" + 
				"OPTIONAL{\r\n" + 
				"                      ?model fin:trouverDans ?article .\r\n" + 
				"                      ?model fin:NomModele  ?NomModel.\r\n" + 
				"OPTIONAL{  ?model fin:DescreptionDuModule  ?DescreptionDuModule.}}\r\n" + 
				"?dim  fin:DimReferencePar ?Réference.\r\n" + 
				"OPTIONAL{ ?dim fin:SeMesurePar ?subject .\r\n" + 
				"                      ?model fin:SeComposeDE ?dim .\r\n" + 
				"                      ?model fin:NomModele  ?NomModel.\r\n" + 
				"OPTIONAL{  ?model fin:DescreptionDuModule  ?DescreptionDuModule.}}\r\n" + 
				"?qeustion  fin:QstReferencePar ?Réference.\r\n" + 
				"OPTIONAL{ ?dimonsion1 fin:SeMesurePar ?qeustion .\r\n" + 
				"                      ?model fin:SeComposeDE ?dimonsion1 .\r\n" + 
				"                      ?model fin:NomModele  ?NomModel.\r\n" + 
				"OPTIONAL{  ?model fin:DescreptionDuModule  ?DescreptionDuModule.}}\r\n" + 
				"FILTER regex (str(?Réference),\""+search+"\",\"i\").}\r\n" + 
				"\r\n" + 
				"\r\n" + 
				" OPTIONAL{    ?subject fin:TitreDeLarticle  ?TitreDeLarticle.\r\n" + 
				"OPTIONAL{\r\n" + 
				"                      ?model fin:trouverDans ?subject .\r\n" + 
				"                      ?model fin:NomModele  ?NomModel.\r\n" + 
				"OPTIONAL{  ?model fin:DescreptionDuModule  ?DescreptionDuModule.}}\r\n" + 
				"FILTER regex (str(?TitreDeLarticle),\""+search+"\",\"i\").} \r\n" + 
				"\r\n" + 
				"\r\n" + 
				"OPTIONAL{    ?subject fin:DOI  ?DOI.\r\n" + 
				"OPTIONAL{\r\n" + 
				"                      ?model fin:trouverDans ?subject .\r\n" + 
				"                      ?model fin:NomModele  ?NomModel.\r\n" + 
				"OPTIONAL{  ?model fin:DescreptionDuModule  ?DescreptionDuModule.}}\r\n" + 
				"FILTER regex (str(?DOI),\""+search+"\",\"i\").}\r\n" + 
				"\r\n" + 
				" OPTIONAL{    ?subject fin:ISSN  ?ISSN.\r\n" + 
				"OPTIONAL{\r\n" + 
				"                      ?model fin:trouverDans ?subject .\r\n" + 
				"                      ?model fin:NomModele  ?NomModel.\r\n" + 
				"OPTIONAL{  ?model fin:DescreptionDuModule  ?DescreptionDuModule.}}\r\n" + 
				"FILTER regex (str(?ISSN),\""+search+"\",\"i\").}\r\n" + 
				"\r\n" + 
				" OPTIONAL{    ?subject fin:MotCle  ?MotCle.\r\n" + 
				"OPTIONAL{\r\n" + 
				"                      ?model fin:trouverDans ?subject .\r\n" + 
				"                      ?model fin:NomModele  ?NomModel.\r\n" + 
				"OPTIONAL{  ?model fin:DescreptionDuModule  ?DescreptionDuModule.}}\r\n" + 
				"FILTER regex (str(?MotCle),\""+search+"\",\"i\").}\r\n" + 
				"\r\n" + 
				" OPTIONAL{    ?subject fin:Resume  ?Resume.\r\n" + 
				"OPTIONAL{\r\n" + 
				"                      ?model fin:trouverDans ?subject .\r\n" + 
				"                      ?model fin:NomModele  ?NomModel.\r\n" + 
				"OPTIONAL{  ?model fin:DescreptionDuModule  ?DescreptionDuModule.}}\r\n" + 
				"FILTER regex (str(?Resume),\""+search+"\",\"i\").}\r\n" + 
				"\r\n" + 
				" OPTIONAL{    ?subject fin:URL  ?URL.\r\n" + 
				"OPTIONAL{\r\n" + 
				"                      ?model fin:trouverDans ?subject .\r\n" + 
				"                      ?model fin:NomModele  ?NomModel.\r\n" + 
				"OPTIONAL{  ?model fin:DescreptionDuModule  ?DescreptionDuModule.}}\r\n" + 
				"FILTER regex (str(?URL),\""+search+"\",\"i\").}\r\n" + 
				"\r\n" + 
				" OPTIONAL{    ?subject fin:DateDeNaissance  ?DateDeNaissance.\r\n" + 
				"OPTIONAL{\r\n" + 
				"                      ?subject fin:Ecrire ?model .\r\n" + 
				"                      ?model fin:NomModele  ?NomModel.\r\n" + 
				"OPTIONAL{  ?model fin:DescreptionDuModule  ?DescreptionDuModule.}}\r\n" + 
				"FILTER regex (str(?DateDeNaissance),\""+search+"\",\"i\").}\r\n" + 
				"\r\n" + 
				" OPTIONAL{    ?subject fin:Nom  ?Nom.\r\n" + 
				"OPTIONAL{\r\n" + 
				"                      ?subject fin:Ecrire ?model .\r\n" + 
				"                      ?model fin:NomModele  ?NomModel.\r\n" + 
				"OPTIONAL{  ?model fin:DescreptionDuModule  ?DescreptionDuModule.}}\r\n" + 
				"FILTER regex (str(?Nom),\""+search+"\",\"i\").}\r\n" + 
				"\r\n" + 
				" OPTIONAL{    ?subject fin:Prenom  ?Prenom.\r\n" + 
				"OPTIONAL{\r\n" + 
				"                      ?subject fin:Ecrire ?model .\r\n" + 
				"                      ?model fin:NomModele  ?NomModel.\r\n" + 
				"OPTIONAL{  ?model fin:DescreptionDuModule  ?DescreptionDuModule.}}\r\n" + 
				"FILTER regex (str(?Prenom),\""+search+"\",\"i\").}\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				" OPTIONAL{    ?subject fin:ChoixDe  ?Choix.\r\n" + 
				"FILTER regex (str(?Choix),\""+search+"\",\"i\").}\r\n" + 
				"\r\n" + 
				"\r\n" + 
				" OPTIONAL{    ?subject fin:NomModerateur  ?NomModerateur.\r\n" + 
				"FILTER regex (str(?NomModerateur),\""+search+"\",\"i\").}\r\n" + 
				"\r\n" + 
				"\r\n" + 
				" }}";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
	
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
				Form tmp=new Form();
				String desc;String nom;
				QuerySolution soln = results.nextSolution();
				
				Literal descreption=null;
				if(	 soln.getLiteral("NomModel")!=null) {
					 descreption=soln.getLiteral("NomModel");
				}else {
					descreption=null;
				}
				//pour test le modelnn nule
				if(descreption !=null) {
					 desc =descreption.getString();
				}else {
					 desc =null;
				
				}
				Literal name=null;
				if(soln.getLiteral("DescreptionDuModule")!=null) {
					 name =soln.getLiteral("DescreptionDuModule");
				}else {
					name=null;
					
				}
			
				if(name !=null) {
					 nom= name.getString();	
				}else {
					 nom =null;
				}
				String idM;String id=null;;
				if(soln.get("model")!=null) {
					 id =soln.get("model").toString();	
				}else {
					id=null;
				}
				if(id !=null) {
					 idM= id.substring(58);
						tmp.setnomModel(desc);
						tmp.setExplain(nom);
						tmp.setIdmodel(idM);
						resultat.add(tmp);	
				}else {
					 idM =null;
				}	
					
	
			}}finally {
			qexec.close();
		}	

		return resultat;
	}

	@Override
	public Form getNomModeleByID(String idmodel) {
		
		Form tmp=new Form();
		Model model = openFile();
	//	model.write(System.out);	
		String uri="<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+idmodel+">";
		String queryString = "PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"SELECT DISTINCT ?nom ?descreption\r\n" + 
				"WHERE {\r\n" + 
				"  ?x    fin:NomModele  ?nom.\r\n" + 
				"   OPTIONAL{  ?x fin:DescreptionDuModule ?descreption.}\r\n" + 
				"FILTER ( SameTerm(?x,"+ uri+"))\r\n" + 
				" }";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		System.out.println("exeution de requet");
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
			
				String desc;String nom;
				QuerySolution soln = results.nextSolution();
				Literal descreption=soln.getLiteral("descreption");;
				//pour test le modelnn nule
				if(descreption !=null) {
					 desc =descreption.getString();
				}else {
					 desc ="";	
				}
				
				Literal name =soln.getLiteral("nom");
				if(name !=null) {
					 nom= name.getString();	
				}else {
					 nom ="";
				}	
					
				tmp.setExplain(desc);
				tmp.setnomModel(nom);	
				tmp.setIdmodel(idmodel);
			}}finally {
			qexec.close();
		}
		return tmp;
	}

	@Override
	public Article getArticle(String idmodel) {
		Article articel= new Article();
		//ope file
		Model model =openFile();
		
		String uri="<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+idmodel+">";
		String queryString = "\r\n" + 
				"PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"SELECT DISTINCT ?x   ?Article ?resume  ?TitreDeLarticle ?doi ?MoteCle ?refernce ?ISSN ?Url \r\n" + 
				"WHERE {\r\n" + 
				"  ?x    fin:NomModele  ?y.\r\n  " + 
				"OPTIONAL{?x   fin:trouverDans ?Article.   ?Article fin:referencePar ?ref. ?ref fin:Réference ?refernce. \r\n" + 
				"OPTIONAL{            ?Article fin:Resume ?resume.}\r\n" + 
				"OPTIONAL{           ?Article fin:TitreDeLarticle ?TitreDeLarticle.}\r\n" + 
				" OPTIONAL{            ?Article fin:DOI ?doi.}\r\n" + 
				" OPTIONAL{           ?Article fin:MotCle ?MoteCle.}\r\n" + 
				"OPTIONAL{           ?Article fin:URL ?Url.     }"
				+ "OPTIONAL{           ?Article fin:ISSN ?ISSN.     }"
				+ "}\r\n" + 
				"\r\n" + 
				"FILTER ( SameTerm(?x,"+uri+"))\r\n" + 
				" }";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		try {
			ResultSet results = qexec.execSelect();		
			while(results.hasNext()) {				
				QuerySolution soln = results.nextSolution();
				//le ide de model 				
				String l = soln.get("x").toString();			
	           //le nom  darticel
				
				Literal TitreDeLarticle=soln.getLiteral("TitreDeLarticle");
				String descreption;
				//pour test le modelnn nule
				if(TitreDeLarticle !=null) {
					descreption =TitreDeLarticle.getString();
				}else {
					descreption ="";
				}
				
				//le resume
				
				Literal resume=soln.getLiteral("resume");
				String res;
				//pour test le modelnn nule
				if(resume !=null) {
					res =resume.getString();
				}else {
					res ="pas de resume pour cette article ";
				}
				
				
				//dia 
				Literal doi=soln.getLiteral("doi");
				String DOI;
				//pour test le modelnn nule
				if(doi !=null) {
					DOI =doi.getString();
				}else {
					DOI ="pas de DOI pour cette models ";
				}
				
				
				//mote cle 
				Literal MoteCle=soln.getLiteral("MoteCle");
				String motecle;
				//pour test le modelnn nule
				if(MoteCle !=null) {
					motecle =MoteCle.getString();
				}else {
					motecle ="pas de mote cle pour cette models ";
				}
				
				//mote cle 
				Literal URL=soln.getLiteral("Url");
				String url;
				//pour test le modelnn nule
				if(URL !=null) {
					url =URL.getString();
				}else {
					url ="pas de URL  pour cette ARTICLE ";
				}
				
				Literal ISSN=soln.getLiteral("ISSN");
				String issn;
				//pour test le modelnn nule
				if(ISSN !=null) {
					issn =ISSN.getString();
				}else {
					issn ="pas de ISSN  pour cette ARTICLE ";
				}
				
				Literal REF=soln.getLiteral("refernce");
				String refernce;
				//pour test le modelnn nule
				if(REF !=null) {
					refernce =REF.getString();
				}else {
					refernce ="pas de réference  pour cette ARTICLE ";
				}
				
				articel.setDoi(DOI);
				articel.setResume(res);
				articel.setTitle(descreption);
				articel.setMotecle(motecle);
				articel.setIssn(issn);
				articel.setUrl(url);
				articel.setRefernceArticle(refernce);
				
				
			}}finally {
				qexec.close();
			}
		return articel;
	}

	@Override
	public ArrayList<Auteur> getAuteur(String idmodel) {
		ArrayList<Auteur> auteurs= new ArrayList<Auteur>();
		Model model = openFile();		
		String uri="<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+idmodel+">";
		String queryString = "PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"SELECT DISTINCT ?Auteur ?x   ?nom ?prenom ?universite ?position ?DateDeNaissance\r\n" + 
				"WHERE {\r\n" + 
				"?x fin:NomModele ?y.\r\n" + 
				"OPTIONAL{?x   fin:trouverDans ?Article\r\n" + 
				"OPTIONAL{            ?Auteur fin:Ecrire ?Article.}\r\n" + 
				"OPTIONAL{           ?Auteur fin:Nom ?nom.}\r\n" + 
				"                 OPTIONAL{            ?Auteur fin:Prenom ?prenom.}\r\n" + 
				"                  OPTIONAL{           ?Auteur fin:Université ?universite.}"
				+ " OPTIONAL{           ?Auteur fin:DateDeNaissance ?DateDeNaissance.}"
				+ "  OPTIONAL{           ?Auteur fin:Position ?position.}\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"FILTER ( SameTerm(?x,"+ uri+"))\r\n" + 
				" }";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
				Auteur a=new Auteur();
				QuerySolution soln = results.nextSolution();
				//le ide de model 
				RDFNode lm = soln.get("Auteur");
		     	String l;
		     	if(lm!=null) {
		     		l=lm.toString();
		     	}else {
		     		l="";
		     	}
	           //le nom 
				
				Literal nom=soln.getLiteral("nom");
				String nomA;
				//pour nom
				if(nom !=null) {
					nomA =nom.getString();
				}else {
					nomA ="";
				}
				
				//le resume
				
				Literal prenom=soln.getLiteral("prenom");
				String prenoma;
				//pour test le modelnn nule
				if(prenom !=null) {
					prenoma =prenom.getString();
				}else {
					prenoma ="";
				}
				
				
				//dia 
				Literal universite=soln.getLiteral("universite");
				String univers;
				//pour test le modelnn nule
				if(universite !=null) {
					univers =universite.getString();
				}else {
					univers ="";
				}
				
				Literal Pos=soln.getLiteral("position");
				String position;
				//pour nom
				if(Pos !=null) {
					position =Pos.getString();
				}else {
					position ="";
				}
				
				Literal DateDeNaissance=soln.getLiteral("DateDeNaissance");
				String datn;
				//pour nom
				if(DateDeNaissance !=null) {
					datn =DateDeNaissance.getString();
				}else {
					datn ="";
				}
				
				a.setNom(nomA);
				a.setPrenom(prenoma);
				a.setUnivers(univers);
				a.setPosition(position);
				a.setIdauterur(l);
				a.setDatedenaissance(datn);
	             auteurs.add(a);	
			}}finally {
				qexec.close();
			}
		return auteurs;

	}

	@Override
	public ArrayList<Dimonsion> getAllDimonsion(String idmodel) {
		
		ArrayList<Dimonsion> dimonsions= new ArrayList<Dimonsion>();
		Model model = openFile();
		
		String uri="<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+idmodel+">";
		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \r\n" + 
				"PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"				SELECT DISTINCT ?dim  ?nomDim  ?DescreptionDimension ?rdftype ?nomMere\r\n" + 
				"				WHERE {\r\n" + 
				"				?x  fin:NomModele  ?y. \r\n" + 
				"				?x  fin:SeComposeDE ?dim.\r\n" + 
				"               OPTIONAL{?Mere fin:MèreDE ?dim. ?Mere fin:NomDimension ?nomMere.?dim fin:NomDimension ?nomDim. }     "
				+ "              ?dim  rdf:type ?rdftype.\r\n" + 			
				"				?dim fin:NomDimension ?nomDim.\r\n" + 
				"				OPTIONAL{ ?dim fin:DescreptionDimension ?DescreptionDimension.}\r\n" + 
				"				FILTER ( SameTerm(?x,"+uri+"))\r\n" + 
				"				 }";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
				
				Dimonsion a=new Dimonsion();
				QuerySolution soln = results.nextSolution();
				
				
				RDFNode lm = soln.get("dim");
		     	String l;
		     	if(lm!=null) {
		     		l=lm.toString();
		     	}else {
		     		l="";
		     	}
			
				
				Literal nomDim=soln.getLiteral("nomDim");
				String dimnom;
		
				
				//pour nom
				if(nomDim !=null) {
					dimnom =nomDim.getString();
				}else {
					dimnom =null;
				}
				
				Literal DescreptionDimension=soln.getLiteral("DescreptionDimension");
				String DSC;
				//pour nom
				if(DescreptionDimension !=null) {
					DSC =DescreptionDimension.getString();
				
				}else {
					DSC ="";
				}
				RDFNode rdftyp =soln.get("rdftype");
				String tp;
				//pour nom
				if(rdftyp !=null) {
					tp =rdftyp.toString();
		
				}else {
					tp ="";
				}
				//pour nom
				Literal nomMere=soln.getLiteral("nomMere");
				String nomM;
				if(nomMere !=null) {
					nomM =nomMere.getString();
				
				}else {
					nomM ="";
				}
				a.setIddim(l);
				a.setNom(dimnom);
				a.setDescreption(DSC);
				a.setRdftype(tp);
				a.setNommere(nomM);

				dimonsions.add(a);		
			}}finally {
				qexec.close();
			}
		return dimonsions;

	}

	@Override
	public ArrayList<Qstionne> GetQestByCre(String nomCre,String id) {
		ArrayList<Qstionne> Cre= new ArrayList<Qstionne>();
		
		//open file

		Model model = openFile();
		String uri="<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">";
		String queryString = "  PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"				SELECT DISTINCT ?Question ?Cretaire   ?EnonceQuestion \r\n" + 
				"				WHERE { ?model    fin:NomModele  ?y. ?model   fin:SeComposeDE ?x.\r\n" + 
				"				?x    fin:NomDimension  \""+nomCre+"\".\r\n" + 
				"				OPTIONAL{?x   fin:SeMesurePar ?Question.\r\n" + 
				"				          ?Question fin:EnnonceDeQuestion ?EnonceQuestion.}\r\n" +  
				"	            FILTER ( SameTerm(?model,"+uri+")) }" ;
		
		
	
		
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
				Qstionne a=new Qstionne();
				QuerySolution soln = results.nextSolution();
				RDFNode lm = soln.get("Question");
		     	String l;
		     	if(lm!=null) {
		     		l=lm.toString();
		     	}else {
		     		l="";
		     	}
				Literal cretaire=soln.getLiteral("Cretaire");
				String nomA;
				//pour nom
				if(cretaire !=null) {
					nomA =cretaire.getString();
				}else {
					nomA ="";
				}
				
				//le resume
				
				Literal enonce=soln.getLiteral("EnonceQuestion");
				String prenoma;
				//pour test le modelnn nule
				if(enonce !=null) {
					prenoma =enonce.getString();
				}else {
					prenoma ="";
				}
		
				a.setCretaire(nomCre);
				a.setQest(prenoma);
				a.setIdqstion(l);
				Cre.add(a);	
			}}finally {
				qexec.close();
			}
		return Cre;

	}

	@Override
	public ArrayList<Hypothse> GetHypothses(String idmodel) {
		ArrayList<Hypothse> hypo= new ArrayList<Hypothse>();
		Model model = openFile();
		
		String uri="<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+idmodel+">";
		String queryString = "PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"				SELECT DISTINCT ?h ?EnonceHypothes  ?Demonsionindep ?DimonsionDpond ?NomModerateur\r\n" + 
				"				WHERE {\r\n" + 
				"				  ?x    fin:NomModele  ?y.\r\n" + 
				"				OPTIONAL{?x   fin:SeComposeDE  ?DIM1. \r\n" + 
				"       ?x   fin:SeComposeDE  ?DIM2. \r\n" +  
				"     OPTIONAL{ ?DIM1 fin:influenceDans ?h. \r\n" + 
				"         ?DIM2 fin:InfluenseuseDans ?h. \r\n" + 
				"				OPTIONAL{ ?h fin:EnonceHypothes ?EnonceHypothes.\r\n" + 
				"      ?DIM1 fin:NomDimension ?Demonsionindep.\r\n" + 
				"				 ?DIM2 fin:NomDimension ?DimonsionDpond.\r\n" + 
				"OPTIONAL{?h fin:ModerePar ?modertaur.\r\n" + 
				"?modertaur fin:NomModerateur ?NomModerateur.  }}}\r\n" + 
				"                                                                              \r\n" + 
				"\r\n" + 
				"				}	 \r\n" + 
				"				FILTER ( SameTerm(?x,"+uri+"))\r\n" + 
				"				 }";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
				Hypothse a=new Hypothse();
				QuerySolution soln = results.nextSolution();
				RDFNode lm = soln.get("h");
		     	String l;
		     	if(lm!=null) {
		     		l=lm.toString();
		     	}else {
		     		l="";
		     	}
				Literal nom=soln.getLiteral("EnonceHypothes");
				String nomA;
				//pour nom
				if(nom !=null) {
					nomA =nom.getString();
				}else {
					nomA ="";
				}
				
				Literal Demonsionindep=soln.getLiteral("Demonsionindep");
				String DI;
				//pour nom
				if(Demonsionindep !=null) {
					DI =Demonsionindep.getString();
				}else {
					DI ="";
				}
				
				
				Literal DimonsionDpond=soln.getLiteral("DimonsionDpond");
				String DD;
				//pour nom
				if(DimonsionDpond !=null) {
					DD =DimonsionDpond.getString();
				}else {
					DD ="";
				}
				
				Literal NomModerateur=soln.getLiteral("NomModerateur");
				String nomM;
				//pour nom
				if(NomModerateur !=null) {
					nomM =NomModerateur.getString();
				}else {
					nomM ="";
				}
				
                   a.setIdhypo(l);
				a.setEnonceHypothse(nomA);
				a.setDimonsionindp(DI);
				a.setDimonsiondep(DD);
			
				hypo.add(a);	
			}}finally {
				qexec.close();
			}
		return hypo;

	}

	@Override
	public ArrayList<Moderateur> getModerateur(String hypo, String id) {
		ArrayList<Moderateur> MOter= new ArrayList<Moderateur>();
		Model model = openFile();
		
		String uri="<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">";
		String queryString = "PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"				SELECT DISTINCT ?modertaur ?EnonceHypothes  ?NomModerateur\r\n" + 
				"				WHERE {\r\n" + 
				"				  ?x    fin:NomModele  ?y.\r\n" + 
				"				OPTIONAL{?x   fin:SeComposeDE  ?DIM1. \r\n" + 
				"     ?x   fin:SeComposeDE  ?DIM2. \r\n" + 
				"     OPTIONAL{ ?DIM1 fin:influenceDans ?h. \r\n" + 
				"         ?DIM2 fin:InfluenseuseDans ?h. \r\n" + 
				"				OPTIONAL{ ?h fin:EnonceHypothes \""+hypo+"\".\r\n" + 
				"      ?DIM1 fin:NomDimension ?Demonsionindep.\r\n" + 
				"				 ?DIM2 fin:NomDimension ?DimonsionDpond.\r\n" + 
				"OPTIONAL{?h fin:ModerePar ?modertaur.\r\n" + 
				"?modertaur fin:NomModerateur ?NomModerateur.  }}}\r\n" + 
				"                                                                              \r\n" + 
				"\r\n" + 
				"				}	 \r\n" + 
				"				FILTER ( SameTerm(?x,"+uri+"))\r\n" + 
				"				 }";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
				Moderateur a=new Moderateur();
				QuerySolution soln = results.nextSolution();
				RDFNode lm = soln.get("modertaur");
		     	String l;
		     	if(lm!=null) {
		     		l=lm.toString();
		     	}else {
		     		l="";
		     	}
				
				Literal nom=soln.getLiteral("NomModerateur");
				String nomA;
				//pour nom
				if(nom !=null) {
					nomA =nom.getString();
				}else {
					nomA ="";
				}
		a.setModerateur(nomA);
		a.setEnoncehypothesees(hypo);
a.setIdmoder(l);
		MOter.add(a);	
			}}finally {
				qexec.close();
			}
		return MOter;
	}
	
	@Override
	public ArrayList<choix> GetChoix(String qstion,String id ) {
        ArrayList<choix> Cre= new ArrayList<choix>();
		Model model = openFile();
		String uri="<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">";
		String queryString = "PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"				SELECT DISTINCT ?choix ?encchoix   ?Qeustion  \r\n" + 
				"				WHERE {\r\n" + 
				"				                     ?x    fin:NomModele  ?y.\r\n" + 
				"				OPTIONAL{?x   fin:SeComposeDE  ?DIM1. \r\n" + 
				"     OPTIONAL{        "
				+ "        ?DIM1   fin:SeMesurePar  ?Qeustion.\r\n" + 	
				"?Qeustion fin:EnnonceDeQuestion \""+qstion+"\".\r\n" + 
				"   OPTIONAL {  ?Qeustion fin:ChoixDe  ?choix.\r\n" + 
				"  ?choix  fin:Choix ?encchoix.\r\n" + 
				"                                                                                                                                                    }}} \r\n" + 
				"			 \r\n" + 
				"				FILTER ( SameTerm(?x, "+uri+"))\r\n" + 
				"				 }";

		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
				choix a=new choix();
				QuerySolution soln = results.nextSolution();
				RDFNode lm = soln.get("choix");
		     	String l;
		     	if(lm!=null) {
		     		l=lm.toString();
		     	}else {
		     		l=null;
		     	}
				Literal cretaire=soln.getLiteral("encchoix");
				String nomA;
				//pour nom
				if(cretaire !=null) {
					nomA =cretaire.getString();
				}else {
					nomA =null;
				}
	      	a.setChoix(nomA);
	      	a.setQstion(qstion);
	   a.setIdchoix(l);
	      	Cre.add(a);
			}}finally {
				qexec.close();
			}
		return Cre;

	}

	@Override
	public ArrayList<Cretaire> GetCreatereSansMere() {
		ArrayList<Cretaire> Cre= new ArrayList<Cretaire>();
		Model model = openFile();
		//bl manus adik elimina les cretair lieandhm mere
		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"			\r\n" + 
				"  SELECT DISTINCT ?y   ?NomCreatair ?Descreption  \r\n" + 
				"				WHERE {\r\n" + 
				"				?x    fin:NomModele  ?y.\r\n" + 
				"				 OPTIONAL{?x   fin:SeComposeDE ?Cretaire.\r\n" + 
				"                ?Cretaire  rdf:type fin:Critére.       \r\n" + 
				"			MINUS{     ?Dimonsion fin:MèreDE ?Cretaire. }\r\n" + 
				" ?Cretaire fin:NomDimension ?NomCreatair.}   } ";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
				Cretaire a=new Cretaire();
				QuerySolution soln = results.nextSolution();
	
	           //le nom 
				
				Literal cretaire=soln.getLiteral("NomCreatair");
				String nomA;
				//pour nom
				if(cretaire !=null) {
					nomA =cretaire.getString();
				}else {
					nomA ="pas de cretaire pour cette models ";
				}
				
				//le resume
				
				Literal enonce=soln.getLiteral("y");
				String prenoma;
				//pour test le modelnn nule
				if(enonce !=null) {
					prenoma =enonce.getString();
				}else {
					prenoma =" Le modéle de cette cretaire  ";
				}
				

				//le resume
				
				Literal Descreption=soln.getLiteral("Descreption");
				String DescreptionC;
				//pour test le modelnn nule
				if(Descreption !=null) {
					DescreptionC =enonce.getString();
				}else {
					DescreptionC =" Le modéle de cette cretaire  ";
				}
				
				a.setNomC(nomA);
				a.setNomM(prenoma);
				a.setDescreptionC(DescreptionC);
				Cre.add(a);	
			}}finally {
				qexec.close();
			}
		return Cre;

	}

	@Override
	public ArrayList<Dimonsion> GetDimHSD() {
		ArrayList<Dimonsion> DIMPERE= new ArrayList<Dimonsion>();
		
		//open file

		Model model =openFile();
		//bl manus adik elimina les cretair lieandhm mere
		String queryString = "PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"				SELECT      DISTINCT  ?dim ?descreption   \r\n" + 
				"				WHERE {\r\n" + 
				"			  OPTIONAL{ 	 ?x    fin:NomDimension  ?dim.   OPTIONAL{ ?x    fin:DescreptionDimension  ?descreption. }     \r\n" + 
				"             ?x fin:MèreDE ?sousDim.}\r\n" + 				           
				"}";			
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		try {
			ResultSet results = qexec.execSelect();		
			while(results.hasNext()) {
				Dimonsion a=new Dimonsion();
				QuerySolution soln = results.nextSolution();	
	           //le nom 				
				Literal cretaire=soln.getLiteral("dim");
				String nomA;
				//pour nom
				if(cretaire !=null) {
					nomA =cretaire.getString();
				}else {
					nomA ="";
				}				
				//le resume
				Literal descreption=soln.getLiteral("descreption");
				String descreptionD;
				//pour nom
				if(descreption !=null) {
					descreptionD =descreption.getString();
				}else {
					descreptionD ="";
				}				
				a.setNom(nomA);
				a.setDescreption(descreptionD);
				DIMPERE.add(a);	
			}}finally {
				qexec.close();
			}
		return DIMPERE;
	}

	@Override
	public ArrayList<Qstionne> GetQestByCre(String nomCre) {
		ArrayList<Qstionne> Cre= new ArrayList<Qstionne>();
		
		//open file

		Model model = openFile();
		//bl manus adik elimina les cretair lieandhm mere
		String queryString = "PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"				SELECT DISTINCT ?Cretaire   ?EnonceQuestion \r\n" + 
				"				WHERE {\r\n" + 
				"				 ?x    fin:NomDimension  \""+nomCre+"\".\r\n" + 
				"				OPTIONAL{?x   fin:SeMesurePar ?Question.\r\n" + 
				"				          ?Question fin:EnnonceDeQuestion ?EnonceQuestion.}\r\n" +  
				"	 }\r\n" + 
				" ";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
				Qstionne a=new Qstionne();
				QuerySolution soln = results.nextSolution();
	
	           //le nom 
				
				Literal cretaire=soln.getLiteral("Cretaire");
				String nomA;
				//pour nom
				if(cretaire !=null) {
					nomA =cretaire.getString();
				}else {
					nomA ="";
				}
				
				//le resume
				
				Literal enonce=soln.getLiteral("EnonceQuestion");
				String prenoma;
				//pour test le modelnn nule
				if(enonce !=null) {
					prenoma =enonce.getString();
				}else {
					prenoma ="";
				}
				
				
				a.setCretaire(nomCre);
				a.setQest(prenoma);
				Cre.add(a);	
			}}finally {
				qexec.close();
			}
		return Cre;
	}
	public ArrayList<Dimonsion> GetSubDim(String mere) {
		Model model = openFile();			
		String typeDiM="Dimonsion";
		
		String dimmer=new String(mere);
		ArrayList<Dimonsion> DIMPERE= new ArrayList<Dimonsion>();//returne
	    ArrayList<String> typesDim= new ArrayList<String>();
	    ArrayList<String> DimTmp= new ArrayList<String>();
	    typesDim.add(typeDiM);
	    DimTmp.add(dimmer);
		 //getand put in i th liste
	for(int i =0;i<typesDim.size();i++) {
		if(typesDim.get(i).equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Critére")) {
			//rana f creatir sma lh9na dim lkhra 
			
		}else {
	

				//bl manus adik elimina les cretair lieandhm mere
				String queryString = "PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#> \r\n" + 
						"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>	\r\n" + 
						"				SELECT  DISTINCT  ?NomSousDim  ?descreption  ?type\r\n" + 
						"							WHERE {\r\n" + 
						"							OPTIONAL{ ?x    fin:NomDimension  ?NomSousDim.   OPTIONAL{ ?x    fin:DescreptionDimension  ?descreption. }   \r\n" + 
						"							\r\n" + 
						"			              ?y fin:MèreDE ?x. \r\n" + 
						"			              ?y fin:NomDimension \""+DimTmp.get(i)+"\".\r\n" + 
						"			               ?x rdf:type ?type          \r\n" + 
						"			  }	}";
						
				Query query =QueryFactory.create(queryString);
				QueryExecution qexec = QueryExecutionFactory.create(query,model);
				try {
					ResultSet results = qexec.execSelect();		
					while(results.hasNext()) {
						Dimonsion a=new Dimonsion();
						QuerySolution soln = results.nextSolution();	
			           //le nom de sous dimonsion 				
						Literal NOmSOUS=soln.getLiteral("NomSousDim");
						String nomA;
						//pour nom
						if(NOmSOUS !=null) {
							nomA =NOmSOUS.getString();
						}else {
							nomA ="";
						}				
						//la descreption des sous dimonsion
						Literal descreption=soln.getLiteral("descreption");
						String descreptionD;
						//pour nom
						if(descreption !=null) {
							descreptionD =descreption.getString();
						}else {
							descreptionD ="";
						}	
						//le resume
						RDFNode types=soln.get("type");
						
						String typess;
						//pour nom
						if(types !=null) {
							typess =types.toString();
						}else {
							typess ="";
						}	
						
						if(typess.equals("http://www.w3.org/2002/07/owl#NamedIndividual")) {
							
						}else {

							////remplir hadok les seu liste
							typesDim.remove(0);
							DimTmp.remove(0);
							typesDim.add(typess);
							DimTmp.add(nomA);
							
							
							//dimonsion lireturniha nhtha biha bmamaha 
							a.setNommere(DimTmp.get(i));
							a.setNom(nomA);
							a.setDescreption(descreptionD);
							DIMPERE.add(a);	
						}
						
					}}finally {
						qexec.close();
					}
		
		}
	}
		return DIMPERE;

		}

	public void UpdateRefernce(String Refernce,String refernceOld) {
		Model model = openFile();	

		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ " PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>"
				+ "DELETE{"
				+ "?dimon fin:Réference \""+refernceOld+"\"."
				+ "}"
				+ "INSERT{"
				+ "?dimon fin:Réference \""+Refernce+"\"."
				+ "}"
				+ "WHERE{"
				+ "?dimon fin:Réference \""+refernceOld+"\"."
				+ "}";
		
		UpdateRequest queryA =UpdateFactory.create(queryString);
		UpdateProcessor proocessor= UpdateExecutionFactory.create(queryA,GraphStoreFactory.create(model));
		proocessor.execute();
	System.out.println("update resset");
		try {
			model.write(new  FileOutputStream("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
	}
	@Override
	public void UpdateModel(String nom, String descreption, String oldNom, String odldescreption) {
		// TODO Auto-generated method stub
		Model model = openFile();	

		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ " PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>"
				+ "DELETE{"
				+ "?dimon fin:NomModele \""+oldNom+"\"."
				+ "?dimon fin:DescreptionDuModule \""+odldescreption+"\".}"
				+ "INSERT{"
				+ "?dimon fin:NomModele \""+nom+"\"."
				+ "?dimon fin:DescreptionDuModule \""+descreption+"\"."
				+ "}"
				+ "WHERE{?dimon rdf:type fin:Modéle. "
				+ "?dimon fin:NomModele \""+oldNom+"\"."
				+ "?dimon fin:DescreptionDuModule \""+odldescreption+"\".}";
		
		UpdateRequest queryA =UpdateFactory.create(queryString);
		UpdateProcessor proocessor= UpdateExecutionFactory.create(queryA,GraphStoreFactory.create(model));
		proocessor.execute();
	System.out.println("update resset");
		try {
			model.write(new  FileOutputStream("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

	@Override
	public void UpdateArticle(String Title, String resume, String mcle, String url, String ISSn, String doi,
			String Titleold, String resumeold, String mcleold, String urlOld, String ISSnold, String doiold) {
		// TODO Auto-generated method stub
		Model model = openFile();	

		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ " PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>"
				+ "DELETE{"
				+ "?article fin:TitreDeLarticle \""+Titleold+"\"."
				+ "?article fin:Resume \""+resumeold+"\"."
				+ "?article fin:DOI \""+doiold+"\"."
				+ "?article fin:MotCle \""+mcleold+"\"."
				+ "?article fin:URL \""+urlOld+"\"."
				+ "?article fin:ISSN \""+ISSnold+"\""
				+ "}"
				+ "INSERT{"
				+ "?article fin:TitreDeLarticle \""+Title+"\"."
				+ "?article fin:Resume \""+resume+"\"."
				+ "?article fin:DOI \""+doi+"\"."
				+ "?article fin:MotCle \""+mcle+"\"."
				+ "?article fin:ISSN \""+ISSn+"\""
				+ "?article fin:URL \""+url+"\".}"
				
				+ "WHERE{?article rdf:type fin:Article. "
				+ "?article fin:TitreDeLarticle \""+Titleold+"\"."
				+ "?article fin:Resume \""+resumeold+"\"."
				+ "?article fin:DOI \""+doiold+"\"."
				+ "OPTIONAL{?article fin:MotCle \""+mcleold+"\".}"
				+ "OPTIONAL{?article fin:URL \""+urlOld+"\".}"
				+ "OPTIONAL{?article fin:ISSN\""+ISSnold+"\".}"
						+ "}";
		
		

		
				
		UpdateRequest queryA =UpdateFactory.create(queryString);
		UpdateProcessor proocessor= UpdateExecutionFactory.create(queryA,GraphStoreFactory.create(model));
		proocessor.execute();
	
		try {
			model.write(new  FileOutputStream("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

	@Override
	public void UpdateAuteru(String nom, String prenom, String universite, String info, String dan, String datnold,String nomold, String prenomold,
			String universiteold, String infoold) {
		// TODO Auto-generated method stub
		Model model = openFile();	
		
		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ " PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>"
				+ "DELETE{"
				+ "?Auteru fin:Nom \""+nomold+"\"."
				+ "?Auteru fin:Prenom \""+prenomold+"\"."
				+ "?Auteru fin:DateDeNaissance \""+datnold+"\"."
				+ "?Auteru fin:Position \""+infoold+"\"."
				+ "?Auteru fin:Université \""+universiteold+"\".}"

				+ "INSERT{"
				+ "?Auteru fin:Nom \""+nom+"\"."
				+ "?Auteru fin:Prenom \""+prenom+"\"."
				+ "?Auteru fin:DateDeNaissance \""+dan+"\"."
				+ "?Auteru fin:Position \""+info+"\"."
				+ "?Auteru fin:Université \""+universite+"\".}"
				
				+ "WHERE{?Auteru rdf:type fin:Auteur. "
				+ "?Auteru fin:Nom \""+nomold+"\"."
				+ "?Auteru fin:Prenom \""+prenomold+"\"."
				+ "OPTIONAL{?Auteru fin:DateDeNaissance \""+datnold+"\".}"
				+ "OPTIONAL{?Auteru fin:Position \""+infoold+"\".}"		
				+ "OPTIONAL{?Auteru fin:Université \""+universiteold+"\".}}";
		
		
		
		System.out.println("update auteru nom "+nom+" Old nom  "+nomold);
		System.out.println("update auteru Prenom "+prenom+" Old prenom  "+prenomold);
		System.out.println("update auteru universite "+universite+" Old universite  "+universiteold);
		
		UpdateRequest queryA =UpdateFactory.create(queryString);
		
		UpdateProcessor proocessor= UpdateExecutionFactory.create(queryA,GraphStoreFactory.create(model));
		proocessor.execute();
	
		try {
			model.write(new  FileOutputStream("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public void UpdateDimonsion(String nomDim, String Descreptiondimonsion, String mereDimonsion, String type,
			String nomDimold, String Descreptiondimonsionold, String mereDimonsionold, String typeold) {
		// TODO Auto-generated method stub

        Model model = openFile();	
		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ " PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>"
				+ "DELETE{"
				+ "?Dimonsion fin:NomDimension \""+nomDimold+"\"."
				+ "?Dimonsion fin:DescreptionDimension \""+Descreptiondimonsionold+"\"."
				+ "?Mere fin:NomDimension \""+mereDimonsionold+"\"."
				+ "}"

				+ "INSERT{ "
				+ "?Dimonsion fin:NomDimension \""+nomDim+"\"."
				+ "?Dimonsion fin:DescreptionDimension \""+Descreptiondimonsion+"\"."
				+ "?Mere fin:NomDimension \""+mereDimonsion+"\".}"
				
				+ "WHERE{ "
				+ "?Dimonsion fin:NomDimension \""+nomDimold+"\"."
				+ "OPTIONAL{?Dimonsion fin:DescreptionDimension \""+Descreptiondimonsionold+"\".}"
				+ "OPTIONAL{?Mere fin:MèreDE ?Dimonsion."
				+ " ?Mere fin:NomDimension \""+mereDimonsionold+"\".}}";
	
		UpdateRequest queryA =UpdateFactory.create(queryString);
		
		UpdateProcessor proocessor= UpdateExecutionFactory.create(queryA,GraphStoreFactory.create(model));
		proocessor.execute();
	System.out.println("update dimonsion new nom "+nomDim);
	System.out.println("update dimonsion new nom "+nomDimold);
	System.out.println("update dimonsion new type "+type);
	System.out.println("update dimonsion old type "+typeold);
		try {
			model.write(new  FileOutputStream("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	
		
	}

	@Override
	public void UpdateQeustion(String qeustion, String cretaire, String qeustionold, String cretaireold) {
		// TODO Auto-generated method stub
		  Model model = openFile();
	
			String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ " PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>"
					+ "DELETE{"
					+ " ?Question fin:EnnonceDeQuestion \""+qeustionold+"\"."
					+ "?x    fin:NomDimension \""+cretaireold+"\"."
					+ "}"

					+ "INSERT{"
					+ " ?Question fin:EnnonceDeQuestion \""+qeustion+"\"."
					+ "?x    fin:NomDimension \""+cretaire+"\"."
					+ "}"
					
					+ "WHERE{?Question rdf:type fin:Question. "
					+ " ?Question fin:EnnonceDeQuestion \""+qeustionold+"\"."
					+ " ?x   fin:SeMesurePar ?Question. ?x    fin:NomDimension \""+cretaireold+"\"."
					+ "}";
		
			UpdateRequest queryA =UpdateFactory.create(queryString);
			
			UpdateProcessor proocessor= UpdateExecutionFactory.create(queryA,GraphStoreFactory.create(model));
			proocessor.execute();
		
			try {
				model.write(new  FileOutputStream("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}

	@Override
	public void UpdateHypothses(String hypothese, String idep, String dep, String hypotheseold, String idepold,
			String depold) {
		// TODO Auto-generated method stub
		  Model model = openFile();

					String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
							+ " PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>"
							+ "DELETE{"
							+ "?h fin:EnonceHypothes  \""+hypotheseold+"\"."
							+ "?DIM1 fin:NomDimension \""+idepold+"\"."
					    	+ "?DIM2 fin:NomDimension \""+depold+"\"."
							+ "  }"
							+ "INSERT{"
							+ " ?h fin:EnonceHypothes  \""+hypothese+"\"."
							+ "?DIM1 fin:NomDimension \""+idep+"\"."
					    	+ "?DIM2 fin:NomDimension \""+dep+"\"."
							+ "  }"	
							+ "WHERE{?h rdf:type fin:Hypothese. "
							+ "?h fin:EnonceHypothes  \""+hypotheseold+"\"."
							+ "?DIM1 fin:NomDimension \""+idepold+"\"."
					    	+ "?DIM2 fin:NomDimension \""+depold+"\"."
							+ "  OPTIONAL{ ?DIM1 fin:influenceDans ?h.}OPTIONAL{  ?DIM2 fin:InfluenseuseDans ?h.}"
							+ "}";
				
					UpdateRequest queryA =UpdateFactory.create(queryString);
					
					UpdateProcessor proocessor= UpdateExecutionFactory.create(queryA,GraphStoreFactory.create(model));
					proocessor.execute();
				
					try {
						model.write(new  FileOutputStream("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	}

	@Override
	public void UpdateChoix(String choix, String qeustion, String choixold, String qeustionold) {
		// TODO Auto-generated method stub
		  Model model = openFile();

		  
					String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
							+ " PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>"
							+ "DELETE{"
							+ "?qeustion fin:EnnonceDeQuestion \""+qeustionold+"\"."
							+ "?choix    fin:Choix \""+choixold+"\"."
							+ "}"

							+ "INSERT{"
							+ " ?qeustion fin:EnnonceDeQuestion \""+qeustion+"\"."
							+ "?choix    fin:Choix \""+choix+"\"."
							+ "}"
							
							+ "WHERE{"
							+ "?Qeustion fin:EnnonceDeQuestion \""+qeustionold+"\"."
							+ " ?Qeustion fin:ChoixDe  ?choix. ?choix    fin:Choix \""+choixold+"\"."
							+ "}";
				
					UpdateRequest queryA =UpdateFactory.create(queryString);
					System.out.println("new "+choix);
					UpdateProcessor proocessor= UpdateExecutionFactory.create(queryA,GraphStoreFactory.create(model));
					proocessor.execute();
				
					try {
						model.write(new  FileOutputStream("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	}

	@Override
	public void UpdateModerateur(String moderateur, String hypothes, String moderateurold, String HypothesOld) {
		// TODO Auto-generated method stub
		  Model model = openFile();
					String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
							+ " PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>"
							+ "DELETE{"
							+ "?modertaur fin:NomModerateur \""+moderateurold+"\"."
							+ "?h fin:EnonceHypothes \""+HypothesOld+"\"."
							+ "}"

							+ "INSERT{"
							+ "?modertaur fin:NomModerateur \""+moderateur+"\"."
							+ "?h fin:EnonceHypothes \""+hypothes+"\"."
							+ "}"
							
							+ "WHERE{?modertaur rdf:type fin:Modérateur."
							+ "?h fin:ModerePar ?modertaur. "
							+ "?modertaur fin:NomModerateur \""+moderateurold+"\"."
							+ "?h fin:EnonceHypothes \""+HypothesOld+"\"."
							+ "}";
				
					UpdateRequest queryA =UpdateFactory.create(queryString);
					
					UpdateProcessor proocessor= UpdateExecutionFactory.create(queryA,GraphStoreFactory.create(model));
					proocessor.execute();
				
					try {
						model.write(new  FileOutputStream("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	}

	@Override
	public void delet(String id) {
		// TODO Auto-generated method stub
		  Model model = openFile();
	String queryString = ""
					+ "PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>"
					+ "DELETE { ?s ?p ?o}WHERE{ "
					+ "?s ?p ?o."
					+ "FILTER regex (str(?s),'"+id+"',\"i\")  "
					+ " }"
					;
		
			UpdateRequest queryA =UpdateFactory.create(queryString);
			
			UpdateProcessor proocessor= UpdateExecutionFactory.create(queryA,GraphStoreFactory.create(model));
			proocessor.execute();
		System.out.println("delet elemnt with id   "+id);
			try {
				model.write(new  FileOutputStream("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public int nbrmodel() {
		Model model = openFile();	 int nbr=0;
		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"SELECT (COUNT(*)AS ?nbrcre)  \r\n" + 
				"WHERE {?subject rdf:type fin:Modéle.\r\n" + 
				"  \r\n" + 
				" }";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		System.out.println("exeution de requet");
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
			
				QuerySolution soln = results.nextSolution();
				Literal nbrcre=soln.getLiteral("nbrcre");
				if(nbrcre !=null) {
					 nbr =nbrcre.getInt();
				}else {
					 nbr =0;	
				}
			
			
			}}finally {
			qexec.close();
		}
		
		return nbr;
	}

	@Override
	public int nbrarticle() {
		Model model = openFile();	 int nbr=0;
		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"SELECT (COUNT(*)AS ?nbrcre)  \r\n" + 
				"WHERE {?subject rdf:type fin:Article.\r\n" + 
				"  \r\n" + 
				" }";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		System.out.println("exeution de requet");
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {			
				QuerySolution soln = results.nextSolution();
				Literal nbrcre=soln.getLiteral("nbrcre");
				if(nbrcre !=null) {
					 nbr =nbrcre.getInt();
				}else {
					 nbr =0;	
				}
			
			
			}}finally {
			qexec.close();
		}
		
		return nbr;
	}

	@Override
	public int nbrauteru() {
		Model model = openFile();	 int nbr=0;
		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"SELECT (COUNT(*)AS ?nbrcre)  \r\n" + 
				"WHERE {?subject rdf:type fin:Auteur.\r\n" + 
				"  \r\n" + 
				" }";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		System.out.println("exeution de requet");
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
			
				QuerySolution soln = results.nextSolution();
				Literal nbrcre=soln.getLiteral("nbrcre");
				if(nbrcre !=null) {
					 nbr =nbrcre.getInt();
				}else {
					 nbr =0;	
				}
			
			
			}}finally {
			qexec.close();
		}
		
		return nbr;
	}

	@Override
	public int nbrdimonsion() {
		Model model = openFile();	 int nbr=0;
		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"SELECT (COUNT(*)AS ?nbrcre)  \r\n" + 
				"WHERE {?subject rdf:type fin:Dimension.\r\n" + 
				"  \r\n" + 
				" }";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
			
				QuerySolution soln = results.nextSolution();
				Literal nbrcre=soln.getLiteral("nbrcre");
				if(nbrcre !=null) {
					 nbr =nbrcre.getInt();
				}else {
					 nbr =0;	
				}
			
			
			}}finally {
			qexec.close();
		}
		
		return nbr;
		
	}

	public int nbrhypothses() {
		Model model = openFile();	 int nbr=0;
		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"SELECT (COUNT(*)AS ?nbrcre)  \r\n" + 
				"WHERE {?subject rdf:type fin:Hypothese.\r\n" + 
				"  \r\n" + 
				" }";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
			
				QuerySolution soln = results.nextSolution();
				Literal nbrcre=soln.getLiteral("nbrcre");
				if(nbrcre !=null) {
					 nbr =nbrcre.getInt();
				}else {
					 nbr =0;	
				}
			
			
			}}finally {
			qexec.close();
		}
		
		return nbr;
	}
	@Override
	public int nbrqestion() {
		Model model = openFile();	 int nbr=0;
		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"SELECT (COUNT(*)AS ?nbrcre)  \r\n" + 
				"WHERE {?subject rdf:type fin:Question.\r\n" + 
				"  \r\n" + 
				" }";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
			
				QuerySolution soln = results.nextSolution();
				Literal nbrcre=soln.getLiteral("nbrcre");
				if(nbrcre !=null) {
					 nbr =nbrcre.getInt();
				}else {
					 nbr =0;	
				}
			
			
			}}finally {
			qexec.close();
		}
		
		return nbr;
	}

	@Override
	public int nbrchoix() {
		Model model = openFile();	 int nbr=0;
		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"SELECT (COUNT(*)AS ?nbrcre)  \r\n" + 
				"WHERE {?subject rdf:type fin:Choix.\r\n" + 
				"  \r\n" + 
				" }";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
	
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
			
				QuerySolution soln = results.nextSolution();
				Literal nbrcre=soln.getLiteral("nbrcre");
				if(nbrcre !=null) {
					 nbr =nbrcre.getInt();
				}else {
					 nbr =0;	
				}
			
			
			}}finally {
			qexec.close();
		}
		
		return nbr;
	}

	@Override
	public int nbrmoderateur() {
		Model model = openFile();	 int nbr=0;
		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"SELECT (COUNT(*)AS ?nbrcre)  \r\n" + 
				"WHERE {?subject rdf:type fin:Modérateur.\r\n" + 
				"  \r\n" + 
				" }";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);

		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
			
				QuerySolution soln = results.nextSolution();
				Literal nbrcre=soln.getLiteral("nbrcre");
				if(nbrcre !=null) {
					 nbr =nbrcre.getInt();
				}else {
					 nbr =0;	
				}
			
			
			}}finally {
			qexec.close();
		}
		
		return nbr;
	}

	@Override
	public int nbrCretaire() {
		Model model = openFile();	 int nbr=0;
		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"SELECT (COUNT(*)AS ?nbrcre)  \r\n" + 
				"WHERE {?subject rdf:type fin:Critére.\r\n" + 
				"  \r\n" + 
				" }";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
			
				QuerySolution soln = results.nextSolution();
				Literal nbrcre=soln.getLiteral("nbrcre");
				if(nbrcre !=null) {
					 nbr =nbrcre.getInt();
				}else {
					 nbr =0;	
				}
			
			
			}}finally {
			qexec.close();
		}
		
		return nbr;
	}
 public int nbrh() {
		Model model = openFile();	 int nbr=0;
		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"SELECT (COUNT(*)AS ?nbrcre)  \r\n" + 
				"WHERE {?subject rdf:type fin:RéferenceBibloigraphique.\r\n" + 
				"  \r\n" + 
				" }";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
			
				QuerySolution soln = results.nextSolution();
				Literal nbrcre=soln.getLiteral("nbrcre");
				if(nbrcre !=null) {
					 nbr =nbrcre.getInt();
				}else {
					 nbr =0;	
				}
			
			
			}}finally {
			qexec.close();
		}
		
		return nbr;
 }

/////////////////////////////////////////
	public void addref(String ref,String id) {
		Model model = openFile();	
		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>"
				+ "INSERT{<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">  rdf:type fin:RéferenceBibloigraphique. "
				+ " <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+"> fin:NomModele \""+ref+"\"."
				
				+ "}WHERE{}";
		UpdateRequest queryA =UpdateFactory.create(queryString);
		UpdateProcessor proocessor= UpdateExecutionFactory.create(queryA,GraphStoreFactory.create(model));
		proocessor.execute();
	
		try {
			model.write(new  FileOutputStream("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@Override
	public void AddModel(String nomModel,String descreptionModel,String id) {

	
	Model model = openFile();	
		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>"
				+ "INSERT{<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">  rdf:type fin:Modéle. "
				+ " <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+"> fin:NomModele \""+nomModel+"\"."
				+ "<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+"> fin:DescreptionDuModule \""+descreptionModel+"\"."
				+ "}WHERE{}";
		UpdateRequest queryA =UpdateFactory.create(queryString);
		UpdateProcessor proocessor= UpdateExecutionFactory.create(queryA,GraphStoreFactory.create(model));
		proocessor.execute();
	
		try {
			model.write(new  FileOutputStream("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

	@Override
	public void addArticle(String Title,String resume,String mcle,String url, String ISSn,String doi,String modelN,String id) {
		Model model = openFile();	
	
		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>"
				+ "INSERT{<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">  rdf:type fin:Article."
				+ "<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+"> fin:TitreDeLarticle \""+Title+"\"."
				+ "<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+"> fin:Resume \""+resume+"\"."
				+ "<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+"> fin:DOI \""+doi+"\"."
				+ "<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+"> fin:MotCle \""+mcle+"\"."
				+ "<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+"> fin:URL \""+url+"\"."
				+ "?x   fin:trouverDans <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">."
				+ "}WHERE{?x    fin:NomModele  \""+modelN+"\"."
				+ ""
				+ "}";
		UpdateRequest queryA =UpdateFactory.create(queryString);
		UpdateProcessor proocessor= UpdateExecutionFactory.create(queryA,GraphStoreFactory.create(model));
		proocessor.execute();
		
		try {
			model.write(new  FileOutputStream("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public void addAuteru(String nom,String prenom,String universite,String info,String modelM,String id) {
		Model model = openFile();	

		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>"
				+ "INSERT{<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">  rdf:type fin:Auteur."
				+ "<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+"> fin:Nom \""+nom+"\"."
				+ "<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+"> fin:Prenom \""+prenom+"\"."
				+ "<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+"> fin:Université \""+universite+"\"."
			    + "<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+"> fin:Ecrire ?Article."
				+ "}WHERE{?x    fin:NomModele  \""+modelM+"\"."
				+ "        ?x   fin:trouverDans ?Article."			
				+ "}";
		UpdateRequest queryA =UpdateFactory.create(queryString);
		UpdateProcessor proocessor= UpdateExecutionFactory.create(queryA,GraphStoreFactory.create(model));
		proocessor.execute();
		System.out.println("Ajouter auteru");
		try {
			model.write(new  FileOutputStream("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

	@Override
	public void addDimonsion(String nomDim,String Descreptiondimonsion,String mereDimonsion,String type,String typrdf,String modelM,String id) {
		Model model = openFile();	
		if(typrdf.equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Dimension")) {
		
			String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>"
					+ "INSERT{"
					+ "<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">  rdf:type fin:Dimension."
					+ "<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+"> fin:NomDimension \""+nomDim+"\"."
					+ "<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+"> fin:DescreptionDimension \""+Descreptiondimonsion+"\"."
				    + "?x   fin:SeComposeDE <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">."
				    + "?dim fin:MèreDE <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">. "
					+ "}WHERE{"
					+ "?x   fin:NomModele \""+modelM+"\"."
					+ "OPTIONAL{?x fin:SeComposeDE ?dim. "
					+ "?dim fin:NomDimension  \""+mereDimonsion+"\".}"
					+ "}";

			UpdateRequest queryA =UpdateFactory.create(queryString);
			UpdateProcessor proocessor= UpdateExecutionFactory.create(queryA,GraphStoreFactory.create(model));
			proocessor.execute();
			System.out.println("Ajouter dimonsion");
			try {
				model.write(new  FileOutputStream("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else {
			System.out.println("les cretaire ");

					String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>"
							+ "INSERT{"
							+ "<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">  rdf:type fin:Critére."
							+ "<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+"> fin:NomDimension \""+nomDim+"\"."
							+ "<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+"> fin:DescreptionDimension \""+Descreptiondimonsion+"\"."
						    + "?x   fin:SeComposeDE <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">."
						    + "?dim fin:MèreDE <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">. "
							+ "}WHERE{"
							+ "?x   fin:NomModele \""+modelM+"\"."
							+ "OPTIONAL{?x fin:SeComposeDE ?dim. "
							+ "?dim fin:NomDimension  \""+mereDimonsion+"\".}"
							+ "}";

			UpdateRequest queryA =UpdateFactory.create(queryString);
			UpdateProcessor proocessor= UpdateExecutionFactory.create(queryA,GraphStoreFactory.create(model));
			proocessor.execute();
		
			try {
				model.write(new  FileOutputStream("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
			
	}

@Override
public void addQeustion(String enonceqestion, String cretaire, String modelM, String id) {
	// TODO Auto-generated method stub
	Model model = openFile();	

	String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>"
			+ "INSERT{<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">  rdf:type fin:Question."
			+ "<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+"> fin:EnnonceDeQuestion \""+enonceqestion+"\"."
		    + " ?Cretaire fin:SeMesurePar <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">."
			+ "}WHERE{?x    fin:NomModele  \""+modelM+"\"."
			+ " ?x   fin:SeComposeDE ?Cretaire.  ?Cretaire fin:NomDimension \""+cretaire+"\". "			
			+ "}";
	UpdateRequest queryA =UpdateFactory.create(queryString);
	UpdateProcessor proocessor= UpdateExecutionFactory.create(queryA,GraphStoreFactory.create(model));
	proocessor.execute();

	try {
		model.write(new  FileOutputStream("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf"));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}

@Override
public void addHypothses(String hypothses, String dimind, String dimdep, String modelM, String id) {
	// TODO Auto-generated method stub
	Model model = openFile();	
	String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>"
			+ "INSERT{<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">  rdf:type fin:Hypothese."
			+ "<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+"> fin:EnonceHypothes \""+hypothses+"\"."
		    + "?dim1  fin:influenceDans  <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">. "
		    + " ?dim2 fin:InfluenseuseDans <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">. "
			+ "}WHERE{?x    fin:NomModele  \""+modelM+"\"."
			+ "OPTIONAL{ ?x   fin:SeComposeDE ?dim1.   ?dim1 fin:NomDimension \""+dimind+"\".}"
			+ "OPTIONAL{ ?x   fin:SeComposeDE ?dim2.   ?dim2  fin:NomDimension \""+dimdep+"\".}"			
			+ "}";


	
	
	UpdateRequest queryA =UpdateFactory.create(queryString);
	UpdateProcessor proocessor= UpdateExecutionFactory.create(queryA,GraphStoreFactory.create(model));
	proocessor.execute();

	try {
		model.write(new  FileOutputStream("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf"));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}

@Override
public void addChoix(String choix, String qeustion, String modelM, String id) {
	Model model = openFile();	

	String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>"
			+ "INSERT{<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">  rdf:type fin:Choix."
			+ "<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#\"+id+\"> fin:Choix  \""+choix+"\"."
		    + "?qstion  fin:ChoixDe  <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">."
			+ "}WHERE{?x    fin:NomModele  \""+modelM+"\"."
			+ "        ?x   fin:SeComposeDE ?dim1.  ?dim1   fin:SeMesurePar ?qstion.  "
			+ "?qstion fin:EnnonceDeQuestion  \""+qeustion+"\"."		
			+ "}";
	
	UpdateRequest queryA =UpdateFactory.create(queryString);
	UpdateProcessor proocessor= UpdateExecutionFactory.create(queryA,GraphStoreFactory.create(model));
	proocessor.execute();

	try {
		model.write(new  FileOutputStream("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf"));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	
}

@Override
public void addModerateur(String moderateur, String hypothses, String modelM, String id) {
	Model model = openFile();	

	String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>"
			+ "INSERT{"
			+ "<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">  rdf:type fin:Modérateur."
			+ "<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+"> fin:NomModerateur \""+moderateur+"\"."
			+ " ?hypo fin:ModerePar <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id+">."
			+ "}WHERE{"
			+ "?x    fin:NomModele  \""+modelM+"\"."
			+ "  "
		    + "OPTIONAL{ ?x   fin:SeComposeDE ?dim1.  "
		    + "?x   fin:SeComposeDE ?dim2. }"
		    + "?dim1  fin:influenceDans  ?hypo. "
		    + "OPTIONAL{ ?dim2 fin:InfluenseuseDans ?hypo. "
		    + "?hypo fin:EnonceHypothes \""+hypothses+"\". } "		
			+ "}";
	
	
	
	UpdateRequest queryA =UpdateFactory.create(queryString);
	UpdateProcessor proocessor= UpdateExecutionFactory.create(queryA,GraphStoreFactory.create(model));
	proocessor.execute();

	try {
		model.write(new  FileOutputStream("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf"));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	
}
}