package Controller;

import java.util.List;

import Modele.Dao;
import Modele.Metier.*;
import Vue.Vueweb;

/*
BDD : 
	- Base : Listes
	- Id : admin
	- Pwd : password
	
Infos :
	- NE PAS ETRE CONNECTER SUR NAVIGATEUR QUAND ON EXECUTE DES REQUETES AVEC L'APPLI!
	- Pour importer des .jar :
		- Clique droit sur le Java Projet dans le Package Explorer (� gauche).
		- Properties > Java Build Path > Add .jar(s)
*/

public class Main
{
	public static void main(String[] args)
	{
		Dao.ping();
		
		Vueweb web = new Vueweb();
		web.afficher();
		
		//List<Liste> listes = Dao.getCompleteListes();
		/*for (Liste l : listes)
		{
			System.out.println(l);
		}*/
		
		/*List<Element> elements = Dao.get();
		for(Element ele : elements)
			System.out.println(ele);*/
	}
}