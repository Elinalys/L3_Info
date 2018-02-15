/*
	TP2 Algo Programmation dynamique
	Distance d'édition
*/

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>

#define MIN(x, y) (((x) < (y)) ? (x) : (y))
#define RESET   "\033[0m"
#define BOLD  "\033[1m"

void affichage(int **D, int n, int m) {
	printf(BOLD "n = " RESET "%d\n",n);
	printf(BOLD "m = " RESET "%d\n",m);
	printf("\n");

	printf(BOLD "Tableau des substitutions :\n\n" RESET);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			printf("%d\t", D[i][j]);
		}
		printf("\n\n");
	}
}

// retourne la souchaine de 0 à len
char *substr(char *chaineSource, int len) { 
  char *dest = NULL;                        
  if (len > 0) {                          
    dest = (char *)calloc(len+1, 1);      
    if (NULL != dest) {
      strncat(dest,chaineSource,len);            
    }
  }                                       
  return dest;                            
}

// ça marche pas lalalala
int distanceR(char *A, char *B) {
	int n = strlen(A);
	int m = strlen(B);

	if (n == 0) return n;
	if (m == 0) return m;
	else {
		if (A[n] == B[m]) return distanceR(substr(A, n-1), substr(B, m-1));
		else {
			return 1 + MIN(distanceR(substr(A, n-1), substr(B, m-1)), MIN(distanceR(A, substr(B, m-1)), distanceR(substr(A, n-1), B)));
		}
	}
}

int distance(char *A, char *B) {

	int n = strlen(A)+1;
	int m = strlen(B)+1;

	// int D[n][m];
	int **D = (int **)malloc(n*sizeof(int*));
	for (int i = 0; i < n; i++) {
		D[i] = (int *)malloc(m*sizeof(int));
	}

	for (int i = 0; i < n; i++) D[i][0] = i; // Initialisation
	for (int j = 0; j < m; j++) D[0][j] = j;	

	for (int i = 1; i < n; i++) {
		for (int j = 1; j < m; j++) {
			if (A[i-1] == B[j-1]) {
				D[i][j] = MIN(D[i-1][j-1], MIN(D[i-1][j]+1, D[i][j-1]+1)); 
			} else { 
				D[i][j] = MIN(D[i-1][j-1], MIN(D[i-1][j], D[i][j-1]))+1; 
			}
		}
	}

	affichage(D, n, m);

	for (int i = 0; i < n; i++) free(D[i]);
	free(D);

	return D[n-1][m-1];
}

int main(int argc, char const *argv[]) {
	char *A = "tourte";
	char *B = "tartre";
	printf("%ld\n", strlen(A));
	char *C = substr(A, 5);
	printf("%s\n", C);

	// distance(A, B);
	// printf(BOLD "distance %s et %s" RESET " = %d\n",A, B, distanceR(A, B));
	// printf("%d\n",MIN(3,7));

	return 0;
}
 