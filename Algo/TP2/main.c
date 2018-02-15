/*
	TP2 Algo Programmation dynamique
	Distance d'édition
*/

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#define MIN(x, y) (((x) < (y)) ? (x) : (y))

int distance(char *A, char *B) {

	int n = strlen(A)+1;
	int m = strlen(B)+1;

	printf("n = %d\n",n);
	printf("m = %d\n",m);

	// int D[n][m];
	int **D = (int **)malloc(n*sizeof(int*));
	for (int i = 0; i<n; i++) {
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
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			printf("%d\t", D[i][j]);
		}
		printf("\n\n");
	}

	for (int i = 0; i < n; i++) free(D[i]);
	free(D);

	return D[n-1][m-1];
}

int main(int argc, char const *argv[]) {
	char *A = "tourte";
	char *B = "tartre";

	// distance(A, B);
	printf("distance = %d\n",distance(A, B));
	// printf("%d\n",MIN(3,7));

	return 0;
}
 