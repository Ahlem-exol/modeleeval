package org.modele.evl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.modele.evl.dao.ModeleDaoImpl;
import org.modele.model.Article;
import org.modele.model.Auteur;
import org.modele.model.Cretaire;
import org.modele.model.Dimonsion;
import org.modele.model.Form;
import org.modele.model.Format;
import org.modele.model.Hypothse;
import org.modele.model.Moderateur;
import org.modele.model.Qstionne;
import org.modele.model.Search;
import org.modele.model.User;
import org.modele.model.choix;
import org.modele.model.test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@Controller

public class ModeleController {
	 @Autowired
	 public void  init() throws ServletException{ }
	 
	 @RequestMapping(value="/", method = {RequestMethod.POST,RequestMethod.GET})
	 public String index(Model model, Search search) {
		ArrayList<Form> result= new ArrayList<Form>();
		ArrayList<Form> withoutDuble =null;		
		if(search.getAsk()!=null) {
		withoutDuble =new ArrayList<Form>();
		    StanfordCoreNLP stanfordCoreNLP;
		    stanfordCoreNLP = Pipeline.getPipeline();
			String text =search.getAsk();
			CoreDocument coreDocument = new CoreDocument(text);
			stanfordCoreNLP.annotate(coreDocument);		
			List<CoreLabel> coreLabelList = coreDocument.tokens();		
			ModeleDaoImpl m= new ModeleDaoImpl();
			result= m.SearchReseult(search.getAsk());
			for(CoreLabel coreLabel : coreLabelList) {
				String pos= coreLabel.getString(CoreAnnotations.PartOfSpeechAnnotation.class);
				System.out.println(coreLabel.originalText()+ "="+pos);
		    	result=  m.SearchReseult(coreLabel.originalText());			
			}
			int k=0;
			if(!result.isEmpty()) {
			for(int i=0 ;i<result.size();i++) {
				k=0;
				for(int j=0;j<withoutDuble.size();j++) {
					if(result.get(i).getnomModel().equals(withoutDuble.get(j).getnomModel())) {
						k=1;
					}else {
						
					}
				}
				if(k==0) {
					withoutDuble.add(result.get(i));
				}
			}
		
			}
	
		}
		model.addAttribute("mess", null);
		model.addAttribute("search", withoutDuble);
	 	return "Index"; }
 
	 @RequestMapping(value="/login", method = {RequestMethod.POST,RequestMethod.GET})
	 public String login(Model model,User user,HttpSession session) {
		 if( (user.getUsername()!=null)  &&  (user.getMotedepase()!=null) ){
			 if((user.getUsername().equals("admin")) && (user.getMotedepase().equals("admin") )){
				 System.out.println(user.getUsername()+"    "+user.getMotedepase());
				 session.setAttribute("username", user.getUsername());				
					 return "redirect:/dach";
				 }else {					
					 model.addAttribute("mess", "verifier votre mote de pass et votre nome d'utilisation");
					 return "Index"; 
				 }	 
		 }else {
			 return "Index"; 
		 }
	
		 
 	 }
	 	 
	 @RequestMapping(value="/logoute", method = {RequestMethod.POST,RequestMethod.GET})
	 public String logoute(Model model,User user,HttpSession session) {		
					session.removeAttribute("username");
					 return "redirect:/"; 	 
 	 }
	 		 
	 @RequestMapping(value="/Bibloitheque", method = {RequestMethod.POST,RequestMethod.GET})
	 public String download(HttpServletResponse response) throws MalformedURLException, IOException {	
	
		 BufferedInputStream inputstream;
			try {
				inputstream = new BufferedInputStream
						(new FileInputStream("C:\\Users\\User\\Spring\\PfeFin\\src\\main\\java\\org\\modele\\evl\\dao\\fin.rdf"));
				
				JFrame parentFrame = new JFrame();		 
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specifier un fichier a enregistrer");   		 
				int userSelection = fileChooser.showSaveDialog(parentFrame);		 
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File fileToSave = fileChooser.getSelectedFile();		    
				    File myObje= new File(fileToSave.getAbsolutePath());
				if(myObje.createNewFile()) {

				FileOutputStream fileSO = new FileOutputStream(fileToSave.getAbsolutePath()+".rdf");
			    byte data[]= new byte[1024];
			    int bycontent;
			    while((bycontent = inputstream.read(data,0,1024))!=-1) {
				fileSO.write(data,0,bycontent);
			    }
			    fileSO.close();}}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return "redirect:/Index"; 	 
 	 }
	 
	 @RequestMapping(value="/dach", method = {RequestMethod.POST,RequestMethod.GET})
	 public String tableudeBord(Model model) {
			ModeleDaoImpl m= new ModeleDaoImpl();	
			int nbrmodel = m.nbrmodel();
			int nbrarticle = m.nbrarticle();
			int nbrauteur= m.nbrauteru();
			int nbrdimonsion = m.nbrdimonsion();
			int  nbrCretaire = m.nbrCretaire();
			int nbrhypothses = m.nbrhypothses();
			int nbrquestion = m.nbrqestion();
			model.addAttribute("nbrmodel",nbrmodel);
			model.addAttribute("nbrarticle", nbrarticle);
			model.addAttribute("nbrauteur",nbrauteur);
			model.addAttribute("nbrdimonsion", nbrdimonsion+nbrCretaire);
			model.addAttribute("nbrhypothses",nbrhypothses);
			model.addAttribute("nbrquestion", nbrquestion);
			
 	 return "dashboard"; }

private Form form= new Form();
private ArrayList<Auteur> ALAuteru = new ArrayList<Auteur>();
private Article article = new Article ();

