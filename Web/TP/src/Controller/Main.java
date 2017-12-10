package Controller;

import static spark.Spark.*;

import Modele.Dao;
import Vue.Vueweb;

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
		
Corriger :
	- Les ftl ne sont pas en UTF-8 (oÃ¹ le probleme avec les accents) ;)
*/

public class Main
{
	public static void main(String[] args)
	{
		/*
		*	v	Afficher Listes
		*	v	Afficher Elements (Est-ce vraiment utile ?)
		*
		*	~	Afficher Liste
		*	~	Afficher Element
		*
		*	x	Modifie Liste		[Update]
		*	x	Modifie Element		[Update]
		*	 
		*	x	Supprime Liste		[Delete]
		*	x	Supprime Element	[Delete]
		*
		*	x	Ajouter Element à une liste
		*	x	Créer élément		[Post]
		*	x	Créer élément		[Post]
		*
		* aide+
		* get("/login", (request, response) -> {
		    return new ModelAndView(new HashMap<>(), "templates/base.vtl");
		}, new VelocityTemplateEngine());
		
		post("/login", (request, response) -> {
		    String a, b, c;
		    a = request.queryParams("txt_username");
		    b = request.queryParams("txt_password");
		    c = request.queryParams("txt_memberid");
		    return String.join(" AND ", a, b, c);
		});
				*
		*
		*/
		
		get("/listes", (request, response) -> {
	        response.status(200);
	        response.type("text/html");
	        return Vueweb.affichageListes(Dao.getListes());
	    });
		
		get("/liste/:nom", (request, response) -> {
	        response.status(200);
	        response.type("text/html");
	        return Vueweb.affichageListe(Dao.getListe(request.params(":nom")));
	    });
		
		get("/elements", (request, response) -> {
	        response.status(200);
	        response.type("text/html");
	        return Vueweb.affichageElements(Dao.getElements());
	    });
		
		get("/element/:nom", (request, response) -> {
	        response.status(200);
	        response.type("text/html");
	        return Vueweb.affichageElement(Dao.getElement(request.params(":nom")));
	    });
		
		delete("/element/:nom", (request, response) -> {
	        response.status(200);
	        Dao.supprimerElement(Dao.getElement(request.params(":nom")));
	        return Vueweb.affichageElements(Dao.getElements());
	    });
	}
}