import java.util.Arrays;
import java.util.Comparator;

public class Graphe {
	// Connaitre n nb aretes et m nb sommets et hauteur
	private Integer[][] aretes;
	private Integer[] sommets;
	
	public Graphe(Integer[] sommets, Integer[][] aretes) {
		this.aretes = aretes;
		this.sommets = sommets;
	}
	
	public Integer[] getSommets() { return this.sommets; }
	public Integer[][] getAretes() { return this.aretes; }
	
	// (ordre croissant)
	public Integer[][]getOrdoredAretes() {
		Integer[][] ordoredAretes = getAretes();
		
		Arrays.sort(ordoredAretes, new Comparator<Integer[]>() {
	    public int compare(final Integer[] entry1, final Integer[] entry2) {
	        final Integer time1 = entry1[2];
	        final Integer time2 = entry2[2];
	        return time1.compareTo(time2);
	    }
		});			
		return ordoredAretes;
	}
	
	public int n() { return this.sommets.length; }
	public int m() { return this.aretes.length; }
}