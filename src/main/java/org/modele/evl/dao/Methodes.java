package org.modele.evl.dao;
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
import org.apache.jena.util.FileManager;
import org.modele.model.Article;
import org.modele.model.Auteur;
import org.modele.model.Cretaire;
import org.modele.model.Dimonsion;
import org.modele.model.Form;
import org.modele.model.Qstionne;
public class Methodes {
	public ArrayList<Form>  getNomModel() {
	
		ArrayList<Form> result = new ArrayList<Form>();
		Model model = ModelFactory.createDefaultModel();
		InputStream in =FileManager.get().open("C:\\Users\\User\\Spring\\FIN\\src\\main\\java\\org\\modele\\evl\\fin.rdf");
		System.out.println("openfill");
		if(in == null) {
			throw new IllegalArgumentException("File : not found");
		}
		model.read(in,null);
	//	model.write(System.out);	
		String queryString = "PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"SELECT ?subject  ?descreption ?nom\r\n" + 
				"	WHERE {    ?subject fin:NomModele ?nom.\r\n" + 
				"                              OPTIONAL{             ?subject fin:DescreptionDuModule ?descreption.}\r\n" + 
				" }";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		System.out.println("exeution de requet");
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
				Form tmp=new Form();
				String desc;String nom;
				QuerySolution soln = results.nextSolution();
				Literal descreption=soln.getLiteral("descreption");;
				//pour test le modelnn nule
				if(descreption !=null) {
					 desc =descreption.getString();
				}else {
					 desc ="pas de descreption pour cette models ";	
				}
			
				String idM;
				String id =soln.get("subject").toString();
				if(id !=null) {
					 idM= id.substring(58);
				}else {
					 idM ="pas de nomp pour cette models ";
				}
				
				
				Literal name =soln.getLiteral("nom");
				if(name !=null) {
					 nom= name.getString();	
				}else {
					 nom ="pas de nomp pour cette models ";
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
	
	
	public   Form  getNomModeleByID(String idmodel) {
		
		Form tmp=new Form();
		Model model = ModelFactory.createDefaultModel();
		InputStream in =FileManager.get().open("C:\\Users\\User\\Spring\\FIN\\src\\main\\java\\org\\modele\\evl\\fin.rdf");
		System.out.println("openfill");
		if(in == null) {
			throw new IllegalArgumentException("File : not found");
		}
		model.read(in,null);
	//	model.write(System.out);	
		String uri="<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+idmodel+">";
		String queryString = "PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"SELECT DISTINCT ?nom ?descreption\r\n" + 
				"WHERE {\r\n" + 
				"  ?x    fin:NomModele  ?nom.\r\n" + 
				"                 OPTIONAL{            ?x fin:DescreptionDuModule ?descreption.}\r\n" + 
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
					 desc ="pas de descreption pour cette models ";	
				}
				
				Literal name =soln.getLiteral("nom");
				if(name !=null) {
					 nom= name.getString();	
				}else {
					 nom ="pas de nomp pour cette models ";
				}	
					
				tmp.setExplain(desc);
				tmp.setnomModel(nom);			
			}}finally {
			qexec.close();
		}
		return tmp;
	}
	//get article

    //get qestion 
	
	public ArrayList<Qstionne> getQstionne(String idmodel) {
		ArrayList<Qstionne> qstions= new ArrayList<Qstionne>();
	
		//open file

		Model model = ModelFactory.createDefaultModel();
		InputStream in =FileManager.get().open("C:\\Users\\User\\Spring\\FIN\\src\\main\\java\\org\\modele\\evl\\fin.rdf");
		System.out.println("openfill");
		if(in == null) {
			throw new IllegalArgumentException("File : not found");
		}
		model.read(in,null);
		
		String uri="<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+idmodel+">";
		String queryString = "PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"SELECT DISTINCT ?enonce ?nomdim\r\n" + 
				"WHERE {\r\n" + 
				"  ?x    fin:NomModele  ?y.\r\n" + 
				"OPTIONAL{?x   fin:SeComposeDE ?dim.}\r\n" + 
				" OPTIONAL{?dim   fin:MèreDE ?cretaire.\r\n" + 
				"?cretaire fin:NomDimension ?nomdim.}\r\n" + 
				" OPTIONAL{?cretaire   fin:SeMesurePar ?Qestionn.\r\n" + 
				"?Qestionn   fin:EnnonceDeQuestion  ?enonce.}\r\n" + 
				"\r\n" + 
				"FILTER ( SameTerm(?x,<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Modele1>))\r\n" + 
				" }";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
				Qstionne a=new Qstionne();
				QuerySolution soln = results.nextSolution();
	
	           //le nom 
				
				Literal cretaire=soln.getLiteral("nomdim");
				String nomA;
				//pour nom
				if(cretaire !=null) {
					nomA =cretaire.getString();
				}else {
					nomA ="pas de cretaire pour cette models ";
				}
				
				//le resume
				
				Literal enonce=soln.getLiteral("enonce");
				String prenoma;
				//pour test le modelnn nule
				if(enonce !=null) {
					prenoma =enonce.getString();
				}else {
					prenoma ="pas de prenom pour cette models ";
				}
				
				a.setCretaire(nomA);
				a.setQest(prenoma);
				qstions.add(a);
			
			}}finally {
				qexec.close();
			}
		return qstions;

		}

