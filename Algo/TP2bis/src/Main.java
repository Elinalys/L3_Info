import java.util.Date;

public class Main {

	public static void main(String[] argc) {	
		String A = new String();
		String B = new String();
		A = "tourte";
		B = "tartre";
		/*
		A = "anticonstitutionnellement";
		B = "hexakosioihexekontahexaphobie";
		*/
		/*
		A = "unlongmotenfrancaispouranalyser";
		B = "unelongexpressionfrancaisepouranalyser";
		*/
		double debut;		
		Comparaison comp = new Comparaison();

		// test pour la fonction recursive
		debut = System.nanoTime();
		System.out.println("La distance selon la fonction recursive: " + comp.Recursif(A, B)); 
		System.out.println(System.nanoTime()-debut + " nanosecondes");
		System.out.println();
		
		// test pour la fonction recusive avec memoisation
		debut = System.nanoTime();
		System.out.println("La distance selon la fonction recursive avec memoisation: " + comp.RecursifMemo(A, B, 0)); 
		System.out.println(System.nanoTime()-debut + " nanosecondes");
				
		// partie memoization
		System.out.println("Et la memoization : ");
		System.out.println(Comparaison.tableauToString(comp.D)); 
		
		// test chemins récursif
		debut = System.nanoTime();
		Comparaison.cheminRecMemo(A.toCharArray(), B.toCharArray(), comp.D);
		System.out.println(System.nanoTime()-debut + " nanosecondes");
		System.out.println();
		
		// test pour la fonction dynamique
		debut = System.nanoTime();
		System.out.print("La distance selon la fonction dynamique: ");
		System.out.println(Comparaison.Dynamique(A.toCharArray(), B.toCharArray())[A.length()][B.length()]); 
		System.out.println(System.nanoTime()-debut + " nanosecondes");
		System.out.println("La structure garde les donnes: ");
		System.out.println(Comparaison.tableauToString(Comparaison.Dynamique(A.toCharArray(), B.toCharArray()))); 
		System.out.println();
		
		// test pour la fonction des chemins
		int[][] D = new int[A.length()+1][];
		D = Comparaison.Dynamique(A.toCharArray(), B.toCharArray()); 
		debut = System.nanoTime();
		Comparaison.chemin(A.toCharArray(), B.toCharArray(), D);
		System.out.println(System.nanoTime()-debut + " nanosecondes");
	}
}
