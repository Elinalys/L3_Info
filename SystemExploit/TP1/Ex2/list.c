#include <stdio.h>
#include <stdlib.h>


#include "list.h"

struct list
{
  void *objet;
  struct list *suivant;
};

list creer_list(void *p) {
  list l = malloc(sizeof(struct list));
  l->objet = p;
  l->suivant = NULL;
  return l;
}


void *objet(list l, int i) {
  int j = 1;
  while (j < i) {
    l = l->suivant;
    j++;
    if (l == NULL) {
      fprintf (stderr, "objet : %d eme n'existe pas\n",i);
      return NULL;
    }
  }
  return l->objet;
}

void inserer_objet(list l, void *p, int i) {
  int j = 1;
  list nouveau = malloc(sizeof(struct list));
  list suivant;
  while (j < i) {
    l = l->suivant;
    j++;
    if (l == NULL) {
      fprintf (stderr, "inserer: %d eme n'existe pas\n",i);
      return;
    }
  }
  suivant = l->suivant;
  l->suivant = nouveau;
  nouveau->objet = p;
  nouveau->suivant = suivant;
}

list liberer_objet(list l, int i) {
  int j = 1;
  list pred = NULL;
  list deb = l;
  if (i==1) {
    deb = l->suivant;
    free(l);
    return deb;
  }
  
  while (j < i) {
    if (deb == NULL) {
      fprintf (stderr, "liberer : %d eme n'existe pas\n",i);
      return NULL;
    }
    pred = deb;
    deb = deb->suivant;
    j++;
  }

  if (deb != NULL) {
    pred->suivant = deb->suivant;
    free(deb);
  }
  return l;
  
}
  