//gest dim
	public ArrayList<Dimonsion> getDim(String idmodel) {
		ArrayList<Dimonsion> dimonsions= new ArrayList<Dimonsion>();
	
		//ope file
	
		Model model = ModelFactory.createDefaultModel();
		InputStream in =FileManager.get().open("C:\\Users\\User\\Spring\\FIN\\src\\main\\java\\org\\modele\\evl\\fin.rdf");
		System.out.println("openfill");
		if(in == null) {
			throw new IllegalArgumentException("File : not found");
		}
		model.read(in,null);
		
		String uri="<http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+idmodel+">";
		String queryString = "\r\n" + 
				"PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"SELECT DISTINCT   ?nomDim  \r\n" + 
				"WHERE {\r\n" + 
				"  ?x    fin:NomModele  ?y.\r\n" + 
				"OPTIONAL{?x   fin:SeComposeDE ?dim.}\r\n" + 
				" OPTIONAL{?dim   fin:MèreDE ?cretaire.\r\n" + 
				"?cretaire fin:NomDimension ?nomDim.}\r\n" + 
				"FILTER ( SameTerm(?x,"+uri+"))\r\n" + 
				" }GROUP BY  ?nomDim \r\n" + 
				"\r\n" + 
				"";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
				Dimonsion a=new Dimonsion();
				QuerySolution soln = results.nextSolution();
				//le ide de model 
				
	
	           //le nom 
				
				Literal nomDim=soln.getLiteral("nomDim");
				String dimnom;
				//pour nom
				if(nomDim !=null) {
					dimnom =nomDim.getString();
					System.out.println(dimnom);
				}else {
					dimnom ="pas de descreption pour cette models ";
				}
				
				a.setNom(dimnom);
				dimonsions.add(a);
	
	         //    auteurs.add(a);						
			//	System.out.println(l.substring(58));
		
			}}finally {
				qexec.close();
			}
		return dimonsions;

		}

	public ArrayList<Dimonsion> GetALLDimonsion() {
		ArrayList<Dimonsion> dimonsions= new ArrayList<Dimonsion>();
	
		Model model = ModelFactory.createDefaultModel();
		InputStream in =FileManager.get().open("C:\\Users\\User\\Spring\\FIN\\src\\main\\java\\org\\modele\\evl\\fin.rdf");
		System.out.println("openfill");
		if(in == null) {
			throw new IllegalArgumentException("File : not found");
		}
		model.read(in,null);

		String queryString = "\r\n" + 
				"PREFIX fin: <http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#>\r\n" + 
				"SELECT DISTINCT   ?nomDim  \r\n" + 
				"WHERE {\r\n" + 
				" OPTIONAL{?dim fin:SeComposeDE ?cretaire.\r\n" + 
				"?cretaire fin:NomDimension ?nomDim.}\r\n" + 
				" }GROUP BY  ?nomDim \r\n" + 
				"\r\n" + 
				"";
		Query query =QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,model);
		try {
			ResultSet results = qexec.execSelect();
		
			while(results.hasNext()) {
				Dimonsion a=new Dimonsion();
				QuerySolution soln = results.nextSolution();
				//le ide de model 
				
	
	           //le nom 
				
				Literal nomDim=soln.getLiteral("nomDim");
				String dimnom;
				//pour nom
				if(nomDim !=null) {
					dimnom =nomDim.getString();
					System.out.println(dimnom);
				}else {
					dimnom ="pas de descreption pour cette models ";
				}
				
				a.setNom(dimnom);
				dimonsions.add(a);
	
	         //    auteurs.add(a);						
			//	System.out.println(l.substring(58));
		
			}}finally {
				qexec.close();
			}
		return dimonsions;

		}
		
	////////////////////////////////////////////////////get cretaire sans mere
	public ArrayList<Cretaire> GetCreatereSansMere() {
		System.out.println("trouve tous lescretair");
		ArrayList<Cretaire> Cre= new ArrayList<Cretaire>();
	
		//open file

		Model model = ModelFactory.createDefaultModel();
		InputStream in =FileManager.get().open("C:\\Users\\User\\Spring\\FIN\\src\\main\\java\\org\\modele\\evl\\fin.rdf");
		System.out.println("openfill");
		if(in == null) {
			throw new IllegalArgumentException("File : not found");
		}
		model.read(in,null);
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
	
	////////////////////////////////////////////////////get qeustion par cretaire
	public ArrayList<Qstionne> GetQestByCre(String nomCre) {
		ArrayList<Qstionne> Cre= new ArrayList<Qstionne>();
	
		//open file

		Model model = ModelFactory.createDefaultModel();
		InputStream in =FileManager.get().open("C:\\Users\\User\\Spring\\FIN\\src\\main\\java\\org\\modele\\evl\\fin.rdf");
		System.out.println("openfill");
		if(in == null) {
			throw new IllegalArgumentException("File : not found");
		}
		model.read(in,null);
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
	
	////////////////////////////////////////////////////get dim
	public ArrayList<Dimonsion> GetDimHSD() {
		ArrayList<Dimonsion> DIMPERE= new ArrayList<Dimonsion>();
	
		//open file

		Model model = ModelFactory.createDefaultModel();
		InputStream in =FileManager.get().open("C:\\Users\\User\\Spring\\FIN\\src\\main\\java\\org\\modele\\evl\\fin.rdf");
		System.out.println("openfill");
		if(in == null) {
			throw new IllegalArgumentException("File : not found");
		}
		model.read(in,null);
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
	
	
	/////////////////////////////////////////////////// get sous dim by nom dim mere ryhin nreturniwfils

	public ArrayList<Dimonsion> GetSubDim(String mere) {
		System.out.println("la dimonsiton mere est "+mere);
		Model model = ModelFactory.createDefaultModel();
		InputStream in =FileManager.get().open("C:\\Users\\User\\Spring\\FIN\\src\\main\\java\\org\\modele\\evl\\fin.rdf");
		System.out.println("openfill");
		if(in == null) {
			throw new IllegalArgumentException("File : not found");
		}
		model.read(in,null);
		
		
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
							System.out.println(dimmer+"Mere de"+nomA+"type"+typess);			
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
	
	
	
}