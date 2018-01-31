#ifndef LIST_H
#define LIST_H

typedef struct list *list;


extern list creer_list();
extern void *objet(list l, int i);
extern void inserer_objet(list l, void *p, int i);
extern list liberer_objet(list l, int i);

#endif /* LIST_H */
