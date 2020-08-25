package org.modele.evl;

import java.io.IOException;
import java.util.Properties;

import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class Pipeline {
private static Properties properties;
private static String propertiesName="tokenize,ssplit,pos";	
private static StanfordCoreNLP stanfodCoreNLP;

//constrecteur
private Pipeline() {
	
}

static {
	properties =new Properties();
	try {
		properties.load(IOUtils.readerFromString("StanfordCoreNLP-french.properties"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	properties.setProperty("annotators",propertiesName );
}

///methode

public static StanfordCoreNLP getPipeline() {
	if(stanfodCoreNLP == null) {
		stanfodCoreNLP = new StanfordCoreNLP(properties);
	}
return stanfodCoreNLP;
}

}
