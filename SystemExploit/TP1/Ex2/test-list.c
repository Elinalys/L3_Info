#include <stdio.h>
#include <stdlib.h>


#include "list.h"

int main (int argc, char *argv[]) {
  int *i = malloc(sizeof(int));
  int cpt;
  list l = creer_list(i);
  for (cpt = 0; cpt <= 1000; cpt++) {
    i = malloc(sizeof(int));
    inserer_objet(l,i,1);
  }

  for (cpt = 0; cpt <= 1000; cpt++) {
    if (rand() % 2 == 0)
      l = liberer_objet(l,cpt);
  }
 
  return 0;
}
