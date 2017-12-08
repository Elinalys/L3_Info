// Client UDP

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
 
#define LOCALHOST "127.0.0.1"
#define TAILLEMSG 500 // Taille max buffer

 
void diep(char *s) {
    perror(s);
    exit(1);
}
 
int main(int argc, char * argv[]) {
    if (argv == NULL) {
        diep("no network port");
    }
	int port = atoi(argv[1]);
	int envoi = 1;

    struct sockaddr_in si_other;
    int s, i, slen = sizeof(si_other);
    char buf[TAILLEMSG];
    char message[TAILLEMSG];
 
    if ((s = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP)) == -1) {
        diep("socket");
    }
 
    memset((char *) &si_other, 0, sizeof(si_other));
    si_other.sin_family = AF_INET;
    si_other.sin_port = htons(port);
     
    if (inet_aton(LOCALHOST , &si_other.sin_addr) == 0) {
        fprintf(stderr, "inet_aton() failed\n");
        exit(1);
    }
 
    while(envoi == 1) {
        printf("Entrer message : ");
        fgets(message, TAILLEMSG, stdin);
        //scanf("%s", message);
         
        // Envoi
        if (sendto(s, message, strlen(message) , 0, (struct sockaddr *) &si_other, slen)==-1) {
            diep("sendto()");
        }
         
        // Reception
        memset(buf,'\0', TAILLEMSG);
        
        // Essai
        if (recvfrom(s, buf, TAILLEMSG, 0, (struct sockaddr *) &si_other, &slen) == -1) {
            diep("recvfrom()");
        }

        // Quitter 
		if(strcmp(buf,"quit") == 10) {
			envoi = 0;
		}         

        fputs(buf, stdin);
		memset(buf,'\0', TAILLEMSG);
    }
 
    close(s);
    return 0;
}


