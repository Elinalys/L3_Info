public class TableauPartition implements GestionPartition {
	private int[] classes;

	// pour cette initialisation on considère que chaque element fait partie d'une classe distincte
	public TableauPartition(int t) {
		this.classes = new int[t];
		for (int i = 0; i < t; i++) {
			this.classes[i] = i;
		}
	}

	public int getClasse(int e)
	{
		return this.classes[e];
	}
	
	public void fusion(int c1, int c2)
	{
		int classeOrig = this.classes[c1];
		int classeDest = this.classes[c2];

		if (this.classes[c1] < this.classes[c2])
		{
			classeOrig = this.classes[c2];
			classeDest = this.classes[c1];
		}
		
		for(int i = 0; i < this.classes.length; i++)
		{
			if (this.classes[i] == classeOrig) {
				this.classes[i] = classeDest;
			}
		}
	}

	@Override
	public String toString()
	{
		String retour = new String();
		retour += "\nValeurs : \n| ";
		for (int i = 0; i < this.classes.length; i++) {
			retour += Integer.toString(i) + " | ";
		}
		retour += "\nClasses : \n| ";
		for (Integer i : this.classes) {
			retour += Integer.toString(i) + " | ";
		}
		return retour;
	}

}