 @RequestMapping(value="/ajouter_nv_modele", method = {RequestMethod.POST,RequestMethod.GET})
 public String Ajoutermodelonto(Model model,Form addModel,Auteur auteur,Article arti,
     Dimonsion dimonsion,Qstionne qest,Hypothse hypo,choix choix,Moderateur moder) {
	 ModeleDaoImpl dao=new ModeleDaoImpl();
	 if(moder.getModerateur()!=null) {
	 	int idmorder =dao.nbrmoderateur()+100;String id ="Moderateur"+idmorder;
	 dao.addModerateur(moder.getModerateur(), moder.getEnoncehypothesees(),   form.getnomModel(), id);	
	 }
	 if(choix.getChoix()!=null) {
	 	int idchoix =dao.nbrchoix()+100;String id ="Choix"+idchoix;
	 	dao.addChoix(choix.getChoix(), choix.getQstion(),  form.getnomModel(), id);	
	 }
	 if(hypo.getEnonceHypothse()!=null) {
	 	int idhypo =dao.nbrhypothses()+100;String id ="Hypothese"+idhypo;
	 	dao.addHypothses(hypo.getEnonceHypothse(), hypo.getDimonsiondep(), hypo.getDimonsiondep(),  form.getnomModel(), id);
	 }
	 if(qest.getQest()!=null) {
	 	int idqstion =dao.nbrqestion()+100; String id="Question"+idqstion;
	 	dao.addQeustion(qest.getQest(), qest.getCretaire(),  form.getnomModel(), id);
	 }

	 if(dimonsion.getDescreption()!=null) {
	 	if(dimonsion.getType().equals("0")) {
	 		int iddimonsion=dao.nbrdimonsion()+100; String id ="Dimension"+iddimonsion;
	 	dao.addDimonsion(dimonsion.getNom(), dimonsion.getDescreption(), dimonsion.getNommere(), dimonsion.getType(), "http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Dimension",  form.getnomModel(), id);
	 	}else {
	 		int iddimonsion=dao.nbrCretaire()+100; String id ="Critere"+iddimonsion;
	 	dao.addDimonsion(dimonsion.getNom(), dimonsion.getDescreption(), dimonsion.getNommere(), dimonsion.getType(), "http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Critere",  form.getnomModel(), id);
	 	}

	 }
	 if(addModel.getnomModel()!=null) {
	 	form.setnomModel(addModel.getnomModel());
	 	form.setExplain(addModel.getExplain());
	 int idmodel=dao.nbrmodel()+1;String id="Modele"+idmodel;
	 	dao.AddModel(form.getnomModel(), form.getExplain(), id);
	 }
	 if(auteur.getPosition()!=null) {
	 	Auteur a= new Auteur();
	 	a.setNom(auteur.getNom());a.setPrenom(auteur.getPrenom()); a.setUnivers(auteur.getUnivers()); a.setDatedenaissance(auteur.getDatedenaissance());a.setPosition(auteur.getPosition());
	     ALAuteru.add(a);
	     int IdAuteur=dao.nbrauteru()+100;String id="Auteur"+IdAuteur;
	     dao.addAuteru(a.getNom(), a.getPrenom(), a.getUnivers(),a.getPosition(), form.getnomModel(),id);
	 }

	 if(arti.getTitle()!=null) {
	 	int IdArticle=dao.nbrarticle()+100;String id="Article"+IdArticle;
	 	article.setDoi(arti.getDoi());article.setIssn(arti.getIssn());article.setMotecle(arti.getMotecle());article.setResume(arti.getResume());article.setTitle(arti.getTitle());article.setUrl(arti.getUrl());article.setRefernceArticle(arti.getRefernceArticle());
	     dao.addArticle(article.getTitle(), article.getResume(), article.getMotecle(), article.getUrl(), article.getIssn(), article.getDoi(), form.getnomModel(),id);
	 }
	return "ajouter_nv_modele"; }
	 
@RequestMapping(value="/affichier_tt_les_modeles", method = {RequestMethod.POST,RequestMethod.GET})
 public String Affichermodelonto(Model model) {
		 try {
				ModeleDaoImpl m= new ModeleDaoImpl();	
			ArrayList<Form> bf= new ArrayList<Form>();	
			bf= m.getNomModel(); 
			model.addAttribute("listedemodels", bf);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
 	return "affichier_tt_les_modeles"; }
	 
@RequestMapping(value="/modifiermodele/{id}", method = {RequestMethod.POST,RequestMethod.GET})
public String modifiermodele(Model model,@PathVariable("id") String id,Form updateModel,Auteur auteur,Article arti, Dimonsion dimonsion,
		Qstionne qest,Hypothse hypo,choix choixQ,Moderateur moder) {
	    ModeleDaoImpl m= new ModeleDaoImpl();	
//update hypothese
		        ArrayList<Moderateur> Mod = new  ArrayList<Moderateur>();
			    ArrayList<Moderateur> Modtmp = new  ArrayList<Moderateur>();	
				ArrayList<Hypothse> hyp =new ArrayList<Hypothse> ();
				hyp= m.GetHypothses(id);
			   ////update hypo
				 if(hypo.getIdhypo()!=null) {
					 for(int k=0;k<hyp.size();k++) {
						if(hyp.get(k).getIdhypo()!=null) {
							 if(hyp.get(k).getIdhypo().equals(hypo.getIdhypo())){		
								 m.UpdateHypothses(hypo.getEnonceHypothse(), hypo.getDimonsionindp(), hypo.getDimonsiondep(),
							hyp.get(k).getEnonceHypothse(), hyp.get(k).getDimonsionindp(), hyp.get(k).getDimonsiondep());

						 }
						}
			
				 }}	
				 hyp= m.GetHypothses(id);
				 ///////////////hypotheses
				for(int hy=0;hy<hyp.size();hy++) {
					 Modtmp= m.getModerateur(hyp.get(hy).getEnonceHypothse(), id);
				}
				for(int kkk=0;kkk<Modtmp.size();kkk++) {
					Mod.add(Modtmp.get(kkk));
				}
				
				//update moderateur
				if(moder.getIdmoder()!=null) {
					 for(int k=0;k<Mod.size();k++) {
						 if(Mod.get(k).getIdmoder().equals(moder.getIdmoder())){
							  System.out.println(moder.getIdmoder());
				  m.UpdateModerateur(moder.getModerateur(), moder.getEnoncehypothesees(), 
						  Mod.get(k).getModerateur(), Mod.get(k).getEnoncehypothesees());
			
						 }
				 }for(int hy=0;hy<hyp.size();hy++) {
					 Modtmp= m.getModerateur(hyp.get(hy).getEnonceHypothse(), id);
				}
				for(int kkk=0;kkk<Modtmp.size();kkk++) {
					Mod.add(Modtmp.get(kkk));
				}}
				
			
				
//update dimonsion
		     ArrayList<Dimonsion> dimF =new ArrayList<Dimonsion> ();
		     ArrayList<Dimonsion> dim =new ArrayList<Dimonsion> ();
		     
		     dimF=m.getAllDimonsion(id);
		 	for(int i=0; i<dimF.size();i++) {
				if(dimF.get(i).getNom()!=null) {
				if((dimF.get(i).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Critere"))
				|| (dimF.get(i).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Dimension"))) {
				Dimonsion d= new Dimonsion();
				d.setNom(dimF.get(i).getNom());
				d.setDescreption(dimF.get(i).getDescreption());
				d.setRdftype(dimF.get(i).getRdftype());
				d.setIddim(dimF.get(i).getIddim());
				if(dimF.get(i).getNommere()!=null) {
					d.setNommere(dimF.get(i).getNommere());
				}		
				dim.add(d);	
				}	
			}}
		 	
		 	if(dimonsion.getIddim()!=null) {
		 		System.out.println(dimonsion.getIddim());
		 			 for(int k=0;k<dim.size();k++) {
		 				 if(dim.get(k).getIddim()!=null) {
		 					if(dim.get(k).getIddim().equals(dimonsion.getIddim())){
								 
							m.UpdateDimonsion(dimonsion.getNom(), dimonsion.getDescreption(), dimonsion.getNommere(), dimonsion.getRdftype(),
							dim.get(k).getNom(), dim.get(k).getDescreption(), dim.get(k).getNommere(), dim.get(k).getRdftype());
							 }
				     } 
		 				 }
		 		    dim =new ArrayList<Dimonsion> ();
		 			  dimF=m.getAllDimonsion(id);
		 			 	for(int i=0; i<dimF.size();i++) {
		 					if(dimF.get(i).getNom()!=null) {
		 					if((dimF.get(i).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Critere"))
		 					|| (dimF.get(i).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Dimension"))) {
		 					Dimonsion d= new Dimonsion();
		 					d.setNom(dimF.get(i).getNom());
		 					d.setDescreption(dimF.get(i).getDescreption());
		 					d.setRdftype(dimF.get(i).getRdftype());
		 					d.setIddim(dimF.get(i).getIddim());
		 					if(dimF.get(i).getNommere()!=null) {
		 						d.setNommere(dimF.get(i).getNommere());
		 					}		
		 					dim.add(d);	
		 					}	
		 				}}
			    	}	
		 	 
			 	
			
			//update question
			ArrayList<Cretaire> Cre =new ArrayList<Cretaire> ();

			ArrayList<Qstionne> qst =new ArrayList<Qstionne> ();
			ArrayList<Qstionne> qsttmp =new ArrayList<Qstionne> ();
			ArrayList<choix> choixx = new ArrayList<choix>() ;
			ArrayList<choix> choixTmp =new ArrayList<choix> ();
			for(int i=0; i<dimF.size();i++) {
				if(dimF.get(i).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Critere")){
					int k=0;
						for(int j=0;j<Cre.size();j++) {
							if(dimF.get(i).getNom().equals(Cre.get(j).getNomC())) {
								k=1;
							}else {
							}
						}
						if(k==0) {
							Cretaire c=new Cretaire();
							c.setNomC(dimF.get(i).getNom());
							c.setDescreptionC(dimF.get(i).getDescreption());
							if(dimF.get(i).getNommere()!=null) {
							c.setNomM(dimF.get(i).getNommere());}
							qsttmp= m.GetQestByCre(c.getNomC(),id);
							Cre.add(c);
							
						}
						
						for(int qs=0;qs<qsttmp.size();qs++) {
							qst.add(qsttmp.get(qs));
							choixTmp = m.GetChoix(qsttmp.get(qs).getQest(), id);	
						}
						 
							for(int j=0;j<choixTmp.size();j++) {
				              if(choixTmp.get(j).getIdchoix()!=null) {
				            	  choixx.add(choixTmp.get(j));
				              }			
				          
				              //update choix
								model.addAttribute("choix", choixx);					
						   
						}		
				   }
		     	}
		
			//update choix
			if(choixQ.getIdchoix()!=null) {
				 for(int k=0;k<choixx.size();k++) {
					 if(choixx.get(k).getIdchoix().equals(choixQ.getIdchoix())){
						 System.out.println("id  "+choixQ.getIdchoix());
	 	m.UpdateChoix(choixQ.getChoix(), choixQ.getQstion(), choixx.get(k).getChoix(), choixx.get(k).getQstion());
					 }
			 }
					for(int i=0; i<dimF.size();i++) {
						if(dimF.get(i).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Critere")){
							int k=0;
								for(int j=0;j<Cre.size();j++) {
									if(dimF.get(i).getNom().equals(Cre.get(j).getNomC())) {
										k=1;
									}else {
									}
								}
								if(k==0) {
									Cretaire c=new Cretaire();
									c.setNomC(dimF.get(i).getNom());
									c.setDescreptionC(dimF.get(i).getDescreption());
									if(dimF.get(i).getNommere()!=null) {
									c.setNomM(dimF.get(i).getNommere());}
									qsttmp= m.GetQestByCre(c.getNomC(),id);
									Cre.add(c);
									
								}
								
								for(int qs=0;qs<qsttmp.size();qs++) {
									qst.add(qsttmp.get(qs));
									choixTmp = m.GetChoix(qsttmp.get(qs).getQest(), id);	
								}
								
									for(int j=0;j<choixTmp.size();j++) {
						              if(choixTmp.get(j).getIdchoix()!=null) {
						            	  choixx.add(choixTmp.get(j));
								
						              }					//update choix
										model.addAttribute("choix", choixx);													   
								}		
						   }
				     	}}
		
			//update question
			if(qest.getIdqstion()!=null) {
				System.out.println(qest.getIdqstion());
				 for(int k=0;k<qst.size();k++) {
					 if(qst.get(k).getIdqstion().equals(qest.getIdqstion())){
			m.UpdateQeustion(qest.getQest(), qest.getCretaire(), qst.get(k).getQest(), qst.get(k).getCretaire());
					 }
			 }				 
					for(int i=0; i<dimF.size();i++) {
						if(dimF.get(i).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Critere")){
							int k=0;
								for(int j=0;j<Cre.size();j++) {
									if(dimF.get(i).getNom().equals(Cre.get(j).getNomC())) {
										k=1;
									}else {
									}
								}
								if(k==0) {
									Cretaire c=new Cretaire();
									c.setNomC(dimF.get(i).getNom());
									c.setDescreptionC(dimF.get(i).getDescreption());
									if(dimF.get(i).getNommere()!=null) {
									c.setNomM(dimF.get(i).getNommere());}
									qsttmp= m.GetQestByCre(c.getNomC(),id);
									Cre.add(c);									
								}
								
								for(int qs=0;qs<qsttmp.size();qs++) {
									qst.add(qsttmp.get(qs));
									choixTmp = m.GetChoix(qsttmp.get(qs).getQest(), id);	
								}								
									for(int j=0;j<choixTmp.size();j++) {
						              if(choixTmp.get(j).getIdchoix()!=null) {
						            	  choixx.add(choixTmp.get(j));								
						              }					//update choix
										model.addAttribute("choix", choixx);					 
				  }		
				 }
			   }
			 }			
			for(int i=0; i<dimF.size();i++) {
				if(dimF.get(i).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Critere")){
					int k=0;
						for(int j=0;j<Cre.size();j++) {
							if(dimF.get(i).getNom().equals(Cre.get(j).getNomC())) {
								k=1;
							}else {
							}
						}
						if(k==0) {
							Cretaire c=new Cretaire();
							c.setNomC(dimF.get(i).getNom());
							c.setDescreptionC(dimF.get(i).getDescreption());
							if(dimF.get(i).getNommere()!=null) {
							c.setNomM(dimF.get(i).getNommere());}
							qsttmp= m.GetQestByCre(c.getNomC(),id);
							Cre.add(c);
							
						}
						
						for(int qs=0;qs<qsttmp.size();qs++) {
							qst.add(qsttmp.get(qs));
							choixTmp = m.GetChoix(qsttmp.get(qs).getQest(), id);	
						}
						
							for(int j=0;j<choixTmp.size();j++) {
				              if(choixTmp.get(j).getIdchoix()!=null) {
				            	  choixx.add(choixTmp.get(j));
						
				              }					//update choix
								model.addAttribute("choix", choixx);					
						   
						}		
				   }
		     	}

             //update auteur
			  ArrayList<Auteur> bf= new ArrayList<Auteur>();
			  bf=m.getAuteur(id);
		 if(auteur.getIdauterur()!=null) {
			 for(int k=0;k<bf.size();k++) {
				 if(bf.get(k).getIdauterur().equals(auteur.getIdauterur())){
					 m.UpdateAuteru(auteur.getNom(), auteur.getPrenom(), auteur.getUnivers(), auteur.getPosition(),auteur.getDatedenaissance(), 
					bf.get(k).getDatedenaissance(),bf.get(k).getNom(), bf.get(k).getPrenom(), bf.get(k).getUnivers(), bf.get(k).getPosition());				
				 }
		 } bf=m.getAuteur(id);} 
		 
			  Form f =new Form();
			  f=m.getNomModeleByID(id);
		 if(updateModel.getnomModel()!=null) {
			 System.out.println(updateModel.getnomModel());
			 System.out.println(f.getnomModel());
               m.UpdateModel(updateModel.getnomModel(), updateModel.getExplain(), f.getnomModel(), f.getExplain());
               f=m.getNomModeleByID(id);
		 }
		
	
		    Article a= new Article();
		    a=m.getArticle(id);		   
		 if(arti.getTitle() !=null) {
			 m.UpdateArticle(arti.getTitle(), arti.getResume(), arti.getMotecle(), arti.getUrl(), arti.getIssn(), arti.getDoi(),
			 a.getTitle(),a.getResume(), a.getMotecle(), a.getUrl(), a.getIssn(), a.getDoi());
			 a=m.getArticle(id);	
		 }
		   	
			model.addAttribute("article", a);
			model.addAttribute("auteurs", bf);
			model.addAttribute("dim", dim);
			model.addAttribute("qst", qst);
			model.addAttribute("model", f);
			model.addAttribute("choix", choixx);
			model.addAttribute("hypo", hyp);
			model.addAttribute("moder", Mod); 
 	return "modifiermodele"; }
@RequestMapping(value="/delet/{id}", method = {RequestMethod.POST,RequestMethod.GET})
public String deletmODEL(Model model,@PathVariable("id") String id) {
	 ModeleDaoImpl m= new ModeleDaoImpl();	
	if(id !=null) {		
		String mm = "http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+id;
		m.delet(mm);
	}
	return "redirect:/affichier_tt_les_modeles";
}
@RequestMapping(value="/delet", method = {RequestMethod.POST,RequestMethod.GET})
public String delete(Model model,test t) {
	 ModeleDaoImpl m= new ModeleDaoImpl();	
	if(t.getNomC() !=null) {		
		String mm = "http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#"+t.getNomC();
		m.delet(mm);
	}
	return "redirect:/affichier_tt_les_modeles";
}
	 
@RequestMapping(value="/modifiermodele/delet/{id}", method = {RequestMethod.POST,RequestMethod.GET})
public String delet(Model model,Auteur auteur,
Dimonsion dimonsion,Qstionne qest,Hypothse hypo,choix choix,Moderateur moder) {
    ModeleDaoImpl m= new ModeleDaoImpl();

	if(auteur.getIdauterur()!= null) {		
		m.delet(auteur.getIdauterur());
	}

	if(dimonsion.getIddim()!= null) {
		m.delet(dimonsion.getIddim());
	}
	
	
	if(qest.getIdqstion()!= null) {
		m.delet(qest.getIdqstion());
	}
	
	if(choix.getIdchoix()!= null) {
		m.delet(choix.getIdchoix());
	}
	
	if(moder.getIdmoder()!= null) {
		m.delet(moder.getIdmoder());
	}

		return "redirect:/modifiermodele/{id}";
	
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@RequestMapping(value="/affichier_tt_les_modeles_user", method = {RequestMethod.POST,RequestMethod.GET})
public String Affichermodeluser(Model model) {
		 try {
				ModeleDaoImpl m= new ModeleDaoImpl();	
			ArrayList<Form> bf= new ArrayList<Form>();	
			bf= m.getNomModel(); 
			model.addAttribute("listedemodels", bf);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
	return "affichier_tt_les_modeles_user"; }


ArrayList<Dimonsion>dim = new ArrayList<Dimonsion>();
ArrayList<Dimonsion>dimFils = new ArrayList<Dimonsion>();
ArrayList<Qstionne> qst  = new ArrayList<Qstionne>();
ArrayList<Cretaire>  cre  = new ArrayList<Cretaire>();
Form ModelInfo = new Form();
ArrayList<Cretaire>  CSM  = new ArrayList<Cretaire>();
ArrayList<Dimonsion>  DSM  = new ArrayList<Dimonsion>();
@RequestMapping(value="/ajouter_nv_modele_user", method = {RequestMethod.POST,RequestMethod.GET})
public String AddModele(Model model, Cretaire m, Qstionne qstn, test t, Dimonsion di, Format download,Form f) throws IOException {

	ModeleDaoImpl mth=new ModeleDaoImpl();
	//aficher les dimonsion sous et creatire
	CSM =mth.GetCreatereSansMere();
	model.addAttribute("listCretair", CSM);

	DSM =mth.GetDimHSD();
	model.addAttribute("listDim", DSM);
	////////////////////////////////////////////////////////////////
	if(download.getNCSD() !=null) {
		//call methode getqstion par cetaire nom;
		System.out.println("le cretaire choisir : "+download.getNCSD());
		Cretaire cd= new Cretaire();
		cd.setNomC(download.getNCSD());
       //remplir objet cretair
		for(int cpt=0;cpt<CSM.size();cpt++) {
    	  if(CSM.get(cpt).getNomC().equals(download.getNCSD())) {
    			cd.setDescreptionC(CSM.get(cpt).getDescreptionC());
    			cpt= CSM.size();
    	  }
      }
		ArrayList<Qstionne> qstImport  = new ArrayList<Qstionne>();
		qstImport = mth.GetQestByCre(download.getNCSD());
		for(int q=0;q<qstImport.size();q++) {
			Qstionne tmperQ=new Qstionne();
			tmperQ.setCretaire(qstImport.get(q).getCretaire());
			tmperQ.setQest(qstImport.get(q).getQest());
			qst.add(tmperQ);
			System.out.println(qstImport.get(q).getQest()+":"+ qstImport.get(q).getCretaire());
		}
	
      if(cre!= null){
			int csqk =0;
		for(int i=0; i<cre.size();i++)
		{
			if(cre.get(i).getNomC().equals(download.getNCSD())) {
				csqk=1;
				i=cre.size();
			}else {
				csqk=0;
			}
		}
		if(download.getNCSD() !=null) {
			if(csqk ==1) {
				System.out.println("deja existe");
				}else {
					cre.add(cd);				
				}		
	}
      }
	}
	///////////////////////////////////////////////////////////////////////aficher dim
	if(download.getNDSC() !=null) {
		//call methode getqstion par cetaire nom;
		System.out.println("la dimonsion choisir : "+download.getNDSC());
		Dimonsion DS= new Dimonsion();
		DS.setNom(download.getNDSC());
   ///remplir objet dim
		for(int cpt=0;cpt<DSM.size();cpt++) {
  	     if(DSM.get(cpt).getNom().equals(download.getNDSC())) {
 			DS.setDescreption(DSM.get(cpt).getDescreption());
   			cpt= DSM.size();
   			
    	  }
      }
		ArrayList<Dimonsion> tmp= new ArrayList<Dimonsion>();
		tmp=mth.GetSubDim(DS.getNom().toString());
		for(int s=0;s<tmp.size();s++) {
			Dimonsion tmporel=new Dimonsion();
			tmporel.setDescreption(tmp.get(s).getDescreption());
			tmporel.setNom(tmp.get(s).getNom());
			tmporel.setNommere(tmp.get(s).getNommere());
			dimFils.add(tmporel);
			
			ArrayList<Qstionne> qstImport  = new ArrayList<Qstionne>();
			qstImport = mth.GetQestByCre(tmp.get(s).getNom());
			for(int q=0;q<qstImport.size();q++) {
				Qstionne tmperQ=new Qstionne();
				tmperQ.setCretaire(qstImport.get(q).getCretaire());
				tmperQ.setQest(qstImport.get(q).getQest());
				qst.add(tmperQ);
				System.out.println(qstImport.get(q).getQest()+":"+ qstImport.get(q).getCretaire());
			}
		}	
	
      if(dim!= null){
			int csqk =0;
		for(int i=0; i<dim.size();i++)
		{
			if(dim.get(i).getNom().equals(download.getNDSC())) {
				csqk=1;
				i=dim.size();
			}else {
				csqk=0;
			}
		}
		if(download.getNDSC() !=null) {
			if(csqk ==1) {
				System.out.println("deja existe");
				}else {				
					dim.add(DS);				
				}		
		}
      }	
}

	//////////////////////////////////////////////////////////////traitmn des form
	String nom = m.getNomC();
	String qstionn = qstn.getQest();
	String dimonstion = di.getNom();
	/////////////////////////////////////////////////cretaire//////////////////////////////////////////////////////////////////
	if(nom == null) {
		}
	if(  (cre!= null) ){
			int k =0;
		for(int i=0; i<cre.size();i++)
		{
			if(cre.get(i).getNomC().equals(m.getNomC())) {
				k=1;
				i=cre.size();
			}else {
				k=0;
			}
		}
		if(nom !=null) {
			if(k ==1) {
				System.out.println("deja existe");

				}else {
					System.out.print("add cretaire"+m.getNomC());
					cre.add(m);
				    model.addAttribute("Cretaire",cre);
				}
		}
		model.addAttribute("Cretaire",cre);
		}
	
//	/////////////////////Qstion///////////////////////////////////////////////////////////////////////////////////////
	if(qstionn == null) {
	}
	if(qst != null){
	int qsk =0;
	for(int i=0; i<qst.size();i++)
	{
		if(qst.get(i).getQest().equals(qstn.getQest())) {
			qsk=1;
			i=qst.size();
		}else {
			qsk=0;
		}
	}
	if(qstionn != null) {	
	if(qsk ==1) {
	model.addAttribute("Qstionne",qst);
	}else {
	qst.add(qstn);
	System.out.println("addqeustionn: "+qstn.getQest()+"de cretaire : "+qstn.getCretaire());
	model.addAttribute("Qstionne",qst);
	}
	}
	model.addAttribute("Qstionne",qst);
	}
	

	///////////////////////////////////// dimonsion////////////////////////////////////////////////////////////
	if(dimonstion == null) {
	}
	if(di.getNommere()== null) {
		//rana dans dim mere
		if(dim != null) {
			int dik =0;
			for(int i=0; i<dim.size();i++)
			{
				if(dim.get(i).getNom().equals(di.getNom())) {
					dik=1;
					i=dim.size();
				}
			}
			if(dimonstion != null) {
				if(dik ==1) {
					System.out.println("deja existe");
					model.addAttribute("Dimonsion",dim);
					}else {
					dim.add(di);
					model.addAttribute("Dimonsion",dim);
					}
			}
			model.addAttribute("Dimonsion",dim);
		}	
	}else {		
		/////////////////////////files a une mere////////////////////////////////////
		if(dimFils != null) {
			int difk =0;
			for(int i=0; i<dimFils.size();i++)
			{
				if(dimFils.get(i).getNom().equals(di.getNom())) {
					difk=1;
					i=dimFils.size();
				}				
			}
			if(dimonstion != null) {
				if(difk ==1) {
					System.out.println("deja existe");
					model.addAttribute("dimFils",dimFils);
					}else {
						dimFils.add(di);
					model.addAttribute("dimFils",dimFils);
					}
			}
			model.addAttribute("dimFils",dimFils);
			model.addAttribute("Dimonsion",dim);
		}			
	}
/////////////////////////////////////
	model.addAttribute("dimFils",dimFils);
	model.addAttribute("Dimonsion",dim);
	model.addAttribute("Qstionne",qst);
	model.addAttribute("Cretaire",cre);

	if(f.getnomModel()!=null ) {
		ModelInfo.setnomModel(f.getnomModel());
		ModelInfo.setExplain(f.getExplain());
		model.addAttribute("noma",ModelInfo);		
	}else {
		if(ModelInfo.getnomModel()!=null) {
			model.addAttribute("noma",ModelInfo);	
		}else {
			model.addAttribute("noma",null);	
		}
	}
///////////////////////////////////////////ajouter txt//////////////////////////////////////////////////////
if(download.getType() !=null) {
System.out.println(download.getType());
int nbr= Integer.parseInt(download.getType());
switch (nbr){
case 0:
System.out.println("Txt"); 
JFrame parentFrame = new JFrame();		 
JFileChooser fileChooser = new JFileChooser();
fileChooser.setDialogTitle("Specifier un fichier a enregistrer");   		 
int userSelection = fileChooser.showSaveDialog(parentFrame);		 
if (userSelection == JFileChooser.APPROVE_OPTION) {
File fileToSave = fileChooser.getSelectedFile();		    
File myObje= new File(fileToSave.getAbsolutePath());
if(myObje.createNewFile()) {

FileWriter my =  new FileWriter(fileToSave.getAbsolutePath());
if(ModelInfo!=null) {
my.write("                          Votre modele                                                       \n");
my.write("Le nom de modele: "+ModelInfo.getnomModel()+"\n");
my.write("Descreption:  "+ModelInfo.getExplain()+"\n");
my.write("Dimonsiton et itmes:  \n");
/////////////////////////////////////////////////////////////////////////////////////////////////
for(int j =0;j<dim.size();j++) {
my.write("Dimonstion"+j+":"+dim.get(j).getNom()+"\n");
my.write("Descreption de dimension: "+dim.get(j).getDescreption()+"\n");
for(int i=0;i<dimFils.size(); i++) {
if(dimFils.get(i).getNommere().equals(dim.get(j).getNom())) {
//j  hiya la mere tae i 
my.write("Sous_Dimonstion"+i+":"+dimFils.get(i).getNom()+"\n");
my.write("Descreption dimension: "+dimFils.get(i).getDescreption()+"\n");		

//afficher qstion 
for(int q=0; q<qst.size();q++) {
if(qst.get(q).getCretaire().equals(dimFils.get(i).getNom())) {
my.write("Question"+q+":  "+qst.get(q).getQest()+"\n");
}
}

}
for(int k=0; k<dimFils.size(); k++ ) {
if(dimFils.get(i).getNommere().equals(dimFils.get(k).getNom())) {
//k  hiya la mere tae i 
my.write("Sous_Dimonstion"+i+":  "+dimFils.get(i).getNom()+"\n");
my.write(dimFils.get(i).getDescreption()+"\n");	
//afficher qstion 
for(int q=0; q<qst.size();q++) {
if(qst.get(q).getCretaire().equals(dimFils.get(i).getNom())) {
my.write("Question"+q+": "+qst.get(q).getQest()+"\n");
}
}

}
}
}
}
for(int c =0;c<cre.size();c++) {
my.write("Cretaire"+c+":"+cre.get(c).getNomC()+"\n");
my.write(cre.get(c).getDescreptionC()+"\n");

//afficher qstion 
for(int q=0; q<qst.size();q++) {
if(qst.get(q).getCretaire().equals(cre.get(c).getNomC())) {
my.write("Question"+q+":"+qst.get(q).getQest()+"\n");
}
}

}

System.out.println(ModelInfo.getnomModel());
}


my.close();
System.out.println("Save as file: " + fileToSave.getAbsolutePath());
}else {
//dans esi deja existe 
System.out.println("Filenot created");
}    
}
break;
////////////////////////////////////////exel//////////////////////////////////////////////
case 1: System.out.println("exel");
int index=5;
JFrame parentFrameE = new JFrame();		 
JFileChooser fileChooserE = new JFileChooser();
fileChooserE.setDialogTitle("Specifier un fichier a enregistrer");   		 
int userSelectionE = fileChooserE.showSaveDialog(parentFrameE);		 
if (userSelectionE == JFileChooser.APPROVE_OPTION) {
File fileToSaveE = fileChooserE.getSelectedFile();  
File myObjeD= new File(fileToSaveE.getAbsolutePath());

if(myObjeD.createNewFile()) {
XSSFWorkbook workbook = new XSSFWorkbook();

//Create a blank sheet
XSSFSheet sheet = workbook.createSheet("Modele Data");

//This data needs to be written (Object[])
Map< Integer, Object[]> data = new TreeMap<Integer ,Object[]>();
if(ModelInfo!=null) {		    	
data.put(index++, new Object[]{"Nom  de modele ",ModelInfo.getnomModel()});
data.put(index++, new Object[]{"Descreption   de modele ",ModelInfo.getExplain()});
//////////////////////////////////////////////////////////////////////::	
data.put(index++, new Object[]{"Nom Dimonstion ","Descreption De dimonsion","Dimonstion mere","Qeustion"});

for(int j =0;j<dim.size();j++) {
data.put(index++, new Object[]{dim.get(j).getNom(),dim.get(j).getDescreption()});
for(int i=0;i<dimFils.size(); i++) {
if(dimFils.get(i).getNommere().equals(dim.get(j).getNom())) {
//j  hiya la mere tae i 		
data.put(index++, new Object[]{dimFils.get(i).getNom(),dim.get(i).getDescreption(),dimFils.get(i).getNommere()});
//afficher qstion 
for(int q=0; q<qst.size();q++) {
if(qst.get(q).getCretaire().equals(dimFils.get(i).getNom())) {
data.put(index++, new Object[]{dimFils.get(i).getNom(),dim.get(i).getDescreption(),dimFils.get(i).getNommere(),qst.get(q).getQest()});
}
}

}
for(int k=0; k<dimFils.size(); k++ ) {
if(dimFils.get(i).getNommere().equals(dimFils.get(k).getNom())) {
//k  hiya la mere tae i 
data.put(index++, new Object[]{dimFils.get(i).getNom(),dim.get(i).getDescreption(),dimFils.get(i).getNommere()});

//afficher qstion 
for(int q=0; q<qst.size();q++) {
if(qst.get(q).getCretaire().equals(dimFils.get(i).getNom())) {
	 data.put(index++, new Object[]{dimFils.get(i).getNom(),dim.get(i).getDescreption(),dimFils.get(i).getNommere(),qst.get(q).getQest()});
}
}

}
}
}
}   



for(int c =0;c<cre.size();c++) {

data.put(index++, new Object[]{cre.get(c).getNomC(),cre.get(c).getDescreptionC()});
//afficher qstion 
for(int q=0; q<qst.size();q++) {
if(qst.get(q).getCretaire().equals(cre.get(c).getNomC())) {
data.put(index++, new Object[]{cre.get(c).getNomC(),cre.get(c).getDescreptionC(),"",qst.get(q).getQest()});
}}}    }
//Iterate over data and write to sheet
Set<Integer> keyset = data.keySet();

int rownum = 0;
for (Integer key : keyset) 
{
//create a row of excelsheet
Row row = sheet.createRow(rownum++);

//get object array of prerticuler key
Object[] objArr = data.get(key);

int cellnum = 0;

for (Object obj : objArr) 
{
Cell cell = row.createCell(cellnum++);
if (obj instanceof String) 
{
cell.setCellValue((String) obj);
}
else if (obj instanceof Integer) 
{
cell.setCellValue((Integer) obj);
}
}
}
try 
{
//Write the workbook in file system
FileOutputStream out = new FileOutputStream(myObjeD);
workbook.write(out);
out.close();
System.out.println("written successfully on disk.");
} 
catch (Exception e)
{
e.printStackTrace();
}

System.out.println("execl written successully");   }

}
break;
//////////////////////////////////////doc////////////////////////////////////////////////
case 2:  System.out.println("doc"); 
JFrame parentFrameD = new JFrame();		 
JFileChooser fileChooserD = new JFileChooser();
fileChooserD.setDialogTitle("Specifier un fichier a enregistrer");   		 
int userSelectionD = fileChooserD.showSaveDialog(parentFrameD);		 
if (userSelectionD == JFileChooser.APPROVE_OPTION) {
File fileToSaveD = fileChooserD.getSelectedFile();  
File myObjeD= new File(fileToSaveD.getAbsolutePath());
if(myObjeD.createNewFile()) {
XWPFDocument document = new XWPFDocument();
////wrie the docmen infront system
FileOutputStream out =  new FileOutputStream(myObjeD);
//create paragraph
XWPFParagraph paragraph = document.createParagraph();	
if(ModelInfo!=null) {
//Set Bold an Italic
XWPFRun paragraphOneRunOne = paragraph.createRun();
paragraphOneRunOne.setBold(true);
paragraphOneRunOne.setText("Le nom de modele:   "+ModelInfo.getnomModel());
paragraphOneRunOne.addBreak();
paragraphOneRunOne.setText("Descreption: "+ModelInfo.getExplain());
paragraphOneRunOne.addBreak();
paragraphOneRunOne.setText("Dimonsiton et itmes :   \n");
paragraphOneRunOne.addBreak();

for(int j =0;j<dim.size();j++) {

paragraphOneRunOne.setBold(true);
paragraphOneRunOne.setText("Dimonstion"+j+":"+dim.get(j).getNom());
paragraphOneRunOne.addBreak();
paragraphOneRunOne.setText("Descreption: "+dim.get(j).getDescreption());
paragraphOneRunOne.addBreak();
for(int i=0;i<dimFils.size(); i++) {
if(dimFils.get(i).getNommere().equals(dim.get(j).getNom())) {
//j  hiya la mere tae i 							
paragraphOneRunOne.setBold(true);
paragraphOneRunOne.setText("Sous_Dimonstion"+i+":"+dimFils.get(i).getNom());
paragraphOneRunOne.addBreak();
paragraphOneRunOne.setText(dimFils.get(i).getDescreption());
paragraphOneRunOne.addBreak();

//afficher qstion 
for(int q=0; q<qst.size();q++) {
if(qst.get(q).getCretaire().equals(dimFils.get(i).getNom())) {
paragraphOneRunOne.setBold(true);
paragraphOneRunOne.setText("Question"+q+":"+qst.get(q).getQest());
paragraphOneRunOne.addBreak();

}
}

}
for(int k=0; k<dimFils.size(); k++ ) {
if(dimFils.get(i).getNommere().equals(dimFils.get(k).getNom())) {
//k  hiya la mere tae i 

paragraphOneRunOne.setBold(true);
paragraphOneRunOne.setText("Sous_Dimonstion"+i+":"+dimFils.get(i).getNom());
paragraphOneRunOne.addBreak();
paragraphOneRunOne.setText("Descreption : "+dimFils.get(i).getDescreption());
paragraphOneRunOne.addBreak();	
//afficher qstion 
for(int q=0; q<qst.size();q++) {
if(qst.get(q).getCretaire().equals(dimFils.get(i).getNom())) {
paragraphOneRunOne.setText("Question"+q+":"+qst.get(q).getQest());
paragraphOneRunOne.addBreak();
}
}

}
}
}
}
for(int c =0;c<cre.size();c++) {

paragraphOneRunOne.setText("Cretaire"+c+":"+cre.get(c).getNomC());
paragraphOneRunOne.addBreak();
paragraphOneRunOne.setText("Descreption: "+cre.get(c).getDescreptionC());
paragraphOneRunOne.addBreak();	
//afficher qstion 
for(int q=0; q<qst.size();q++) {
if(qst.get(q).getCretaire().equals(cre.get(c).getNomC())) {
paragraphOneRunOne.setText("Question"+q+":"+qst.get(q).getQest());
paragraphOneRunOne.addBreak();

}
}

}

System.out.println(ModelInfo.getnomModel());




}
document.write(out);
out.close();		

System.out.println("docx written successully");
}}

break;
default: System.out.println("pas de choix");

}
}
	
	return "ajouterr_nv_modele_user";
}

@RequestMapping(value="/afficher_detaille_model_user/{id}", method ={RequestMethod.POST,RequestMethod.GET})
public String afficher_detaille_model_user(Model model, @PathVariable("id")String id, Format download) throws IOException {

	System.out.println("le id de models est "+id);
	ModeleDaoImpl m= new ModeleDaoImpl();
	Article a= new Article();
	a= m.getArticle(id);
	ArrayList<Auteur> bf= new ArrayList<Auteur>();
    bf=m.getAuteur(id);
    ArrayList<Moderateur> Mod = new  ArrayList<Moderateur>();
    ArrayList<Moderateur> Modtmp = new  ArrayList<Moderateur>();
	ArrayList<Dimonsion> dim= new ArrayList<Dimonsion>();
	dim=m.getAllDimonsion(id);
	ArrayList<Hypothse> hyp =new ArrayList<Hypothse> ();
	hyp= m.GetHypothses(id);
	for(int hy=0;hy<hyp.size();hy++) {
		
		 Modtmp= m.getModerateur(hyp.get(hy).getEnonceHypothse(), id);
	}
	for(int kkk=0;kkk<Modtmp.size();kkk++) {
		Mod.add(Modtmp.get(kkk));
	}
	
	ArrayList<Cretaire> Cre =new ArrayList<Cretaire> ();
	ArrayList<Dimonsion> DimF =new ArrayList<Dimonsion> ();
	ArrayList<Qstionne> qst =new ArrayList<Qstionne> ();
	ArrayList<Qstionne> qsttmp =new ArrayList<Qstionne> ();
	ArrayList<choix> choixx = new ArrayList<choix>() ;
	ArrayList<choix> choixTmp =new ArrayList<choix> ();
	
	for(int i=0; i<dim.size();i++) {
	//if cretaire selection qstion eamr 
	if(dim.get(i).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Critere")) {
		int k=0;
			for(int j=0;j<Cre.size();j++) {
				if(dim.get(i).getNom().equals(Cre.get(j).getNomC())) {
					k=1;
				}else {
				}
			}
			if(k==0) {
				Cretaire c=new Cretaire();
				c.setNomC(dim.get(i).getNom());
				c.setDescreptionC(dim.get(i).getDescreption());
				if(dim.get(i).getNommere()!=null) {
				c.setNomM(dim.get(i).getNommere());}
				qsttmp= m.GetQestByCre(c.getNomC(),id);
				Cre.add(c);
			}
			
			for(int qs=0;qs<qsttmp.size();qs++) {
				qst.add(qsttmp.get(qs));
				choixTmp = m.GetChoix(qsttmp.get(qs).getQest(), id);	
			}
			if(choixTmp.isEmpty()) {
				choixx=null;
				model.addAttribute("choix", null);
			}else {
				for(int j=0;j<choixTmp.size();j++) {
					ArrayList<choix> choix = new ArrayList<choix>() ;
							choix.add(choixTmp.get(j));
							model.addAttribute("choix", choixx);
				}
			}
		
	
	}

	// sinn cestun fils au dimons sineandoumere putdiles else put in demonsion
	if(dim.get(i).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Dimension")) {
		int k=0;
			for(int j=0;j<DimF.size();j++) {
				if(dim.get(i).getNom().equals(DimF.get(j).getNom())) {
					k=1;
				}else {
				}
			}
			if(k==0) {
				Dimonsion d= new Dimonsion();
				d.setNom(dim.get(i).getNom());
				System.out.println("dimonsion " +dim.get(i).getNom());
				d.setDescreption(dim.get(i).getDescreption());
				if(dim.get(i).getNommere()!=null) {
					d.setNommere(dim.get(i).getNommere());
				}
				DimF.add(d);	
			}
	}	
}

    Form f =new Form();
    f=m.getNomModeleByID(id);
	model.addAttribute("article", a);
	model.addAttribute("auteurs", bf);
	model.addAttribute("dim", DimF);
	model.addAttribute("Cret", Cre);
	model.addAttribute("qst", qst);
	model.addAttribute("model", f);
	model.addAttribute("choix", choixx);
	model.addAttribute("hypo", hyp);
	model.addAttribute("moder", Mod);

if(download.getType() !=null) {
System.out.println(download.getType());
int nbr= Integer.parseInt(download.getType());
switch (nbr){
case 0:
System.out.println(" Download format Txt"); 
    dwolnolad_text( f, a,bf, dim, Cre, qst,hyp,choixx,Mod);
break;
case 1:
	System.out.println("Download format exel");
	dwolnolad_excel( f, a,bf, dim, Cre, qst,hyp,choixx,Mod);
break;
case 2:  
	System.out.println("Download format doc"); 
	dwolnolad_world( f, a,bf, dim, Cre, qst,hyp,choixx,Mod);
break;
default: System.out.println("pas de choix");
}}	
return "afficher_detaille_model_user";
}

@RequestMapping(value="/afficher_detaille/{id}", method ={RequestMethod.POST,RequestMethod.GET})
public String afficher_detaille_r(Model model, @PathVariable("id")String id, Format download) throws IOException {

	System.out.println("le id de models est "+id);
	ModeleDaoImpl m= new ModeleDaoImpl();
	Article a= new Article();
	a= m.getArticle(id);
	ArrayList<Auteur> bf= new ArrayList<Auteur>();
    bf=m.getAuteur(id);
    ArrayList<Moderateur> Mod = new  ArrayList<Moderateur>();
    ArrayList<Moderateur> Modtmp = new  ArrayList<Moderateur>();
	ArrayList<Dimonsion> dim= new ArrayList<Dimonsion>();
	dim=m.getAllDimonsion(id);
	ArrayList<Hypothse> hyp =new ArrayList<Hypothse> ();
	hyp= m.GetHypothses(id);
	for(int hy=0;hy<hyp.size();hy++) {
		
		 Modtmp= m.getModerateur(hyp.get(hy).getEnonceHypothse(), id);
	}
	for(int kkk=0;kkk<Modtmp.size();kkk++) {
		Mod.add(Modtmp.get(kkk));
	}
	
	ArrayList<Cretaire> Cre =new ArrayList<Cretaire> ();
	ArrayList<Dimonsion> DimF =new ArrayList<Dimonsion> ();
	ArrayList<Qstionne> qst =new ArrayList<Qstionne> ();
	ArrayList<Qstionne> qsttmp =new ArrayList<Qstionne> ();
	ArrayList<choix> choixx = new ArrayList<choix>() ;
	ArrayList<choix> choixTmp =new ArrayList<choix> ();
	
	for(int i=0; i<dim.size();i++) {
	//if cretaire selection qstion eamr 
	if(dim.get(i).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Critere")) {
		int k=0;
			for(int j=0;j<Cre.size();j++) {
				if(dim.get(i).getNom().equals(Cre.get(j).getNomC())) {
					k=1;
				}else {
				}
			}
			if(k==0) {
				Cretaire c=new Cretaire();
				c.setNomC(dim.get(i).getNom());
				c.setDescreptionC(dim.get(i).getDescreption());
				if(dim.get(i).getNommere()!=null) {
				c.setNomM(dim.get(i).getNommere());}
				qsttmp= m.GetQestByCre(c.getNomC(),id);
				Cre.add(c);
			}
			
			for(int qs=0;qs<qsttmp.size();qs++) {
				qst.add(qsttmp.get(qs));
				choixTmp = m.GetChoix(qsttmp.get(qs).getQest(), id);	
			}
			if(choixTmp.isEmpty()) {
				choixx=null;
				model.addAttribute("choix", null);
			}else {
				for(int j=0;j<choixTmp.size();j++) {
					ArrayList<choix> choix = new ArrayList<choix>() ;
							choix.add(choixTmp.get(j));
							model.addAttribute("choix", choixx);
				}
			}
		
	
	}

	// sinn cestun fils au dimons sineandoumere putdiles else put in demonsion
	if(dim.get(i).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Dimension")) {
		int k=0;
			for(int j=0;j<DimF.size();j++) {
				if(dim.get(i).getNom().equals(DimF.get(j).getNom())) {
					k=1;
				}else {
				}
			}
			if(k==0) {
				Dimonsion d= new Dimonsion();
				d.setNom(dim.get(i).getNom());
				System.out.println("dimonsion " +dim.get(i).getNom());
				d.setDescreption(dim.get(i).getDescreption());
				if(dim.get(i).getNommere()!=null) {
					d.setNommere(dim.get(i).getNommere());
				}
				DimF.add(d);	
			}
	}	
}

    Form f =new Form();
    f=m.getNomModeleByID(id);
	model.addAttribute("article", a);
	model.addAttribute("auteurs", bf);
	model.addAttribute("dim", DimF);
	model.addAttribute("Cret", Cre);
	model.addAttribute("qst", qst);
	model.addAttribute("model", f);
	model.addAttribute("choix", choixx);
	model.addAttribute("hypo", hyp);
	model.addAttribute("moder", Mod);

if(download.getType() !=null) {
System.out.println(download.getType());
int nbr= Integer.parseInt(download.getType());
switch (nbr){
case 0:
System.out.println(" Download format Txt"); 
    dwolnolad_text( f, a,bf, dim, Cre, qst,hyp,choixx,Mod);
break;
case 1:
	System.out.println("Download format exel");
	dwolnolad_excel( f, a,bf, dim, Cre, qst,hyp,choixx,Mod);
break;
case 2:  
	System.out.println("Download format doc"); 
	dwolnolad_world( f, a,bf, dim, Cre, qst,hyp,choixx,Mod);
break;
default: System.out.println("pas de choix");
}}	
return "afficher_detaille";
}
public void dwolnolad_text(Form f,Article a,ArrayList<Auteur>bf,ArrayList<Dimonsion> dim,ArrayList<Cretaire> Cre,ArrayList<Qstionne> qst,
	ArrayList<Hypothse>hyp,ArrayList<choix>choixx,ArrayList<Moderateur>Mod) throws IOException{
	JFrame parentFrame = new JFrame();		 
	JFileChooser fileChooser = new JFileChooser();
	fileChooser.setDialogTitle("Specifier un fichier a enregistrer");   		 
	int userSelection = fileChooser.showSaveDialog(parentFrame);		 
	if (userSelection == JFileChooser.APPROVE_OPTION) {
	File fileToSave = fileChooser.getSelectedFile();		    
	File myObje= new File(fileToSave.getAbsolutePath());
	if(myObje.createNewFile()) {
	FileWriter my =  new FileWriter(fileToSave.getAbsolutePath());
	if(f!=null) {
	my.write("                         Information sur le modele                                         \n");
	my.write("Le nom de ce modele est:  "+f.getnomModel()+"\n");
	my.write("Descreption: "+f.getExplain()+"\n");
	if(a!=null) {
	my.write("Article :  \n");
	my.write("Titre: "+a.getTitle()+"\n"); my.write("Resume: "+a.getResume()+"\n"); my.write("MoteCle: "+a.getMotecle()+"\n");  my.write("DOI: "+a.getDoi()+"\n");  my.write("Issn: "+a.getIssn()+"\n");  my.write("Url: "+a.getUrl()+"\n"); my.write("Reference  bibloigraphique: "+a.getRefernceArticle()+"\n");  
	}
	if(bf !=null) {
	my.write("Auteur:   \n");
	for(int au=0;au<bf.size();au++) {
	my.write("Nom: "+bf.get(au).getNom()+" , "+bf.get(au).getPrenom()+" ,"); my.write("Universite: "+bf.get(au).getUnivers()+ "Position: "+bf.get(au).getPosition()+"\n"); 
	}
	}
	my.write("Dimonsiton et itmes:  \n");
	/////////////////////////////////////////////////////////////////////////////////////////////////
	for(int j =0;j<dim.size();j++) {
		if(dim.get(j).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Dimension")  ) {	

			my.write("Dimonstion "+j+":"+dim.get(j).getNom()+"\n");
			my.write("Descreption: "+dim.get(j).getDescreption()+"\n");
			for(int i=0;i<dim.size(); i++) {
			if((dim.get(i).getNommere().equals(dim.get(j).getNom())) && 
		    (dim.get(i).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Dimension")) ){		
				my.write("Sous_Dimonstion"+i+": "+dim.get(i).getNom()+"\n");
				my.write("Descreption: " +dim.get(i).getDescreption()+"\n");					
		}
	if(dim.get(i).getNommere().equals(dim.get(j).getNom()) && 
			(dim.get(i).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Critere"))){
			my.write("cretaire "+i+":"+dim.get(i).getNom()+"\n");
			my.write("Descreption: "+dim.get(i).getDescreption()+"\n");
			//afficher qstion 
			for(int q=0; q<qst.size();q++) {
			if(qst.get(q).getCretaire().equals(dim.get(i).getNom())) {
			my.write("Question"+q+":"+qst.get(q).getQest()+"\n");
			for(int ch=0;ch<choixx.size();ch++) {
				if(choixx.get(ch).getQstion().equals(qst.get(q).getQest())) {
					my.write(choixx.get(ch).getChoix()+"\n");
				}else {
				my.write("faible __ __ __ __ __ __ __ eleve.\n");
				}}}
		}
				}
	
	}
		}
		if((dim.get(j).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Critere")) && ((dim.get(j).getNommere()==null))) {	
			my.write("cretaire "+j+":"+dim.get(j).getNom()+"\n");
			my.write("Descreption: "+dim.get(j).getDescreption()+"\n");
			//afficher qstion 
			for(int q=0; q<qst.size();q++) {
			if(qst.get(q).getCretaire().equals(dim.get(j).getNom())) {
			my.write("Question"+q+":"+qst.get(q).getQest()+"\n");
			for(int ch=0;ch<choixx.size();ch++) {
				if(choixx.get(ch).getQstion().equals(qst.get(q).getQest())) {
					my.write(choixx.get(ch).getChoix()+"\n");
				}else {
				my.write("faible __ __ __ __ __ __ __ eleve.\n");
				}}}
		}
		}
	}
	//////hypothese
	if(hyp.size()==0) {
		my.write("pas des hypotheese et des moderateur pour ce modele.");
	}else {
	for(int hp=0;hp<hyp.size();hp++) {
		my.write("hypothese enonce : "+hyp.get(hp).getEnonceHypothse()+"  ");
		my.write("Dimonsion cible : "+hyp.get(hp).getDimonsionindp()+"  ");
		my.write("Dimonsion dintintion : "+hyp.get(hp).getDimonsiondep()+"  ");
		for(int ch=0;ch<Mod.size();ch++) {
			if(Mod.get(ch).getEnoncehypothesees().equals(hyp.get(hp).getEnonceHypothse())) {
				my.write("Moderateur : "+Mod.get(ch).getModerateur() +" ");
			}	}	}}
		
	}
	my.close();
	System.out.println("Save as file: " + fileToSave.getAbsolutePath());
	}else {
	//dans esi deja existe 
	System.out.println("File  not created");
	}    
	}
}

public void dwolnolad_excel(Form f,Article a,ArrayList<Auteur>bf,ArrayList<Dimonsion> dim,ArrayList<Cretaire> Cre,ArrayList<Qstionne> qst,
		ArrayList<Hypothse>hyp,ArrayList<choix>choixx,ArrayList<Moderateur>Mod) throws IOException{
	int index=5;
	JFrame parentFrameE = new JFrame();		 
	JFileChooser fileChooserE = new JFileChooser();
	fileChooserE.setDialogTitle("Specifier un fichier a enregistrer");   		 
	int userSelectionE = fileChooserE.showSaveDialog(parentFrameE);		 
	if (userSelectionE == JFileChooser.APPROVE_OPTION) {
	File fileToSaveE = fileChooserE.getSelectedFile();  
	File myObjeD= new File(fileToSaveE.getAbsolutePath());

	if(myObjeD.createNewFile()) {
	XSSFWorkbook workbook = new XSSFWorkbook();

	//Create a blank sheet
	XSSFSheet sheet = workbook.createSheet("Modele Data");

	//This data needs to be written (Object[])
	Map< Integer, Object[]> data = new TreeMap<Integer ,Object[]>();
	if(f!=null) {		    	
	data.put(index++, new Object[]{"Nom  de modele ",f.getnomModel()});
	data.put(index++, new Object[]{"Descreption   de modele ",f.getExplain()});
	data.put(index++, new Object[]{"",""});
	if(a!=null) {
	data.put(index++, new Object[]{"title article",a.getTitle()});
	data.put(index++, new Object[]{"Resume",a.getResume()});
	data.put(index++, new Object[]{"MoteCle",a.getMotecle()});
	data.put(index++, new Object[]{"DOI",a.getDoi()});
	data.put(index++, new Object[]{"Issn",a.getIssn()});
	data.put(index++, new Object[]{"url",a.getUrl()});
	data.put(index++, new Object[]{"Reference bibloigraphique",a.getRefernceArticle()});
	}
	data.put(index++, new Object[]{""});
	if(bf!=null) {
	data.put(index++, new Object[]{"Nom ","Prenom","Universite","Position","Date de naissance"});
	for(int au=0;au<bf.size();au++) {
		data.put(index++, new Object[]{bf.get(au).getNom(),bf.get(au).getPrenom(),bf.get(au).getUnivers(),bf.get(au).getPosition(),bf.get(au).getDatedenaissance()});
	}
	}
	data.put(index++, new Object[]{""});	
data.put(index++, new Object[]{"Nom Dimonstion ","Descreption De dimonsion","Dimonstion mere","Qeustion","Choix"});
for(int j =0;j<dim.size();j++) {
	if(dim.get(j).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Dimension")  ) {	
		data.put(index++, new Object[] {dim.get(j).getNom(),dim.get(j).getDescreption(),dim.get(j).getNommere(),"",""});	
		for(int i=0;i<dim.size(); i++) {
		if((dim.get(i).getNommere().equals(dim.get(j).getNom())) && 
	    (dim.get(i).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Dimension")) ){		
			data.put(index++, new Object[] {dim.get(i).getNom(),dim.get(i).getDescreption(),dim.get(i).getNommere(),"",""});				
	}
if(dim.get(i).getNommere().equals(dim.get(j).getNom()) && 
		(dim.get(i).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Critere"))){
		//afficher qstion 
		for(int q=0; q<qst.size();q++) {
		if(qst.get(q).getCretaire().equals(dim.get(i).getNom())) {
			if(choixx.size()>0) {
				for(int ch=0;ch<choixx.size();ch++) {
					if(choixx.get(ch).getQstion().equals(qst.get(q).getQest())) {
						data.put(index++, new Object[] {dim.get(i).getNom(),dim.get(i).getDescreption(),dim.get(i).getNommere(),qst.get(q).getQest(),choixx.get(ch).getChoix()});
					}}
			}else {
				data.put(index++, new Object[] {dim.get(i).getNom(),dim.get(i).getDescreption(),dim.get(i).getNommere(),qst.get(q).getQest(),"faible __ __ __ __ __ __ __ eleve."});
			}
		}
	}
			}

}
	}
	if((dim.get(j).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Critere")) && ((dim.get(j).getNommere()==null))) {	

		//afficher qstion 
		for(int q=0; q<qst.size();q++) {
		if(qst.get(q).getCretaire().equals(dim.get(j).getNom())) {
		for(int ch=0;ch<choixx.size();ch++) {
			if(choixx.get(ch).getQstion().equals(qst.get(q).getQest())) {
				data.put(index++, new Object[] {dim.get(j).getNom(),dim.get(j).getDescreption(),dim.get(j).getNommere(),qst.get(q).getQest(),choixx.get(ch).getChoix()});

			}else {
				data.put(index++, new Object[] {dim.get(j).getNom(),dim.get(j).getDescreption(),dim.get(j).getNommere(),qst.get(q).getQest(),"faible __ __ __ __ __ __ __ eleve."});
			}}}
	}
	}
}

	}
	
	data.put(index++, new Object[]{""});	
data.put(index++, new Object[]{"hypothese enonce ","Dimonsion cible","Dimonsion dintintion ","Moderateur"});
	
if(hyp.size()==0) {
	data.put(index++, new Object[]{""});
}else {
for(int hp=0;hp<hyp.size();hp++) {
	data.put(index++, new Object[]{hyp.get(hp).getEnonceHypothse(),hyp.get(hp).getDimonsionindp(),hyp.get(hp).getDimonsiondep()});
	for(int ch=0;ch<Mod.size();ch++) {
		if(Mod.get(ch).getEnoncehypothesees().equals(hyp.get(hp).getEnonceHypothse())) {
			data.put(index++, new Object[]{hyp.get(hp).getEnonceHypothse(),hyp.get(hp).getDimonsionindp(),hyp.get(hp).getDimonsiondep(),Mod.get(ch).getModerateur()});

		}
	}
		
	}
}
	//Iterate over data and write to sheet
	Set<Integer> keyset = data.keySet();
	int rownum = 0;
	for (Integer key : keyset) 
	{//create a row of excelsheet
	Row row = sheet.createRow(rownum++);
	//get object array of prerticuler key
	Object[] objArr = data.get(key);
	int cellnum = 0;
	for (Object obj : objArr) 
	{Cell cell = row.createCell(cellnum++);
	if (obj instanceof String) {cell.setCellValue((String) obj);}
	else if (obj instanceof Integer) 
	{cell.setCellValue((Integer) obj);}}}
	try 
	{FileOutputStream out = new FileOutputStream(myObjeD);
	workbook.write(out);out.close();
	System.out.println(" written successfully on disk.");} 
	catch (Exception e)
	{e.printStackTrace();}  }
	}
}

public void dwolnolad_world(Form f,Article a,ArrayList<Auteur>bf,ArrayList<Dimonsion> dim,ArrayList<Cretaire> Cre,ArrayList<Qstionne> qst,
		ArrayList<Hypothse>hyp,ArrayList<choix>choixx,ArrayList<Moderateur>Mod) throws FileNotFoundException, IOException{
	JFrame parentFrameD = new JFrame();		 
	JFileChooser fileChooserD = new JFileChooser();
	fileChooserD.setDialogTitle("Specifier un fichier a enregistrer");   		 
	int userSelectionD = fileChooserD.showSaveDialog(parentFrameD);		 
	if (userSelectionD == JFileChooser.APPROVE_OPTION) {
	File fileToSaveD = fileChooserD.getSelectedFile();  
	File myObjeD= new File(fileToSaveD.getAbsolutePath());
	if(myObjeD.createNewFile()) {
	XWPFDocument document = new XWPFDocument();
	////wrie the docmen infront system
	FileOutputStream out =  new FileOutputStream(myObjeD);
	//create paragraph
	XWPFParagraph paragraph = document.createParagraph();	
	if(f!=null) {
	//Set Bold an Italic
	XWPFRun paragraphOneRunOne = paragraph.createRun();
	paragraphOneRunOne.setBold(true);
	paragraphOneRunOne.setText("Le nom model est: "+f.getnomModel());
	paragraphOneRunOne.addBreak();
	paragraphOneRunOne.setText("Descreption: "+f.getExplain());
	paragraphOneRunOne.addBreak();
	if(a !=null) {
		paragraphOneRunOne.setText("L'article de titre : "+a.getTitle());
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("Resume : "+a.getResume());
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("Mote cle : "+a.getMotecle());
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("DOI: "+a.getDoi());
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("Isnn: "+a.getIssn());
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("Url: "+a.getUrl());
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText("Referencebibloigraphique: "+a.getRefernceArticle());
		paragraphOneRunOne.addBreak();
	}
	if(bf!=null) {
	paragraphOneRunOne.setText("Les auterus : ");
	paragraphOneRunOne.addBreak();
	for(int au=0;au<bf.size();au++) {
	
		paragraphOneRunOne.setText("Nom "+bf.get(au).getNom()+""+bf.get(au).getPrenom()+"Universite "+bf.get(au).getUnivers()); 
		paragraphOneRunOne.addBreak();
	}}

	paragraphOneRunOne.setText("Dimonsiton et itmes:  ");
	paragraphOneRunOne.addBreak();
	for(int j =0;j<dim.size();j++) {
		
		if(dim.get(j).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Dimension")  ) {	

			paragraphOneRunOne.setText("Dimonstion"+j+":"+dim.get(j).getNom());
			paragraphOneRunOne.addBreak();
			paragraphOneRunOne.setText(dim.get(j).getDescreption());
			paragraphOneRunOne.addBreak();
			for(int i=0;i<dim.size(); i++) {
			if((dim.get(i).getNommere().equals(dim.get(j).getNom())) && 
		    (dim.get(i).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Dimension")) ){		
				paragraphOneRunOne.setText("Dimonstion"+i+":"+dim.get(i).getNom());
				paragraphOneRunOne.addBreak();
				paragraphOneRunOne.setText(dim.get(i).getDescreption());
				paragraphOneRunOne.addBreak();				
		}
	if(dim.get(i).getNommere().equals(dim.get(j).getNom()) && 
			(dim.get(i).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Critere"))){
		paragraphOneRunOne.setText("Sous dimension"+i+":"+dim.get(i).getNom());
		paragraphOneRunOne.addBreak();
		paragraphOneRunOne.setText(dim.get(i).getDescreption());
		paragraphOneRunOne.addBreak();
			
			//afficher qstion 
			for(int q=0; q<qst.size();q++) {
			if(qst.get(q).getCretaire().equals(dim.get(i).getNom())) {
				paragraphOneRunOne.setText("Question"+q+":"+qst.get(q).getQest());
				paragraphOneRunOne.addBreak();
			for(int ch=0;ch<choixx.size();ch++) {
				if(choixx.get(ch).getQstion().equals(qst.get(q).getQest())) {
					paragraphOneRunOne.setText(choixx.get(ch).getChoix());
					paragraphOneRunOne.addBreak();
				}else {
					paragraphOneRunOne.setText("faible __ __ __ __ __ __ __ eleve.");
					paragraphOneRunOne.addBreak();
			
				}}}	}}}}
		if((dim.get(j).getRdftype().equals("http://www.semanticweb.org/user/ontologies/2020/6/fin.owl#Critere")) && ((dim.get(j).getNommere()==null))) {	
			paragraphOneRunOne.setText("Cretaire "+j+":"+dim.get(j).getNom());
			paragraphOneRunOne.addBreak();
			paragraphOneRunOne.setText("Descreption: "+dim.get(j).getDescreption());
			paragraphOneRunOne.addBreak();
			//afficher qstion 
			for(int q=0; q<qst.size();q++) {
			if(qst.get(q).getCretaire().equals(dim.get(j).getNom())) {
				paragraphOneRunOne.setText("Question"+q+":"+qst.get(q).getQest());
				paragraphOneRunOne.addBreak();
			for(int ch=0;ch<choixx.size();ch++) {
				if(choixx.get(ch).getQstion().equals(qst.get(q).getQest())) {
					paragraphOneRunOne.setText(choixx.get(ch).getChoix());
					paragraphOneRunOne.addBreak();				
				}else {
					paragraphOneRunOne.setText("faible __ __ __ __ __ __ __ eleve.");
					paragraphOneRunOne.addBreak();	
				}}}	}	}}

	for(int hp=0;hp<hyp.size();hp++) {
		paragraphOneRunOne.setText("hypothese enonce : "+hyp.get(hp).getEnonceHypothse()+"  ");
		paragraphOneRunOne.setText("Dimonsion cible : "+hyp.get(hp).getDimonsionindp()+"  ");
		paragraphOneRunOne.setText("Dimonsion dintintion : "+hyp.get(hp).getDimonsiondep()+"  ");
		for(int ch=0;ch<Mod.size();ch++) {
			if(Mod.get(ch).getEnoncehypothesees().equals(hyp.get(hp).getEnonceHypothse())) {
				
				paragraphOneRunOne.setText("Moderateur : "+Mod.get(ch).getModerateur() +" ");
			}}
		paragraphOneRunOne.addBreak();	
		}		
	}
	document.write(out);
	out.close();		
	System.out.println("docx written successully");
	}}
}
}
