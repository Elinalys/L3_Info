package Controller;

import java.util.List;

import Modele.DAO;
import Modele.Metier.*;

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
*/

public class Main
{
	public static void main(String[] args)
	{
		DAO.ping();
		
		List<Liste> listes = DAO.getCompleteListes();
		for (Liste l : listes)
		{
			System.out.println(l);
		}
	}
}