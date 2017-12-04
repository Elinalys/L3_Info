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
		- Clique droit sur le Java Projet dans le Package Explorer (à gauche).
		- Properties > Java Build Path > Add .jar(s)
		
Corriger :
	- Les ftl ne sont pas en UTF-8 (où le probleme avec les accents) ;)
*/

public class Main
{
	public static void main(String[] args)
	{
		Dao.ping();
		
		get("/listes", (request, response) -> {
	        response.status(200);
	        response.type("text/html");
	        return Vueweb.affichageListes(Dao.getListes());
	    });
		
		get("/liste:", (request, response) -> {
	        response.status(200);
	        response.type("text/html");
	        return Vueweb.affichageListes(Dao.getListes());
	    });
		
		get("/listes", (request, response) -> {
	        response.status(200);
	        response.type("text/html");
	        return Vueweb.affichageListes(Dao.getListes());
	    });
		
		get("/element", (request, response) -> {
	        response.status(200);
	        response.type("text/html");
	        return Vueweb.affichageListes(Dao.getListes());
	    });
	}
}