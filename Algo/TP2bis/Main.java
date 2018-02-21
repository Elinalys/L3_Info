import java.util.Date;

public class Main {

	public static void main(String[] argc) {	
		String A = new String();
		String B = new String();
		/*A = "tourte";
		B = "tartre";*/
		A = "blablacar";
		B = "blatardar";
		/* A = "unlongmotenfrancaispouranalyser";
		B = "unelongexpressionfrancaisepouranalyser" */
		
		
		double debut;		
		Comparaison comp = new Comparaison();

		
		// test pour la fonction recursive
		// date1 = new Date();
		debut = System.nanoTime();
		System.out.println("La distance selon la fonction recursive: " + comp.Recursif(A, B)); 
		System.out.println(System.nanoTime()-debut);
		//date2 = new Date();
		// System.out.println("Duree : " + Integer.toString(Integer.parseInt(df.format(date2)) - Integer.parseInt(df.format(date1))) + " ms.\n");
		System.out.println();
		
		// test pour la fonction recusive avec memoisation
		debut = System.nanoTime();
		//date1 = new Date();
		System.out.println("La distance selon la fonction recursive avec memoisation: " + comp.RecursifMemo(A, B, 0)); 
		// date2 = new Date();
		System.out.println(System.nanoTime()-debut);
		// System.out.println("Duree : " + Integer.toString(Integer.parseInt(df.format(date2)) - Integer.parseInt(df.format(date1))) + " ms.\n");
				
		// test pour la fonction recusive memo: partie memoisation
		System.out.println("Et la memoisation : ");
		System.out.println(Comparaison.tableauToString(comp.D)); 
		Comparaison.cheminRecMemo(A.toCharArray(), B.toCharArray(), comp.D);
		System.out.println();
		
		// test pour la fonction dynamique
		//date1 = new Date();
		debut = System.nanoTime();
		System.out.println("La distance selon la fonction dynamique: ");
		System.out.println(Comparaison.Dynamique(A.toCharArray(), B.toCharArray())[A.length()][B.length()]); 
		// date2 = new Date();
		System.out.println(System.nanoTime()-debut);
		// System.out.println("Duree : " + Integer.toString(Integer.parseInt(df.format(date2)) - Integer.parseInt(df.format(date1))) + " ms.\n");
		System.out.println("La structure garde les donnes: ");
		System.out.println(Comparaison.tableauToString(Comparaison.Dynamique(A.toCharArray(), B.toCharArray()))); 
		System.out.println();
		
		// test pour la fonction des chemins
		int[][] D = new int[A.length()+1][];
		D = Comparaison.Dynamique(A.toCharArray(), B.toCharArray());
		System.out.println(Comparaison.tableauToString(D)); 
		Comparaison.chemin(A.toCharArray(), B.toCharArray(), D);
	}
}
