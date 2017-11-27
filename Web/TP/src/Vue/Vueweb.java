package Vue;

import Modele.Dao;
import Modele.Metier.*;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public class Vueweb {
	public void afficher() {

		Element test = Dao.getElements().get(0);
		List<Liste> liste = Dao.getCompleteListes();
		//List<Element> elements = Dao.getElementsParListe(liste.);
		
        Configuration cfg = new Configuration();

        // Where do we load the templates from:
        cfg.setClassForTemplateLoading(Vueweb.class, "templates");

        // Some other recommended settings:
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.FRANCE);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // 2. Proccess template(s)
        Map<String, Object> input = new HashMap<String, Object>();

        input.put("title", test);
        input.put("listes", liste);
//        intput.put()


        // 2.2. Get the template
        Template template;
        Writer fileWriter;

        try {
         template = cfg.getTemplate("complet.ftl");
         
        // 2.3. Generate the output

        // Write output to the console
        Writer consoleWriter = new OutputStreamWriter(System.out);
        template.process(input, consoleWriter);

        // For the sake of example, also write output into a file:
        fileWriter = new FileWriter(new File("output.html"));
        
        template.process(input, fileWriter);
        
        fileWriter.close();
        }catch(Exception ex)
        { System.out.println(ex.getMessage()); }
	}
}
