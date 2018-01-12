package Controller;

import static spark.Spark.*;

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

		get("/", (request, response) -> {
			response.status(200);
			response.type("text/html");
			return Vueweb.affichageListes(Dao.getListes());
		});

		get("/listes", (request, response) -> {
	        response.status(200);
	        response.type("text/html");
	        return Vueweb.affichageListes(Dao.getListes());
	  });
		
		// get("/liste/:nom", (request, response) -> {
	 //        response.status(200);
	 //        response.type("text/html");
	 //        return Vueweb.affichageListe(Dao.getElementsParListe(request.params(":nom"))); // marche pas getListe..
	 //    });

		// get("/liste/:nom", (request, response) -> {
		// 	response.status(200);
		// 	response.type("text/html");
		// 	Liste liste = new Liste();
		// 	liste.setTitre(":nom");
		// 	return Vueweb.affichageListe(Dao.getElementsParListe(liste)); // marche pas getListe.. puis getElementparListe ?
		// });
		
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
		
		// delete("/element/:nom", (request, response) -> {
	 //        response.status(200);
	 //        Dao.supprimerElement(Dao.getElement(request.params(":nom")));
	 //        return Vueweb.affichageElements(Dao.getElements());
	 //    });

		delete("/supprimerListe", (request, response) -> {
			System.out.println(request.body()); // request body sent by the client);
			Liste liste = Dao.getListe(request.queryParams("titreMaListe"));
			response.status(200);
			Dao.supprimerListe(liste);
			return Vueweb.affichageListes(Dao.getListes());
		});

		get("/complet", (request, reponse) -> {
			reponse.status(200);
			reponse.type("text/html");
			return Vueweb.affichageListesCompletes(Dao.getCompleteListes());
		});

		post("/creerListe", (request, reponse) -> {
			Liste liste = new Liste();
			liste.setTitre(request.queryParams("titreListe"));
			liste.setDescription(request.queryParams("descriptionListe"));
			Dao.creerListe(liste);
			return Vueweb.affichageListes(Dao.getListes());
		});

		post("/creerElement", (request, reponse) -> {
			Element element = new Element();
			Liste liste = Dao.getListe(request.queryParams("titreMaListe"));
			element.setTitre(request.queryParams("titreElement"));
			element.setDescription(request.queryParams("descriptionElement"));
			element.setMyList(Dao.getListe("titreMaListe"));
			// System.out.println("Debug " + Dao.getListe(request.queryParams("titreMaListe")));
			Dao.creerElement(element,Dao.getIDListe(liste));
			return Vueweb.affichageListes(Dao.getListes());
		}); 
	}

}