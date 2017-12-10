package Modele;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Modele.Metier.*;

import org.sql2o.*;

public class Dao
{
	private static final String databasePath = "jdbc:h2:~/";
	private static final String databaseTable = "Listes";
	private static final String databaseId = "admin";
	private static final String databasePwd = "password";
	private static Sql2o sql2o = new Sql2o(databasePath + databaseTable, databaseId, databasePwd);
	
	public static boolean ping()
	{
		final String query = "SELECT * FROM LISTE;";
		
		try (Connection con = sql2o.open())
		{
		    con.createQuery(query).executeAndFetch(Liste.class);
		}
		catch(Exception ex)
		{
			return false;
		}
		
		return true;
	}
	
	public static List<Liste> getListes()
	{
		final String query = "SELECT TITRE, DESCRIPTION FROM LISTE;";
		List<Liste> listes = new ArrayList<Liste>();
		
		try (Connection con = sql2o.open())
		{
			listes = con.createQuery(query).executeAndFetch(Liste.class);
		}
		catch(Exception ex)
		{
			System.out.println("Erreur requete : " + ex.getMessage());
		}
		
		return listes;
	}
	
	/*
	* Tu fais comme la fonction completeListe pour obtenir tous les éléments
	*/
	public static Liste getListe(String nom)
	{
		final String query = "SELECT TITRE, DESCRIPTION FROM LISTE WHERE TITRE = :titre;";
		Liste liste = null;
		
		try (Connection con = sql2o.open())
		{
			liste = con.createQuery(query).addParameter("titre", nom).executeAndFetchFirst(Liste.class);
		}
		catch(Exception ex)
		{
			System.out.println("Erreur requete : " + ex.getMessage());
		}
		
		return liste;
	}
	
	public static List<Element> getElements()
	{
		final String query = "SELECT TITRE, DESCRIPTION, DATECREATION, DATEMODIFICATION FROM ELEMENT;";
		List<Element> elements = new ArrayList<Element>();
		
		try (Connection con = sql2o.open())
		{
			elements = con.createQuery(query).executeAndFetch(Element.class);
		}
		catch(Exception ex)
		{
			System.out.println("Erreur requete : " + ex.getMessage());
		}
		
		return elements;
	}
	
	public static Element getElement(String nom)
	{
		final String query = "SELECT TITRE, DESCRIPTION, DATECREATION, DATEMODIFICATION FROM ELEMENT WHERE TITRE = :titre;";
		Element element = new Element();
		
		try (Connection con = sql2o.open())
		{
			element = con.createQuery(query).addParameter("titre", nom).executeAndFetchFirst(Element.class);
		}
		catch(Exception ex)
		{
			System.out.println("Erreur requete : " + ex.getMessage());
		}
		
		return element;
	}
	
	public static List<Element> getElementsParListe(Liste liste)
	{
		final String query = "SELECT TITRE, DESCRIPTION, DATECREATION, DATEMODIFICATION FROM ELEMENT WHERE IDLISTE = :IDListe;";
		int IDListe = Dao.getIDListe(liste);
		List<Element> elements = new ArrayList<Element>();
		System.out.println(IDListe);
		
		try (Connection con = sql2o.open())
		{
		    elements = con.createQuery(query).addParameter("IDListe", IDListe).executeAndFetch(Element.class);
		}
		catch(Exception ex)
		{
			System.out.println("Erreur requete : " + ex.getMessage());
		}
		
		return elements;
	}
	
	public static Integer getIDListe(Liste liste)
	{
		final String query = "SELECT ID FROM LISTE WHERE TITRE = :titre AND DESCRIPTION = :description;";
		Integer resultat = 0;
		
		try (Connection con = sql2o.open())
		{
			resultat =  con.createQuery(query).addParameter("titre", liste.getTitre()).addParameter("description", liste.getDescription()).executeScalar(Integer.class);
		}
		catch(Exception ex)
		{
			System.out.println("Erreur requete : " + ex.getMessage());
		}
		
		return resultat;
	}
	
