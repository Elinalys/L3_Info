# Études de cas

Ici, mini-problèmes -> algorithme

<!-- refaire hierarchie titres -->

## 3 cas

1. Pb orienté optimisation et satisfaction des contraintes
2. Pb orienté "simulation"
3. Pb orienté "Apprentissag et réseaux de neurones"

## Problème orienté optimisation

### Pb n reines

	Input : Tableau n×n TAB a valeurs dans nombres entiers ̛̛supérieurs ou égaux à 0
		TAB[i,j] ...

	Output : Un placement de n "reines" sur l'échiquier (marquage de n cases)

		tant que on a pas 2 cases marquées sur une même ligne, colonne, diagonales
		l'argent correspondant aux cases marquées soit maximal

#### Étape 1

Établier une spécification formelle via un modèle linéaire (PLNE).
**modèle linéaire** : calculer un vecteur Z a valeurs dans des nombres (flottants ou entiers).
	tq (1) pour i dans J > 1..N, Z, doit petre entier
		 (2) pour i dans 1..K, Σ Ak,i, , Zi ?? Bk
		 (3) Maximiser Σ Ci Zi

		Input : 
			N
			J <= (1..N)
			Ak, k = 1..K
			Bk, k = 1..K
			Ci, i = 1..N

**Ce qu'il faut savoir** :
	* Les modèles linéaires → Beaucoup d'effort des editeurs de logiciels
		- Bibliothèque dédiées IBM (OSL, CPLEX), XPRESS, GUROBI
		- Bibliothèques de calcul généraliste MATLAB, SAS, ...
		- Logiciels de gestion d'entreprises : SAP
		- Langages : CHIP, Prolog, CP-optimizer
	* Ces bibliothèques peuvent traiter des modèles avec => 10^5 variables ou contraintes
	* Rôle renctral en Algo car Beaucoup de Problèmes pratiques peuent être exprimés via ce formalisme

##### Le cas des n reines

Vecteurs inconnu 

Z indexe sur les cases de l'echiquier

Z=(Zi,j , i = 1..N, j = 1..N) Entier scrictement entre 0 et 1 (exclues)...

Sémantique : Zi,j = 1 ~ on lace une reine en case (i, j)
										0 ~ on ne places pas

**Quantité a maximum (critere qualité)**

	ΣTAB[i,j] * Zi,j
	i = 1..N
	j = 1..N

**Contraintes**

1. je veux exactement n reines
	Σi,j Zi,j = n

2. Pas + de 1 reine par ligne
	Pour toute ligne j = 1..N
		Σ Zi,j =< 1

3. Pas + de 1 reine par colonne
	Pour toute colonne i = 1..N
		Σ Zi,j =< 1

4. Pas + de 1 reine pour une diagonale montante, 2n-1, diagonales montante

Pour tout k = -(n-1)..(n-1)

Σ Zi,i+k =< 1
i tq 1 <= i <= n
i tq 1 <= i+k <= n

2 types d'algos :
	- *exact* : Realise exactement la spécification
	- *approché* : Induisent une marge d'erreur 

Prendre en compte temps d'exec et critère de qualité de l'approximation dans le cas d'un algo approché (également la robustesse). 

###### Approché

**Remarque** : Il faut préciser "appoche" :
	- J'impose vraiment *n-reines* (sinon échec)
	- J'accepte moins de n-reine → maximiser l'argent donc pénalités (n - nbres de reines placées)

```
not stop;
tant que not stop faire
	choisir une case libre;
	si echec(choisir)
		alors stop;
	sinon
		placer une reine sur la case choisie;
		déduire le plus de choses possibles;
		mettre à jour stop;
```
→ algo glouton avec propagation de contraintes

Déduire des reines imposées, interdites

**Exercice** : Écrire algorithme pour réalistion ces déductions imposé/interdit
→ SDD, Algo (on ne prend pas en compte le gains ici)


- Un tableau deux dimensions i,j où :
	- 0 case interdite
	- -1 case libre
	- 1 case occupée
- Un tableau compteur ligne, en ligne j nbe de case libre
- statut ligne, statutligne[1] = 1, il y a une reine en ligne j
- Idem colonnes :
	- compteur colonnes
	- statut colonnes
- Liste de reines imposées (fait déclencheurs en propagation de contraintes) :
	- couples (i,j) correspond à des cases où une reine est imposée
	- ehec, booleen diagnostiquant les impossibilités

Algo : 

```
Echec ← 0;
LISTE ← case qi'on vient de choisir dans la boucle principale de l'algo;
Tant que (Not Échec) && (Liste != Nil) Faire
|	(i,j) ← Tête(LISTE);
|	LISTE ← Queue(LISTE);
|	Pour toute case (i',j') telle que (i = i') || (j = j') || [(i+j) = (i'+j')] || [(i-j)=(i'-j')] Faire
|	Si occupé[i',j'] = 1 alors Échec
|	Sinon 
|	|	Si (occupé[i',j'] = -1) alors 
|	|	|	occupé[i',j'] = O;
|	|	|	compteur_lignes[i'] ← compteur_lignes[i]-1
|	|	|	compteur_colonnes[j'] ← compteur_colonnes[j]-1
|	|	|	Si (compteur_lignes[i] = 0) && (statut_lignes[i'] = 0) alors ÉCHEC;
|	|	|	Sinon
|	|	|	|	Si (compteur_lignes[i'] = 1) && (statut_lignes[0] = 0)
|	|	|	|	|	soit j0 ← unique j tq occupé [i',j] = 0;
|	|	|	|	|	occupé[i',j0] ← 1;
|	|	|	|	|	LISTE ← (i',  j0).LISTE;
|	|	|	|	|	statut_lignes[i'] = 1;
|	|	|	|	|	statut_colonnes[j0] = 1;
```

