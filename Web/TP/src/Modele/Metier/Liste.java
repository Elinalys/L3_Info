package Modele.Metier;

import java.util.List;

public class Liste
{
	private String titre;
	private String description;
	private List<Element> listeElements;

	public Liste()
	{

	}

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

	public List<Element> getListeElement()
	{
		return listeElements;
	}

	public void setListeElement(List<Element> listeElment)
	{
		this.listeElements = listeElment;
	}

	@Override
	public String toString()
	{
		String resultat = this.getTitre() + " : " + this.getDescription();
		
		if (listeElements != null && listeElements.size() > 0)
		{
			for (Element ele : listeElements)
			{
				resultat += "\n\t" + ele.toString();
			}
		}
		
		return resultat;
	}
	
	@Override
	public boolean equals(Object other)
	{
		if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Liste))return false;
	    Liste otherListe = (Liste)other;
	    
	    if (otherListe.titre == titre && otherListe.description == description)
	    	return true;
	    else
	    	return false;
	}
}
