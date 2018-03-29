import java.util.ArrayList;

public class Arbre {
	private ArrayList<int[]> parents;
	
	public Arbre() {
		this.parents = new ArrayList<int[]>();
	}

	public void ajouter(int enfant, int parent, int poids) {
		this.parents.add(new int[]{enfant, parent, poids});
	}
	
	public ArrayList<int[]> getArbre() { return this.parents; }
	
	@Override
	public String toString() {
		String info = new String();
		
		for (int[] liaison : this.parents) {
			info += Integer.toString(liaison[1]) + " -> " + Integer.toString(liaison[0]) + " (" + Integer.toString(liaison[2]) +")\n";
		}
		
		return info;
	}
}
