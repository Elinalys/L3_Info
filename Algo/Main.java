public class Main {

	/*
	On considère ici que les elements sont des entiers.
	*/

	public static void main(String[] args) {

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
	}
}
