import java.util.ArrayList;

public class Liste {
	private String titre;
	private String description;
	private ArrayList<Element> listeElment;

	public Liste() {

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

	public ArrayList<Element> getListeElment() {
		return listeElment;
	}

	public void setListeElment(ArrayList<Element> listeElment) {
		this.listeElment = listeElment;
	}

	

	
}
