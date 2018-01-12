package Vue;

import Modele.Metier.*;

import java.io.OutputStreamWriter;
import java.io.StringWriter;
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
	private static boolean debug = false;
	
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
	
	public static String completeTemplate(Configuration config, String templateFile, String outputFile, Map<String, Object> inputs)
	{
		Template template;
        StringWriter stringWriter = new StringWriter();

        try
        {
			template = config.getTemplate(templateFile);
			
			if (debug)
			{
				Writer consoleWriter = new OutputStreamWriter(System.out);
				template.process(inputs, consoleWriter);
			}
			
			template.process(inputs, stringWriter);
        }
        catch(Exception ex)
        {
        	if(debug)
        		System.out.println(ex.getMessage());
        	stringWriter.write(ex.getMessage());
    	}
        
        return stringWriter.toString();
	}
	
	public static String affichageListesCompletes(List<Liste> listes)
	{		
    Configuration config = getConfig();
    String templateFile = "complet.ftl";
    String outputFile = "complet.html";
    
    Map<String, Object> inputs = new HashMap<String, Object>();
    inputs.put("listes", listes);
    inputs.put("title", "Listes et éléments");

    return completeTemplate(config, templateFile, outputFile, inputs);
	}
	
	public static String affichageListe(Liste liste)
	{
	Configuration config = getConfig();
    String templateFile = "liste.ftl";
    String outputFile = "liste.html";

    Map<String, Object> inputs = new HashMap<String, Object>();
    inputs.put("liste", liste);
    inputs.put("title", "Liste " + liste.getTitre());
    //inputs.put("element", element);
    // inputs.put()

    return completeTemplate(config, templateFile, outputFile, inputs);
}
	
	public static String affichageElement(Element element)
	{
		Configuration config = getConfig();
        String templateFile = "element.ftl";
        String outputFile = "element.html";

        Map<String, Object> inputs = new HashMap<String, Object>();
        inputs.put("element", element);
        inputs.put("title", "Élément " + element.getTitre());

        return completeTemplate(config, templateFile, outputFile, inputs);
	}
	
	public static String affichageListes(List<Liste> listes)
	{
		Configuration config = getConfig();
		String templateFile = "listes.ftl";
		String outputFile = "listes.html";
		
		Map<String, Object> inputs = new HashMap<String, Object>();
		inputs.put("listes", listes);
		inputs.put("title", "Listes");
		
		return completeTemplate(config, templateFile, outputFile, inputs);
	}
	
	public static String affichageElements(List<Element> elements)
	{
		Configuration config = getConfig();
		String templateFile = "elements.ftl";
		String outputFile = "elements.html";
		
		Map<String, Object> inputs = new HashMap<String, Object>();
		inputs.put("elements", elements);
		inputs.put("title", "Elements");
		
		return completeTemplate(config, templateFile, outputFile, inputs);
	}
}
	