public class Comparaison {
	public int[][] D;
	public Comparaison()	{	}
	
	public int Recursif(String Aa, String Bb) {
		// conditions d'arret
		if (Aa.length() == 0) {
			return Bb.length();
		}
		else if (Bb.length() == 0) {
			return Aa.length();
		}	
		// si les 2 caracteres d'i et de j sont pareils, sauter aux 2 précedents 
		else if (Aa.substring(Aa.length()-1, Aa.length()).equals(Bb.substring(Bb.length() -1, Bb.length()))) {	
			return Recursif(Aa.substring(0, Aa.length()-1), Bb.substring(0, Bb.length()-1));
		}
		// Sinon distance = distance + 1, et tester le précédent le plus proche
		else if (Aa.substring(Aa.length()-1) != Bb.substring(Bb.length() -1)) {
			return (1 + (Minimum (Recursif(Aa.substring(0, Aa.length()-1),Bb.substring(0, Bb.length()-1)), 
				Recursif(Aa.substring(0, Aa.length()-1),Bb.substring(0, Bb.length())),
				Recursif(Aa.substring(0, Aa.length()),Bb.substring(0, Bb.length()-1)))));
		} 
		System.out.println("Error");
		return 0;
	}
	
	
	public int RecursifMemo(String Aa, String Bb, int level) {
		if (level == 0) {
			D = new int[Aa.length()][];
			for (int i = 0; i < Aa.length(); i++) {
				D[i] = new int[Bb.length()];
				for (int y = 0; y < Bb.length(); y++) {
					D[i][y] = 999;
				}
			}
		}		
		if (Aa.length() == 0)	{
			if (D[0][Bb.length()] == 999) {
				D[0][Bb.length()] = Bb.length();
			}
			return D[0][Bb.length()];
		}
		else if (Bb.length() == 0) {
			if (D[Aa.length()][0] == 999) {
				D[Aa.length()][0] = Aa.length();
			}
			return D[Aa.length()][0];
		}
		else if (Aa.substring(Aa.length()-1, Aa.length()).equals(Bb.substring(Bb.length() -1, Bb.length()))) {	
			if (D[Aa.length()-1][Bb.length()-1] == 999) {
				D[Aa.length()-1][Bb.length()-1] = RecursifMemo(Aa.substring(0, Aa.length()-1), Bb.substring(0, Bb.length()-1),level+1);
			}
			return D[Aa.length()-1][Bb.length()-1];
		}
		else if (Aa.substring(Aa.length()-1) != Bb.substring(Bb.length() -1)) {
			if (D[Aa.length()-1][Bb.length()-1] == 999) {
				D[Aa.length()-1][Bb.length()-1] = (1 + (Minimum (RecursifMemo(Aa.substring(0, Aa.length()-1),Bb.substring(0, Bb.length()-1), level+1), 
					RecursifMemo(Aa.substring(0, Aa.length()-1),Bb.substring(0, Bb.length()), level+1),
					RecursifMemo(Aa.substring(0, Aa.length()),Bb.substring(0, Bb.length()-1), level+1))));
			}
			return D[Aa.length()-1][Bb.length()-1];
		}

		System.out.println("Error");
		return 0;
	}
	

	public static int[][] Dynamique(char[] A, char[] B) {
		int[][] res = new int[A.length+1][];

		/* Initialisation */
		for (int i = 0; i < A.length+1; i++) {
			res[i] = new int[B.length+1];
			res[i][0] = i;
		}
		
		for (int j = 0; j < B.length+1; j++) {
			res[0][j] = j;
		}

		/* Calcul */
		for (int i = 1; i < A.length+1; i++) {
			for (int j = 1; j < B.length+1; j++) {
				if (A[i-1] == B[j-1]) {
					res[i][j] = Minimum(res[i][j-1]+1, res[i-1][j] + 1, res[i-1][j-1]);
				}
				else {
					res[i][j] = Minimum(res[i][j-1]+1, res[i-1][j] + 1, res[i-1][j-1]+1);
				}
			}
		}
		
		return res;
	}
	