*Remarque* : le schéma peut échouer parce qu'il ne trouve pas une solution estimée acceptable. Il peut aussi renvoyer une solution de qualité médiocre.

2 pistes d'améliorations :

1. On garde le schéma glouton et on le rend non déterministe (“randomize”), de façon à pouvoir l'executer plusieurs fois de suite.
	Fixe le nb de N “réplications”, pour i = 1..N faire 
		Executer l'algo glouton (non déterministe);
		récuperer le "meilleur" résultat obtenu;
	Question : comment est-ce que je rends mon algo "non déterministe" ?
	Algo courant (deterministe)
	Itération courante : Cases imposées
	interdites, Libres 
	(Random avec proba si prob supérieure premier sinon deuxieme meilleur)

2. À l'issue de l'execution de l'algo glouton, on récupère une solution REINE (ex : vecteur indexe sur les cases et à valeurs en [0,1]) On essaie alors d'ameliorer cette solution en lui appliquant des opérateurs de "Transformation locale" (local search )

(...) 

Schéma GRASP

	Pour i = 1..N (Replcatio) Faire
		Creer une solution REINE via
		la procédure gloutonne "randomizée";
		not stop;
		tant que not stop;
			Appliquer a reine l'opérateur 0 pour une valeur ad hoc de parametre;
			mettre à jour stop;
		Consinier le meilleur objet REINE obtenu;
Pour mettre en oeuvre ce schema, il me faut définir le (ou les )opératur 0 + la façon d'aleer chercher les bonnes valeurs de paramètres

Ici difficultés (liée au fait qu'il y a beaucoup de contrainessur l'obet cherché) → j'ai du mal à définir un opératuer 0 qui s'applique à un placement REINE satisfaisant les contraintes et qui le maintienne dans les contraines

Question améliorer ?

1ère Approche, replication

2° approche, on applique sur l'objet REINE produit par l'algo glouton une boucle dite de transformation locale ("local search") :

	not stop;
	tant que not stop faire
		perturber REINE;

Cette 2° approche repose sur le design "d'operateurs", c'est à dire de prcédures `TRANSFO(Reine, λ)` Reine : adresse, λ : valeur.


**Opérateurs génériques `Build/Destroy`**

* J'enleve p reines parmi les *n* reines placées (p ~ x% · n)
* Je me retrouve avec q = n-p (ou un peu moins) reines placées; J'applique la propagation de contraintes de ces q reines (j'interdis et impose des cases)
* Je relance le procede glouton "randomise" à partir de la situation obtenue 

l'objet RBIND transforme de ? de ces 3 étapes

L'opérateur BUILD.DESTROY ainsi défini, prend comme paramètre.

`BUILD.DESTROY(Reine, λ)`

**Questions sous-jacentes**

1. Comment je choisis p et les reines de la liste λ ?
2. Qu'est ce qu'on met derriere "Tant que not stop faire perturber (Reine)" ?

*Question 1*

	Fixer les idées →
		n = 100
		L'objet REINE est un vecteur a taille 10000
		➡ À chaque etape p = 20 (20%) → on enlève 20 reines
		Pas possibilité d'enumerer toutes les possibilités

1. Possibilité : Enlever les reines qui ont été placées en 1°
2. Possibilité : Enlver les reines faibles (qui portent le moins d'argent)

**NB :** Il est souhaitable de génerer plusieurs paquets de p reines à faire a les tester tous et selectionner le + approprié.

Je peux mixer les 2 critères

Spécification de la boucle "local search"

	not stop; solcour ← reine;
	tant que not stop faire
		not stop 1;
		Tant que not stop 1 faire
			generer un paquet λ de reines à enlever; //  (on peut générer eventuellement tous les paquets des les début)
			Tester l'application de BUILD.DESTROY(REINE, λ); // Utilise une copie de REINE
			Si OK(Tester) alors
				stop1;
				Appliquer BUILD.DESTROY(Reine, λ);
			Sinon mettre à jour stop1;

*Reste à préciser*

`OK(Tester)` ~ Dans quelles conditions j'estime que le paquet λ de reines à retirer justifie l'application de BUILD.DESTROY(Reine, λ)

1. Approche : OK si l'application de l'operateur ameliore REINE (place plus de reine ou fait gagner + d'argent) (*Descente ou Hill-climbing*)

2. Approche : OK toujours vrai → je genere 1 paquet et j'applique (*Marche aléatoire ou Random Walk*)

→ Approche mixte : Recul simulé, Tabou, Genetique