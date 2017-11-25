package Modele.Metier;

import java.util.Date;

public class Element
{
	// Attributs
	private String titre;
	private String description;
	private Date dateCreation;
	private Date dateModification;	
	private Liste MyList;
	
	// Méthodes
	public Element()
	{
		super();
	}
	
	// Accesseurs
	public String getTitre()
	{
		return titre;
	}
	public void setTitre(String titre)
	{
		this.titre = titre;
	}
	
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public Liste getMyList()
	{
		return MyList;
	}
	public void setMyList(Liste myList)
	{
		MyList = myList;
	}
	
	public Date getDateCreation()
	{
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation)
	{
		this.dateCreation = dateCreation;
	}

	public Date getDateModification()
	{
		return dateModification;
	}
	public void setDateModification(Date dateModification)
	{
		this.dateModification = dateModification;
	}
	
	@Override
	public String toString()
	{
		return titre + " : " + description + " (Créé le " + dateCreation + ", derniere modification le " + dateModification + ").";
	}
}