	public static String tableauToString(int[][] array) {
		String info = "";
		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				info += array[i][j] + "\t";
			}
			info+="\n";
		}
		
		return info;
	}
	

	public static void chemin(char[] A, char[] B, int[][] D) {
		int i = D.length-1;
		int j = D[0].length-1;
		int c = 0;
		
		// le tableau operation est pour enregistrer les operations (= modifications à faire sur les mots)
		String[] operation = new String[D[i][j]];
		/* 
		On commence par la derniere case dans la matrice, et on cherche le plus petit parmi les 3 distances en haut a gauche, a gauche et en haut 
		de cette case. 
		Si le plus petit = la distance dans cette case, la distance ne change pas, on saute a la case en haut a gauche. 
		Si le plus petit = la case a gauche, c'est-a-dire qu'il existe une operation d'insertion dans cette case. Et on saute a la case a gauche.
		Si le plus petit = la case en haut, c'est-a-dire qu'il existe une operation de suppresion dans cette case. Et on saute a la case en haut.
		Si le plus petit = la case en haut a gauche, c'est-a-dire qu'il existe une operation de substitution dans cette case. Et on saute a la 
		case en haut a gauche.
		*/
		
		// cas d'arret : quand la distance est 0
		while (D[i][j] != 0) {
			if (Minimum(D[i][j-1], D[i-1][j], D[i-1][j-1]) == D[i][j]) {
				i--;
				j--;
			}
			else if (D[i][j-1] == D[i][j]-1) {
				operation[c] = ("insertion: ajouter '" + B[j-1] + "' a la position " + i);
				c++;
				j--;
			}
			else if (D[i-1][j] == D[i][j]-1) {
				operation[c] = ("suppression: enlever '" + A[i-1] + "' a la position " + i);
				c++;
				i--;
			}
			else if (D[i-1][j-1] == D[i][j]-1) {
				operation[c] = ("substitution: remplacer '" + A[i-1] + "' par '" + B[j-1] + "' a la position " + i);
				c++;
				i--;
				j--;
			}
		}
		// affichage des chemins. 
		// Parce qu'on commence par la derniere case de la matrice, les chemins sont enregistrés dans une ordre inverse.
		for (int k = c; k>0; k--) {
			System.out.println(operation[k-1]);
		}
	}

	
	// cette fonction utilise la meme algo pour trouver le chemin correspondant de la memoration crée par la méthode récursif mémoration
	// Les différences c'est juste les indice dans le mot A et B
	public static void cheminRecMemo(char[] A, char[] B, int[][] D) {
		int i = D.length-1;
		int j = D[0].length-1;
		int c = 0;
		
		String[] operation = new String[D[i][j]];
		
		// cas d'arret : quand la distance est 0
		while (D[i][j] != 0) {
			if (Minimum(D[i][j-1], D[i-1][j], D[i-1][j-1]) == D[i][j]) {
				i--;
				j--;
			}
			else if (D[i][j-1] == D[i][j]-1) {
				operation[c] = ("insertion: ajouter '" + B[j] + "' a la position " + (i + 1));
				c++;
				j--;
			}
			else if (D[i-1][j] == D[i][j]-1) {
				operation[c] = ("suppression: enlever '" + A[i] + "' a la position " + (i + 1));
				c++;
				i--;
			}
			else if (D[i-1][j-1] == D[i][j]-1) {
				operation[c] = ("substitution: remplacer '" + A[i] + "' par '" + B[j] + "' a la position " + (i + 1));
				c++;
				i--;
				j--;
			}
		}
		// affichage des chemins. 
		for (int k = c; k>0; k--) {
			System.out.println(operation[k-1]);
		}
	}

	public static int Minimum(int a, int b, int c) {
		int min = 0;
		if (a <= b && a <= c) {
			min = a;
		}
		else if (b <= a && b <= c) {
			min = b;
		}
		else {
			min = c;
		}
		
		return min;
	}
}