	public static Integer getIDElement(Element element)
	{
		final String query = "SELECT ID FROM ELEMENT WHERE TITRE = :titre AND DESCRIPTION = :description;";
		Integer resultat = 0;
		
		try (Connection con = sql2o.open())
		{
			resultat =  con.createQuery(query).addParameter("titre", element.getTitre()).addParameter("description", element.getDescription()).executeScalar(Integer.class);
		}
		catch(Exception ex)
		{
			System.out.println("Erreur requete : " + ex.getMessage());
		}
		
		return resultat;
	}
	
	public static Integer getNbListes()
	{
		final String query = "SELECT count(ID) FROM LISTE;";
		Integer resultat = 0;
		
		try (Connection con = sql2o.open())
		{
			resultat =  con.createQuery(query).executeScalar(Integer.class);
		}
		catch(Exception ex)
		{
			System.out.println("Erreur requete : " + ex.getMessage());
		}
		
		return resultat;
	}
	
	public static Integer getNbElements()
	{
		final String query = "SELECT count(ID) FROM ELEMENT;";
		Integer resultat = 0;
		
		try (Connection con = sql2o.open())
		{
			resultat =  con.createQuery(query).executeScalar(Integer.class);
		}
		catch(Exception ex)
		{
			System.out.println("Erreur requete : " + ex.getMessage());
		}
		
		return resultat;
	}
	
	public static List<Element> getElementsParDateCreation(Date dateCreation)
	{
		final String query = "SELECT TITRE, DESCRIPTION, DATECREATION, DATEMODIFICATION FROM ELEMENT ORDER BY DATECREATION ASC;";
		List<Element> elements = new ArrayList<Element>();
		
		try (Connection con = sql2o.open())
		{
			elements =  con.createQuery(query).executeAndFetch(Element.class);
		}
		catch(Exception ex)
		{
			System.out.println("Erreur requete : " + ex.getMessage());
		}
		
		return elements;
	}
	
	public static List<Element> getElementsCreeLe(Date dateCreation)
	{
		final String query = "SELECT TITRE, DESCRIPTION, DATECREATION, DATEMODIFICATION FROM ELEMENT WHERE DATECREATION = :dateCreation;";
		List<Element> elements = new ArrayList<Element>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		try (Connection con = sql2o.open())
		{
			elements =  con.createQuery(query).addParameter("dateCreation", formatter.format(dateCreation)).executeAndFetch(Element.class);
		}
		catch(Exception ex)
		{
			System.out.println("Erreur requete : " + ex.getMessage());
		}
		
		return elements;
	}
	
	public static List<Element> getElementsCreeApresLe(Date dateCreation)
	{
		final String query = "SELECT TITRE, DESCRIPTION, DATECREATION, DATEMODIFICATION FROM ELEMENT WHERE DATECREATION > :dateCreation;";
		List<Element> elements = new ArrayList<Element>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		try (Connection con = sql2o.open())
		{
			elements =  con.createQuery(query).addParameter("dateCreation", formatter.format(dateCreation)).executeAndFetch(Element.class);
		}
		catch(Exception ex)
		{
			System.out.println("Erreur requete : " + ex.getMessage());
		}
		
		return elements;
	}
	
	public static List<Element> getElementsCreeAvantLe(Date dateCreation)
	{
		final String query = "SELECT TITRE, DESCRIPTION, DATECREATION, DATEMODIFICATION FROM ELEMENT WHERE DATECREATION < :dateCreation;";
		List<Element> elements = new ArrayList<Element>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		try (Connection con = sql2o.open())
		{
			elements =  con.createQuery(query).addParameter("dateCreation", formatter.format(dateCreation)).executeAndFetch(Element.class);
		}
		catch(Exception ex)
		{
			System.out.println("Erreur requete : " + ex.getMessage());
		}
		
		return elements;
	}
	
	public static void supprimerElement(Element element)
	{
		final String query = "DELETE FROM ELEMENT WHERE TITRE = :titre AND DESCRIPTION = :description;";
		
		try (Connection con = sql2o.open())
		{
			con.createQuery(query).addParameter("titre", element.getTitre()).addParameter("description", element.getDescription()).executeUpdate();
		}
		catch(Exception ex)
		{
			System.out.println("Erreur requete : " + ex.getMessage());
		}
	}
	
