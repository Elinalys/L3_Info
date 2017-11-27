package Controller;

import java.util.List;

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
		- Clique droit sur le Java Projet dans le Package Explorer (ï¿½ gauche).
		- Properties > Java Build Path > Add .jar(s)
		
Corriger :
	- Les ftl ne sont pas en UTF-8 (où le probleme avec les accents) ;)
*/

public class Main
{
	public static void main(String[] args)
	{
		Dao.ping();
		
		//Vueweb.affichageElement(Dao.getElements().get(0));
		Vueweb.affichageListe(Dao.getListes().get(0));
	}
}