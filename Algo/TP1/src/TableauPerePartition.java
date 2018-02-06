public class TableauPerePartition implements GestionPartition {
	private Integer[] peres;
	private Integer[] hauteurs;
	
	public TableauPerePartition(int s) {
		this.peres = new Integer[s];
		this.hauteurs = new Integer[s];
		for (int i = 0; i < s; i++) peres[i] = i;
		for (int i = 0; i < s; i++) hauteurs[i] = 1;
	}

	public int getClasse(int e) {
		while(peres[e] != e) e = peres[e];
		return e;
	}
	
	public void fusion(int c1, int c2) {
		int classe1 = getClasse(c1);
		int classe2 = getClasse(c2);
		
		if (classe1 == classe2)
			return;
		
		if (hauteurs[classe1] > hauteurs[classe2]) {
			peres[classe2] = classe1;
		}
		else if (hauteurs[classe1] < hauteurs[classe2]) {
			peres[classe1] = classe2;
		}
		else {
			peres[classe1] = classe2;
			hauteurs[classe2] = hauteurs[classe2]+1;
		}
	}

	@Override
	public String toString() {
		String retour = new String();
		retour += "\nIndex : \n| ";
		for (int i = 0; i < this.peres.length; i++)
			retour+= i + " | ";
		retour += "\nPeres : \n| ";
		for (int i = 0; i < peres.length; i++)
			retour += Integer.toString(peres[i]) + " | ";
		retour += "\nHauteurs : \n| ";
		for (int i = 0; i < hauteurs.length; i++)
			retour += Integer.toString(hauteurs[i]) + " | ";
		return retour;
	}


}