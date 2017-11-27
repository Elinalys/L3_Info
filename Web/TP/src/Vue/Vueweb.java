package Vue;

import Modele.Metier.*;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class Vueweb
{
	private static Configuration config;
	
	private static Configuration getConfig()
	{
		if (config == null)
		{
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
	
	        cfg.setClassForTemplateLoading(Vueweb.class, "templates");
	        cfg.setDefaultEncoding("UTF-8");
	        cfg.setLocale(Locale.FRANCE);
	        
	        config = cfg;
		}
        
        return config;
	}
	
	public static void creerFichier(Configuration config, String templateFile, String outputFile, Map<String, Object> inputs)
	{
		Template template;
        Writer fileWriter;

        try
        {
			template = config.getTemplate(templateFile);
			 
			Writer consoleWriter = new OutputStreamWriter(System.out);
			template.process(inputs, consoleWriter);
			
			fileWriter = new FileWriter(new File(outputFile));
			
			template.process(inputs, fileWriter);
			
			fileWriter.close();
        }
        catch(Exception ex)
        {
        	System.out.println(ex.getMessage());
    	}
	}
	
	public static void affichageListesCompletes(List<Liste> listes)
	{		
        Configuration config = getConfig();
        String templateFile = "complet.ftl";
        String outputFile = "output.html";
        
        Map<String, Object> inputs = new HashMap<String, Object>();
        inputs.put("listes", listes);
        inputs.put("title", "Listes et éléments");

        creerFichier(config, templateFile, outputFile, inputs);
	}
	
	public static void affichageListe(Liste liste)
	{
		Configuration config = getConfig();
        String templateFile = "liste.ftl";
        String outputFile = "output.html";

        Map<String, Object> inputs = new HashMap<String, Object>();
        inputs.put("liste", liste);
        inputs.put("title", "Liste " + liste.getTitre());

        creerFichier(config, templateFile, outputFile, inputs);
	}
	
	public static void affichageElement(Element element)
	{
		Configuration config = getConfig();
        String templateFile = "element.ftl";
        String outputFile = "output.html";

        Map<String, Object> inputs = new HashMap<String, Object>();
        inputs.put("element", element);
        inputs.put("title", "Liste " + element.getTitre());

        creerFichier(config, templateFile, outputFile, inputs);
	}
}
