package Controller;

import static spark.Spark.*;

import java.awt.Desktop;
import java.net.URL;

import Modele.Dao;
import Vue.Vueweb;
import Modele.Metier.*;

/*
BDD : 
	- Base : Listes
	- Id : admin
	- Pwd : password
	
Infos :
	- NE PAS ETRE CONNECTER SUR NAVIGATEUR QUAND ON EXECUTE DES REQUETES AVEC L'APPLI!
	- Pour importer des .jar :
		- Clique droit sur le Java Projet dans le Package Explorer (Ã  gauche).
		- Properties > Java Build Path > Add .jar(s)
		- spark : http://127.0.0.1:4567/listes/
		- h2 : http://192.168.1.21:8082/login.jsp
		
*/

public class Main
{
	public static void main(String[] args)
	{
		get("/", (request, response) -> {
			response.status(200);
			response.type("text/html");
			return Vueweb.affichageListes(Dao.getListes());
		});

		// obsolète
		get("/listes", (request, response) -> {
	        response.status(200);
	        response.type("text/html");
	        return Vueweb.affichageListes(Dao.getListes());
	  	});

		get("/liste/:nom", (request, response) -> {
			response.status(200);
			response.type("text/html");
			Liste liste = Dao.getListe(request.params(":nom"));
			return Vueweb.affichageListeDetail(liste ,Dao.getElementsParListe(liste));
		});
		
		get("/supprimerListe/:nom", (request, response) -> { 
			response.status(200);
			response.type("text/html");
			Liste liste = Dao.getListe(request.params(":nom"));
			for (Element elt : Dao.getElementsParListe(liste)) {
				Dao.supprimerElement(elt);
			}
			Dao.supprimerListe(liste);
			response.redirect("/");
			return Vueweb.affichageListes(Dao.getListes());
		});
		get("/supprimerElement/:liste/:nom", (request, response) -> {
			response.status(200);
			response.type("text/html");
			Element element = Dao.getElement(request.params(":nom"));
			Liste liste = Dao.getListe(request.params(":liste"));
			Dao.supprimerElement(element);
			response.redirect("/liste/"+ request.params(":liste"));
			return Vueweb.affichageListeDetail(liste ,Dao.getElementsParListe(liste));
		});

		get("/complet", (request, response) -> {
			response.status(200);
			response.type("text/html");
			return Vueweb.affichageListesCompletes(Dao.getCompleteListes());
		});

		post("/creerListe", (request, response) -> {
			response.status(201);
			Liste liste = new Liste();
			liste.setTitre(request.queryParams("titreListe"));
			liste.setDescription(request.queryParams("descriptionListe"));
			Dao.creerListe(liste);
			response.redirect("/");
			return Vueweb.affichageListes(Dao.getListes());
		});
		
		post("/:liste/modifierElement", (request, response) -> {
			response.status(200);
			Element element = Dao.getElement(request.queryParams("titreMonElement"));
			int idElement = Dao.getIDElement(element);
			System.out.println(request.queryParams());
			element.setTitre(request.queryParams("titreElement"));
			element.setDescription(request.queryParams("descriptionElement"));
			Dao.updateElement(element, idElement);
			response.redirect("/liste/"+ request.params(":liste"));
			return Vueweb.affichageListes(Dao.getListes());
		});
		
		post("/modifierListe", (request, response) -> {
			response.status(200);
			Liste liste = Dao.getListe(request.queryParams("titreMaListe"));
			int idListe = Dao.getIDListe(liste);
			System.out.println(request.queryParams());
			liste.setTitre(request.queryParams("titreListe"));
			liste.setDescription(request.queryParams("descriptionListe"));
			Dao.updateListe(liste, idListe);
			response.redirect("/");
			return Vueweb.affichageListes(Dao.getListes());
		});

		post("/creerElement", (request, response) -> {
			response.status(201);
			Element element = new Element();
			Liste liste = Dao.getListe(request.queryParams("titreMaListe"));
			element.setTitre(request.queryParams("titreElement"));
			element.setDescription(request.queryParams("descriptionElement"));
			element.setMyList(Dao.getListe("titreMaListe"));
			Dao.creerElement(element,Dao.getIDListe(liste));
			response.redirect("/");
			return Vueweb.affichageListes(Dao.getListes());
		});

		try {
			Desktop.getDesktop().browse(new URL("http://localhost:4567").toURI());
		} catch (Exception e) {}
	}

}