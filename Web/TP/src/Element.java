import java.util.Date;

public class Element {
	private final Date dateCreation;
	private final Date dateModification;
	private String titre;
	private String description;
	private Liste MyList;
	
	public Element() {
		super();
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Liste getMyList() {
		return MyList;
	}
	public void setMyList(Liste myList) {
		MyList = myList;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public Date getDateModification() {
		return dateModification;
	}

	

}