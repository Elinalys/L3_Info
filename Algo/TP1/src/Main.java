public class Main {

	/*
	On considère ici que les elements sont des entiers.
	*/

	public static void main(String[] args) {

		
		System.out.println("Tableau père partition");
		TableauPerePartition test = new TableauPerePartition(5);
		System.out.println(test);
		System.out.println(test.getClasse(2));
		System.out.println(test.getClasse(0));
		System.out.println("Une fusion");
		test.fusion(1,2);
		System.out.println(test); //  Fusion OK
		System.out.println(test.getClasse(0));
		System.out.println(test.getClasse(2));
		
		test.fusion(1,3);
		System.out.println(test); //  Fusion OK
		

		Graphe grapheValue = new Graphe(new Integer[] { 0, 1, 2, 3, 4 },
			new Integer[][] { {0, 2, 40}, {0, 1, 3}, {1, 3, 80}, {3, 0, 2}, {2, 3, 1} });

		// System.out.println;

		TableauPartition test2 = new TableauPartition(grapheValue.n());
		System.out.println("Tableau partition graphe : " );
		System.out.println(test2);
		Arbre arbre = test2.GrapheToArbre(grapheValue);
		System.out.println("Arbre : \n" + arbre);
	}
}
