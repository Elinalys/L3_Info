public class Main {

	/*
	On considère ici que les elements sont des entiers.
	*/

	public static void main(String[] args) {

		double debut;

		/* Comparaison entre tableau partition et tableau père */
		/*System.out.println("Tableau partition");
		debut = System.nanoTime();
		TableauPartition test = new TableauPartition(5);
		System.out.println(System.nanoTime() - debut + " nanosecondes");
		System.out.println(test);

		System.out.println("Une fusion (classe 1 et 2)");
		debut = System.nanoTime();
		test.fusion(1,2);
		System.out.println(System.nanoTime() - debut + " nanosecondes");

		debut = System.nanoTime();
		System.out.println("Classe de 2 : " + test.getClasse(2));
		System.out.println(System.nanoTime() - debut + " nanosecondes");
		System.out.println(test);


		System.out.println("\nTableau père partition");
		debut = System.nanoTime();
		TableauPerePartition test2 = new TableauPerePartition(5);
		System.out.println(System.nanoTime() - debut + " nanosecondes");
		System.out.println(test2);

		System.out.println("Une fusion (classe 1 et 2)");
		debut = System.nanoTime();
		test2.fusion(1,2);
		System.out.println(System.nanoTime() - debut + " nanosecondes");
		System.out.println(test2);

		debut = System.nanoTime();
		System.out.println("Classe de 2 : " + test2.getClasse(2));
		System.out.println(System.nanoTime() - debut + " nanosecondes");*/

		Graphe grapheTest = new Graphe(new Integer[] {0, 1, 2, 3}, new Integer[][] { {1, 3}, {0, 2} });
		TableauPartition tableauPartTest = new TableauPartition(grapheTest);
		System.out.println(tableauPartTest);
		
		/*Graphe grapheValue = new Graphe(new Integer[] { 0, 1, 2, 3, 4 },
			new Integer[][] { {0, 2, 40}, {0, 1, 3}, {1, 3, 80}, {3, 0, 2}, {2, 3, 1}, {4, 3, 6} });

		TableauPartition test3 = new TableauPartition(grapheValue.n());
		System.out.println("\nTableau partition graphe via graphe : " );
		System.out.println(test3);
		Arbre arbre = test3.GrapheToArbre(grapheValue);
		System.out.println("Arbre : \n" + arbre);*/


	}
}
