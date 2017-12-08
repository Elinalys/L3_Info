#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define BUFFER 250

char ** splitURL(char * url) {
	char server[50];
	char uri[BUFFER];
	char split[1][BUFFER];
	int i;
	int j = 0;

	if (url[0] == '\0') {
		printf("url vide\n");
		exit(0);
	}
	else {
		// http://
		if (url[0] == 'h' && url[1] == 't' && url[2] == 't' && url[3] == 'p' && url[4] == ':' && url[5] == '/' && url[6] == '/') {
				i = 7;
				while (url[i] != '/') {
					server[i-7] = url[i];
					i++;
				}
				while (url[j] != '\0') {
					uri[j] = url[i];
					j++;
					i++;
				}
		}
		// "http://" non precisé
		else {
			i = 0;
			while (url[i] != '/') {
					server[i] = url[i];
					i++;
				}
				while (url[j] != '\0') {
					uri[j] = url[i];
					j++;
					i++;
				}

		}
	}

	strcpy(split[0],server);
	strcpy(split[1],uri);
	
	// pour le test
	printf("serveur : %s\n", split[0]);
	printf("URI : %s\n", split[1]);

	// reset
	memset(split[0],0,strlen(split[0]));
	memset(split[1],0,strlen(split[1]));
}

FILE downloadURI(char * server, char * uri, FILE * file) {
	/**/
}

int main(int argc, char const *argv[]) {
	// tests
	splitURL("http://www.google.com/index.html");
	printf("\n");
	splitURL("test/");
	printf("\n");
	splitURL("");
	return 0;
}