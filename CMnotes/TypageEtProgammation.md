# Typages et programmation - Option

## Simuler de l'objet en C (modularité)

Encapsulation des données, constructeur...

Programmation modulaire en C : *diviser le programme en modules qui intérragissent*, elle se fait ici à travers 2 fichiers :
	- Le `.h` : le header, l'information publique du module (en objet on appelle ça une interface)
	- Le `.c` : l'implémentation de l'interface

**Avantage :** Le monde extérieur ne connaît que ce qui est dans le `.h`. On peut donc modifier son implémentation à volonté.

*Par exemple :* Les modules, `string`, `stack`...

### Écrire un module de vecteur extensible

>pseudo code

```c
/* vecteur.h */
#ifndef v_H
#define v_H

typedef struct vecteur *vecteur;
void liberer(vecteur v);
vecteur creerVecteur();
void *get(vecteur v, int i);
void set(vecteur, int i, void *e);
int length (vecteur v);
#endif

```

```c
/* vecteur .c */

#include "vecteur.h"

struct vecteur {
	void **tab;
	void (*liberer)(void *);
	int taille;
};

vecteur creerVecteur(/*?*/ void (*lib)(void *)) {
	vecteur v = malloc(sizeof(vecteur));
	v->tab = malloc(5);
	v->taille = 5;
	v->liberer = lib;
	return v;
}

void *get(vecteur v, int i) {
	if (i > v->taille-1) {
		printf("erreur");
	}
	return v->tab[i];
}

// rappel on ne gère pas toutes les erreurs dans ce code
int length(vecteur v) {
	return v->taille;
}

void set(vecteur v, int i, void *e) {
	if (i < v->taille) {
	 v->tab[i] = e;
	}
	else {
			v->tab = realloc(2*v->taille);
			v->tab[i] = e;
	}
}

void liberer(vecteur v) {
	void (int i = 0; i < v->taille; i++) {
		v->liberer(get(v,i));
	}
	free(v->tab);
	free(v);
}

```