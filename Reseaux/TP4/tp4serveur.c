// serveur 

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h> /* close */
#include <netdb.h> /* gethostbyname */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define TAILLEMSG 500

// quitter avec msg erreur
void diep(char *s) {
	perror(s);
	exit(1);
}

int main(int argc, char **argv) {
	if (argv[1] == NULL) {
      printf("Erreur parametre\n");
      exit(1);
  }

	// port passé en paramètre
	int port = atoi(argv[1]);
	int ecouter = 1;

	struct sockaddr_in si_me, si_other;
	int s, i, slen = sizeof(si_other), recv_len;
	char buf[TAILLEMSG];

	if ((s=socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP)) == -1) {
		diep("socket");
	}

	memset((char *) &si_me, 0, sizeof(si_me));

  si_me.sin_family = AF_INET;
  si_me.sin_port = htons(port);
  si_me.sin_addr.s_addr = htonl(INADDR_ANY);

	if (bind(s, (struct sockaddr*)&si_me, sizeof(si_me))) {
		diep("bind");
	}

	while (ecouter == 1) {
		printf("écoute...\n\n");

		// recevoir
		if (recvfrom(s, buf, TAILLEMSG, 0, (struct sockaddr *)&si_other,&slen) == -1) {
			diep("recvfrom()");
		}

		// afficher
    printf("%s:%d envoie : %s \n", inet_ntoa(si_other.sin_addr), ntohs(si_other.sin_port), buf);
     
    // répondre
    char message[recv_len];
    sprintf(message,"%s",buf);
    if (sendto(s, message, recv_len, 0, (struct sockaddr*) &si_other, slen) == -1) {
      diep("sendto()");
    }

    // quitter
    if (strcmp(buf,"quit") == 10)	{
			ecouter = 0;
		}  

		memset(buf,'\0', TAILLEMSG);

	}
	close(s);
	return 0;
}