	public static void supprimerListe(Liste liste)
	{
		final String query = "DELETE FROM LISTE WHERE TITRE = :titre AND DESCRIPTION = :description;";
		
		try (Connection con = sql2o.open())
		{
			con.createQuery(query).addParameter("titre", liste.getTitre()).addParameter("description", liste.getDescription()).executeUpdate();
		}
		catch(Exception ex)
		{
			System.out.println("Erreur requete : " + ex.getMessage());
		}
	}
	
	public static int updateElement(Element element, int IDElement)
	{
		final String query = "UPDATE ELEMENT SET TITRE = :titre, DESCRIPTION = :description, DATEMODIFICATION = NOW() WHERE ID = :IDElement;";
		int updatedRows = 0;
		
		try (Connection con = sql2o.open())
		{
			updatedRows = con.createQuery(query).addParameter("titre", element.getTitre()).addParameter("description", element.getDescription())
			.addParameter("IDElement", IDElement).executeUpdate().getResult();
		}
		catch(Exception ex)
		{
			System.out.println("Erreur requete : " + ex.getMessage());
		}
		
		return updatedRows;
	}
	
	public static int updateListe(Liste liste, int IDListe)
	{
		final String query = "UPDATE LISTE SET TITRE = :titre, DESCRIPTION = :description WHERE ID = :IDListe;";
		int updatedRows = 0;
		
		try (Connection con = sql2o.open())
		{
			updatedRows = con.createQuery(query).addParameter("titre", liste.getTitre()).addParameter("description", liste.getDescription())
			.addParameter("IDListe", IDListe).executeUpdate().getResult();
		}
		catch(Exception ex)
		{
			System.out.println("Erreur requete : " + ex.getMessage());
		}
		
		return updatedRows;
	}
	
	public static void creerListe(Liste liste)
	{
		final String query = "INSERT INTO LISTE(TITRE, DESCRIPTION) VALUES (:titre, :description);";
		
		try (Connection con = sql2o.open())
		{
			con.createQuery(query).addParameter("titre", liste.getTitre()).addParameter("description", liste.getDescription()).executeUpdate();
		}
		catch(Exception ex)
		{
			System.out.println("Erreur requete : " + ex.getMessage());
		}
	}
	
	public static int creerElement(Element element, int IDListe)
	{
		final String query = "INSERT INTO ELEMENT(TITRE, DESCRIPTION, DATECREATION, DATEMODIFICATION, IDLISTE) VALUES (:titre, :description, NOW(), NOW(), :IDListe);";
		int IDElement = 0;
		
		System.out.println(element.getTitre());
		
		try (Connection con = sql2o.open())
		{
			IDElement = (int)(con.createQuery(query, true).addParameter("titre", element.getTitre()).addParameter("description", element.getDescription())
			.addParameter("IDListe", IDListe).executeUpdate().getKey());
		}
		catch(Exception ex)
		{
			System.out.println("Erreur requete : " + ex.getMessage());
		}
		
		return IDElement;
	}
	
	public static List<Liste> getCompleteListes()
	{
		final String queryDatabaseListe = "SELECT * FROM LISTE;";
		final String queryElements = "SELECT TITRE, DESCRIPTION, DATECREATION, DATEMODIFICATION FROM ELEMENT WHERE IDLISTE = :IDListe;";
		List<DatabaseListe> databaseListes = new ArrayList<DatabaseListe>();
		List<Liste> listes = new ArrayList<Liste>();
		
		try (Connection con = sql2o.open())
		{
			databaseListes = con.createQuery(queryDatabaseListe).executeAndFetch(DatabaseListe.class);
			
			for (DatabaseListe DBListe : databaseListes)
			{
				List<Element> elements = con.createQuery(queryElements).addParameter("IDListe", DBListe.getID()).executeAndFetch(Element.class);
				
				Liste liste = new Liste();
				liste.setTitre(DBListe.getTitre());
				liste.setDescription(DBListe.getDescription());
				liste.setListeElement(elements);
				listes.add(liste);
				
				for (Element element : elements)
				{
					element.setMyList(liste);
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println("Erreur requete : " + ex.getMessage());
		}
		
		return listes;
	}
}

class DatabaseListe extends Liste
{
	private String ID;
	
	public DatabaseListe()
	{ }

	public String getID()
	{ return ID; }
	public void setID(String ID)
	{ this.ID = ID; }
}